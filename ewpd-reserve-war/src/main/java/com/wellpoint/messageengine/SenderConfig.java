package com.wellpoint.messageengine;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.wellpoint.contractcodepool.exception.InvalidInputParameterException;
import com.wellpoint.ewpd.rest.service.PropertyGetter;
import com.wellpoint.ewpd.rest.util.ServiceUtil;
import com.wellpoint.messageengine.KafkaProperties;




@Configuration
public class SenderConfig {
	private static final Logger LOGGER = LogManager.getLogger(SenderConfig.class.getName());
	@Autowired
	private PropertyGetter propertyGetter; 
	@Autowired
	KafkaProperties KafkaProperties;
	
	@Bean
	public Map<String, Object> producerConfigs() throws InvalidInputParameterException {
		LOGGER.info("Kafka Producer initialted");
		LOGGER.info("testing "+ propertyGetter.getValue("kafkaSecurityProtocol"));
		Map<String, Object> props = new HashMap<>();
		System.setProperty("java.security.auth.login.config", propertyGetter.getValue("kafkaClientJaasFileName"));
		System.setProperty("java.security.krb5.conf", propertyGetter.getValue("KakfaKrbConfig")); 
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, propertyGetter.getValue("kafkaBootstrapServers"));
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ServiceUtil.SECURITYPROTOCOL, propertyGetter.getValue("kafkaSecurityProtocol"));
		props.put(ServiceUtil.SASLSERVICENAME, propertyGetter.getValue("kafkaSaslKerberosServiceName"));
		props.put(ServiceUtil.SSLTRUSTSTORELOCATION, propertyGetter.getValue("kafkaSslTruststoreLocation"));
		props.put(ServiceUtil.SSLTRUSTSTOREPWRD, propertyGetter.getValue("kafkaSslTruststorePassword"));
		props.put("sasl.jaas.config",propertyGetter.getValue("sasl.jaas.config"));
		props.put(ProducerConfig.RETRIES_CONFIG, 0);
		
		return props;
	}

	@Bean
    public ProducerFactory<String, String> producerFactory() throws InvalidInputParameterException {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() throws InvalidInputParameterException {
        return new KafkaTemplate<>(producerFactory());
    }

}

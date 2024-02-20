package com.wellpoint.messageengine;



import org.apache.kafka.common.errors.TimeoutException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.wellpoint.contractcodepool.exception.InvalidInputParameterException;
import com.wellpoint.ewpd.rest.service.PropertyGetter;
import com.wellpoint.messageengine.KafkaProperties;

@Service
public class KafkaProducer {
	private static final Logger LOGGER = LogManager.getLogger(KafkaProducer.class.getName());
	@Autowired
	private PropertyGetter propertyGetter; 
	@Autowired
	KafkaProperties kafkaProperties;
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	@Autowired
	private Environment evt;

	public void sendTransformPayload(String WpdJson) throws InvalidInputParameterException {
	
		LOGGER.info("sending validation errors='{}' to topic='{}'");
		try{
			boolean publishtoTopic = true;
			try{
				if(propertyGetter.getValue("publishtoTopic") != null && propertyGetter.getValue("publishtoTopic").equalsIgnoreCase("false")){
					publishtoTopic = false;
				}
			}
			catch(InvalidInputParameterException ex){
				LOGGER.error("Invalid input parameter exception"+ex);
			}
          	LOGGER.info("publishing kafka message");
			if(publishtoTopic){
              	LOGGER.info("publishing kafka message to topic");
				kafkaTemplate.send(propertyGetter.getValue("kafkaDgaTopicOut"), WpdJson);
			}
		}catch(TimeoutException te){
			LOGGER.error("Internal server issue exception"+te);
		}catch(Exception ex){
			LOGGER.error("Internal server issue exception"+ex);
		}
		LOGGER.info("validation errors has been sent successfully to DocGenAdaptor Kafka");	
		
	}

}


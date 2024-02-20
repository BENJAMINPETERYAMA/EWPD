package com.wellpoint.messageengine;

//import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.wellpoint.ewpd.rest.service.PropertyGetter;

@Configuration
//@PropertySource("classpath:EPWDKafkaConfig.properties")

public class KafkaProperties {
	
	//@Value("${kafkaDgaTopicIn}")
	private String kafkaDgaTopicIn;
	//@Value("${kafkaDgaTopicOut}")
	private String kafkaDgaTopicOut;
	//@Value("${kafkaBootstrapServers}")
	private String kafkaBootstrapServers;
	//@Value("${kafkaGroupId}")
	private String kafkaGroupId;
	//@Value("${kafkaAutoOffsetReset}")
	private String kafkaAutoOffsetReset;
	//@Value("${kafkaEnableAutoCommit}")
	private String kafkaEnableAutoCommit;
	//@Value("${kafkaSecurityProtocol}")
	private String kafkaSecurityProtocol;
	//@Value("${kafkaSaslKerberosServiceName}")
	private String kafkaSaslKerberosServiceName;
	//@Value("${kafkaSslTruststoreLocation}")
	private String kafkaSslTruststoreLocation;
	//@Value("${kafkaSslTruststorePassword}")
	private String kafkaSslTruststorePassword;
	//@Value("${kafkaClientJaasFileName}")
	private String kafkaClientJaasFileName;
	//@Value("${KakfaKrbConfig}")
	private String KakfaKrbConfig;
	
	

	
	public String getKafkaDgaTopicIn() {
		return kafkaDgaTopicIn;
	}

	public void setKafkaDgaTopicIn(String kafkaDgaTopicIn) {
		this.kafkaDgaTopicIn = kafkaDgaTopicIn;
	}

	public String getKakfaKrbConfig() {
		return KakfaKrbConfig;
	}

	public void setKakfaKrbConfig(String kakfaKrbConfig) {
		KakfaKrbConfig = kakfaKrbConfig;
	}


	public String getKafkaDgaTopicOut() {
		return kafkaDgaTopicOut;
	}

	public void setKafkaDgaTopicOut(String kafkaDgaTopicOut) {
		this.kafkaDgaTopicOut = kafkaDgaTopicOut;
	}

	public String getKafkaBootstrapServers() {
		return kafkaBootstrapServers;
	}

	public void setKafkaBootstrapServers(String kafkaBootstrapServers) {
		this.kafkaBootstrapServers = kafkaBootstrapServers;
	}

	public String getKafkaGroupId() {
		return kafkaGroupId;
	}

	public void setKafkaGroupId(String kafkaGroupId) {
		this.kafkaGroupId = kafkaGroupId;
	}

	public String getKafkaAutoOffsetReset() {
		return kafkaAutoOffsetReset;
	}

	public void setKafkaAutoOffsetReset(String kafkaAutoOffsetReset) {
		this.kafkaAutoOffsetReset = kafkaAutoOffsetReset;
	}

	public String getKafkaEnableAutoCommit() {
		return kafkaEnableAutoCommit;
	}

	public void setKafkaEnableAutoCommit(String kafkaEnableAutoCommit) {
		this.kafkaEnableAutoCommit = kafkaEnableAutoCommit;
	}

	public String getKafkaSecurityProtocol() {
		return kafkaSecurityProtocol;
	}

	public void setKafkaSecurityProtocol(String kafkaSecurityProtocol) {
		this.kafkaSecurityProtocol = kafkaSecurityProtocol;
	}

	public String getKafkaSaslKerberosServiceName() {
		return kafkaSaslKerberosServiceName;
	}

	public void setKafkaSaslKerberosServiceName(String kafkaSaslKerberosServiceName) {
		this.kafkaSaslKerberosServiceName = kafkaSaslKerberosServiceName;
	}

	public String getKafkaSslTruststoreLocation() {
		return kafkaSslTruststoreLocation;
	}

	public void setKafkaSslTruststoreLocation(String kafkaSslTruststoreLocation) {
		this.kafkaSslTruststoreLocation = kafkaSslTruststoreLocation;
	}

	public String getKafkaSslTruststorePassword() {
		return kafkaSslTruststorePassword;
	}

	public void setKafkaSslTruststorePassword(String kafkaSslTruststorePassword) {
		this.kafkaSslTruststorePassword = kafkaSslTruststorePassword;
	}

	public String getKafkaClientJaasFileName() {
		return kafkaClientJaasFileName;
	}

	public void setKafkaClientJaasFileName(String kafkaClientJaasFileName) {
		this.kafkaClientJaasFileName = kafkaClientJaasFileName;
	}
	

}

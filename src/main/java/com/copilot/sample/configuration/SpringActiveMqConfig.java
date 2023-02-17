package com.copilot.sample.configuration;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Queue;

@Configuration
@EnableJms
public class SpringActiveMqConfig {
	
	
	@Value("${active-mq.broker-url}")
    private String brokerUrl;
	
	
	@Value("${active-mq.broker-url}")
    private String userName;
	
	
	@Value("${active-mq.broker-url}")
    private String password;
	
	@Value("${active-mq.queueName}")
    private String queueName;
	
	@Bean
	public ActiveMQConnectionFactory activeMQConnectionFactory() {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL(brokerUrl);
		activeMQConnectionFactory.setUserName(userName);
		activeMQConnectionFactory.setPassword(password);
		return activeMQConnectionFactory;
	}

	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(activeMQConnectionFactory());
		//jmsTemplate.setMessageConverter(messageConverter());
		return jmsTemplate;
	}

	@Bean
	public Queue activeMQQueue() {
		return new ActiveMQQueue(queueName.trim());
	}

//	@Bean
//	public org.springframework.jms.support.converter.MessageConverter messageConverter() {
//		org.springframework.jms.support.converter.MappingJackson2MessageConverter messageConverter = new org.springframework.jms.support.converter.MappingJackson2MessageConverter();
//		messageConverter.setTypeIdPropertyName("_type");
//		return messageConverter;
//	}
}

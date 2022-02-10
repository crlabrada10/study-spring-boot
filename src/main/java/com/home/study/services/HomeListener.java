package com.home.study.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author crlabrada10
 *
 */
@Component
public class HomeListener {
	
	
	private static final Logger log = LoggerFactory.getLogger(HomeListener.class);

	
	//topics = "home-topic", Nombre del topico creado en kafka
	//groupId = "homeConsumer" nombre que se configuro en la configuracion del consumer en el bean de configuracion
	@KafkaListener(topics = "home-topic", groupId = "homeConsumer")
	public void listen(String message) {
		System.out.println("Received Messasge in group foo: " + message);
		log.info("Received Messasge in group foo: {} ", message);
	}

}

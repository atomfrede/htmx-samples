package org.codeberg.atomfrede.htmx_samples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableWebSocket
@Async
@EnableScheduling
public class HTMXSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(HTMXSampleApplication.class, args);
	}


}

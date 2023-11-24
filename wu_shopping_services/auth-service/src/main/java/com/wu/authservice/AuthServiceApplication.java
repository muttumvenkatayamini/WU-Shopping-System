package com.wu.authservice;

import java.sql.SQLException;


import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthServiceApplication /*extends SpringBootServletInitializer*/ {

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}
//	public static void main(String[] args) {
//		startH2Server();
//		SpringApplication.run(AuthServiceApplication.class, args);
//	}
//	@Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        startH2Server();
//        return application.sources(AuthServiceApplication.class);
//    }
//
//	private static void startH2Server() {
//		try {
//			Server h2Server = Server.createTcpServer().start();
//			if (h2Server.isRunning(true)) {
////				log.info("H2 server was started and is running.");
//			} else {
//				throw new RuntimeException("Could not start H2 server.");
//			}
//		} catch (SQLException e) {
//			throw new RuntimeException("Failed to start H2 server: ", e);
//		}
//	}
	
//	@Bean(initMethod = "start", destroyMethod = "stop")
//	public Server inMemoryH2DatabaseaServer() throws SQLException {
//	    return Server.createTcpServer(
//	      "-tcp", "-tcpAllowOthers", "-tcpPort", "8081");
//	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}

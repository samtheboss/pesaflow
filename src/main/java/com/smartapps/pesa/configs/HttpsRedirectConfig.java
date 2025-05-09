//package com.smartapps.pesa.configs;
//
//import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
//import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.apache.catalina.connector.Connector;
//
//@Configuration
//public class HttpsRedirectConfig {
//
//    @Bean
//    public ServletWebServerFactory servletContainer() {
//        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
//        factory.addAdditionalTomcatConnectors(httpConnector());
//        return factory;
//    }
//
//    private Connector httpConnector() {
//        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
//        connector.setScheme("http");
//        connector.setPort(8080); // HTTP port
//        connector.setSecure(false);
//        connector.setRedirectPort(9890); // HTTPS port
//        return connector;
//    }
//}

package com.credable.scoringmiddleware.config;

import com.credable.scoringmiddleware.util.SOAPConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;


@Configuration
public class SoapClientConfig {

    private final SOAPConnector soapConnector;

    public SoapClientConfig(SOAPConnector soapConnector) {
        this.soapConnector = soapConnector;
    }

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        //marshaller.setPackagesToScan("com.credable.scoringmiddleware.service.soap.api.model");
         marshaller.setContextPath("com.credable.scoringmiddleware.service.soap.api.model"); //
        return marshaller;
    }

    @Bean
    public SOAPConnector soapConnector(Jaxb2Marshaller jaxb2Marshaller) {
        soapConnector.setMarshaller(jaxb2Marshaller);
        soapConnector.setUnmarshaller(jaxb2Marshaller);
        return soapConnector;
    }
}

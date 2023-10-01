package com.credable.scoringmiddleware.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;


@Component
public class SOAPConnector extends WebServiceGatewaySupport {

    private final Logger log = LoggerFactory.getLogger(SOAPConnector.class.getName());

    public <T, R> T callWebService(R request, String requestType, String customUri) {
        log.info("Sending {} request for: {} ",requestType, request);
        WebServiceTemplate webServiceTemplate = getWebServiceTemplate();
        webServiceTemplate.setDefaultUri(customUri);
        return  (T)webServiceTemplate.marshalSendAndReceive(customUri,request,new TokenHeaderRequestCallback());
    }
/*
    private Wss4jSecurityInterceptor securityInterceptor() {
        Wss4jSecurityInterceptor interceptor = new Wss4jSecurityInterceptor();
        interceptor.setSecurementUsername(username);
        interceptor.setSecurementPassword(password);
        return interceptor;
    }
*/

}

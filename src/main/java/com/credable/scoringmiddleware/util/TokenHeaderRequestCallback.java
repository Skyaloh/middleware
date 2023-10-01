package com.credable.scoringmiddleware.util;

import jakarta.persistence.Column;
import jakarta.xml.soap.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.soap.saaj.SaajSoapMessage;

@Component
public class TokenHeaderRequestCallback  implements WebServiceMessageCallback {

    private static final Logger logger = LoggerFactory.getLogger(TokenHeaderRequestCallback.class);


    @Value("${kycapitest.webservice.username}")
    private String username;

    @Value("${kycapitest.webservice.password}")
    private String password;


    public void doWithMessage(WebServiceMessage message) {

        try {

            SaajSoapMessage saajSoapMessage = (SaajSoapMessage)message;

            SOAPMessage soapMessage = saajSoapMessage.getSaajMessage();

            SOAPPart soapPart = soapMessage.getSOAPPart();

            SOAPEnvelope soapEnvelope = soapPart.getEnvelope();

            SOAPHeader soapHeader = soapEnvelope.getHeader();

            Name headerElementName = soapEnvelope.createName(
                    "Security",
                    "wsse",
                    "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd"
            );
            SOAPHeaderElement soapHeaderElement = soapHeader.addHeaderElement(headerElementName);

            SOAPElement usernameTokenSOAPElement = soapHeaderElement.addChildElement("UsernameToken", "wsse");

            SOAPElement userNameSOAPElement = usernameTokenSOAPElement.addChildElement("Username", "wsse");
            logger.info(this.username);
            userNameSOAPElement.addTextNode(this.username);

            SOAPElement passwordSOAPElement = usernameTokenSOAPElement.addChildElement("Password", "wsse");

            passwordSOAPElement.addTextNode(this.password);

            soapMessage.saveChanges();
        } catch (SOAPException soapException) {
            throw new RuntimeException("TokenHeaderRequestCallback", soapException);
        }
    }
}

package com.credable.scoringmiddleware.service;



import com.credable.scoringmiddleware.service.soap.api.model.TransactionData;
import com.credable.scoringmiddleware.service.soap.api.model.TransactionsRequest;
import com.credable.scoringmiddleware.service.soap.api.model.TransactionsResponse;
import com.credable.scoringmiddleware.util.SOAPConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TransactionManagementService {

    private final Logger log = LoggerFactory.getLogger(TransactionManagementService.class.getName());

    private final SOAPConnector soapConnector;

    @Value("${customer.transactions.url}")
    private String customerKYCUrl;

    public TransactionManagementService(SOAPConnector soapConnector) {
        this.soapConnector = soapConnector;
    }

    public List<TransactionData> getAllTransactions(String customerNumber) {
        TransactionsRequest transactionRequest = new TransactionsRequest();
        transactionRequest.setCustomerNumber(customerNumber);
        TransactionsResponse transactionsResponse = soapConnector.callWebService( transactionRequest,"Customer Transactions",customerKYCUrl);
        log.info("Transactions Response : {}",transactionsResponse);
        return transactionsResponse.getTransactions();
    }
}

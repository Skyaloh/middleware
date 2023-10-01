package com.credable.scoringmiddleware.resource;

import com.credable.scoringmiddleware.service.TransactionManagementService;
import com.credable.scoringmiddleware.service.soap.api.model.TransactionData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionResource {
    private final Logger log = LoggerFactory.getLogger(TransactionResource.class);

    private final TransactionManagementService transactionManagementService;

    public TransactionResource(TransactionManagementService transactionManagementService) {
        this.transactionManagementService = transactionManagementService;
    }

    @PostMapping("/middleware/transactions")
    public ResponseEntity<List<TransactionData>> fetchCustomerTransactions(@RequestParam String customerNumber){
        log.debug("REST request to fetch transactions : {}", customerNumber);
        List<TransactionData> allTransactions = transactionManagementService.getAllTransactions(customerNumber);
        return ResponseEntity.ok().body(allTransactions);
    }
}

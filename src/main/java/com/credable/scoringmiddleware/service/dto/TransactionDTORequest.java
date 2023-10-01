package com.credable.scoringmiddleware.service.dto;

import java.io.Serializable;

public class TransactionDTORequest implements Serializable {
    private String customerNumber;

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    @Override
    public String toString() {
        return "TransactionDTORequest{" +
                "customerNumber='" + customerNumber + '\'' +
                '}';
    }
}

/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * [OVERVIEW] Transaction Level Response.
 *
 * @author: (VNEXT)LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/15      (VNEXT)LinhDT      Create new
*/
public class TransactionLevelResponse {

    @JsonProperty("transaction_level_id")
    private Integer transactionLevelId;

    @JsonProperty("bank_id")
    private Integer bankId;

    @JsonProperty("transaction_min")
    private Double transactionMin;

    @JsonProperty("transaction_max")
    private Double transactionMax;

    @JsonProperty("transaction_fee")
    private Double transactionFee;

    @JsonProperty("transaction_level_type")
    private String transactionLevelType;

    public Integer getTransactionLevelId() {
        return transactionLevelId;
    }

    public void setTransactionLevelId(Integer transactionLevelId) {
        this.transactionLevelId = transactionLevelId;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public Double getTransactionMin() {
        return transactionMin;
    }

    public void setTransactionMin(Double transactionMin) {
        this.transactionMin = transactionMin;
    }

    public Double getTransactionMax() {
        return transactionMax;
    }

    public void setTransactionMax(Double transactionMax) {
        this.transactionMax = transactionMax;
    }

    public Double getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(Double transactionFee) {
        this.transactionFee = transactionFee;
    }

    public String getTransactionLevelType() {
        return transactionLevelType;
    }

    public void setTransactionLevelType(String transactionLevelType) {
        this.transactionLevelType = transactionLevelType;
    }

    public TransactionLevelResponse(Integer transactionLevelId, Integer bankId, Double transactionMin, Double transactionMax, Double transactionFee,
            String transactionLevelType) {
        super();
        this.transactionLevelId = transactionLevelId;
        this.bankId = bankId;
        this.transactionMin = transactionMin;
        this.transactionMax = transactionMax;
        this.transactionFee = transactionFee;
        this.transactionLevelType = transactionLevelType;
    }

}

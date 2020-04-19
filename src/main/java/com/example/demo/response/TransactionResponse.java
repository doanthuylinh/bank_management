/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * [OVERVIEW] Transaction Response.
 *
 * @author: (VNEXT)LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/15      (VNEXT)LinhDT      Create new
*/
public class TransactionResponse {

    @JsonProperty("transaction_id")
    private Integer transactionId;

    @JsonProperty("user_id")
    private Integer userId;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("bank_id")
    private Integer bankId;

    @JsonProperty("bank_name")
    private String bankName;

    @JsonProperty("transaction_money")
    private Double transactionMoney;

    @JsonProperty("transaction_date")
    private Date transactionDate;

    @JsonProperty("transaction_type")
    private String transactionType;

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Double getTransactionMoney() {
        return transactionMoney;
    }

    public void setTransactionMoney(Double transactionMoney) {
        this.transactionMoney = transactionMoney;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public TransactionResponse(Integer transactionId, Integer userId, String userName, Integer bankId, String bankName, Double transactionMoney,
            Date transactionDate, String transactionType) {
        super();
        this.transactionId = transactionId;
        this.userId = userId;
        this.userName = userName;
        this.bankId = bankId;
        this.bankName = bankName;
        this.transactionMoney = transactionMoney;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
    }

}

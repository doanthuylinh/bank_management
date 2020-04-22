/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.response;

import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * [OVERVIEW] Transaction Response used for deposit (type 0) and withdrawal (type 1) money.
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

    @Column(name = "account_id")
    private Integer accountId;

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

    @JsonProperty("transaction_fee")
    private Double transactionFee;

    @JsonProperty("transaction_date")
    private Date transactionDate;

    @JsonProperty("transaction_type")
    private String transactionType;

    @JsonProperty("from_user_id")
    private Integer fromUserId;

    @JsonProperty("to_user_id")
    private Integer toUserId;

    @JsonProperty("bank_id_target")
    private Integer bankIdTarget;

    @JsonProperty("from_user_name")
    private String fromUserName;

    @JsonProperty("to_user_name")
    private String toUserName;

    @JsonProperty("bank_target_name")
    private String bankTargetName;

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
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

    public Double getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(Double transactionFee) {
        this.transactionFee = transactionFee;
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

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    public Integer getBankIdTarget() {
        return bankIdTarget;
    }

    public void setBankIdTarget(Integer bankIdTarget) {
        this.bankIdTarget = bankIdTarget;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getBankTargetName() {
        return bankTargetName;
    }

    public void setBankTargetName(String bankTargetName) {
        this.bankTargetName = bankTargetName;
    }

    public TransactionResponse(Integer transactionId, Integer accountId, Integer userId, String userName, Integer bankId, String bankName,
            Double transactionMoney, Date transactionDate, String transactionType) {
        super();
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.userId = userId;
        this.userName = userName;
        this.bankId = bankId;
        this.bankName = bankName;
        this.transactionMoney = transactionMoney;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
    }

    public TransactionResponse(Integer transactionId, Integer accountId, Integer userId, String userName, Integer bankId, String bankName,
            Double transactionMoney, Double transactionFee, Date transactionDate, String transactionType, Integer fromUserId, Integer toUserId,
            Integer bankIdTarget, String fromUserName, String toUserName, String bankTargetName) {
        super();
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.userId = userId;
        this.userName = userName;
        this.bankId = bankId;
        this.bankName = bankName;
        this.transactionMoney = transactionMoney;
        this.transactionFee = transactionFee;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.bankIdTarget = bankIdTarget;
        this.fromUserName = fromUserName;
        this.toUserName = toUserName;
        this.bankTargetName = bankTargetName;
    }

}

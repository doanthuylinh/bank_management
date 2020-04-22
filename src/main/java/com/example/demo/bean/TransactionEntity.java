/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * [OVERVIEW] Transaction Entity.
 *
 * @author: (VNEXT)LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/15      (VNEXT)LinhDT      Create new
*/
@Entity
@Table(name = "transaction")
public class TransactionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Integer transactionId;

    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "bank_id")
    private Integer bankId;

    @Column(name = "from_user_id")
    private Integer fromUserId;

    @Column(name = "to_user_id")
    private Integer toUserId;

    @Column(name = "bank_id_target")
    private Integer bankIdTarget;

    @Column(name = "transaction_money")
    private Double transactionMoney;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @Column(name = "transaction_type")
    private String transactionType;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public Double getTransactionMoney() {
        return transactionMoney;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
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

    public TransactionEntity() {
        super();
        // TODO Auto-generated constructor stub
    }

    public TransactionEntity(Integer transactionId, Integer accountId, Integer userId, Integer bankId, Integer fromUserId, Integer toUserId,
            Integer bankIdTarget, Double transactionMoney, Date transactionDate, String transactionType) {
        super();
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.userId = userId;
        this.bankId = bankId;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.bankIdTarget = bankIdTarget;
        this.transactionMoney = transactionMoney;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
    }

}

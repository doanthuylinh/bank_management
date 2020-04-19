/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * [OVERVIEW] Transaction Level Entity.
 *
 * @author: (VNEXT)LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/15      (VNEXT)LinhDT      Create new
*/
@Entity
@Table(name = "transaction_level")
public class TransactionLevelEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_level_id")
    private Integer transactionLevelId;

    @Column(name = "bank_id")
    private Integer bankId;

    @Column(name = "transaction_min")
    private Double transactionMin;

    @Column(name = "transaction_max")
    private Double transactionMax;

    @Column(name = "transaction_fee")
    private Double transactionFee;

    @Column(name = "transaction_level_type")
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

    public TransactionLevelEntity(Integer transactionLevelId, Integer bankId, Double transactionMin, Double transactionMax, Double transactionFee,
            String transactionLevelType) {
        super();
        this.transactionLevelId = transactionLevelId;
        this.bankId = bankId;
        this.transactionMin = transactionMin;
        this.transactionMax = transactionMax;
        this.transactionFee = transactionFee;
        this.transactionLevelType = transactionLevelType;
    }

    public TransactionLevelEntity() {
        super();
    }

    @Override
    public String toString() {
        return "TransactionLevelEntity [transactionLevelId=" + transactionLevelId + ", bankId=" + bankId + ", transactionMin=" + transactionMin
                + ", transactionMax=" + transactionMax + ", transactionFee=" + transactionFee + ", transactionLevelType=" + transactionLevelType + "]";
    }

}
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

import com.fasterxml.jackson.annotation.JsonFilter;

/**
 * [OVERVIEW] Account Entity.
 *
 * @author: (VNEXT)LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/15      (VNEXT)LinhDT      Create new
*/
@Entity
@Table(name = "account")
public class AccountEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "bank_id")
    private Integer bankId;

    @Column(name = "balance")
    private Double balance;

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

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public AccountEntity(Integer accountId, Integer userId, Integer bankId, Double balance) {
        super();
        this.accountId = accountId;
        this.userId = userId;
        this.bankId = bankId;
        this.balance = balance;
    }

    public AccountEntity() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "AccountEntity [accountId=" + accountId + ", userId=" + userId + ", bankId=" + bankId + ", balance=" + balance + "]";
    }

}

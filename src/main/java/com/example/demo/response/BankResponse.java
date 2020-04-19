/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * [OVERVIEW] Bank Response.
 *
 * @author: (VNEXT)LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/15      (VNEXT)LinhDT      Create new
*/
public class BankResponse {

    @JsonProperty("bank_id")
    private Integer bankId;
    @JsonProperty("bank_name")
    private String bankName;
    @JsonProperty("balance")
    private Double balance;

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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public BankResponse(Integer bankId, String bankName, Double balance) {
        super();
        this.bankId = bankId;
        this.bankName = bankName;
        this.balance = balance;
    }

    public BankResponse(Integer bankId, String bankName) {
        super();
        this.bankId = bankId;
        this.bankName = bankName;
    }

}

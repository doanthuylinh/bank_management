/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * [OVERVIEW] Transfer Response used for transfer money (type 2 for source account and type 3 for target account).
 *
 * @author: (VNEXT)LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/21      (VNEXT)LinhDT       Create new
*/
public class TransferResponse {

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
}

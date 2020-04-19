/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * [OVERVIEW] User Response.
 *
 * @author: (VNEXT)LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/15      (VNEXT)LinhDT      Create new
*/
public class UserResponse {

    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("dob")
    private String dob;
    @JsonProperty("bank")
    private List<BankResponse> bank;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public List<BankResponse> getBank() {
        return bank;
    }

    public void setBank(List<BankResponse> bank) {
        this.bank = bank;
    }

    public UserResponse(Integer userId, String userName, String phone, String dob) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.phone = phone;
        this.dob = dob;
    }

}

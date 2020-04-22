/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.utils;

/**
 * [OVERVIEW] Regex.
 *
 * @author: (VNEXT)LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/16      (VNEXT)LinhDT       Create new
*/
public class Regex {

    /**
     * Regex for User Validation
     */
    public static final String ID_PATTERN = "[0-9]+";
    public static final String NAME_PATTERN = "^[a-zA-Z\\s]{1,10}$";
    public static final String PHONE_PATTERN = "^[0-9]{10,11}$";
    public static final String DATE_PATTERN = "^\\d{4}[\\-\\/\\s]?((((0[13578])|(1[02]))[\\-\\/\\s]?(([0-2][0-9])|(3[01])))|(((0[469])|(11))[\\-\\/\\s]?(([0-2][0-9])|(30)))|(02[\\-\\/\\s]?[0-2][0-9]))$";
    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=])(?=\\S+$).{1,8}$";
    public static final String MONEY_PATTERN = "[0-9]{1,13}(.[0-9]*)?";

}

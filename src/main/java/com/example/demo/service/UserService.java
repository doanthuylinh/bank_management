/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.service;

import java.util.Map;

import com.example.demo.bean.ResultBean;
import com.example.demo.utils.ApiValidateException;

/**
 * [OVERVIEW] User Service.
 *
 * @author: (VNEXT)LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/15      (VNEXT)LinhDT      Create new
*/
public interface UserService {

    /**
     * addUser
     * @author: (VNEXT)LinhDT
     * @param entity
     * @return
     * @throws ApiValidateException
     */
    public ResultBean addUser(String entity) throws ApiValidateException;

    /**
     * @author: (VNEXT)LinhDT
     * @param entity
     * @throws ApiValidateException
     */
    public void updateUser(String entity) throws ApiValidateException;

    /**
     * @author: (VNEXT)LinhDT
     * @return
     */
    public ResultBean getUserById();

    /**
     * @author: (VNEXT)LinhDT
     * @param entity
     * @throws ApiValidateException
     */
    public Map<String, String> login(String entity) throws ApiValidateException;

    /**
     * changePassword
     * @author: (VNEXT)LinhDT
     * @param pass
     * @throws ApiValidateException
     */
    public void changePassword(String pass) throws ApiValidateException;
}

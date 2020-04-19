/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.service;

import com.example.demo.bean.ResultBean;
import com.example.demo.bean.UserEntity;
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
     * @author: (VNEXT)LinhDT
     * @param entity
     * @throws ApiValidateException
     */
    public void addUser(String entity) throws ApiValidateException;

    /**
     * @author: (VNEXT)LinhDT
     * @param entity
     * @throws ApiValidateException
     */
    public void updateUser(UserEntity entity) throws ApiValidateException;

    /**
     * @author: (VNEXT)LinhDT
     * @param id
     * @return
     */
    public ResultBean getUserById(Integer id);

    /**
     * @author: (VNEXT)LinhDT
     * @param entity
     * @throws ApiValidateException
     */
    public void login(String entity) throws ApiValidateException;

}

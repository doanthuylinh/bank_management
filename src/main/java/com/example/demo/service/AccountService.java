/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.service;

import com.example.demo.bean.ResultBean;
import com.example.demo.utils.ApiValidateException;

/**
 * [OVERVIEW] Account Service.
 *
 * @author: (VNEXT)LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/15      (VNEXT)LinhDT      Create new
*/
public interface AccountService {

    /**
     * @author: (VNEXT)LinhDT
     * @param entity
     * @throws ApiValidateException
     */
    public void addAccount(String entity) throws ApiValidateException;

    /**
     * @author: (VNEXT)LinhDT
     * @param id
     * @return
     */
    public ResultBean getAccountById(Integer id);
}

/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.service;

import com.example.demo.bean.ResultBean;
import com.example.demo.utils.ApiValidateException;

/**
 * [OVERVIEW] Transaction Level Service.
 *
 * @author: (VNEXT)LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/15      (VNEXT)LinhDT      Create new
*/
public interface TransactionLevelService {
    /**
     * @author: (VNEXT)LinhDT
     * @param entity
     * @throws ApiValidateException
     */
    public void addTransactionLevel(String entity) throws ApiValidateException;

    /**
     * @author: (VNEXT)LinhDT
     * @param bankId
     * @return
     */
    public ResultBean getListTransactionLevel(Integer bankId);
}

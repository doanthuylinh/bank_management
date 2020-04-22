/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.service;

import com.example.demo.bean.ResultBean;
import com.example.demo.utils.ApiValidateException;

/**
 * [OVERVIEW] Transaction Service.
 *
 * @author: (VNEXT)LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/15      (VNEXT)LinhDT      Create new
*/
public interface TransactionService {

    /**
     * @author: (VNEXT)LinhDT
     * @return
     */
    public ResultBean getListTransaction();

    /**
     * @author: (VNEXT)LinhDT
     * @param userId
     * @param bankId
     * @return
     */
    public ResultBean getListTransaction(Integer bankId);

    /**
     * @author: (VNEXT)LinhDT
     * @param entity
     * @throws ApiValidateException
     */
    public void deposit(String entity) throws ApiValidateException;

    /**
     * @author: (VNEXT)LinhDT
     * @param entity
     * @throws ApiValidateException
     */
    public void withdraw(String entity) throws ApiValidateException;

    /**
     * @author: (VNEXT)LinhDT
     * @param entity
     * @throws ApiValidateException
     */
    public void transfer(String entity) throws ApiValidateException;

    /**
     * outputTransactionsToCSV
     * @author: (VNEXT)LinhDT
     * @param id
     * @return
     */
    public String outputTransactionsToCSV(Integer id) throws ApiValidateException;
}

/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.dao;

import java.util.List;

import com.example.demo.bean.TransactionEntity;
import com.example.demo.response.TransactionResponse;

/**
 * [OVERVIEW] Transaction Dao.
 *
 * @author: (VNEXT)LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/15      (VNEXT)LinhDT      Create new
*/
public interface TransactionDao {

    /**
     * @author: (VNEXT)LinhDT
     * @param entity
     */
    public void addTransaction(TransactionEntity entity);

    /**
     * @author: (VNEXT)LinhDT
     * @param userId
     * @return
     */
    public List<TransactionEntity> getTransactionByUserId(Integer userId);

    /**
     * @author: (VNEXT)LinhDT
     * @param userId
     * @param bankId
     * @return
     */
    public List<TransactionEntity> getTransaction(Integer userId, Integer bankId);

    /**
     * getTransactionResponse
     * @author: (VNEXT)LinhDT
     * @param userId
     * @param bankId
     * @return
     */
    public List<TransactionResponse> getTransactionResponse(Integer userId, Integer bankId);
}

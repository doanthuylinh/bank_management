/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.dao;

import java.util.List;

import com.example.demo.bean.TransactionLevelEntity;
import com.example.demo.response.TransactionLevelResponse;

/**
 * [OVERVIEW] Transaction Level Dao.
 *
 * @author: (VNEXT)LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/15      (VNEXT)LinhDT      Create new
*/
public interface TransactionLevelDao {

    /**
     * @author: (VNEXT)LinhDT
     * @param entity
     */
    public void addTransactionLevel(TransactionLevelEntity entity);

    /**
     * @author: (VNEXT)LinhDT
     * @param bankId
     * @return
     */
    public List<TransactionLevelResponse> getTransactionLevelEntityByBankId(Integer bankId);

    /**
     * @author: (VNEXT)LinhDT
     * @param bankId
     * @return
     */
    public TransactionLevelEntity getTransactionEntity(Integer bankId);
}

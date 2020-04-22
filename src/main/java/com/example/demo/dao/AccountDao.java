/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.dao;

import java.util.List;

import com.example.demo.bean.AccountEntity;
import com.example.demo.response.AccountResponse;

/**
 * [OVERVIEW] Account Dao.
 *
 * @author: (VNEXT)LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/15      (VNEXT)LinhDT      Create new
*/
public interface AccountDao {

    /**
     * @author: (VNEXT)LinhDT
     * @param accountId
     * @return
     */
    public AccountEntity getAccountEntity(Integer accountId);

    /**
     * getAccountEntity
     * @author: (VNEXT)LinhDT
     * @param userId
     * @param bankId
     * @return
     */
    public List<AccountEntity> getAccountEntity(Integer userId, Integer bankId);

    /**
     * @author: (VNEXT)LinhDT
     * @param userId
     * @param accountId
     * @return
     */
    public AccountEntity getAccountEntityByUserAndAcc(Integer userId, Integer accountId);

    /**
     * @author: (VNEXT)LinhDT
     * @param entity
     */
    public void addAccount(AccountEntity entity);

    /**
     * @author: (VNEXT)LinhDT
     * @param id
     * @return
     */
    public AccountResponse getAccountById(Integer id);
}

/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.dao;

import java.util.List;

import com.example.demo.bean.BankEntity;
import com.example.demo.response.BankResponse;

/**
 * [OVERVIEW] Bank Dao.
 *
 * @author: (VNEXT)LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/15      (VNEXT)LinhDT      Create new
*/
public interface BankDao {

    /**
     * @author: (VNEXT)LinhDT
     * @param entity
     */
    public void addBank(BankEntity entity);

    /**
     * @author: (VNEXT)LinhDT
     * @param entity
     */
    public void updateBank(BankEntity entity);

    /**
     * @author: (VNEXT)LinhDT
     * @param id
     * @return
     */
    public BankResponse getBankById(Integer id);

    /**
     * @author: (VNEXT)LinhDT
     * @param userId
     * @return
     */
    public List<BankResponse> getListBankByUserId(Integer userId);

    /**
     * @author: (VNEXT)LinhDT
     * @param name
     * @return
     */
    public BankEntity getBankByName(String name);
}

/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.service;

import com.example.demo.bean.BankEntity;
import com.example.demo.bean.ResultBean;
import com.example.demo.utils.ApiValidateException;

/**
 * [OVERVIEW] Bank Service.
 *
 * @author: (VNEXT)LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/15      (VNEXT)LinhDT      Create new
*/
public interface BankService {
    /**
     * @author: (VNEXT)LinhDT
     * @param entity
     * @throws ApiValidateException
     */
    public void addBank(String entity) throws ApiValidateException;

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
    public ResultBean getBankById(Integer id);
}

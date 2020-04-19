/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.bean.ResultBean;
import com.example.demo.bean.TransactionLevelEntity;
import com.example.demo.dao.BankDao;
import com.example.demo.dao.TransactionLevelDao;
import com.example.demo.response.BankResponse;
import com.example.demo.response.TransactionLevelResponse;
import com.example.demo.service.TransactionLevelService;
import com.example.demo.utils.ApiValidateException;
import com.example.demo.utils.MessageUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * [OVERVIEW] Transaction Level Service Implementation.
 *
 * @author: (VNEXT)LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/15      (VNEXT)LinhDT      Create new
*/
@Service
@Transactional
public class TransactionLevelServiceImpl implements TransactionLevelService {

    @Autowired
    private TransactionLevelDao transactionLevelDao;
    @Autowired
    private BankDao bankDao;

    /**
     * @author: (VNEXT)LinhDT
     * @param entity
     * @throws ApiValidateException
     */
    @Override
    public void addTransactionLevel(String json) throws ApiValidateException {
        JsonObject jObject = new Gson().fromJson(json, JsonObject.class);

        Integer bankId = jObject.get("bank_id").getAsInt();

        BankResponse bankEntity = bankDao.getBankById(bankId);

        if (Objects.isNull(bankEntity)) {
            throw new ApiValidateException("400", MessageUtils.getMessage("ERR02", new Object[] { "Bank" }));
        }

        TransactionLevelEntity entity = new TransactionLevelEntity();

        entity.setBankId(jObject.get("bank_id").getAsInt());
        entity.setTransactionMin(jObject.get("transaction_min").getAsDouble());
        entity.setTransactionMax(jObject.get("transaction_max").getAsDouble());
        entity.setTransactionFee(jObject.get("transaction_fee").getAsDouble());
        entity.setTransactionLevelType(jObject.get("transaction_level_type").getAsString());

        transactionLevelDao.addTransactionLevel(entity);
    }

    /**
     * @author: (VNEXT)LinhDT
     * @param bankId
     * @return
     */
    @Override
    public ResultBean getListTransactionLevel(Integer bankId) {
        List<TransactionLevelResponse> entity = transactionLevelDao.getTransactionLevelEntityByBankId(bankId);
        return new ResultBean(entity, "200", MessageUtils.getMessage("MSG01", new Object[] { "transaction level" }));
    }

}

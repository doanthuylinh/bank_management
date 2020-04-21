/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.service.impl;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.bean.BankEntity;
import com.example.demo.bean.ResultBean;
import com.example.demo.dao.BankDao;
import com.example.demo.response.BankResponse;
import com.example.demo.service.BankService;
import com.example.demo.utils.ApiValidateException;
import com.example.demo.utils.MessageUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * [OVERVIEW] Bank Service Implementation.
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
public class BankServiceImpl implements BankService {

    @Autowired
    private BankDao bankDao;

    private static final Logger LOGGER = LogManager.getLogger(BankServiceImpl.class);

    /**
     * @author: (VNEXT)LinhDT
     * @param json
     * @throws ApiValidateException
     */
    @Override
    public void addBank(String json) throws ApiValidateException {
        LOGGER.info("------addBank START--------------");
        JsonObject jObject = new Gson().fromJson(json, JsonObject.class);
        //get bank by name duoc nhap vao
        String name = jObject.get("bank_name").getAsString();
        BankEntity bankEntity = bankDao.getBankByName(name);
        //check xem bank name da co trong db hay chua, neu co roi thi throw message bank da ton tai
        if (!Objects.isNull(bankEntity)) {
            throw new ApiValidateException("400", MessageUtils.getMessage("ERR03", new Object[] { "Bank" }));
        }
        BankEntity entity = new BankEntity();
        entity.setBankName(jObject.get("bank_name").getAsString());
        bankDao.addBank(entity);
        LOGGER.info("------addBank END--------------");
    }

    /**
     * @author: (VNEXT)LinhDT
     * @param entity
     */
    @Override
    public void updateBank(BankEntity entity) {
        LOGGER.info("------updateBank START--------------");
        bankDao.updateBank(entity);
        LOGGER.info("------updateBank END--------------");
    }

    /**
     * @author: (VNEXT)LinhDT
     * @param id
     * @return
     */
    @Override
    public ResultBean getBankById(Integer id) {
        LOGGER.info("------getBankById START--------------");
        BankResponse entity = bankDao.getBankById(id);
        LOGGER.info("------getBankById END--------------");
        return new ResultBean(entity, "200", MessageUtils.getMessage("MSG01", new Object[] { "bank" }));
    }

}

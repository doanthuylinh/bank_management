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

import com.example.demo.bean.AccountEntity;
import com.example.demo.bean.BankEntity;
import com.example.demo.bean.ResultBean;
import com.example.demo.dao.AccountDao;
import com.example.demo.dao.BankDao;
import com.example.demo.dao.UserDao;
import com.example.demo.response.AccountResponse;
import com.example.demo.service.AccountService;
import com.example.demo.utils.ApiValidateException;
import com.example.demo.utils.DataUtils;
import com.example.demo.utils.MessageUtils;
import com.example.demo.utils.Regex;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * [OVERVIEW] Account Service Implementation.
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
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BankDao bankDao;

    private static final Logger LOGGER = LogManager.getLogger(AccountServiceImpl.class);

    /**
     * @author: (VNEXT)LinhDT
     * @param entity
     * @throws ApiValidateException
     */
    @Override
    public void addAccount(String json) throws ApiValidateException {
        LOGGER.info("------addAccount START--------------");
        JsonObject jObject = new Gson().fromJson(json, JsonObject.class);

        Integer userId = userDao.getUserById(Integer.parseInt(DataUtils.getUserIdByToken())).getUserId();

        // kiem tra bank ID validate chua?
        String bankIdString = jObject.get("bank_id").getAsString();
        if (!bankIdString.matches(Regex.ID_PATTERN)) {
            throw new ApiValidateException("400", MessageUtils.getMessage("ERR09", new Object[] { "Bank ID" }));
        }

        Integer bankId = jObject.get("bank_id").getAsInt();

        BankEntity bankEntity = bankDao.getBankEntityById(bankId);
        // check xem trong db bang bank co bankId duoc nhap chua
        if (Objects.isNull(bankEntity)) {
            throw new ApiValidateException("400", MessageUtils.getMessage("ERR02", new Object[] { "Bank" }));
        }

        AccountEntity accountEntity = accountDao.getAccountEntity(userId, bankId);
        // check xem trong db bang account co account nao chua
        if (!Objects.isNull(accountEntity)) {
            throw new ApiValidateException("400", MessageUtils.getMessage("ERR03", new Object[] { "Account" }));
        }

        // kiem tra so du tai khoan duoc nhap dung chua
        String balanceString = jObject.get("balance").getAsString();
        if (!balanceString.matches(Regex.MONEY_PATTERN)) {
            throw new ApiValidateException("400", MessageUtils.getMessage("ERR09", new Object[] { "money" }));
        }

        // add account
        AccountEntity entity = new AccountEntity();

        entity.setUserId(userId);
        entity.setBankId(bankId);
        entity.setBalance(jObject.get("balance").getAsDouble());

        accountDao.addAccount(entity);

        LOGGER.info("------addAccount END--------------");
    }

    /**
     * @author: (VNEXT)LinhDT
     * @param id
     * @return
     */
    @Override
    public ResultBean getAccountById(Integer id) {
        LOGGER.info("------getAccountById START--------------");
        AccountResponse entity = accountDao.getAccountById(id);
        LOGGER.info("------getAccountById END--------------");
        return new ResultBean(entity, "200", MessageUtils.getMessage("MSG01", new Object[] { "account" }));
    }

}

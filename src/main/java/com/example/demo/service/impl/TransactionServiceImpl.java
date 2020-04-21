/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.bean.AccountEntity;
import com.example.demo.bean.ResultBean;
import com.example.demo.bean.TransactionEntity;
import com.example.demo.dao.AccountDao;
import com.example.demo.dao.TransactionDao;
import com.example.demo.dao.TransactionLevelDao;
import com.example.demo.response.TransactionLevelResponse;
import com.example.demo.service.TransactionService;
import com.example.demo.utils.ApiValidateException;
import com.example.demo.utils.DataUtils;
import com.example.demo.utils.MessageUtils;
import com.example.demo.utils.Regex;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * [OVERVIEW] Transaction Service Implementation.
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
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionDao transactionDao;
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private TransactionLevelDao transactionLevelDao;

    private static final Logger LOGGER = LogManager.getLogger(TransactionServiceImpl.class);

    /**
     * @author: (VNEXT)LinhDT
     * @param json
     * @throws ApiValidateException
     */
    @Override
    public void addTransaction(String json) throws ApiValidateException {
        LOGGER.info("------addTransaction START--------------");
        JsonObject jObject = new Gson().fromJson(json, JsonObject.class);
        TransactionEntity entity = new TransactionEntity();
        //AccountEntity accountEntity = null;
        this.checkAccount(jObject, entity);
        LOGGER.info("------addTransaction END--------------");
    }

    /**
     * @author: (VNEXT)LinhDT
     * @param userId
     * @return
     */
    @Override
    public ResultBean getListTransaction() {
        LOGGER.info("------getListTransaction START--------------");
        List<TransactionEntity> entity = transactionDao.getTransactionByUserId(Integer.parseInt(DataUtils.getUserIdByToken()));
        LOGGER.info("------getListTransaction END--------------");
        return new ResultBean(entity, "200", MessageUtils.getMessage("MSG01", new Object[] { "transactions" }));
    }

    /**
     * @author: (VNEXT)LinhDT
     * @param userId
     * @param bankId
     * @return
     */
    @Override
    public ResultBean getListTransaction(Integer bankId) {
        LOGGER.info("------getListTransaction START--------------");
        List<TransactionEntity> entity = transactionDao.getTransaction(Integer.parseInt(DataUtils.getUserIdByToken()), bankId);
        LOGGER.info("------getListTransaction END--------------");
        return new ResultBean(entity, "200", MessageUtils.getMessage("MSG01", new Object[] { "transactions" }));
    }

    /**
     * @author: (VNEXT)LinhDT
     * @param jObject
     * @param entity
     * @return
     * @throws ApiValidateException
     */
    private AccountEntity checkAccount(JsonObject jObject, TransactionEntity entity) throws ApiValidateException {
        LOGGER.info("------checkAccount START--------------");
        // kiem tra user ID duoc nhap dung chua
        String userIdString = jObject.get("user_id").getAsString();
        if (!userIdString.matches(Regex.ID_PATTERN)) {
            throw new ApiValidateException("400", MessageUtils.getMessage("ERR09", new Object[] { "User ID" }));
        }
        // kiem tra bank ID duoc nhap dung chua
        String bankIdString = jObject.get("bank_id").getAsString();
        if (!bankIdString.matches(Regex.ID_PATTERN)) {
            throw new ApiValidateException("400", MessageUtils.getMessage("ERR09", new Object[] { "Bank ID" }));
        }
        Integer userId = jObject.get("user_id").getAsInt();
        Integer bankId = jObject.get("bank_id").getAsInt();
        AccountEntity accountEntity = accountDao.getAccountEntity(userId, bankId);
        // check xem account co ton tai khong
        if (Objects.isNull(accountEntity)) {
            throw new ApiValidateException("400", MessageUtils.getMessage("ERR02", new Object[] { "Account" }));
        }

        // kiem tra so tien giao dich duoc nhap dung chua
        String moneyString = jObject.get("transaction_money").getAsString();
        if (!moneyString.matches(Regex.MONEY_PATTERN)) {
            throw new ApiValidateException("400", MessageUtils.getMessage("ERR09", new Object[] { "money" }));
        }

        entity.setUserId(userId);
        entity.setBankId(bankId);
        entity.setTransactionMoney(jObject.get("transaction_money").getAsDouble());
        entity.setTransactionDate(new Date());

        LOGGER.info("------checkAccount END--------------");

        return accountEntity;
    }

    /**
     * @author: (VNEXT)LinhDT
     * @param json
     * @throws ApiValidateException
     */
    @Override
    public void deposit(String json) throws ApiValidateException {
        LOGGER.info("------deposit START--------------");
        JsonObject jObject = new Gson().fromJson(json, JsonObject.class);
        TransactionEntity entity = new TransactionEntity();
        AccountEntity accountEntity = this.checkAccount(jObject, entity);
        Double balance = accountEntity.getBalance() + entity.getTransactionMoney();
        entity.setTransactionType("0");
        transactionDao.addTransaction(entity);
        accountEntity.setBalance(balance);
        LOGGER.info("------deposit END--------------");
    }

    /**
     * @author: (VNEXT)LinhDT
     * @param json
     * @throws ApiValidateException
     */
    @Override
    public void withdraw(String json) throws ApiValidateException {
        LOGGER.info("------withdraw START--------------");
        JsonObject jObject = new Gson().fromJson(json, JsonObject.class);
        TransactionEntity entity = new TransactionEntity();
        AccountEntity accountEntity = this.checkAccount(jObject, entity);
        Integer bankId = jObject.get("bank_id").getAsInt();
        List<TransactionLevelResponse> lsTransactionLevelResponses = transactionLevelDao.getTransactionLevelEntityByBankId(bankId);
        Double balance = null;
        for (TransactionLevelResponse transactionLevelEntity : lsTransactionLevelResponses) {
            if (transactionLevelEntity.getTransactionMin() <= entity.getTransactionMoney()
                    && entity.getTransactionMoney() < transactionLevelEntity.getTransactionMax()) {
                // neu la type 0 thi tru them phi, neu khong phai type 0 thi lay muc phi nhan
                // voi so tien giao dich
                if (transactionLevelEntity.getTransactionLevelType().equals("0")) {
                    balance = accountEntity.getBalance() - entity.getTransactionMoney() - transactionLevelEntity.getTransactionFee();
                } else {
                    balance = accountEntity.getBalance() - entity.getTransactionMoney() * (1 + transactionLevelEntity.getTransactionFee());
                }
                break;
            }
        }

        if (balance < 50000) {
            throw new ApiValidateException("400", MessageUtils.getMessage("ERR04"));
        }
        entity.setTransactionType("1");
        transactionDao.addTransaction(entity);
        accountEntity.setBalance(balance);
        LOGGER.info("------withdraw END--------------");
    }

    /**
     * @author: (VNEXT)LinhDT
     * @param json
     * @throws ApiValidateException
     */
    @Override
    public void transfer(String json) throws ApiValidateException {
        LOGGER.info("------transfer START--------------");

        // withdraw
        withdraw(json);

        // deposit
        JsonObject jObject = new Gson().fromJson(json, JsonObject.class);
        TransactionEntity entity = new TransactionEntity();

        // kiem tra user ID duoc nhap dung chua
        String userTargetIdString = jObject.get("user_target_id").getAsString();
        if (!userTargetIdString.matches(Regex.ID_PATTERN)) {
            throw new ApiValidateException("400", MessageUtils.getMessage("ERR09", new Object[] { "Target User ID" }));
        }
        // kiem tra bank ID duoc nhap dung chua
        String bankTargetIdString = jObject.get("bank_target_id").getAsString();
        if (!bankTargetIdString.matches(Regex.ID_PATTERN)) {
            throw new ApiValidateException("400", MessageUtils.getMessage("ERR09", new Object[] { "Target Bank ID" }));
        }

        Integer userTargetId = jObject.get("user_target_id").getAsInt();
        Integer bankTargetId = jObject.get("bank_target_id").getAsInt();

        AccountEntity accountTargetEntity = accountDao.getAccountEntity(userTargetId, bankTargetId);

        // check xem account target co ton tai khong
        if (Objects.isNull(accountTargetEntity)) {
            throw new ApiValidateException("400", MessageUtils.getMessage("ERR02", new Object[] { "Account" }));
        }

        // kiem tra so tien giao dich duoc nhap dung chua
        String moneyString = jObject.get("transaction_money").getAsString();
        if (!moneyString.matches(Regex.MONEY_PATTERN)) {
            throw new ApiValidateException("400", MessageUtils.getMessage("ERR09", new Object[] { "money" }));
        }

        // add giao dich
        entity.setUserId(userTargetId);
        entity.setBankId(bankTargetId);
        entity.setTransactionMoney(jObject.get("transaction_money").getAsDouble());
        entity.setTransactionDate(new Date());

        Double balance = accountTargetEntity.getBalance() + entity.getTransactionMoney();
        entity.setTransactionType("0");
        transactionDao.addTransaction(entity);
        accountTargetEntity.setBalance(balance);

        LOGGER.info("------transfer END--------------");
    }

}

/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.service.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
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
import com.example.demo.response.TransactionResponse;
import com.example.demo.service.TransactionService;
import com.example.demo.utils.ApiValidateException;
import com.example.demo.utils.ConstantColumn;
import com.example.demo.utils.DataUtils;
import com.example.demo.utils.MessageUtils;
import com.example.demo.utils.RenameFile;
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
    private AccountEntity checkAccount(JsonObject jObject, TransactionEntity entity, Boolean isTargetUser) throws ApiValidateException {
        LOGGER.info("------checkAccount START--------------");

        Integer bankId = 0;
        AccountEntity accountEntity = null;
        if (!isTargetUser) {
            bankId = DataUtils.getAsIntegerByJson(jObject, ConstantColumn.BANK_ID);
            accountEntity = accountDao.getAccountEntity(Integer.parseInt(DataUtils.getUserIdByToken()), bankId);
        } else {
            bankId = DataUtils.getAsIntegerByJson(jObject, ConstantColumn.BANK_ID_TARGET);
            Integer targetUserId = DataUtils.getAsIntegerByJson(jObject, ConstantColumn.TO_USER_ID);
            accountEntity = accountDao.getAccountEntity(targetUserId, bankId);
        }

        // check xem account co ton tai khong
        if (Objects.isNull(accountEntity)) {
            throw new ApiValidateException("ERR02", MessageUtils.getMessage("ERR02", new Object[] { "Account" }));
        }

        // kiem tra so tien giao dich duoc nhap dung chua
        Double money = DataUtils.getAsDoubleByJson(jObject, ConstantColumn.TRANSACTION_MONEY);

        entity.setAccountId(accountEntity.getAccountId());
        entity.setUserId(accountEntity.getUserId());
        entity.setBankId(accountEntity.getBankId());
        entity.setTransactionMoney(money);
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
        AccountEntity accountEntity = this.checkAccount(jObject, entity, false);
        Double balance = accountEntity.getBalance() + entity.getTransactionMoney();
        entity.setTransactionType("0");
        transactionDao.addTransaction(entity);
        accountEntity.setBalance(balance);
        LOGGER.info("------deposit END--------------");
    }

    /**
     * getBalance
     * @author: (VNEXT)LinhDT
     * @param entity
     * @param accountEntity
     * @return
     * @throws ApiValidateException
     */
    private Double getBalance(TransactionEntity entity, AccountEntity accountEntity) throws ApiValidateException {
        LOGGER.info("------getBalance START--------------");
        Integer bankId = accountEntity.getBankId();
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

        if (Objects.isNull(balance) || balance < 50000) {
            throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04"));
        }
        LOGGER.info("------getBalance END--------------");
        return balance;
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
        AccountEntity accountEntity = this.checkAccount(jObject, entity, false);
        Double balance = this.getBalance(entity, accountEntity);
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
        JsonObject jObject = new Gson().fromJson(json, JsonObject.class);
        TransactionEntity entityTarget = new TransactionEntity();
        TransactionEntity entity = new TransactionEntity();

        AccountEntity accountEntityTarget = this.checkAccount(jObject, entityTarget, true);
        AccountEntity accountEntity = this.checkAccount(jObject, entity, false);

        Double balance = this.getBalance(entity, accountEntity);

        entity.setTransactionType("1");
        entityTarget.setTransactionType("0");

        entity.setBankIdTarget(entityTarget.getBankId());
        entityTarget.setBankIdTarget(entity.getBankId());

        entity.setToUserId(accountEntityTarget.getUserId());
        entityTarget.setFromUserId(entity.getUserId());

        transactionDao.addTransaction(entity);
        transactionDao.addTransaction(entityTarget);

        accountEntity.setBalance(balance);
        accountEntityTarget.setBalance(accountEntityTarget.getBalance() + entityTarget.getTransactionMoney());
        LOGGER.info("------transfer END--------------");
    }

    /**
     * outputTransactionsToCSV
     * @author: (VNEXT)LinhDT
     * @param id
     * @return
     */
    @Override
    public String outputTransactionsToCSV(Integer bankId) throws ApiValidateException {
        List<TransactionResponse> entity = transactionDao.getTransactionResponse(Integer.parseInt(DataUtils.getUserIdByToken()), bankId);
        String fileName = RenameFile.renameFile();
        String csvFile = "D:/CSV/output/transaction_output" + fileName + ".csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile));
                CSVPrinter csvPrinter = new CSVPrinter(writer,
                        CSVFormat.DEFAULT.withHeader("TransactionID", "AccountID", "UserID", "UserName", "BankID", "BankName", "MoneyTransaction", "Date",
                                "Type", "FromUserID", "ToUserID", "BankIDTarget", "FromUserName", "ToUserName", "BankTargetName"));) {
            for (TransactionResponse transactionResponse : entity) {
                csvPrinter.printRecord(transactionResponse.getTransactionId(), transactionResponse.getAccountId(), transactionResponse.getUserId(),
                        transactionResponse.getUserName(), transactionResponse.getBankId(), transactionResponse.getBankName(),
                        transactionResponse.getTransactionMoney(), transactionResponse.getTransactionDate(), transactionResponse.getTransactionType(),
                        transactionResponse.getFromUserId(), transactionResponse.getToUserId(), transactionResponse.getBankIdTarget(),
                        transactionResponse.getFromUserName(), transactionResponse.getToUserName(), transactionResponse.getBankTargetName());
            }
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvFile;
    }

}

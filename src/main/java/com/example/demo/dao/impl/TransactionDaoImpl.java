/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.bean.TransactionEntity;
import com.example.demo.dao.TransactionDao;
import com.example.demo.response.TransactionResponse;

/**
 * [OVERVIEW] Transaction Dao Implementation.
 *
 * @author: (VNEXT)LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/15      (VNEXT)LinhDT      Create new
*/
@Repository
@Transactional
public class TransactionDaoImpl implements TransactionDao {
    @Autowired
    private EntityManager entityManager;

    private static final Logger LOGGER = LogManager.getLogger(TransactionDaoImpl.class);

    /**
     * @author: (VNEXT)LinhDT
     * @param entity
     */
    @Override
    public void addTransaction(TransactionEntity entity) {
        LOGGER.info("------addTransaction START--------------");
        this.entityManager.persist(entity);
        LOGGER.info("------addTransaction END--------------");
    }

    /**
     * @author: (VNEXT)LinhDT
     * @param userId
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TransactionEntity> getTransactionByUserId(Integer userId) {
        LOGGER.info("------getTransactionByUserId START--------------");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new com.example.demo.response.TransactionResponse(");
        sql.append("    te.transactionId, ");
        sql.append("    te.accountId, ");
        sql.append("    te.userId, ");
        sql.append("    ue.userName, ");
        sql.append("    te.bankId, ");
        sql.append("    be.bankName, ");
        sql.append("    te.transactionMoney, ");
        sql.append("    te.transactionDate, ");
        sql.append("    te.transactionType, ");
        sql.append("    te.fromUserId, ");
        sql.append("    te.toUserId, ");
        sql.append("    te.bankIdTarget, ");
        sql.append("    ue1.userName, ");
        sql.append("    ue2.userName, ");
        sql.append("    be1.bankName ) ");
        sql.append(" FROM ");
        sql.append("    TransactionEntity te ");
        sql.append(" JOIN ");
        sql.append("    BankEntity be ");
        sql.append(" ON ");
        sql.append("    te.bankId = be.bankId ");
        sql.append(" JOIN ");
        sql.append("    AccountEntity ae ");
        sql.append(" ON ");
        sql.append("    (te.accountId = ae.accountId ");
        sql.append(" AND ");
        sql.append("    te.userId = ae.userId ");
        sql.append(" AND ");
        sql.append("    te.bankId = ae.bankId) ");
        sql.append(" JOIN ");
        sql.append("    UserEntity ue ");
        sql.append(" ON ");
        sql.append("    ae.userId = ue.userId ");
        sql.append(" LEFT JOIN ");
        sql.append("    UserEntity ue1 ");
        sql.append(" ON ");
        sql.append("    ue1.userId = te.fromUserId ");
        sql.append(" LEFT JOIN ");
        sql.append("    UserEntity ue2 ");
        sql.append(" ON ");
        sql.append("    ue2.userId = te.toUserId ");
        sql.append(" LEFT JOIN ");
        sql.append("    BankEntity be1 ");
        sql.append(" ON ");
        sql.append("    be1.bankId = te.bankIdTarget ");
        sql.append(" WHERE ");
        sql.append("    te.userId = :userId ");
        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("userId", userId);
        List<TransactionEntity> entity = null;
        entity = query.getResultList();
        LOGGER.info("------getTransactionByUserId END--------------");
        return entity;
    }

    /**
     * @author: (VNEXT)LinhDT
     * @param userId
     * @param bankId
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TransactionEntity> getTransaction(Integer userId, Integer bankId) {
        LOGGER.info("------getTransaction START--------------");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new com.example.demo.response.TransactionResponse(");
        sql.append("    te.transactionId, ");
        sql.append("    te.accountId, ");
        sql.append("    te.userId, ");
        sql.append("    ue.userName, ");
        sql.append("    te.bankId, ");
        sql.append("    be.bankName, ");
        sql.append("    te.transactionMoney, ");
        sql.append("    te.transactionDate, ");
        sql.append("    te.transactionType, ");
        sql.append("    te.fromUserId, ");
        sql.append("    te.toUserId, ");
        sql.append("    te.bankIdTarget, ");
        sql.append("    ue1.userName, ");
        sql.append("    ue2.userName, ");
        sql.append("    be1.bankName ) ");
        sql.append(" FROM ");
        sql.append("    TransactionEntity te ");
        sql.append(" JOIN ");
        sql.append("    BankEntity be ");
        sql.append(" ON ");
        sql.append("    te.bankId = be.bankId ");
        sql.append(" JOIN ");
        sql.append("    AccountEntity ae ");
        sql.append(" ON ");
        sql.append("    (te.accountId = ae.accountId ");
        sql.append(" AND ");
        sql.append("    te.userId = ae.userId ");
        sql.append(" AND ");
        sql.append("    te.bankId = ae.bankId) ");
        sql.append(" JOIN ");
        sql.append("    UserEntity ue ");
        sql.append(" ON ");
        sql.append("    ae.userId = ue.userId ");
        sql.append(" LEFT JOIN ");
        sql.append("    UserEntity ue1 ");
        sql.append(" ON ");
        sql.append("    ue1.userId = te.fromUserId ");
        sql.append(" LEFT JOIN ");
        sql.append("    UserEntity ue2 ");
        sql.append(" ON ");
        sql.append("    ue2.userId = te.toUserId ");
        sql.append(" LEFT JOIN ");
        sql.append("    BankEntity be1 ");
        sql.append(" ON ");
        sql.append("    be1.bankId = te.bankIdTarget ");
        sql.append(" WHERE ");
        sql.append("    te.userId = :userId ");
        sql.append(" AND ");
        sql.append("    te.bankId = :bankId ");
        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("userId", userId);
        query.setParameter("bankId", bankId);
        List<TransactionEntity> entity = null;
        entity = query.getResultList();
        LOGGER.info("------getTransaction END--------------");
        return entity;
    }

    /**
     * getTransactionResponse
     * @author: (VNEXT)LinhDT
     * @param userId
     * @param bankId
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TransactionResponse> getTransactionResponse(Integer userId, Integer bankId) {
        LOGGER.info("------getTransactionResponse START--------------");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new com.example.demo.response.TransactionResponse(");
        sql.append("    te.transactionId, ");
        sql.append("    te.accountId, ");
        sql.append("    te.userId, ");
        sql.append("    ue.userName, ");
        sql.append("    te.bankId, ");
        sql.append("    be.bankName, ");
        sql.append("    te.transactionMoney, ");
        sql.append("    te.transactionDate, ");
        sql.append("    te.transactionType, ");
        sql.append("    te.fromUserId, ");
        sql.append("    te.toUserId, ");
        sql.append("    te.bankIdTarget, ");
        sql.append("    ue1.userName, ");
        sql.append("    ue2.userName, ");
        sql.append("    be1.bankName ) ");
        sql.append(" FROM ");
        sql.append("    TransactionEntity te ");
        sql.append(" JOIN ");
        sql.append("    BankEntity be ");
        sql.append(" ON ");
        sql.append("    te.bankId = be.bankId ");
        sql.append(" JOIN ");
        sql.append("    AccountEntity ae ");
        sql.append(" ON ");
        sql.append("    (te.accountId = ae.accountId ");
        sql.append(" AND ");
        sql.append("    te.userId = ae.userId ");
        sql.append(" AND ");
        sql.append("    te.bankId = ae.bankId) ");
        sql.append(" JOIN ");
        sql.append("    UserEntity ue ");
        sql.append(" ON ");
        sql.append("    ae.userId = ue.userId ");
        sql.append(" LEFT JOIN ");
        sql.append("    UserEntity ue1 ");
        sql.append(" ON ");
        sql.append("    ue1.userId = te.fromUserId ");
        sql.append(" LEFT JOIN ");
        sql.append("    UserEntity ue2 ");
        sql.append(" ON ");
        sql.append("    ue2.userId = te.toUserId ");
        sql.append(" LEFT JOIN ");
        sql.append("    BankEntity be1 ");
        sql.append(" ON ");
        sql.append("    be1.bankId = te.bankIdTarget ");
        sql.append(" WHERE ");
        sql.append("    te.userId = :userId ");
        sql.append(" AND ");
        sql.append("    te.bankId = :bankId ");
        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("userId", userId);
        query.setParameter("bankId", bankId);
        List<TransactionResponse> entity = null;
        entity = query.getResultList();
        LOGGER.info("------getTransactionResponse END--------------");
        return entity;
    }

}

/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.bean.AccountEntity;
import com.example.demo.dao.AccountDao;
import com.example.demo.response.AccountResponse;

/**
 * [OVERVIEW] Account Dao Implementation.
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
public class AccountDaoImpl implements AccountDao {

    @PersistenceContext
    @Autowired
    private EntityManager entityManager;

    private static final Logger LOGGER = LogManager.getLogger(AccountDaoImpl.class);

    /**
     * @author: (VNEXT)LinhDT
     * @param entity
     */
    @Override
    public void addAccount(AccountEntity entity) {
        LOGGER.info("------addAccount START--------------");
        this.entityManager.persist(entity);
        LOGGER.info("------addAccount END--------------");
    }

    /**
     * @author: (VNEXT)LinhDT
     * @param id
     * @return
     */
    @Override
    public AccountResponse getAccountById(Integer id) {
        LOGGER.info("------getAccountById START--------------");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new com.example.demo.response.AccountResponse(");
        sql.append("    ae.accountId, ");
        sql.append("    ue.userId, ");
        sql.append("    ue.userName, ");
        sql.append("    be.bankId, ");
        sql.append("    be.bankName, ");
        sql.append("    ae.balance ) ");
        sql.append(" FROM ");
        sql.append("    AccountEntity ae ");
        sql.append(" INNER JOIN ");
        sql.append("    UserEntity ue ");
        sql.append(" ON ");
        sql.append("    ae.userId = ue.userId ");
        sql.append(" INNER JOIN ");
        sql.append("    BankEntity be ");
        sql.append(" ON ");
        sql.append("    ae.bankId = be.bankId ");
        sql.append(" WHERE ");
        sql.append("    ae.accountId = :id ");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("id", id);
        AccountResponse entity = null;
        try {
            entity = (AccountResponse) query.getSingleResult();
        } catch (NoResultException e) {

        }
        LOGGER.info("------getAccountById END--------------");
        return entity;
    }

    /**
     * @author: (VNEXT)LinhDT
     * @param userId
     * @param bankId
     * @return
     */
    @Override
    public AccountEntity getAccountEntity(Integer accountId) {
        LOGGER.info("------getAccountEntity START--------------");
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM ");
        sql.append("    AccountEntity ae ");
        sql.append(" WHERE ");
        sql.append("    ae.accountId = :accountId ");

        Query query = this.entityManager.createQuery(sql.toString());

        query.setParameter("accountId", accountId);
        AccountEntity entity = null;
        try {
            entity = (AccountEntity) query.getSingleResult();
        } catch (NoResultException e) {

        }
        LOGGER.info("------getAccountEntity END--------------");
        return entity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<AccountEntity> getAccountEntity(Integer userId, Integer bankId) {
        LOGGER.info("------getAccountEntity START--------------");
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM ");
        sql.append("    AccountEntity ae ");
        sql.append(" WHERE ");
        sql.append("    ae.userId = :userId ");
        sql.append(" AND ");
        sql.append("    ae.bankId = :bankId ");

        Query query = this.entityManager.createQuery(sql.toString());

        query.setParameter("userId", userId);
        query.setParameter("bankId", bankId);
        List<AccountEntity> entity = null;

        entity = query.getResultList();

        LOGGER.info("------getAccountEntity END--------------");
        return entity;
    }

    @Override
    public AccountEntity getAccountEntityByUserAndAcc(Integer userId, Integer accountId) {
        LOGGER.info("------getAccountEntity START--------------");
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM ");
        sql.append("    AccountEntity ae ");
        sql.append(" WHERE ");
        sql.append("    ae.userId = :userId ");
        sql.append(" AND ");
        sql.append("    ae.accountId = :accountId ");

        Query query = this.entityManager.createQuery(sql.toString());

        query.setParameter("userId", userId);
        query.setParameter("accountId", accountId);
        AccountEntity entity = null;
        try {
            entity = (AccountEntity) query.getSingleResult();
        } catch (NoResultException e) {

        }

        LOGGER.info("------getAccountEntity END--------------");
        return entity;
    }
}

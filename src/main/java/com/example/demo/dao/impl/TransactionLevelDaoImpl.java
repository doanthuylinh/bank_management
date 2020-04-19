/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.bean.TransactionLevelEntity;
import com.example.demo.dao.TransactionLevelDao;
import com.example.demo.response.TransactionLevelResponse;

/**
 * [OVERVIEW] Transaction Level Dao Implementation.
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
public class TransactionLevelDaoImpl implements TransactionLevelDao {

    @Autowired
    private EntityManager entityManager;

    /**
     * @author: (VNEXT)LinhDT
     * @param entity
     */
    @Override
    public void addTransactionLevel(TransactionLevelEntity entity) {
        this.entityManager.persist(entity);

    }

    /**
     * @author: (VNEXT)LinhDT
     * @param bankId
     * @return
     */
    @Override
    public List<TransactionLevelResponse> getTransactionLevelEntityByBankId(Integer bankId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new com.example.demo.response.TransactionLevelResponse(");
        sql.append("     tle.transactionLevelId, ");
        sql.append("     tle.bankId, ");
        sql.append("     tle.transactionMin, ");
        sql.append("     tle.transactionMax, ");
        sql.append("     tle.transactionFee, ");
        sql.append("     tle.transactionLevelType) ");
        sql.append(" FROM ");
        sql.append("    TransactionLevelEntity tle ");
        sql.append(" WHERE ");
        sql.append("    tle.bankId = :bankId ");
        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("bankId", bankId);
        List<TransactionLevelResponse> entity = null;
        entity = query.getResultList();
        return entity;
    }

    /**
     * @author: (VNEXT)LinhDT
     * @param bankId
     * @return
     */
    @Override
    public TransactionLevelEntity getTransactionEntity(Integer bankId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM ");
        sql.append("    TransactionLevelEntity tle ");
        sql.append(" WHERE ");
        sql.append("    tle.bankId = :bankId ");
        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("bankId", bankId);
        TransactionLevelEntity entity = null;
        try {
            entity = (TransactionLevelEntity) query.getSingleResult();
        } catch (NoResultException e) {

        }
        return entity;
    }

}

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

import com.example.demo.bean.BankEntity;
import com.example.demo.dao.BankDao;
import com.example.demo.response.BankResponse;

/**
 * [OVERVIEW] Bank Dao Implementation.
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
public class BankDaoImpl implements BankDao {

    @PersistenceContext
    @Autowired
    private EntityManager entityManager;

    private static final Logger LOGGER = LogManager.getLogger(BankDaoImpl.class);

    /**
     * @author: (VNEXT)LinhDT
     * @param entity
     */
    @Override
    public void addBank(BankEntity entity) {
        LOGGER.info("------addBank START--------------");
        this.entityManager.persist(entity);
        LOGGER.info("------addBank END--------------");
    }

    /**
     * @author: (VNEXT)LinhDT
     * @param entity
     */
    @Override
    public void updateBank(BankEntity entity) {
        LOGGER.info("------updateBank START--------------");
        this.entityManager.merge(entity);
        LOGGER.info("------updateBank END--------------");

    }

    /**
     * @author: (VNEXT)LinhDT
     * @param id
     * @return
     */
    @Override
    public BankResponse getBankById(Integer id) {
        LOGGER.info("------getBankById START--------------");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new com.example.demo.response.BankResponse(");
        sql.append("    be.bankId, ");
        sql.append("    be.bankName) ");
        sql.append(" FROM ");
        sql.append("    BankEntity be ");
        sql.append(" WHERE ");
        sql.append("    be.bankId = :id ");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("id", id);
        BankResponse entity = null;
        try {
            entity = (BankResponse) query.getSingleResult();
        } catch (NoResultException e) {

        }
        LOGGER.info("------getBankById END--------------");
        return entity;
    }

    /**
     * @author: (VNEXT)LinhDT
     * @param userId
     * @return
     */
    @Override
    public List<BankResponse> getListBankByUserId(Integer userId) {
        LOGGER.info("------getListBankByUserId START--------------");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new com.example.demo.response.BankResponse(be.bankId, be.bankName, ae.balance)");
        sql.append(" FROM ");
        sql.append("    BankEntity be ");
        sql.append(" JOIN ");
        sql.append("    AccountEntity ae ");
        sql.append(" ON ");
        sql.append("    be.bankId = ae.bankId ");
        sql.append(" JOIN ");
        sql.append("    UserEntity ue ");
        sql.append(" ON ");
        sql.append("    ue.userId = ae.userId ");
        sql.append(" WHERE ");
        sql.append("    ue.userId = :userId ");

        Query query = this.entityManager.createQuery(sql.toString());

        query.setParameter("userId", userId);
        List<BankResponse> entity = null;

        entity = query.getResultList();

        LOGGER.info("------getListBankByUserId END--------------");

        return entity;
    }

    /**
     * @author: (VNEXT)LinhDT
     * @param name
     * @return
     */
    @Override
    public BankEntity getBankByName(String name) {
        LOGGER.info("------getBankByName START--------------");
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM ");
        sql.append("    BankEntity be ");
        sql.append(" WHERE ");
        sql.append("    be.bankName = :name ");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("name", name);
        BankEntity entity = null;
        try {
            entity = (BankEntity) query.getSingleResult();
        } catch (NoResultException e) {

        }
        LOGGER.info("------getBankByName END--------------");
        return entity;
    }

}

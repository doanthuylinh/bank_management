/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.bean.UserEntity;
import com.example.demo.dao.UserDao;
import com.example.demo.response.UserResponse;

/**
 * [OVERVIEW] User Dao Implementation.
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
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    @Autowired
    private EntityManager entityManager;

    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

    /**
     * @author: (VNEXT)LinhDT
     * @param entity
     */
    @Override
    public void addUser(UserEntity entity) {
        LOGGER.info("------addUser START--------------");
        this.entityManager.persist(entity);
        LOGGER.info("------addUser END--------------");
    }

    /**
     * @author: (VNEXT)LinhDT
     * @param entity
     */
    @Override
    public void updateUser(UserEntity entity) {
        LOGGER.info("------updateUser START--------------");
        this.entityManager.merge(entity);
        LOGGER.info("------updateUser END--------------");
    }

    /**
     * @author: (VNEXT)LinhDT
     * @param id
     * @return
     */
    @Override
    public UserResponse getUserById(Integer id) {
        LOGGER.info("------getUserById START--------------");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new com.example.demo.response.UserResponse(");
        sql.append("    ue.userId, ");
        sql.append("    ue.userName, ");
        sql.append("    ue.phone, ");
        sql.append("    ue.dob ) ");
        sql.append(" FROM ");
        sql.append("    UserEntity ue ");
        sql.append(" WHERE ");
        sql.append("    ue.userId = :id ");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("id", id);
        UserResponse entity = null;
        try {
            entity = (UserResponse) query.getSingleResult();
        } catch (NoResultException e) {

        }
        LOGGER.info("------getUserById END--------------");
        return entity;
    }

    /**
     * @author: (VNEXT)LinhDT
     * @param userId
     * @return
     */
    @Override
    public UserEntity getUserEntity(Integer userId) {
        LOGGER.info("------getUserEntity START--------------");
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM ");
        sql.append("    UserEntity ue ");
        sql.append(" WHERE ");
        sql.append("    ue.userId = :userId ");

        Query query = this.entityManager.createQuery(sql.toString());

        query.setParameter("userId", userId);
        UserEntity entity = null;
        try {
            entity = (UserEntity) query.getSingleResult();
        } catch (NoResultException e) {

        }
        LOGGER.info("------getUserEntity END--------------");
        return entity;
    }

    /**
     * @author: (VNEXT)LinhDT
     * @param phone
     * @return
     */
    @Override
    public UserEntity getUserByPhone(String phone) {
        LOGGER.info("------getUserByPhone START--------------");
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM ");
        sql.append("    UserEntity ue ");
        sql.append(" WHERE ");
        sql.append("    ue.phone = :phone ");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("phone", phone);
        UserEntity entity = null;
        try {
            entity = (UserEntity) query.getSingleResult();
        } catch (NoResultException e) {

        }
        LOGGER.info("------getUserByPhone END--------------");
        return entity;
    }

}

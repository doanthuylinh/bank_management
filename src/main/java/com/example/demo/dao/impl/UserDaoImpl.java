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

    /**
     * @author: (VNEXT)LinhDT
     * @param entity
     */
    @Override
    public void addUser(UserEntity entity) {
        this.entityManager.persist(entity);
    }

    /**
     * @author: (VNEXT)LinhDT
     * @param entity
     */
    @Override
    public void updateUser(UserEntity entity) {
        this.entityManager.merge(entity);

    }

    /**
     * @author: (VNEXT)LinhDT
     * @param id
     * @return
     */
    @Override
    public UserResponse getUserById(Integer id) {
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
        return entity;
    }

    /**
     * @author: (VNEXT)LinhDT
     * @param userId
     * @return
     */
    @Override
    public UserEntity getUserEntity(Integer userId) {
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
        return entity;
    }

    /**
     * @author: (VNEXT)LinhDT
     * @param phone
     * @return
     */
    @Override
    public UserEntity getUserByPhone(String phone) {
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
        return entity;
    }

    /**
     * @author: (VNEXT)LinhDT
     * @param userId
     * @param pass
     * @return
     */
    @Override
    public UserEntity getUserLogin(Integer userId, String pass) {
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM ");
        sql.append("    UserEntity ue ");
        sql.append(" WHERE ");
        sql.append("    ue.userId = :userId ");
        sql.append(" AND ");
        sql.append("    ue.pass = :pass ");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("userId", userId);
        query.setParameter("pass", pass);
        UserEntity entity = null;
        try {
            entity = (UserEntity) query.getSingleResult();
        } catch (NoResultException e) {

        }
        return entity;
    }

}

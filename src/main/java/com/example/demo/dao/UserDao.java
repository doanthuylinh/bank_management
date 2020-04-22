/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.dao;

import com.example.demo.bean.UserEntity;
import com.example.demo.response.UserResponse;

/**
 * [OVERVIEW] User Dao.
 *
 * @author: (VNEXT)LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/15      (VNEXT)LinhDT      Create new
*/
public interface UserDao {

    /**
     * @author: (VNEXT)LinhDT
     * @param entity
     */
    public void addUser(UserEntity entity);

    /**
     * @author: (VNEXT)LinhDT
     * @param entity
     */
    public void updateUser(UserEntity entity);

    /**
     * @author: (VNEXT)LinhDT
     * @param id
     * @return
     */
    public UserResponse getUserById(Integer id);

    /**
     * @author: (VNEXT)LinhDT
     * @param id
     * @return
     */
    public UserEntity getUserEntity(Integer id);

    /**
     * @author: (VNEXT)LinhDT
     * @param phone
     * @return
     */
    public UserEntity getUserByPhone(String phone);

}

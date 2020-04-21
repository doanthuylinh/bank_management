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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.bean.UserDetail;
import com.example.demo.bean.UserEntity;
import com.example.demo.controller.BankController;
import com.example.demo.dao.UserDao;
import com.example.demo.utils.ApiValidateException;

/**
 * [OVERVIEW] XXXXX.
 *
 * @author: (VNEXT)LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/19      (VNEXT)LinhDT       Create new
*/
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserDao userDao;

    private static final Logger LOGGER = LogManager.getLogger(BankController.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("------loadUserByUsername START--------------");
        UserEntity user = userDao.getUserEntity(Integer.parseInt(username));
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException(username);
        }
        LOGGER.info("------loadUserByUsername END--------------");
        return new UserDetail(user);
    }

    /**
     * @author: (VNEXT)LinhDT
     * @param id
     * @return
     * @throws ApiValidateException
     */
    public UserDetails loadUserById(Integer id) throws ApiValidateException {
        LOGGER.info("------loadUserById START--------------");
        UserEntity userEntity = userDao.getUserEntity(id);
        LOGGER.info("------loadUserById END--------------");
        return new UserDetail(userEntity);
    }
}

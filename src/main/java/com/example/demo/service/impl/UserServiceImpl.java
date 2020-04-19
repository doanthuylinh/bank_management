/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.service.impl;

import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.bean.ResultBean;
import com.example.demo.bean.UserEntity;
import com.example.demo.dao.BankDao;
import com.example.demo.dao.UserDao;
import com.example.demo.response.BankResponse;
import com.example.demo.response.UserResponse;
import com.example.demo.service.UserService;
import com.example.demo.utils.ApiValidateException;
import com.example.demo.utils.MessageUtils;
import com.example.demo.utils.Regex;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * [OVERVIEW] User Service Implementation.
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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BankDao bankDao;

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    /**
     * @author: (VNEXT)LinhDT
     * @param entity
     * @throws ApiValidateException
     */
    @Override
    public void addUser(String json) throws ApiValidateException {
        LOGGER.debug("Debug log message");
        JsonObject jObject = new Gson().fromJson(json, JsonObject.class);
        // get phone duoc nhap vao va kiem tra
        String phone = jObject.get("phone").getAsString();
        if (!phone.matches(Regex.PHONE_PATTERN)) {
            throw new ApiValidateException("400", MessageUtils.getMessage("ERR06"));
        }
        // get userName duoc nhap vao va kiem tra
        String userName = jObject.get("user_name").getAsString();
        if (!userName.matches(Regex.NAME_PATTERN)) {
            throw new ApiValidateException("400", MessageUtils.getMessage("ERR07"));
        }
        // get ngay sinh duoc nhap vao va kiem tra
        String dob = jObject.get("dob").getAsString();
        if (!dob.matches(Regex.DATE_PATTERN)) {
            throw new ApiValidateException("400", MessageUtils.getMessage("ERR09", new Object[] { "date" }));
        }
        // get password duoc nhap vao va kiem tra
        String pass = jObject.get("pass").getAsString();
        if (!pass.matches(Regex.PASSWORD_PATTERN)) {
            throw new ApiValidateException("400", MessageUtils.getMessage("ERR08"));
        }
        //get user by phone
        UserEntity userEntity = userDao.getUserByPhone(phone);
        // check xem phone da co trong db hay chua, neu co roi thi throw message phone da ton tai
        if (!Objects.isNull(userEntity)) {
            throw new ApiValidateException("400", MessageUtils.getMessage("ERR03", new Object[] { "Phone" }));
        }

        UserEntity entity = new UserEntity();
        entity.setUserName(userName);
        entity.setPhone(phone);
        entity.setDob(dob);
        entity.setPass(pass);
        userDao.addUser(entity);

        LOGGER.info(entity);
        LOGGER.fatal("Fatal log message");
    }

    /**
     * @author: (VNEXT)LinhDT
     * @param entity
     * @throws ApiValidateException
     */
    @Override
    public void updateUser(UserEntity entity) throws ApiValidateException {
        LOGGER.debug("Debug log message");
        UserEntity userEntity = userDao.getUserEntity(entity.getUserId());
        if (entity.getUserName() == null) {
            entity.setUserName(userEntity.getUserName());
        }
        if (entity.getPhone() == null) {
            entity.setPhone(userEntity.getPhone());
        }
        if (entity.getDob() == null) {
            entity.setDob(userEntity.getDob());
        }
        if (entity.getPass() == null) {
            entity.setPass(userEntity.getPass());
        }
        userDao.updateUser(entity);

        LOGGER.info(entity);
        LOGGER.fatal("Fatal log message");
    }

    /**
     * @author: (VNEXT)LinhDT
     * @param id
     * @return
     */
    @Override
    public ResultBean getUserById(Integer id) {
        UserResponse entity = userDao.getUserById(id);
        List<BankResponse> listBank = bankDao.getListBankByUserId(entity.getUserId());
        entity.setBank(listBank);
        return new ResultBean(entity, "200", MessageUtils.getMessage("MSG01", new Object[] { "user" }));
    }

    /**
     * @author: (VNEXT)LinhDT
     * @param entity
     * @throws ApiValidateException
     */
    @Override
    public void login(String json) throws ApiValidateException {
        JsonObject jObject = new Gson().fromJson(json, JsonObject.class);
        Integer userId = jObject.get("user_id").getAsInt();
        String pass = jObject.get("pass").getAsString();
        UserEntity userEntity = userDao.getUserLogin(userId, pass);
        if (Objects.isNull(userEntity)) {
            throw new ApiValidateException("400", MessageUtils.getMessage("ERR05"));
        }
        throw new ApiValidateException("200", MessageUtils.getMessage("MSG03"));

    }

}

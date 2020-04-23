/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.WebSecurityConfig;
import com.example.demo.bean.ResultBean;
import com.example.demo.bean.UserDetail;
import com.example.demo.bean.UserEntity;
import com.example.demo.dao.BankDao;
import com.example.demo.dao.UserDao;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.response.BankResponse;
import com.example.demo.response.UserResponse;
import com.example.demo.service.UserService;
import com.example.demo.utils.ApiValidateException;
import com.example.demo.utils.ConstantColumn;
import com.example.demo.utils.DataUtils;
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

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    WebSecurityConfig webSecurityConfig;

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    /**
     * @author: (VNEXT)LinhDT
     * @param entity
     * @throws ApiValidateException
     */
    @Override
    public void addUser(String json) throws ApiValidateException {
        LOGGER.info("------addUser START--------------");
        JsonObject jObject = new Gson().fromJson(json, JsonObject.class);

        // get phone duoc nhap vao va kiem tra
        String phone = DataUtils.getAsStringByJson(jObject, ConstantColumn.PHONE);
        if (Objects.isNull(phone) || !phone.matches(Regex.PHONE_PATTERN)) {
            throw new ApiValidateException("ERR06", MessageUtils.getMessage("ERR06"));
        }

        // get userName duoc nhap vao va kiem tra
        String userName = DataUtils.getAsStringByJson(jObject, ConstantColumn.USER_NAME);
        if (Objects.isNull(userName) || !userName.matches(Regex.NAME_PATTERN)) {
            throw new ApiValidateException("ERR07", MessageUtils.getMessage("ERR07"));
        }
        // get ngay sinh duoc nhap vao va kiem tra
        String dob = DataUtils.getAsStringByJson(jObject, ConstantColumn.DOB);
        if (Objects.isNull(dob) || !dob.matches(Regex.DATE_PATTERN)) {
            throw new ApiValidateException("ERR09", MessageUtils.getMessage("ERR09", new Object[] { "date" }));
        }
        // get password duoc nhap vao va kiem tra
        String pass = DataUtils.getAsStringByJson(jObject, ConstantColumn.PASS);
        if (Objects.isNull(pass) || !pass.matches(Regex.PASSWORD_PATTERN)) {
            throw new ApiValidateException("ERR08", MessageUtils.getMessage("ERR08"));
        }
        // get user by phone
        UserEntity userEntity = userDao.getUserByPhone(phone);
        // check xem phone da co trong db hay chua, neu co roi thi throw message phone da ton tai
        if (!Objects.isNull(userEntity)) {
            throw new ApiValidateException("ERR03", MessageUtils.getMessage("ERR03", new Object[] { "Phone" }));
        }

        UserEntity entity = new UserEntity();
        entity.setUserName(userName);
        entity.setPhone(phone);
        entity.setDob(dob);
        entity.setPass(webSecurityConfig.passwordEncoder().encode(pass));
        userDao.addUser(entity);
        LOGGER.info("------addUser END------------");
    }

    /**
     * @author: (VNEXT)LinhDT
     * @param json
     * @throws ApiValidateException
     */

    @Override
    public void updateUser(String json) throws ApiValidateException {
        LOGGER.info("------updateUser START--------------");

        UserEntity entity = userDao.getUserEntity(Integer.parseInt(DataUtils.getUserIdByToken()));

        JsonObject jObject = new Gson().fromJson(json, JsonObject.class);

        String userName = DataUtils.getAsStringByJson(jObject, "user_name");
        if (Objects.isNull(userName) || !userName.matches(Regex.NAME_PATTERN)) {
            throw new ApiValidateException("ERR07", MessageUtils.getMessage("ERR07"));
        }
        entity.setUserName(userName);

        String phone = DataUtils.getAsStringByJson(jObject, "phone");
        if (Objects.isNull(phone) || !phone.matches(Regex.PHONE_PATTERN)) {
            throw new ApiValidateException("ERR06", MessageUtils.getMessage("ERR06"));
        }

        if (!entity.getPhone().equals(phone)) {
            UserEntity userEntity = userDao.getUserByPhone(phone);
            // check xem phone da co trong db hay chua, neu co roi thi throw message phone da ton tai
            if (!Objects.isNull(userEntity)) {
                throw new ApiValidateException("ERR03", MessageUtils.getMessage("ERR03", new Object[] { "Phone" }));
            }
            entity.setPhone(phone);
        }

        String dob = DataUtils.getAsStringByJson(jObject, "dob");
        if (Objects.isNull(dob) || !dob.matches(Regex.DATE_PATTERN)) {
            throw new ApiValidateException("ERR09", MessageUtils.getMessage("ERR09", new Object[] { "date" }));
        }
        entity.setDob(dob);

        userDao.updateUser(entity);

        LOGGER.info("------updateUser END------------");
    }

    /**
     * @author: (VNEXT)LinhDT
     * @return
     */
    @Override
    public ResultBean getUserById() {
        LOGGER.info("------getUserById START--------------");
        UserResponse entity = userDao.getUserById(Integer.parseInt(DataUtils.getUserIdByToken()));
        List<BankResponse> listBank = bankDao.getListBankByUserId(entity.getUserId());
        entity.setBank(listBank);
        LOGGER.info("------getUserById END------------");
        return new ResultBean(entity, "MSG01", MessageUtils.getMessage("MSG01", new Object[] { "user" }));
    }

    /**
     * @author: (VNEXT)LinhDT
     * @param json
     * @throws ApiValidateException
     */
    @Override
    public Map<String, String> login(String json) throws ApiValidateException {
        LOGGER.info("------login START--------------");
        JsonObject jObject = new Gson().fromJson(json, JsonObject.class);
        Integer userId = jObject.get("user_id").getAsInt();
        String pass = jObject.get("pass").getAsString();
        String tmp = "";
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userId, pass));
            // Nếu không xảy ra exception tức là thông tin hợp lệ
            // Set thông tin authentication vào Security Context
            SecurityContextHolder.getContext().setAuthentication(authentication);
            tmp = tokenProvider.generateToken((UserDetail) authentication.getPrincipal());
        } catch (Exception e) {
            throw new ApiValidateException("ERR05", MessageUtils.getMessage("ERR05"));
        }
        Map<String, String> result = new HashMap<String, String>();
        result.put("type", "Bearer");
        result.put("token", tmp);
        LOGGER.info("------login END------------");
        return result;
    }

    /**
     * changePassword
     * @author: (VNEXT)LinhDT
     * @param pass
     * @throws ApiValidateException
     */
    @Override
    public void changePassword(String json) throws ApiValidateException {
        LOGGER.info("------changePassword START--------------");
        JsonObject jObject = new Gson().fromJson(json, JsonObject.class);

        UserEntity userEntity = userDao.getUserEntity(Integer.parseInt(DataUtils.getUserIdByToken()));

        if (DataUtils.isNullWithMemberNameByJson(jObject, ConstantColumn.PASS)) {
            throw new ApiValidateException("ERR12", MessageUtils.getMessage("ERR12", new Object[] { ConstantColumn.PASS }));
        }

        if (DataUtils.isNullWithMemberNameByJson(jObject, ConstantColumn.CONFIRMED_PASS)) {
            throw new ApiValidateException("ERR12", MessageUtils.getMessage("ERR12", new Object[] { ConstantColumn.CONFIRMED_PASS }));
        }

        String pass = DataUtils.getAsStringByJson(jObject, ConstantColumn.PASS);
        if (!pass.matches(Regex.PASSWORD_PATTERN)) {
            throw new ApiValidateException("ERR08", MessageUtils.getMessage("ERR08"));
        }

        String confirmedPass = DataUtils.getAsStringByJson(jObject, ConstantColumn.CONFIRMED_PASS);

        if (!pass.equals(confirmedPass)) {
            throw new ApiValidateException("ERR13", MessageUtils.getMessage("ERR13"));
        }

        userEntity.setPass(webSecurityConfig.passwordEncoder().encode(pass));
        userDao.updateUser(userEntity);

        LOGGER.info("------changePassword END------------");
    }

}

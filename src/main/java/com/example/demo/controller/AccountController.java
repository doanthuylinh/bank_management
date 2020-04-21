/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.ResultBean;
import com.example.demo.service.AccountService;
import com.example.demo.utils.ApiValidateException;

/**
 * [OVERVIEW] Account Controller.
 *
 * @author: (VNEXT)LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/15      (VNEXT)LinhDT      Create new
*/
@RestController
@RequestMapping(value = "/api")
public class AccountController {
    @Autowired
    private AccountService accountService;

    private static final Logger LOGGER = LogManager.getLogger(AccountController.class);

    @RequestMapping(value = "/account", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> addAccount(@RequestBody String entity) {
        LOGGER.info("------addAccount START--------------");
        try {
            accountService.addAccount(entity);
        } catch (ApiValidateException e) {
            return new ResponseEntity<ResultBean>(new ResultBean(e.getCode(), e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResultBean>(new ResultBean("500", "Internal server error"), HttpStatus.BAD_REQUEST);
        }
        LOGGER.info("------addAccount END--------------");
        return new ResponseEntity<ResultBean>(new ResultBean("201", "Insert successfully"), HttpStatus.OK);
    }

    @RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResultBean> getAccount(@PathVariable Integer id) {
        LOGGER.info("------getAccount START--------------");
        ResultBean entity = null;
        try {
            entity = accountService.getAccountById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResultBean>(entity, HttpStatus.BAD_REQUEST);
        }
        LOGGER.info("------getAccount END--------------");
        return new ResponseEntity<ResultBean>(entity, HttpStatus.OK);
    }
}

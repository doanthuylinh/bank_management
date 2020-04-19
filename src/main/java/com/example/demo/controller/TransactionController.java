/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.controller;

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
import com.example.demo.service.TransactionService;
import com.example.demo.utils.ApiValidateException;
import com.example.demo.utils.MessageUtils;

/**
 * [OVERVIEW] Transaction Controller.
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
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/transaction", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> addTransaction(@RequestBody String entity) {
        try {
            transactionService.addTransaction(entity);
        } catch (ApiValidateException e) {
            return new ResponseEntity<ResultBean>(new ResultBean(e.getCode(), e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResultBean>(new ResultBean("500", "Internal server error"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(new ResultBean("201", MessageUtils.getMessage("MSG02", new Object[] { "transaction" })), HttpStatus.OK);
    }

    @RequestMapping(value = "/transaction/list/{userId}", method = RequestMethod.GET)
    public ResponseEntity<ResultBean> getListTransaction(@PathVariable Integer userId) {
        ResultBean entity = null;
        try {
            entity = transactionService.getListTransaction(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResultBean>(entity, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(entity, HttpStatus.OK);
    }

    @RequestMapping(value = "/transaction/list/{userId}/{bankId}", method = RequestMethod.GET)
    public ResponseEntity<ResultBean> getListTransaction(@PathVariable Integer userId, @PathVariable Integer bankId) {
        ResultBean entity = null;
        try {
            entity = transactionService.getListTransaction(userId, bankId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResultBean>(entity, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(entity, HttpStatus.OK);
    }

    @RequestMapping(value = "/transaction/deposit", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> depositTransaction(@RequestBody String entity) {
        try {
            transactionService.deposit(entity);
        } catch (ApiValidateException e) {
            return new ResponseEntity<ResultBean>(new ResultBean(e.getCode(), e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResultBean>(new ResultBean("500", "Internal server error"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(new ResultBean("201", MessageUtils.getMessage("MSG02", new Object[] { "transactions" })), HttpStatus.OK);
    }

    @RequestMapping(value = "/transaction/withdraw", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> withdrawTransaction(@RequestBody String entity) {
        try {
            transactionService.withdraw(entity);
        } catch (ApiValidateException e) {
            return new ResponseEntity<ResultBean>(new ResultBean(e.getCode(), e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResultBean>(new ResultBean("500", "Internal server error"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(new ResultBean("201", MessageUtils.getMessage("MSG02", new Object[] { "transactions" })), HttpStatus.OK);
    }

    @RequestMapping(value = "/transaction/transfer", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> transferTransaction(@RequestBody String entity) {
        try {
            transactionService.transfer(entity);
        } catch (ApiValidateException e) {
            return new ResponseEntity<ResultBean>(new ResultBean(e.getCode(), e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResultBean>(new ResultBean("500", "Internal server error"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(new ResultBean("201", MessageUtils.getMessage("MSG02", new Object[] { "transactions" })), HttpStatus.OK);
    }

}

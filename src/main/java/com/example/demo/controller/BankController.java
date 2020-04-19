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

import com.example.demo.bean.BankEntity;
import com.example.demo.bean.ResultBean;
import com.example.demo.service.BankService;
import com.example.demo.utils.ApiValidateException;
import com.example.demo.utils.MessageUtils;

/**
 * [OVERVIEW] Bank Controller.
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
public class BankController {

    @Autowired
    private BankService bankService;

    @RequestMapping(value = "/bank", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> addBank(@RequestBody String entity) {
        try {
            bankService.addBank(entity);
        } catch (ApiValidateException e) {
            return new ResponseEntity<ResultBean>(new ResultBean(e.getCode(), e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResultBean>(new ResultBean("500", "Internal server error"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(new ResultBean("201", MessageUtils.getMessage("MSG02", new Object[] { "bank" })), HttpStatus.OK);
    }

    @RequestMapping(value = "/bank/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResultBean> getBank(@PathVariable Integer id) {
        ResultBean entity = null;
        try {
            entity = bankService.getBankById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResultBean>(entity, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(entity, HttpStatus.OK);
    }

    @RequestMapping(value = "/bank/{id}", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<String> updateBank(@RequestBody BankEntity entity, @PathVariable Integer id) {

        try {
            entity.setBankId(id);
            ;
            bankService.updateBank(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("update failed", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("update successed", HttpStatus.OK);
    }

}

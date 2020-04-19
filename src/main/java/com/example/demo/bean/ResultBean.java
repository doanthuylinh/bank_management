/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.bean;

import java.io.Serializable;

/**
 * [OVERVIEW] Result Bean.
 *
 * @author: (VNEXT)LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/15      (VNEXT)LinhDT      Create new
*/
public class ResultBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private MetaClass meta;

    private Object data;

    public ResultBean(Object data, String code, String message) {
        this.meta = new ResultBean.MetaClass(code, message);
        this.data = data;
    }

    public ResultBean(String code, String message) {
        this.meta = new ResultBean.MetaClass(code, message);
    }

    public ResultBean(String code, String field, String message) {
        this.meta = new ResultBean.MetaClass(code, field, message);
    }

    public MetaClass getMeta() {
        return meta;
    }

    public void setMeta(MetaClass meta) {
        this.meta = meta;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public class MetaClass implements Serializable {

        private static final long serialVersionUID = 1L;

        private String code;

        private String field;

        private String message;

        public MetaClass(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public MetaClass(String code, String field, String message) {
            this.code = code;
            this.message = message;
            this.field = field;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }
    };
}

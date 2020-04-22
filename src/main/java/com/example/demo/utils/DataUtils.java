/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.utils;

import java.util.Objects;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.google.gson.JsonObject;

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
public class DataUtils {

    /**
     * @author: (VNEXT)LinhDT
     * @return
     */
    public static String getUserIdByToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return currentUserName;
        }
        return null;
    }

    public static boolean isNullWithMemberNameByJson(JsonObject object, String member) throws ApiValidateException {
        if(!object.has(member) || object.has(member) && (object.get(member).equals(null) ||  object.get(member).equals(""))) {
            return true;
        }
        return false;
    }
    
    public static Integer getAsIntegerByJson(JsonObject object, String member) throws ApiValidateException {
        try {
            if (object.has(member)) {
                return object.get(member).getAsInt();
            }
        } catch (Exception e) {
            throw new ApiValidateException("ERR", "JSON " + member + " is invalid.");
        }
        return null;
    }

    public static String getAsStringByJson(JsonObject object, String member) throws ApiValidateException {
        try {
            if (object.has(member)) {
                return object.get(member).getAsString();
            }
        } catch (Exception e) {
            throw new ApiValidateException("ERR", "JSON " + member + " is invalid.");
        }
        return null;
    }

    public static Double getAsDoubleByJson(JsonObject object, String member) throws ApiValidateException {
        try {
            if(object.has(member)) {
                return object.get(member).getAsDouble();
            }
        } catch (Exception e) {
            throw new ApiValidateException("ERR", "JSON " + member + " is invalid.");
        }
        return null;
    }
}

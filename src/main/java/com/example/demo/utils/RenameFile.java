/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * [OVERVIEW] Rename File.
 *
 * @author: (VNEXT)LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/22      (VNEXT)LinhDT       Create new
*/
public class RenameFile {

    /**
     * renameFile
     * @author: (VNEXT)LinhDT
     * @return
     */
    public static String renameFile() {
        String pattern = "yyyyMMddhmmss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        return date;
    }
}

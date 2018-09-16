/**
 *
 */
package com.qslion.core.util;

import com.qslion.framework.util.DBUtils;

import java.sql.ResultSet;


/**
 * 修改备注：
 */

public class TreeTools {
    public static String getTreeCode(String tableName, String columnName, int stepLen, String parentCode) {
        int pLen = parentCode.length();
        String wildcard = "";
        for (int i = 0; i < stepLen; ++i) {
            wildcard = wildcard + "_";
        }

        String strSql = "select MAX(" + columnName + ") maxcode from " + tableName + " t where " + columnName + " like'" + parentCode + wildcard + "'";
        //String maxcode = (String)getCommonBsInstance().doQueryForObject(strSql, new RowMapper() {
        //  public Object mapRow(ResultSet rs, int i) throws SQLException {
        //   return rs.getString("maxcode"); } });
        ResultSet rs = null;
        String maxcode = null;
        try {
            rs = DBUtils.execute(strSql);
            maxcode = rs.getString("maxcode");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String code = null;
        if ((maxcode == null) || (maxcode.length() == 0))
            code = "1";
        else
            code = String.valueOf(Integer.parseInt(maxcode.substring(pLen)) + 1);

        while (code.length() < stepLen)
            code = "0" + code;

        return parentCode + code;
    }

    public static String[] splitTreeCode(String code, int rootLen, int stepLen) {
        if ((code == null) || (stepLen == 0))
            return null;

        int pLen = code.length();
        int arrLen = (pLen - rootLen) / stepLen + 1;
        String[] codeArray = new String[arrLen];
        for (int i = 0; i < arrLen; ++i) {
            code = code.substring(0, pLen);
            codeArray[i] = code;
            pLen = pLen - stepLen;
        }
        return codeArray;
    }

    public static String[] splitTreeCode(String code) {
        return splitTreeCode(code, 19, 5);
    }
/*
      public static ICommonBs getCommonBsInstance() {
	    return ((ICommonBs)Helper.getBean("au_common_bs"));
	  }*/

    public static boolean judgeNum(int bigNum, int smallNum) {
        if (smallNum < 1)
            return false;
        if (smallNum == 1)
            return true;
        for (int i = 1; i * smallNum <= bigNum; ++i)
            if (i * smallNum == bigNum)
                return true;

        return false;
    }

    public static void main(String[] args) {
    }
}

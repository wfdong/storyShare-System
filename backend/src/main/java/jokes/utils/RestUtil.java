package jokes.utils;

import com.alibaba.fastjson.JSON;

import jokes.beans.response.RestfullPage;

public class RestUtil {

	public static final int SUCCESS_CODE = 20000;

    public static final int ERROR_CODE_EXCEPTION = 40001;

    public static final int ERROR_CODE_NO_PERMISSION = 40003;

    public static final int ERROR_CODE_ILLEGAL_TOKEN = 50008;

    public static final int ERROR_CODE_ILLEGAL_INNER_TOKEN = 50014;

    public static final int ERROR_CODE_IN_BLACKLIST = 60001;

    public static final int ERROR_CODE_IN_BONDS = 60002;

    public static final int ERROR_CODE_HAVE_LOAN = 60003;


    public static String toSuccessResult(Object object) {
        return toSuccessResult(null, object, 0);
    }

    public static String toSuccessResult(Object object, int total) {
        return toSuccessResult(null, object, total);
    }

    public static String toSuccessResult(String msg, Object object, int total) {
        RestfullPage restfullPage = new RestfullPage();
        restfullPage.setCode(SUCCESS_CODE);
        restfullPage.setMsg(msg);
        restfullPage.setTotal(total);
        restfullPage.setData(object);


        return JSON.toJSONString(restfullPage);
    }

    public static String toFailedResult(int code, String msg, Object object) {
        RestfullPage restfullPage = new RestfullPage();
        restfullPage.setCode(code);
        restfullPage.setMsg(msg);
        restfullPage.setTotal(0);
        restfullPage.setData(object);


        return JSON.toJSONString(restfullPage);
    }


    public static String toJson(Object object) {
        return JSON.toJSONString(object);
    }
}

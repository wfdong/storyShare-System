package jokes.beans.response;

import java.io.Serializable;

public class RestfullPage implements Response,Serializable {

	private static final long serialVersionUID = 2903977856405524787L;

    private int code;
    
    private long total;
    
    private String msg;
    
    private Object data;
    
    public RestfullPage() {
        
    }
    
    public RestfullPage(int code, int total, String msg, Object data) {
        this.code = code;
        this.total = total;
        this.msg = msg;
        this.data = data;
    }

    
    public int getCode() {
        return code;
    }

    
    public void setCode(int code) {
        this.code = code;
    }

    
    public long getTotal() {
        return total;
    }

    
    public void setTotal(long total) {
        this.total = total;
    }

    
    public String getMsg() {
        return msg;
    }

    
    public void setMsg(String msg) {
        this.msg = msg;
    }

    
    public Object getData() {
        return data;
    }

    
    public void setData(Object data) {
        this.data = data;
    }
}

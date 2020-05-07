package jokes.beans.response;

import java.io.Serializable;

public class LoginResponse implements Response,Serializable {

	private static final long serialVersionUID = 2165834256076260580L;

	// 0 is success, we can extend other codes such as -1, -2 , ....
    int responseCode = 0; 
    
    String token;
    
    String userRole;
    
    String message;
    
    public LoginResponse() {
        
    }

    public LoginResponse(int responseCode, String token, String userRole, String message) {
        this.responseCode = responseCode;
        this.token = token;
        this.userRole = userRole;
        this.message = message;
    }
    
    @Override
    public String toString() {
        return "\"responseCode\":" + responseCode + ", \"token\":\"" + token + "\", \"userRole\":\"" + userRole + "\", \"message\":\"" + message+"\"";
    }
    
    
    public int getResponseCode() {
        return responseCode;
    }

    
    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    
    public String getToken() {
        return token;
    }

    
    public void setToken(String token) {
        this.token = token;
    }

    
    public String getUserRole() {
        return userRole;
    }

    
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    
    public String getMessage() {
        return message;
    }

    
    public void setMessage(String message) {
        this.message = message;
    }
}

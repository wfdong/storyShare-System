package jokes.beans.requests;

import java.io.Serializable;
import java.util.Date;

public class AddedUserInfo implements Serializable {

	private static final long serialVersionUID = -5201138195839388361L;

public AddedUserInfo() {
        
    }
    public AddedUserInfo(String resttoken, String username,String password,String confirmedpassword,String role,String phone,
                         String email, String userType, String realName, 
                         Date updateTime, String originalPassword,Long regionID, String code) {
        this.resttoken = resttoken;
        this.username = username;
        this.password = password;
        this.confirmedpassword = confirmedpassword;
        this.role = role;
        this.phone = phone;
        this.email = email;
        this.userType = userType;
        this.realName = realName;
        this.updateTime = updateTime;
        this.originalPassword = originalPassword;
        this.regionID = regionID;
        this.code = code;
    }
    
    String resttoken;
    String username;
    String password;
    String confirmedpassword;
    String role;
    String phone;
    String email;
    String userType;
    String realName;
    Date updateTime;
    String originalPassword;
    Long regionID;
    String code;
    
    
    
    
    
    
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    public Long getRegionID() {
        return regionID;
    }
    
    public void setRegionID(Long regionID) {
        this.regionID = regionID;
    }
    
    public String getOriginalPassword() {
        return originalPassword;
    }
    
    public void setOriginalPassword(String originalPassword) {
        this.originalPassword = originalPassword;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public String getRealName() {
        return realName;
    }
    
    public void setRealName(String realName) {
        this.realName = realName;
    }
    
    public String getUserType() {
        return userType;
    }
    
    public void setUserType(String userType) {
        this.userType = userType;
    }
    
    public String getResttoken() {
        return resttoken;
    }
    
    public void setResttoken(String resttoken) {
        this.resttoken = resttoken;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getConfirmedpassword() {
        return confirmedpassword;
    }
    
    public void setConfirmedpassword(String confirmedpassword) {
        this.confirmedpassword = confirmedpassword;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
}

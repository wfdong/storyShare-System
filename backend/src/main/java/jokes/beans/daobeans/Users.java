package jokes.beans.daobeans;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;

import org.springframework.stereotype.Component;

@Component
public class Users implements Serializable {

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3348642+10:00", comments = "Source field: users.username")
	private String username;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3358636+10:00", comments = "Source field: users.password")
	private String password;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3358636+10:00", comments = "Source field: users.PHONE")
	private String phone;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3358636+10:00", comments = "Source field: users.EMAIL")
	private String email;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.336863+10:00", comments = "Source field: users.REG_DATE")
	private Date regDate;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.336863+10:00", comments = "Source field: users.UPDATE_DATE")
	private Date updateDate;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.336863+10:00", comments = "Source field: users.user_type")
	private String userType;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.336863+10:00", comments = "Source Table: users")
	private static final long serialVersionUID = 1L;

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3358636+10:00", comments = "Source field: users.username")
	public String getUsername() {
		return username;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3358636+10:00", comments = "Source field: users.username")
	public void setUsername(String username) {
		this.username = username;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3358636+10:00", comments = "Source field: users.password")
	public String getPassword() {
		return password;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3358636+10:00", comments = "Source field: users.password")
	public void setPassword(String password) {
		this.password = password;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3358636+10:00", comments = "Source field: users.PHONE")
	public String getPhone() {
		return phone;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3358636+10:00", comments = "Source field: users.PHONE")
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3358636+10:00", comments = "Source field: users.EMAIL")
	public String getEmail() {
		return email;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3358636+10:00", comments = "Source field: users.EMAIL")
	public void setEmail(String email) {
		this.email = email;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.336863+10:00", comments = "Source field: users.REG_DATE")
	public Date getRegDate() {
		return regDate;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.336863+10:00", comments = "Source field: users.REG_DATE")
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.336863+10:00", comments = "Source field: users.UPDATE_DATE")
	public Date getUpdateDate() {
		return updateDate;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.336863+10:00", comments = "Source field: users.UPDATE_DATE")
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.336863+10:00", comments = "Source field: users.user_type")
	public String getUserType() {
		return userType;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.336863+10:00", comments = "Source field: users.user_type")
	public void setUserType(String userType) {
		this.userType = userType;
	}
}
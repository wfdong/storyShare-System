package jokes.beans.daobeans;

import java.io.Serializable;
import javax.annotation.Generated;

import org.springframework.stereotype.Component;

@Component
public class UserRole implements Serializable {

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3438625+10:00", comments = "Source field: user_role.username")
	private String username;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3438625+10:00", comments = "Source field: user_role.password")
	private String password;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3438625+10:00", comments = "Source field: user_role.role")
	private String role;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3438625+10:00", comments = "Source Table: user_role")
	private static final long serialVersionUID = 1L;

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3438625+10:00", comments = "Source field: user_role.username")
	public String getUsername() {
		return username;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3438625+10:00", comments = "Source field: user_role.username")
	public void setUsername(String username) {
		this.username = username;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3438625+10:00", comments = "Source field: user_role.password")
	public String getPassword() {
		return password;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3438625+10:00", comments = "Source field: user_role.password")
	public void setPassword(String password) {
		this.password = password;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3438625+10:00", comments = "Source field: user_role.role")
	public String getRole() {
		return role;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3438625+10:00", comments = "Source field: user_role.role")
	public void setRole(String role) {
		this.role = role;
	}
}
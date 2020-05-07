package jokes.beans.daobeans;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;

import org.springframework.stereotype.Component;

@Component
public class TokenCache implements Serializable {

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3478637+10:00", comments = "Source field: token_cache.username")
	private String username;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3478637+10:00", comments = "Source field: token_cache.token")
	private String token;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3478637+10:00", comments = "Source field: token_cache.updatetime")
	private Date updatetime;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3488645+10:00", comments = "Source field: token_cache.gmt_created")
	private Date gmtCreated;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3488645+10:00", comments = "Source field: token_cache.gmt_modified")
	private Date gmtModified;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3488645+10:00", comments = "Source Table: token_cache")
	private static final long serialVersionUID = 1L;

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3478637+10:00", comments = "Source field: token_cache.username")
	public String getUsername() {
		return username;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3478637+10:00", comments = "Source field: token_cache.username")
	public void setUsername(String username) {
		this.username = username;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3478637+10:00", comments = "Source field: token_cache.token")
	public String getToken() {
		return token;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3478637+10:00", comments = "Source field: token_cache.token")
	public void setToken(String token) {
		this.token = token;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3488645+10:00", comments = "Source field: token_cache.updatetime")
	public Date getUpdatetime() {
		return updatetime;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3488645+10:00", comments = "Source field: token_cache.updatetime")
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3488645+10:00", comments = "Source field: token_cache.gmt_created")
	public Date getGmtCreated() {
		return gmtCreated;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3488645+10:00", comments = "Source field: token_cache.gmt_created")
	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3488645+10:00", comments = "Source field: token_cache.gmt_modified")
	public Date getGmtModified() {
		return gmtModified;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3488645+10:00", comments = "Source field: token_cache.gmt_modified")
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
}
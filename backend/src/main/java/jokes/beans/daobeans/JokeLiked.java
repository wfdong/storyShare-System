package jokes.beans.daobeans;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;

public class JokeLiked implements Serializable {

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3678612+10:00", comments = "Source field: jokeLiked.username")
	private String username;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.37419+10:00", comments = "Source field: jokeLiked.joketid")
	private String joketid;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3752389+10:00", comments = "Source field: jokeLiked.liked")
	private Integer liked;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3752389+10:00", comments = "Source field: jokeLiked.disliked")
	private Integer disliked;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3752389+10:00", comments = "Source field: jokeLiked.updatetime")
	private Date updatetime;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3752389+10:00", comments = "Source Table: jokeLiked")
	private static final long serialVersionUID = 1L;

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3688615+10:00", comments = "Source field: jokeLiked.username")
	public String getUsername() {
		return username;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3721423+10:00", comments = "Source field: jokeLiked.username")
	public void setUsername(String username) {
		this.username = username;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.37419+10:00", comments = "Source field: jokeLiked.joketid")
	public String getJoketid() {
		return joketid;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.37419+10:00", comments = "Source field: jokeLiked.joketid")
	public void setJoketid(String joketid) {
		this.joketid = joketid;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3752389+10:00", comments = "Source field: jokeLiked.liked")
	public Integer getLiked() {
		return liked;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3752389+10:00", comments = "Source field: jokeLiked.liked")
	public void setLiked(Integer liked) {
		this.liked = liked;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3752389+10:00", comments = "Source field: jokeLiked.disliked")
	public Integer getDisliked() {
		return disliked;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3752389+10:00", comments = "Source field: jokeLiked.disliked")
	public void setDisliked(Integer disliked) {
		this.disliked = disliked;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3752389+10:00", comments = "Source field: jokeLiked.updatetime")
	public Date getUpdatetime() {
		return updatetime;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3752389+10:00", comments = "Source field: jokeLiked.updatetime")
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
}
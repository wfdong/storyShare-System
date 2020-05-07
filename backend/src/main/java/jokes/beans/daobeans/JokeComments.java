package jokes.beans.daobeans;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;

import org.springframework.stereotype.Component;

@Component
public class JokeComments implements Serializable {

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3578613+10:00", comments = "Source field: joke_comments.commentid")
	private String commentid;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3588607+10:00", comments = "Source field: joke_comments.joketid")
	private String joketid;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3588607+10:00", comments = "Source field: joke_comments.username")
	private String username;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3588607+10:00", comments = "Source field: joke_comments.updatetime")
	private Date updatetime;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3588607+10:00", comments = "Source field: joke_comments.comment")
	private String comment;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3588607+10:00", comments = "Source Table: joke_comments")
	private static final long serialVersionUID = 1L;

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3588607+10:00", comments = "Source field: joke_comments.commentid")
	public String getCommentid() {
		return commentid;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3588607+10:00", comments = "Source field: joke_comments.commentid")
	public void setCommentid(String commentid) {
		this.commentid = commentid;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3588607+10:00", comments = "Source field: joke_comments.joketid")
	public String getJoketid() {
		return joketid;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3588607+10:00", comments = "Source field: joke_comments.joketid")
	public void setJoketid(String joketid) {
		this.joketid = joketid;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3588607+10:00", comments = "Source field: joke_comments.username")
	public String getUsername() {
		return username;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3588607+10:00", comments = "Source field: joke_comments.username")
	public void setUsername(String username) {
		this.username = username;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3588607+10:00", comments = "Source field: joke_comments.updatetime")
	public Date getUpdatetime() {
		return updatetime;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3588607+10:00", comments = "Source field: joke_comments.updatetime")
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3588607+10:00", comments = "Source field: joke_comments.comment")
	public String getComment() {
		return comment;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3588607+10:00", comments = "Source field: joke_comments.comment")
	public void setComment(String comment) {
		this.comment = comment;
	}
}
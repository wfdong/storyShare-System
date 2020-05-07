package jokes.beans.daobeans;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;

import org.springframework.stereotype.Component;

@Component
public class JokeTable implements Serializable {

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3528609+10:00", comments = "Source field: joke_table.tid")
	private String tid;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3538612+10:00", comments = "Source field: joke_table.username")
	private String username;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3538612+10:00", comments = "Source field: joke_table.filelocation")
	private String filelocation;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3538612+10:00", comments = "Source field: joke_table.liked")
	private Integer liked;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3538612+10:00", comments = "Source field: joke_table.disliked")
	private Integer disliked;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3538612+10:00", comments = "Source field: joke_table.commentsnum")
	private Integer commentsnum;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3538612+10:00", comments = "Source field: joke_table.uploadtime")
	private Date uploadtime;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3548611+10:00", comments = "Source field: joke_table.content")
	private String content;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3548611+10:00", comments = "Source Table: joke_table")
	private static final long serialVersionUID = 1L;

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3528609+10:00", comments = "Source field: joke_table.tid")
	public String getTid() {
		return tid;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3538612+10:00", comments = "Source field: joke_table.tid")
	public void setTid(String tid) {
		this.tid = tid;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3538612+10:00", comments = "Source field: joke_table.username")
	public String getUsername() {
		return username;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3538612+10:00", comments = "Source field: joke_table.username")
	public void setUsername(String username) {
		this.username = username;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3538612+10:00", comments = "Source field: joke_table.filelocation")
	public String getFilelocation() {
		return filelocation;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3538612+10:00", comments = "Source field: joke_table.filelocation")
	public void setFilelocation(String filelocation) {
		this.filelocation = filelocation;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3538612+10:00", comments = "Source field: joke_table.liked")
	public Integer getLiked() {
		return liked;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3538612+10:00", comments = "Source field: joke_table.liked")
	public void setLiked(Integer liked) {
		this.liked = liked;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3538612+10:00", comments = "Source field: joke_table.disliked")
	public Integer getDisliked() {
		return disliked;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3538612+10:00", comments = "Source field: joke_table.disliked")
	public void setDisliked(Integer disliked) {
		this.disliked = disliked;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3538612+10:00", comments = "Source field: joke_table.commentsnum")
	public Integer getCommentsnum() {
		return commentsnum;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3538612+10:00", comments = "Source field: joke_table.commentsnum")
	public void setCommentsnum(Integer commentsnum) {
		this.commentsnum = commentsnum;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3538612+10:00", comments = "Source field: joke_table.uploadtime")
	public Date getUploadtime() {
		return uploadtime;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3548611+10:00", comments = "Source field: joke_table.uploadtime")
	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3548611+10:00", comments = "Source field: joke_table.content")
	public String getContent() {
		return content;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3548611+10:00", comments = "Source field: joke_table.content")
	public void setContent(String content) {
		this.content = content;
	}
}
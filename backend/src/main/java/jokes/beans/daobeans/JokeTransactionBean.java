package jokes.beans.daobeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import jokes.beans.daobeans.UserFile;
import com.fasterxml.jackson.annotation.JsonFormat;

@Component
public class JokeTransactionBean implements Serializable{

	private static final long serialVersionUID = -3002504119217506396L;
	
	private String tid;
	
	private String username;
	
	private String content;
	
	private String filelocation;
	
	private int liked;
	
	private int disliked;
	
	private int commentsnum;
	
	private Date uploadtime;
	
	private String newAddedComment;
	
//	@JsonFormat
//	private List<UserFile> uploadFileList = new ArrayList<UserFile>();
	
	public String getNewAddedComment() {
		return newAddedComment;
	}

	public void setNewAddedComment(String newAddedComment) {
		this.newAddedComment = newAddedComment;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFilelocation() {
		return filelocation;
	}

	public void setFilelocation(String filelocation) {
		this.filelocation = filelocation;
	}

	public int getLiked() {
		return liked;
	}

	public void setLiked(int liked) {
		this.liked = liked;
	}

	public int getDisliked() {
		return disliked;
	}

	public void setDisliked(int disliked) {
		this.disliked = disliked;
	}

	public int getCommentsnum() {
		return commentsnum;
	}

	public void setCommentsnum(int commentsnum) {
		this.commentsnum = commentsnum;
	}

	public Date getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
	}

	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

//	public List<UserFile> getUploadFileList() {
//		return uploadFileList;
//	}
//
//	public void setUploadFileList(List<UserFile> uploadFileList) {
//		this.uploadFileList = uploadFileList;
//	}

}

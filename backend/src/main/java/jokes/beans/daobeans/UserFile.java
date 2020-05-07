package jokes.beans.daobeans;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;

import org.springframework.stereotype.Component;

@Component
public class UserFile implements Serializable {

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3628631+10:00", comments = "Source field: user_file.id")
	private Long id;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3628631+10:00", comments = "Source field: user_file.file_path")
	private String filePath;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3628631+10:00", comments = "Source field: user_file.foreign_id")
	private String foreignId;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3628631+10:00", comments = "Source field: user_file.foreign_key")
	private String foreignKey;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3638613+10:00", comments = "Source field: user_file.gmt_created")
	private Date gmtCreated;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3638613+10:00", comments = "Source field: user_file.gmt_modified")
	private Date gmtModified;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3638613+10:00", comments = "Source Table: user_file")
	private static final long serialVersionUID = 1L;

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3628631+10:00", comments = "Source field: user_file.id")
	public Long getId() {
		return id;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3628631+10:00", comments = "Source field: user_file.id")
	public void setId(Long id) {
		this.id = id;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3628631+10:00", comments = "Source field: user_file.file_path")
	public String getFilePath() {
		return filePath;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3628631+10:00", comments = "Source field: user_file.file_path")
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3628631+10:00", comments = "Source field: user_file.foreign_id")
	public String getForeignId() {
		return foreignId;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3628631+10:00", comments = "Source field: user_file.foreign_id")
	public void setForeignId(String foreignId) {
		this.foreignId = foreignId;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3628631+10:00", comments = "Source field: user_file.foreign_key")
	public String getForeignKey() {
		return foreignKey;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3628631+10:00", comments = "Source field: user_file.foreign_key")
	public void setForeignKey(String foreignKey) {
		this.foreignKey = foreignKey;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3638613+10:00", comments = "Source field: user_file.gmt_created")
	public Date getGmtCreated() {
		return gmtCreated;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3638613+10:00", comments = "Source field: user_file.gmt_created")
	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3638613+10:00", comments = "Source field: user_file.gmt_modified")
	public Date getGmtModified() {
		return gmtModified;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3638613+10:00", comments = "Source field: user_file.gmt_modified")
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
}
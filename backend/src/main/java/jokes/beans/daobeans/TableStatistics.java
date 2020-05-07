package jokes.beans.daobeans;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;

public class TableStatistics implements Serializable {

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3778672+10:00", comments = "Source field: table_statistics.id")
	private Long id;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3778672+10:00", comments = "Source field: table_statistics.fromaddress")
	private String fromaddress;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.4138596+10:00", comments = "Source field: table_statistics.time")
	private Date time;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.4148624+10:00", comments = "Source Table: table_statistics")
	private static final long serialVersionUID = 1L;

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3778672+10:00", comments = "Source field: table_statistics.id")
	public Long getId() {
		return id;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3778672+10:00", comments = "Source field: table_statistics.id")
	public void setId(Long id) {
		this.id = id;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3778672+10:00", comments = "Source field: table_statistics.fromaddress")
	public String getFromaddress() {
		return fromaddress;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.4138596+10:00", comments = "Source field: table_statistics.fromaddress")
	public void setFromaddress(String fromaddress) {
		this.fromaddress = fromaddress;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.4138596+10:00", comments = "Source field: table_statistics.time")
	public Date getTime() {
		return time;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.4148624+10:00", comments = "Source field: table_statistics.time")
	public void setTime(Date time) {
		this.time = time;
	}
}
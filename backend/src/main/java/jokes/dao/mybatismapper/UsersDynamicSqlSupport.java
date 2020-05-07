package jokes.dao.mybatismapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UsersDynamicSqlSupport {

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3388633+10:00", comments = "Source Table: users")
	public static final Users users = new Users();
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3388633+10:00", comments = "Source field: users.username")
	public static final SqlColumn<String> username = users.username;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3388633+10:00", comments = "Source field: users.password")
	public static final SqlColumn<String> password = users.password;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3388633+10:00", comments = "Source field: users.PHONE")
	public static final SqlColumn<String> phone = users.phone;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3398619+10:00", comments = "Source field: users.EMAIL")
	public static final SqlColumn<String> email = users.email;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3398619+10:00", comments = "Source field: users.REG_DATE")
	public static final SqlColumn<Date> regDate = users.regDate;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3398619+10:00", comments = "Source field: users.UPDATE_DATE")
	public static final SqlColumn<Date> updateDate = users.updateDate;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3398619+10:00", comments = "Source field: users.user_type")
	public static final SqlColumn<String> userType = users.userType;

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3388633+10:00", comments = "Source Table: users")
	public static final class Users extends SqlTable {
		public final SqlColumn<String> username = column("username", JDBCType.VARCHAR);
		public final SqlColumn<String> password = column("password", JDBCType.VARCHAR);
		public final SqlColumn<String> phone = column("PHONE", JDBCType.VARCHAR);
		public final SqlColumn<String> email = column("EMAIL", JDBCType.VARCHAR);
		public final SqlColumn<Date> regDate = column("REG_DATE", JDBCType.TIMESTAMP);
		public final SqlColumn<Date> updateDate = column("UPDATE_DATE", JDBCType.TIMESTAMP);
		public final SqlColumn<String> userType = column("user_type", JDBCType.VARCHAR);

		public Users() {
			super("users");
		}
	}
}
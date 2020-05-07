package jokes.dao.mybatismapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UserRoleDynamicSqlSupport {

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3438625+10:00", comments = "Source Table: user_role")
	public static final UserRole userRole = new UserRole();
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3448634+10:00", comments = "Source field: user_role.username")
	public static final SqlColumn<String> username = userRole.username;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3448634+10:00", comments = "Source field: user_role.password")
	public static final SqlColumn<String> password = userRole.password;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3448634+10:00", comments = "Source field: user_role.role")
	public static final SqlColumn<String> role = userRole.role;

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3438625+10:00", comments = "Source Table: user_role")
	public static final class UserRole extends SqlTable {
		public final SqlColumn<String> username = column("username", JDBCType.VARCHAR);
		public final SqlColumn<String> password = column("password", JDBCType.VARCHAR);
		public final SqlColumn<String> role = column("role", JDBCType.VARCHAR);

		public UserRole() {
			super("user_role");
		}
	}
}
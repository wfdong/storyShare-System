package jokes.dao.mybatismapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UserFileDynamicSqlSupport {

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3638613+10:00", comments = "Source Table: user_file")
	public static final UserFile userFile = new UserFile();
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3648638+10:00", comments = "Source field: user_file.id")
	public static final SqlColumn<Long> id = userFile.id;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3648638+10:00", comments = "Source field: user_file.file_path")
	public static final SqlColumn<String> filePath = userFile.filePath;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3648638+10:00", comments = "Source field: user_file.foreign_id")
	public static final SqlColumn<String> foreignId = userFile.foreignId;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3648638+10:00", comments = "Source field: user_file.foreign_key")
	public static final SqlColumn<String> foreignKey = userFile.foreignKey;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.365863+10:00", comments = "Source field: user_file.gmt_created")
	public static final SqlColumn<Date> gmtCreated = userFile.gmtCreated;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.365863+10:00", comments = "Source field: user_file.gmt_modified")
	public static final SqlColumn<Date> gmtModified = userFile.gmtModified;

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3638613+10:00", comments = "Source Table: user_file")
	public static final class UserFile extends SqlTable {
		public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);
		public final SqlColumn<String> filePath = column("file_path", JDBCType.VARCHAR);
		public final SqlColumn<String> foreignId = column("foreign_id", JDBCType.VARCHAR);
		public final SqlColumn<String> foreignKey = column("foreign_key", JDBCType.VARCHAR);
		public final SqlColumn<Date> gmtCreated = column("gmt_created", JDBCType.TIMESTAMP);
		public final SqlColumn<Date> gmtModified = column("gmt_modified", JDBCType.TIMESTAMP);

		public UserFile() {
			super("user_file");
		}
	}
}
package jokes.dao.mybatismapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class TableStatisticsDynamicSqlSupport {

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.4148624+10:00", comments = "Source Table: table_statistics")
	public static final TableStatistics tableStatistics = new TableStatistics();
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.4148624+10:00", comments = "Source field: table_statistics.id")
	public static final SqlColumn<Long> id = tableStatistics.id;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.4148624+10:00", comments = "Source field: table_statistics.fromaddress")
	public static final SqlColumn<String> fromaddress = tableStatistics.fromaddress;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.4148624+10:00", comments = "Source field: table_statistics.time")
	public static final SqlColumn<Date> time = tableStatistics.time;

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.4148624+10:00", comments = "Source Table: table_statistics")
	public static final class TableStatistics extends SqlTable {
		public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);
		public final SqlColumn<String> fromaddress = column("fromaddress", JDBCType.VARCHAR);
		public final SqlColumn<Date> time = column("time", JDBCType.TIMESTAMP);

		public TableStatistics() {
			super("table_statistics");
		}
	}
}
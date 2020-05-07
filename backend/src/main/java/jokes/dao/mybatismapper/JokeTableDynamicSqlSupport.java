package jokes.dao.mybatismapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class JokeTableDynamicSqlSupport {

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3548611+10:00", comments = "Source Table: joke_table")
	public static final JokeTable jokeTable = new JokeTable();
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3548611+10:00", comments = "Source field: joke_table.tid")
	public static final SqlColumn<String> tid = jokeTable.tid;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3548611+10:00", comments = "Source field: joke_table.username")
	public static final SqlColumn<String> username = jokeTable.username;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3548611+10:00", comments = "Source field: joke_table.filelocation")
	public static final SqlColumn<String> filelocation = jokeTable.filelocation;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3548611+10:00", comments = "Source field: joke_table.liked")
	public static final SqlColumn<Integer> liked = jokeTable.liked;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3558603+10:00", comments = "Source field: joke_table.disliked")
	public static final SqlColumn<Integer> disliked = jokeTable.disliked;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3558603+10:00", comments = "Source field: joke_table.commentsnum")
	public static final SqlColumn<Integer> commentsnum = jokeTable.commentsnum;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3558603+10:00", comments = "Source field: joke_table.uploadtime")
	public static final SqlColumn<Date> uploadtime = jokeTable.uploadtime;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3558603+10:00", comments = "Source field: joke_table.content")
	public static final SqlColumn<String> content = jokeTable.content;

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3548611+10:00", comments = "Source Table: joke_table")
	public static final class JokeTable extends SqlTable {
		public final SqlColumn<String> tid = column("tid", JDBCType.VARCHAR);
		public final SqlColumn<String> username = column("username", JDBCType.VARCHAR);
		public final SqlColumn<String> filelocation = column("filelocation", JDBCType.VARCHAR);
		public final SqlColumn<Integer> liked = column("liked", JDBCType.INTEGER);
		public final SqlColumn<Integer> disliked = column("disliked", JDBCType.INTEGER);
		public final SqlColumn<Integer> commentsnum = column("commentsnum", JDBCType.INTEGER);
		public final SqlColumn<Date> uploadtime = column("uploadtime", JDBCType.TIMESTAMP);
		public final SqlColumn<String> content = column("content", JDBCType.LONGVARCHAR);

		public JokeTable() {
			super("joke_table");
		}
	}
}
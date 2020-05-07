package jokes.dao.mybatismapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class JokeCommentsDynamicSqlSupport {

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3588607+10:00", comments = "Source Table: joke_comments")
	public static final JokeComments jokeComments = new JokeComments();
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3598615+10:00", comments = "Source field: joke_comments.commentid")
	public static final SqlColumn<String> commentid = jokeComments.commentid;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3598615+10:00", comments = "Source field: joke_comments.joketid")
	public static final SqlColumn<String> joketid = jokeComments.joketid;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3598615+10:00", comments = "Source field: joke_comments.username")
	public static final SqlColumn<String> username = jokeComments.username;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3598615+10:00", comments = "Source field: joke_comments.updatetime")
	public static final SqlColumn<Date> updatetime = jokeComments.updatetime;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3598615+10:00", comments = "Source field: joke_comments.comment")
	public static final SqlColumn<String> comment = jokeComments.comment;

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3598615+10:00", comments = "Source Table: joke_comments")
	public static final class JokeComments extends SqlTable {
		public final SqlColumn<String> commentid = column("commentid", JDBCType.VARCHAR);
		public final SqlColumn<String> joketid = column("joketid", JDBCType.VARCHAR);
		public final SqlColumn<String> username = column("username", JDBCType.VARCHAR);
		public final SqlColumn<Date> updatetime = column("updatetime", JDBCType.TIMESTAMP);
		public final SqlColumn<String> comment = column("comment", JDBCType.LONGVARCHAR);

		public JokeComments() {
			super("joke_comments");
		}
	}
}
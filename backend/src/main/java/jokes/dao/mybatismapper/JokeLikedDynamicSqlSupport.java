package jokes.dao.mybatismapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class JokeLikedDynamicSqlSupport {

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3752389+10:00", comments = "Source Table: jokeLiked")
	public static final JokeLiked jokeLiked = new JokeLiked();
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3752389+10:00", comments = "Source field: jokeLiked.username")
	public static final SqlColumn<String> username = jokeLiked.username;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3758624+10:00", comments = "Source field: jokeLiked.joketid")
	public static final SqlColumn<String> joketid = jokeLiked.joketid;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3758624+10:00", comments = "Source field: jokeLiked.liked")
	public static final SqlColumn<Integer> liked = jokeLiked.liked;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3758624+10:00", comments = "Source field: jokeLiked.disliked")
	public static final SqlColumn<Integer> disliked = jokeLiked.disliked;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3758624+10:00", comments = "Source field: jokeLiked.updatetime")
	public static final SqlColumn<Date> updatetime = jokeLiked.updatetime;

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3752389+10:00", comments = "Source Table: jokeLiked")
	public static final class JokeLiked extends SqlTable {
		public final SqlColumn<String> username = column("username", JDBCType.VARCHAR);
		public final SqlColumn<String> joketid = column("joketid", JDBCType.VARCHAR);
		public final SqlColumn<Integer> liked = column("liked", JDBCType.INTEGER);
		public final SqlColumn<Integer> disliked = column("disliked", JDBCType.INTEGER);
		public final SqlColumn<Date> updatetime = column("updatetime", JDBCType.TIMESTAMP);

		public JokeLiked() {
			super("jokeLiked");
		}
	}
}
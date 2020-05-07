package jokes.dao.mybatismapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class TokenCacheDynamicSqlSupport {

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3488645+10:00", comments = "Source Table: token_cache")
	public static final TokenCache tokenCache = new TokenCache();
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.349862+10:00", comments = "Source field: token_cache.username")
	public static final SqlColumn<String> username = tokenCache.username;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.349862+10:00", comments = "Source field: token_cache.token")
	public static final SqlColumn<String> token = tokenCache.token;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.349862+10:00", comments = "Source field: token_cache.updatetime")
	public static final SqlColumn<Date> updatetime = tokenCache.updatetime;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.349862+10:00", comments = "Source field: token_cache.gmt_created")
	public static final SqlColumn<Date> gmtCreated = tokenCache.gmtCreated;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.349862+10:00", comments = "Source field: token_cache.gmt_modified")
	public static final SqlColumn<Date> gmtModified = tokenCache.gmtModified;

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3488645+10:00", comments = "Source Table: token_cache")
	public static final class TokenCache extends SqlTable {
		public final SqlColumn<String> username = column("username", JDBCType.VARCHAR);
		public final SqlColumn<String> token = column("token", JDBCType.VARCHAR);
		public final SqlColumn<Date> updatetime = column("updatetime", JDBCType.TIMESTAMP);
		public final SqlColumn<Date> gmtCreated = column("gmt_created", JDBCType.TIMESTAMP);
		public final SqlColumn<Date> gmtModified = column("gmt_modified", JDBCType.TIMESTAMP);

		public TokenCache() {
			super("token_cache");
		}
	}
}
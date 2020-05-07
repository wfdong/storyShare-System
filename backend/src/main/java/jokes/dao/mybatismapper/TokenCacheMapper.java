package jokes.dao.mybatismapper;

import static jokes.dao.mybatismapper.TokenCacheDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import jokes.beans.daobeans.TokenCache;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface TokenCacheMapper {

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3518608+10:00", comments = "Source Table: token_cache")
	BasicColumn[] selectList = BasicColumn.columnList(username, token, updatetime, gmtCreated, gmtModified);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.349862+10:00", comments = "Source Table: token_cache")
	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	long count(SelectStatementProvider selectStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.349862+10:00", comments = "Source Table: token_cache")
	@DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
	int delete(DeleteStatementProvider deleteStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.349862+10:00", comments = "Source Table: token_cache")
	@InsertProvider(type = SqlProviderAdapter.class, method = "insert")
	int insert(InsertStatementProvider<TokenCache> insertStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3508609+10:00", comments = "Source Table: token_cache")
	@InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
	int insertMultiple(MultiRowInsertStatementProvider<TokenCache> multipleInsertStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3508609+10:00", comments = "Source Table: token_cache")
	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@ResultMap("TokenCacheResult")
	Optional<TokenCache> selectOne(SelectStatementProvider selectStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3508609+10:00", comments = "Source Table: token_cache")
	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@Results(id = "TokenCacheResult", value = {
			@Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR, id = true),
			@Result(column = "token", property = "token", jdbcType = JdbcType.VARCHAR),
			@Result(column = "updatetime", property = "updatetime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "gmt_created", property = "gmtCreated", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "gmt_modified", property = "gmtModified", jdbcType = JdbcType.TIMESTAMP) })
	List<TokenCache> selectMany(SelectStatementProvider selectStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3508609+10:00", comments = "Source Table: token_cache")
	@UpdateProvider(type = SqlProviderAdapter.class, method = "update")
	int update(UpdateStatementProvider updateStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3508609+10:00", comments = "Source Table: token_cache")
	default long count(CountDSLCompleter completer) {
		return MyBatis3Utils.countFrom(this::count, tokenCache, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3508609+10:00", comments = "Source Table: token_cache")
	default int delete(DeleteDSLCompleter completer) {
		return MyBatis3Utils.deleteFrom(this::delete, tokenCache, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3508609+10:00", comments = "Source Table: token_cache")
	default int deleteByPrimaryKey(String username_) {
		return delete(c -> c.where(username, isEqualTo(username_)));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3508609+10:00", comments = "Source Table: token_cache")
	default int insert(TokenCache record) {
		return MyBatis3Utils.insert(this::insert, record, tokenCache,
				c -> c.map(username).toProperty("username").map(token).toProperty("token").map(updatetime)
						.toProperty("updatetime").map(gmtCreated).toProperty("gmtCreated").map(gmtModified)
						.toProperty("gmtModified"));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3508609+10:00", comments = "Source Table: token_cache")
	default int insertMultiple(Collection<TokenCache> records) {
		return MyBatis3Utils.insertMultiple(this::insertMultiple, records, tokenCache,
				c -> c.map(username).toProperty("username").map(token).toProperty("token").map(updatetime)
						.toProperty("updatetime").map(gmtCreated).toProperty("gmtCreated").map(gmtModified)
						.toProperty("gmtModified"));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3508609+10:00", comments = "Source Table: token_cache")
	default int insertSelective(TokenCache record) {
		return MyBatis3Utils.insert(this::insert, record, tokenCache,
				c -> c.map(username).toPropertyWhenPresent("username", record::getUsername).map(token)
						.toPropertyWhenPresent("token", record::getToken).map(updatetime)
						.toPropertyWhenPresent("updatetime", record::getUpdatetime).map(gmtCreated)
						.toPropertyWhenPresent("gmtCreated", record::getGmtCreated).map(gmtModified)
						.toPropertyWhenPresent("gmtModified", record::getGmtModified));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3518608+10:00", comments = "Source Table: token_cache")
	default Optional<TokenCache> selectOne(SelectDSLCompleter completer) {
		return MyBatis3Utils.selectOne(this::selectOne, selectList, tokenCache, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3518608+10:00", comments = "Source Table: token_cache")
	default List<TokenCache> select(SelectDSLCompleter completer) {
		return MyBatis3Utils.selectList(this::selectMany, selectList, tokenCache, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3518608+10:00", comments = "Source Table: token_cache")
	default List<TokenCache> selectDistinct(SelectDSLCompleter completer) {
		return MyBatis3Utils.selectDistinct(this::selectMany, selectList, tokenCache, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3518608+10:00", comments = "Source Table: token_cache")
	default Optional<TokenCache> selectByPrimaryKey(String username_) {
		return selectOne(c -> c.where(username, isEqualTo(username_)));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3518608+10:00", comments = "Source Table: token_cache")
	default int update(UpdateDSLCompleter completer) {
		return MyBatis3Utils.update(this::update, tokenCache, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3518608+10:00", comments = "Source Table: token_cache")
	static UpdateDSL<UpdateModel> updateAllColumns(TokenCache record, UpdateDSL<UpdateModel> dsl) {
		return dsl.set(username).equalTo(record::getUsername).set(token).equalTo(record::getToken).set(updatetime)
				.equalTo(record::getUpdatetime).set(gmtCreated).equalTo(record::getGmtCreated).set(gmtModified)
				.equalTo(record::getGmtModified);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3518608+10:00", comments = "Source Table: token_cache")
	static UpdateDSL<UpdateModel> updateSelectiveColumns(TokenCache record, UpdateDSL<UpdateModel> dsl) {
		return dsl.set(username).equalToWhenPresent(record::getUsername).set(token).equalToWhenPresent(record::getToken)
				.set(updatetime).equalToWhenPresent(record::getUpdatetime).set(gmtCreated)
				.equalToWhenPresent(record::getGmtCreated).set(gmtModified).equalToWhenPresent(record::getGmtModified);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3528609+10:00", comments = "Source Table: token_cache")
	default int updateByPrimaryKey(TokenCache record) {
		return update(c -> c.set(token).equalTo(record::getToken).set(updatetime).equalTo(record::getUpdatetime)
				.set(gmtCreated).equalTo(record::getGmtCreated).set(gmtModified).equalTo(record::getGmtModified)
				.where(username, isEqualTo(record::getUsername)));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3528609+10:00", comments = "Source Table: token_cache")
	default int updateByPrimaryKeySelective(TokenCache record) {
		return update(c -> c.set(token).equalToWhenPresent(record::getToken).set(updatetime)
				.equalToWhenPresent(record::getUpdatetime).set(gmtCreated).equalToWhenPresent(record::getGmtCreated)
				.set(gmtModified).equalToWhenPresent(record::getGmtModified)
				.where(username, isEqualTo(record::getUsername)));
	}
}
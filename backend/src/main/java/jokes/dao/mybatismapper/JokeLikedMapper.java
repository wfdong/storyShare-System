package jokes.dao.mybatismapper;

import static jokes.dao.mybatismapper.JokeLikedDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import jokes.beans.daobeans.JokeLiked;
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
public interface JokeLikedMapper {

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3768652+10:00", comments = "Source Table: jokeLiked")
	BasicColumn[] selectList = BasicColumn.columnList(username, joketid, liked, disliked, updatetime);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3758624+10:00", comments = "Source Table: jokeLiked")
	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	long count(SelectStatementProvider selectStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3758624+10:00", comments = "Source Table: jokeLiked")
	@DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
	int delete(DeleteStatementProvider deleteStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3758624+10:00", comments = "Source Table: jokeLiked")
	@InsertProvider(type = SqlProviderAdapter.class, method = "insert")
	int insert(InsertStatementProvider<JokeLiked> insertStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3758624+10:00", comments = "Source Table: jokeLiked")
	@InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
	int insertMultiple(MultiRowInsertStatementProvider<JokeLiked> multipleInsertStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3758624+10:00", comments = "Source Table: jokeLiked")
	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@ResultMap("JokeLikedResult")
	Optional<JokeLiked> selectOne(SelectStatementProvider selectStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3758624+10:00", comments = "Source Table: jokeLiked")
	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@Results(id = "JokeLikedResult", value = {
			@Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR, id = true),
			@Result(column = "joketid", property = "joketid", jdbcType = JdbcType.VARCHAR, id = true),
			@Result(column = "liked", property = "liked", jdbcType = JdbcType.INTEGER),
			@Result(column = "disliked", property = "disliked", jdbcType = JdbcType.INTEGER),
			@Result(column = "updatetime", property = "updatetime", jdbcType = JdbcType.TIMESTAMP) })
	List<JokeLiked> selectMany(SelectStatementProvider selectStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3758624+10:00", comments = "Source Table: jokeLiked")
	@UpdateProvider(type = SqlProviderAdapter.class, method = "update")
	int update(UpdateStatementProvider updateStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3758624+10:00", comments = "Source Table: jokeLiked")
	default long count(CountDSLCompleter completer) {
		return MyBatis3Utils.countFrom(this::count, jokeLiked, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3758624+10:00", comments = "Source Table: jokeLiked")
	default int delete(DeleteDSLCompleter completer) {
		return MyBatis3Utils.deleteFrom(this::delete, jokeLiked, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3758624+10:00", comments = "Source Table: jokeLiked")
	default int deleteByPrimaryKey(String username_, String joketid_) {
		return delete(c -> c.where(username, isEqualTo(username_)).and(joketid, isEqualTo(joketid_)));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3758624+10:00", comments = "Source Table: jokeLiked")
	default int insert(JokeLiked record) {
		return MyBatis3Utils.insert(this::insert, record, jokeLiked,
				c -> c.map(username).toProperty("username").map(joketid).toProperty("joketid").map(liked)
						.toProperty("liked").map(disliked).toProperty("disliked").map(updatetime)
						.toProperty("updatetime"));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3768652+10:00", comments = "Source Table: jokeLiked")
	default int insertMultiple(Collection<JokeLiked> records) {
		return MyBatis3Utils.insertMultiple(this::insertMultiple, records, jokeLiked,
				c -> c.map(username).toProperty("username").map(joketid).toProperty("joketid").map(liked)
						.toProperty("liked").map(disliked).toProperty("disliked").map(updatetime)
						.toProperty("updatetime"));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3768652+10:00", comments = "Source Table: jokeLiked")
	default int insertSelective(JokeLiked record) {
		return MyBatis3Utils.insert(this::insert, record, jokeLiked,
				c -> c.map(username).toPropertyWhenPresent("username", record::getUsername).map(joketid)
						.toPropertyWhenPresent("joketid", record::getJoketid).map(liked)
						.toPropertyWhenPresent("liked", record::getLiked).map(disliked)
						.toPropertyWhenPresent("disliked", record::getDisliked).map(updatetime)
						.toPropertyWhenPresent("updatetime", record::getUpdatetime));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3768652+10:00", comments = "Source Table: jokeLiked")
	default Optional<JokeLiked> selectOne(SelectDSLCompleter completer) {
		return MyBatis3Utils.selectOne(this::selectOne, selectList, jokeLiked, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3768652+10:00", comments = "Source Table: jokeLiked")
	default List<JokeLiked> select(SelectDSLCompleter completer) {
		return MyBatis3Utils.selectList(this::selectMany, selectList, jokeLiked, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3768652+10:00", comments = "Source Table: jokeLiked")
	default List<JokeLiked> selectDistinct(SelectDSLCompleter completer) {
		return MyBatis3Utils.selectDistinct(this::selectMany, selectList, jokeLiked, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3768652+10:00", comments = "Source Table: jokeLiked")
	default Optional<JokeLiked> selectByPrimaryKey(String username_, String joketid_) {
		return selectOne(c -> c.where(username, isEqualTo(username_)).and(joketid, isEqualTo(joketid_)));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3768652+10:00", comments = "Source Table: jokeLiked")
	default int update(UpdateDSLCompleter completer) {
		return MyBatis3Utils.update(this::update, jokeLiked, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3768652+10:00", comments = "Source Table: jokeLiked")
	static UpdateDSL<UpdateModel> updateAllColumns(JokeLiked record, UpdateDSL<UpdateModel> dsl) {
		return dsl.set(username).equalTo(record::getUsername).set(joketid).equalTo(record::getJoketid).set(liked)
				.equalTo(record::getLiked).set(disliked).equalTo(record::getDisliked).set(updatetime)
				.equalTo(record::getUpdatetime);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3768652+10:00", comments = "Source Table: jokeLiked")
	static UpdateDSL<UpdateModel> updateSelectiveColumns(JokeLiked record, UpdateDSL<UpdateModel> dsl) {
		return dsl.set(username).equalToWhenPresent(record::getUsername).set(joketid)
				.equalToWhenPresent(record::getJoketid).set(liked).equalToWhenPresent(record::getLiked).set(disliked)
				.equalToWhenPresent(record::getDisliked).set(updatetime).equalToWhenPresent(record::getUpdatetime);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3768652+10:00", comments = "Source Table: jokeLiked")
	default int updateByPrimaryKey(JokeLiked record) {
		return update(c -> c.set(liked).equalTo(record::getLiked).set(disliked).equalTo(record::getDisliked)
				.set(updatetime).equalTo(record::getUpdatetime).where(username, isEqualTo(record::getUsername))
				.and(joketid, isEqualTo(record::getJoketid)));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3768652+10:00", comments = "Source Table: jokeLiked")
	default int updateByPrimaryKeySelective(JokeLiked record) {
		return update(c -> c.set(liked).equalToWhenPresent(record::getLiked).set(disliked)
				.equalToWhenPresent(record::getDisliked).set(updatetime).equalToWhenPresent(record::getUpdatetime)
				.where(username, isEqualTo(record::getUsername)).and(joketid, isEqualTo(record::getJoketid)));
	}
}
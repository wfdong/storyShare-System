package jokes.dao.mybatismapper;

import static jokes.dao.mybatismapper.JokeCommentsDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import jokes.beans.daobeans.JokeComments;
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
public interface JokeCommentsMapper {

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.360863+10:00", comments = "Source Table: joke_comments")
	BasicColumn[] selectList = BasicColumn.columnList(commentid, joketid, username, updatetime, comment);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3598615+10:00", comments = "Source Table: joke_comments")
	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	long count(SelectStatementProvider selectStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3598615+10:00", comments = "Source Table: joke_comments")
	@DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
	int delete(DeleteStatementProvider deleteStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3598615+10:00", comments = "Source Table: joke_comments")
	@InsertProvider(type = SqlProviderAdapter.class, method = "insert")
	int insert(InsertStatementProvider<JokeComments> insertStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.360863+10:00", comments = "Source Table: joke_comments")
	@InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
	int insertMultiple(MultiRowInsertStatementProvider<JokeComments> multipleInsertStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.360863+10:00", comments = "Source Table: joke_comments")
	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@ResultMap("JokeCommentsResult")
	Optional<JokeComments> selectOne(SelectStatementProvider selectStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.360863+10:00", comments = "Source Table: joke_comments")
	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@Results(id = "JokeCommentsResult", value = {
			@Result(column = "commentid", property = "commentid", jdbcType = JdbcType.VARCHAR, id = true),
			@Result(column = "joketid", property = "joketid", jdbcType = JdbcType.VARCHAR, id = true),
			@Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
			@Result(column = "updatetime", property = "updatetime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "comment", property = "comment", jdbcType = JdbcType.LONGVARCHAR) })
	List<JokeComments> selectMany(SelectStatementProvider selectStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.360863+10:00", comments = "Source Table: joke_comments")
	@UpdateProvider(type = SqlProviderAdapter.class, method = "update")
	int update(UpdateStatementProvider updateStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.360863+10:00", comments = "Source Table: joke_comments")
	default long count(CountDSLCompleter completer) {
		return MyBatis3Utils.countFrom(this::count, jokeComments, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.360863+10:00", comments = "Source Table: joke_comments")
	default int delete(DeleteDSLCompleter completer) {
		return MyBatis3Utils.deleteFrom(this::delete, jokeComments, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.360863+10:00", comments = "Source Table: joke_comments")
	default int deleteByPrimaryKey(String commentid_, String joketid_) {
		return delete(c -> c.where(commentid, isEqualTo(commentid_)).and(joketid, isEqualTo(joketid_)));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.360863+10:00", comments = "Source Table: joke_comments")
	default int insert(JokeComments record) {
		return MyBatis3Utils.insert(this::insert, record, jokeComments,
				c -> c.map(commentid).toProperty("commentid").map(joketid).toProperty("joketid").map(username)
						.toProperty("username").map(updatetime).toProperty("updatetime").map(comment)
						.toProperty("comment"));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.360863+10:00", comments = "Source Table: joke_comments")
	default int insertMultiple(Collection<JokeComments> records) {
		return MyBatis3Utils.insertMultiple(this::insertMultiple, records, jokeComments,
				c -> c.map(commentid).toProperty("commentid").map(joketid).toProperty("joketid").map(username)
						.toProperty("username").map(updatetime).toProperty("updatetime").map(comment)
						.toProperty("comment"));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.360863+10:00", comments = "Source Table: joke_comments")
	default int insertSelective(JokeComments record) {
		return MyBatis3Utils.insert(this::insert, record, jokeComments,
				c -> c.map(commentid).toPropertyWhenPresent("commentid", record::getCommentid).map(joketid)
						.toPropertyWhenPresent("joketid", record::getJoketid).map(username)
						.toPropertyWhenPresent("username", record::getUsername).map(updatetime)
						.toPropertyWhenPresent("updatetime", record::getUpdatetime).map(comment)
						.toPropertyWhenPresent("comment", record::getComment));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3618629+10:00", comments = "Source Table: joke_comments")
	default Optional<JokeComments> selectOne(SelectDSLCompleter completer) {
		return MyBatis3Utils.selectOne(this::selectOne, selectList, jokeComments, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3618629+10:00", comments = "Source Table: joke_comments")
	default List<JokeComments> select(SelectDSLCompleter completer) {
		return MyBatis3Utils.selectList(this::selectMany, selectList, jokeComments, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3618629+10:00", comments = "Source Table: joke_comments")
	default List<JokeComments> selectDistinct(SelectDSLCompleter completer) {
		return MyBatis3Utils.selectDistinct(this::selectMany, selectList, jokeComments, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3618629+10:00", comments = "Source Table: joke_comments")
	default Optional<JokeComments> selectByPrimaryKey(String commentid_, String joketid_) {
		return selectOne(c -> c.where(commentid, isEqualTo(commentid_)).and(joketid, isEqualTo(joketid_)));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3618629+10:00", comments = "Source Table: joke_comments")
	default int update(UpdateDSLCompleter completer) {
		return MyBatis3Utils.update(this::update, jokeComments, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3618629+10:00", comments = "Source Table: joke_comments")
	static UpdateDSL<UpdateModel> updateAllColumns(JokeComments record, UpdateDSL<UpdateModel> dsl) {
		return dsl.set(commentid).equalTo(record::getCommentid).set(joketid).equalTo(record::getJoketid).set(username)
				.equalTo(record::getUsername).set(updatetime).equalTo(record::getUpdatetime).set(comment)
				.equalTo(record::getComment);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3618629+10:00", comments = "Source Table: joke_comments")
	static UpdateDSL<UpdateModel> updateSelectiveColumns(JokeComments record, UpdateDSL<UpdateModel> dsl) {
		return dsl.set(commentid).equalToWhenPresent(record::getCommentid).set(joketid)
				.equalToWhenPresent(record::getJoketid).set(username).equalToWhenPresent(record::getUsername)
				.set(updatetime).equalToWhenPresent(record::getUpdatetime).set(comment)
				.equalToWhenPresent(record::getComment);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3618629+10:00", comments = "Source Table: joke_comments")
	default int updateByPrimaryKey(JokeComments record) {
		return update(c -> c.set(username).equalTo(record::getUsername).set(updatetime).equalTo(record::getUpdatetime)
				.set(comment).equalTo(record::getComment).where(commentid, isEqualTo(record::getCommentid))
				.and(joketid, isEqualTo(record::getJoketid)));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3618629+10:00", comments = "Source Table: joke_comments")
	default int updateByPrimaryKeySelective(JokeComments record) {
		return update(c -> c.set(username).equalToWhenPresent(record::getUsername).set(updatetime)
				.equalToWhenPresent(record::getUpdatetime).set(comment).equalToWhenPresent(record::getComment)
				.where(commentid, isEqualTo(record::getCommentid)).and(joketid, isEqualTo(record::getJoketid)));
	}
}
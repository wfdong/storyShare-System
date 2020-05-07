package jokes.dao.mybatismapper;

import static jokes.dao.mybatismapper.JokeTableDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import jokes.beans.daobeans.JokeTable;
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
public interface JokeTableMapper {

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3568618+10:00", comments = "Source Table: joke_table")
	BasicColumn[] selectList = BasicColumn.columnList(tid, username, filelocation, liked, disliked, commentsnum,
			uploadtime, content);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3558603+10:00", comments = "Source Table: joke_table")
	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	long count(SelectStatementProvider selectStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3558603+10:00", comments = "Source Table: joke_table")
	@DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
	int delete(DeleteStatementProvider deleteStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3558603+10:00", comments = "Source Table: joke_table")
	@InsertProvider(type = SqlProviderAdapter.class, method = "insert")
	int insert(InsertStatementProvider<JokeTable> insertStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3558603+10:00", comments = "Source Table: joke_table")
	@InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
	int insertMultiple(MultiRowInsertStatementProvider<JokeTable> multipleInsertStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3558603+10:00", comments = "Source Table: joke_table")
	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@ResultMap("JokeTableResult")
	Optional<JokeTable> selectOne(SelectStatementProvider selectStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3558603+10:00", comments = "Source Table: joke_table")
	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@Results(id = "JokeTableResult", value = {
			@Result(column = "tid", property = "tid", jdbcType = JdbcType.VARCHAR, id = true),
			@Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
			@Result(column = "filelocation", property = "filelocation", jdbcType = JdbcType.VARCHAR),
			@Result(column = "liked", property = "liked", jdbcType = JdbcType.INTEGER),
			@Result(column = "disliked", property = "disliked", jdbcType = JdbcType.INTEGER),
			@Result(column = "commentsnum", property = "commentsnum", jdbcType = JdbcType.INTEGER),
			@Result(column = "uploadtime", property = "uploadtime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "content", property = "content", jdbcType = JdbcType.LONGVARCHAR) })
	List<JokeTable> selectMany(SelectStatementProvider selectStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3568618+10:00", comments = "Source Table: joke_table")
	@UpdateProvider(type = SqlProviderAdapter.class, method = "update")
	int update(UpdateStatementProvider updateStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3568618+10:00", comments = "Source Table: joke_table")
	default long count(CountDSLCompleter completer) {
		return MyBatis3Utils.countFrom(this::count, jokeTable, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3568618+10:00", comments = "Source Table: joke_table")
	default int delete(DeleteDSLCompleter completer) {
		return MyBatis3Utils.deleteFrom(this::delete, jokeTable, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3568618+10:00", comments = "Source Table: joke_table")
	default int deleteByPrimaryKey(String tid_) {
		return delete(c -> c.where(tid, isEqualTo(tid_)));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3568618+10:00", comments = "Source Table: joke_table")
	default int insert(JokeTable record) {
		return MyBatis3Utils.insert(this::insert, record, jokeTable,
				c -> c.map(tid).toProperty("tid").map(username).toProperty("username").map(filelocation)
						.toProperty("filelocation").map(liked).toProperty("liked").map(disliked).toProperty("disliked")
						.map(commentsnum).toProperty("commentsnum").map(uploadtime).toProperty("uploadtime")
						.map(content).toProperty("content"));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3568618+10:00", comments = "Source Table: joke_table")
	default int insertMultiple(Collection<JokeTable> records) {
		return MyBatis3Utils.insertMultiple(this::insertMultiple, records, jokeTable,
				c -> c.map(tid).toProperty("tid").map(username).toProperty("username").map(filelocation)
						.toProperty("filelocation").map(liked).toProperty("liked").map(disliked).toProperty("disliked")
						.map(commentsnum).toProperty("commentsnum").map(uploadtime).toProperty("uploadtime")
						.map(content).toProperty("content"));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3568618+10:00", comments = "Source Table: joke_table")
	default int insertSelective(JokeTable record) {
		return MyBatis3Utils.insert(this::insert, record, jokeTable,
				c -> c.map(tid).toPropertyWhenPresent("tid", record::getTid).map(username)
						.toPropertyWhenPresent("username", record::getUsername).map(filelocation)
						.toPropertyWhenPresent("filelocation", record::getFilelocation).map(liked)
						.toPropertyWhenPresent("liked", record::getLiked).map(disliked)
						.toPropertyWhenPresent("disliked", record::getDisliked).map(commentsnum)
						.toPropertyWhenPresent("commentsnum", record::getCommentsnum).map(uploadtime)
						.toPropertyWhenPresent("uploadtime", record::getUploadtime).map(content)
						.toPropertyWhenPresent("content", record::getContent));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3568618+10:00", comments = "Source Table: joke_table")
	default Optional<JokeTable> selectOne(SelectDSLCompleter completer) {
		return MyBatis3Utils.selectOne(this::selectOne, selectList, jokeTable, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3568618+10:00", comments = "Source Table: joke_table")
	default List<JokeTable> select(SelectDSLCompleter completer) {
		return MyBatis3Utils.selectList(this::selectMany, selectList, jokeTable, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3568618+10:00", comments = "Source Table: joke_table")
	default List<JokeTable> selectDistinct(SelectDSLCompleter completer) {
		return MyBatis3Utils.selectDistinct(this::selectMany, selectList, jokeTable, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3578613+10:00", comments = "Source Table: joke_table")
	default Optional<JokeTable> selectByPrimaryKey(String tid_) {
		return selectOne(c -> c.where(tid, isEqualTo(tid_)));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3578613+10:00", comments = "Source Table: joke_table")
	default int update(UpdateDSLCompleter completer) {
		return MyBatis3Utils.update(this::update, jokeTable, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3578613+10:00", comments = "Source Table: joke_table")
	static UpdateDSL<UpdateModel> updateAllColumns(JokeTable record, UpdateDSL<UpdateModel> dsl) {
		return dsl.set(tid).equalTo(record::getTid).set(username).equalTo(record::getUsername).set(filelocation)
				.equalTo(record::getFilelocation).set(liked).equalTo(record::getLiked).set(disliked)
				.equalTo(record::getDisliked).set(commentsnum).equalTo(record::getCommentsnum).set(uploadtime)
				.equalTo(record::getUploadtime).set(content).equalTo(record::getContent);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3578613+10:00", comments = "Source Table: joke_table")
	static UpdateDSL<UpdateModel> updateSelectiveColumns(JokeTable record, UpdateDSL<UpdateModel> dsl) {
		return dsl.set(tid).equalToWhenPresent(record::getTid).set(username).equalToWhenPresent(record::getUsername)
				.set(filelocation).equalToWhenPresent(record::getFilelocation).set(liked)
				.equalToWhenPresent(record::getLiked).set(disliked).equalToWhenPresent(record::getDisliked)
				.set(commentsnum).equalToWhenPresent(record::getCommentsnum).set(uploadtime)
				.equalToWhenPresent(record::getUploadtime).set(content).equalToWhenPresent(record::getContent);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3578613+10:00", comments = "Source Table: joke_table")
	default int updateByPrimaryKey(JokeTable record) {
		return update(
				c -> c.set(username).equalTo(record::getUsername).set(filelocation).equalTo(record::getFilelocation)
						.set(liked).equalTo(record::getLiked).set(disliked).equalTo(record::getDisliked)
						.set(commentsnum).equalTo(record::getCommentsnum).set(uploadtime).equalTo(record::getUploadtime)
						.set(content).equalTo(record::getContent).where(tid, isEqualTo(record::getTid)));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3578613+10:00", comments = "Source Table: joke_table")
	default int updateByPrimaryKeySelective(JokeTable record) {
		return update(c -> c.set(username).equalToWhenPresent(record::getUsername).set(filelocation)
				.equalToWhenPresent(record::getFilelocation).set(liked).equalToWhenPresent(record::getLiked)
				.set(disliked).equalToWhenPresent(record::getDisliked).set(commentsnum)
				.equalToWhenPresent(record::getCommentsnum).set(uploadtime).equalToWhenPresent(record::getUploadtime)
				.set(content).equalToWhenPresent(record::getContent).where(tid, isEqualTo(record::getTid)));
	}
}
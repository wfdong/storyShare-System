package jokes.dao.mybatismapper;

import static jokes.dao.mybatismapper.UserFileDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import jokes.beans.daobeans.UserFile;
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
public interface UserFileMapper {

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.366861+10:00", comments = "Source Table: user_file")
	BasicColumn[] selectList = BasicColumn.columnList(id, filePath, foreignId, foreignKey, gmtCreated, gmtModified);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.365863+10:00", comments = "Source Table: user_file")
	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	long count(SelectStatementProvider selectStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.365863+10:00", comments = "Source Table: user_file")
	@DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
	int delete(DeleteStatementProvider deleteStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.365863+10:00", comments = "Source Table: user_file")
	@InsertProvider(type = SqlProviderAdapter.class, method = "insert")
	int insert(InsertStatementProvider<UserFile> insertStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.365863+10:00", comments = "Source Table: user_file")
	@InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
	int insertMultiple(MultiRowInsertStatementProvider<UserFile> multipleInsertStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.365863+10:00", comments = "Source Table: user_file")
	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@ResultMap("UserFileResult")
	Optional<UserFile> selectOne(SelectStatementProvider selectStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.365863+10:00", comments = "Source Table: user_file")
	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@Results(id = "UserFileResult", value = {
			@Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "file_path", property = "filePath", jdbcType = JdbcType.VARCHAR),
			@Result(column = "foreign_id", property = "foreignId", jdbcType = JdbcType.VARCHAR),
			@Result(column = "foreign_key", property = "foreignKey", jdbcType = JdbcType.VARCHAR),
			@Result(column = "gmt_created", property = "gmtCreated", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "gmt_modified", property = "gmtModified", jdbcType = JdbcType.TIMESTAMP) })
	List<UserFile> selectMany(SelectStatementProvider selectStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.365863+10:00", comments = "Source Table: user_file")
	@UpdateProvider(type = SqlProviderAdapter.class, method = "update")
	int update(UpdateStatementProvider updateStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.366861+10:00", comments = "Source Table: user_file")
	default long count(CountDSLCompleter completer) {
		return MyBatis3Utils.countFrom(this::count, userFile, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.366861+10:00", comments = "Source Table: user_file")
	default int delete(DeleteDSLCompleter completer) {
		return MyBatis3Utils.deleteFrom(this::delete, userFile, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.366861+10:00", comments = "Source Table: user_file")
	default int deleteByPrimaryKey(Long id_) {
		return delete(c -> c.where(id, isEqualTo(id_)));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.366861+10:00", comments = "Source Table: user_file")
	default int insert(UserFile record) {
		return MyBatis3Utils.insert(this::insert, record, userFile,
				c -> c.map(id).toProperty("id").map(filePath).toProperty("filePath").map(foreignId)
						.toProperty("foreignId").map(foreignKey).toProperty("foreignKey").map(gmtCreated)
						.toProperty("gmtCreated").map(gmtModified).toProperty("gmtModified"));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.366861+10:00", comments = "Source Table: user_file")
	default int insertMultiple(Collection<UserFile> records) {
		return MyBatis3Utils.insertMultiple(this::insertMultiple, records, userFile,
				c -> c.map(id).toProperty("id").map(filePath).toProperty("filePath").map(foreignId)
						.toProperty("foreignId").map(foreignKey).toProperty("foreignKey").map(gmtCreated)
						.toProperty("gmtCreated").map(gmtModified).toProperty("gmtModified"));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.366861+10:00", comments = "Source Table: user_file")
	default int insertSelective(UserFile record) {
		return MyBatis3Utils.insert(this::insert, record, userFile,
				c -> c.map(id).toPropertyWhenPresent("id", record::getId).map(filePath)
						.toPropertyWhenPresent("filePath", record::getFilePath).map(foreignId)
						.toPropertyWhenPresent("foreignId", record::getForeignId).map(foreignKey)
						.toPropertyWhenPresent("foreignKey", record::getForeignKey).map(gmtCreated)
						.toPropertyWhenPresent("gmtCreated", record::getGmtCreated).map(gmtModified)
						.toPropertyWhenPresent("gmtModified", record::getGmtModified));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.366861+10:00", comments = "Source Table: user_file")
	default Optional<UserFile> selectOne(SelectDSLCompleter completer) {
		return MyBatis3Utils.selectOne(this::selectOne, selectList, userFile, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.366861+10:00", comments = "Source Table: user_file")
	default List<UserFile> select(SelectDSLCompleter completer) {
		return MyBatis3Utils.selectList(this::selectMany, selectList, userFile, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.366861+10:00", comments = "Source Table: user_file")
	default List<UserFile> selectDistinct(SelectDSLCompleter completer) {
		return MyBatis3Utils.selectDistinct(this::selectMany, selectList, userFile, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.366861+10:00", comments = "Source Table: user_file")
	default Optional<UserFile> selectByPrimaryKey(Long id_) {
		return selectOne(c -> c.where(id, isEqualTo(id_)));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.366861+10:00", comments = "Source Table: user_file")
	default int update(UpdateDSLCompleter completer) {
		return MyBatis3Utils.update(this::update, userFile, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3678612+10:00", comments = "Source Table: user_file")
	static UpdateDSL<UpdateModel> updateAllColumns(UserFile record, UpdateDSL<UpdateModel> dsl) {
		return dsl.set(id).equalTo(record::getId).set(filePath).equalTo(record::getFilePath).set(foreignId)
				.equalTo(record::getForeignId).set(foreignKey).equalTo(record::getForeignKey).set(gmtCreated)
				.equalTo(record::getGmtCreated).set(gmtModified).equalTo(record::getGmtModified);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3678612+10:00", comments = "Source Table: user_file")
	static UpdateDSL<UpdateModel> updateSelectiveColumns(UserFile record, UpdateDSL<UpdateModel> dsl) {
		return dsl.set(id).equalToWhenPresent(record::getId).set(filePath).equalToWhenPresent(record::getFilePath)
				.set(foreignId).equalToWhenPresent(record::getForeignId).set(foreignKey)
				.equalToWhenPresent(record::getForeignKey).set(gmtCreated).equalToWhenPresent(record::getGmtCreated)
				.set(gmtModified).equalToWhenPresent(record::getGmtModified);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3678612+10:00", comments = "Source Table: user_file")
	default int updateByPrimaryKey(UserFile record) {
		return update(c -> c.set(filePath).equalTo(record::getFilePath).set(foreignId).equalTo(record::getForeignId)
				.set(foreignKey).equalTo(record::getForeignKey).set(gmtCreated).equalTo(record::getGmtCreated)
				.set(gmtModified).equalTo(record::getGmtModified).where(id, isEqualTo(record::getId)));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3678612+10:00", comments = "Source Table: user_file")
	default int updateByPrimaryKeySelective(UserFile record) {
		return update(c -> c.set(filePath).equalToWhenPresent(record::getFilePath).set(foreignId)
				.equalToWhenPresent(record::getForeignId).set(foreignKey).equalToWhenPresent(record::getForeignKey)
				.set(gmtCreated).equalToWhenPresent(record::getGmtCreated).set(gmtModified)
				.equalToWhenPresent(record::getGmtModified).where(id, isEqualTo(record::getId)));
	}
}
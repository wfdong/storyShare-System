package jokes.dao.mybatismapper;

import static jokes.dao.mybatismapper.UsersDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import jokes.beans.daobeans.Users;
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
public interface UsersMapper {

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3418618+10:00", comments = "Source Table: users")
	BasicColumn[] selectList = BasicColumn.columnList(username, password, phone, email, regDate, updateDate, userType);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3398619+10:00", comments = "Source Table: users")
	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	long count(SelectStatementProvider selectStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3398619+10:00", comments = "Source Table: users")
	@DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
	int delete(DeleteStatementProvider deleteStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3398619+10:00", comments = "Source Table: users")
	@InsertProvider(type = SqlProviderAdapter.class, method = "insert")
	int insert(InsertStatementProvider<Users> insertStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3408627+10:00", comments = "Source Table: users")
	@InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
	int insertMultiple(MultiRowInsertStatementProvider<Users> multipleInsertStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3408627+10:00", comments = "Source Table: users")
	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@ResultMap("UsersResult")
	Optional<Users> selectOne(SelectStatementProvider selectStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3408627+10:00", comments = "Source Table: users")
	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@Results(id = "UsersResult", value = {
			@Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR, id = true),
			@Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
			@Result(column = "PHONE", property = "phone", jdbcType = JdbcType.VARCHAR),
			@Result(column = "EMAIL", property = "email", jdbcType = JdbcType.VARCHAR),
			@Result(column = "REG_DATE", property = "regDate", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "UPDATE_DATE", property = "updateDate", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "user_type", property = "userType", jdbcType = JdbcType.VARCHAR) })
	List<Users> selectMany(SelectStatementProvider selectStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3408627+10:00", comments = "Source Table: users")
	@UpdateProvider(type = SqlProviderAdapter.class, method = "update")
	int update(UpdateStatementProvider updateStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3408627+10:00", comments = "Source Table: users")
	default long count(CountDSLCompleter completer) {
		return MyBatis3Utils.countFrom(this::count, users, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3408627+10:00", comments = "Source Table: users")
	default int delete(DeleteDSLCompleter completer) {
		return MyBatis3Utils.deleteFrom(this::delete, users, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3408627+10:00", comments = "Source Table: users")
	default int deleteByPrimaryKey(String username_) {
		return delete(c -> c.where(username, isEqualTo(username_)));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3418618+10:00", comments = "Source Table: users")
	default int insert(Users record) {
		return MyBatis3Utils.insert(this::insert, record, users,
				c -> c.map(username).toProperty("username").map(password).toProperty("password").map(phone)
						.toProperty("phone").map(email).toProperty("email").map(regDate).toProperty("regDate")
						.map(updateDate).toProperty("updateDate").map(userType).toProperty("userType"));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3418618+10:00", comments = "Source Table: users")
	default int insertMultiple(Collection<Users> records) {
		return MyBatis3Utils.insertMultiple(this::insertMultiple, records, users,
				c -> c.map(username).toProperty("username").map(password).toProperty("password").map(phone)
						.toProperty("phone").map(email).toProperty("email").map(regDate).toProperty("regDate")
						.map(updateDate).toProperty("updateDate").map(userType).toProperty("userType"));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3418618+10:00", comments = "Source Table: users")
	default int insertSelective(Users record) {
		return MyBatis3Utils.insert(this::insert, record, users,
				c -> c.map(username).toPropertyWhenPresent("username", record::getUsername).map(password)
						.toPropertyWhenPresent("password", record::getPassword).map(phone)
						.toPropertyWhenPresent("phone", record::getPhone).map(email)
						.toPropertyWhenPresent("email", record::getEmail).map(regDate)
						.toPropertyWhenPresent("regDate", record::getRegDate).map(updateDate)
						.toPropertyWhenPresent("updateDate", record::getUpdateDate).map(userType)
						.toPropertyWhenPresent("userType", record::getUserType));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3418618+10:00", comments = "Source Table: users")
	default Optional<Users> selectOne(SelectDSLCompleter completer) {
		return MyBatis3Utils.selectOne(this::selectOne, selectList, users, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3418618+10:00", comments = "Source Table: users")
	default List<Users> select(SelectDSLCompleter completer) {
		return MyBatis3Utils.selectList(this::selectMany, selectList, users, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3418618+10:00", comments = "Source Table: users")
	default List<Users> selectDistinct(SelectDSLCompleter completer) {
		return MyBatis3Utils.selectDistinct(this::selectMany, selectList, users, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3418618+10:00", comments = "Source Table: users")
	default Optional<Users> selectByPrimaryKey(String username_) {
		return selectOne(c -> c.where(username, isEqualTo(username_)));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3428623+10:00", comments = "Source Table: users")
	default int update(UpdateDSLCompleter completer) {
		return MyBatis3Utils.update(this::update, users, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3428623+10:00", comments = "Source Table: users")
	static UpdateDSL<UpdateModel> updateAllColumns(Users record, UpdateDSL<UpdateModel> dsl) {
		return dsl.set(username).equalTo(record::getUsername).set(password).equalTo(record::getPassword).set(phone)
				.equalTo(record::getPhone).set(email).equalTo(record::getEmail).set(regDate).equalTo(record::getRegDate)
				.set(updateDate).equalTo(record::getUpdateDate).set(userType).equalTo(record::getUserType);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3428623+10:00", comments = "Source Table: users")
	static UpdateDSL<UpdateModel> updateSelectiveColumns(Users record, UpdateDSL<UpdateModel> dsl) {
		return dsl.set(username).equalToWhenPresent(record::getUsername).set(password)
				.equalToWhenPresent(record::getPassword).set(phone).equalToWhenPresent(record::getPhone).set(email)
				.equalToWhenPresent(record::getEmail).set(regDate).equalToWhenPresent(record::getRegDate)
				.set(updateDate).equalToWhenPresent(record::getUpdateDate).set(userType)
				.equalToWhenPresent(record::getUserType);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3428623+10:00", comments = "Source Table: users")
	default int updateByPrimaryKey(Users record) {
		return update(c -> c.set(password).equalTo(record::getPassword).set(phone).equalTo(record::getPhone).set(email)
				.equalTo(record::getEmail).set(regDate).equalTo(record::getRegDate).set(updateDate)
				.equalTo(record::getUpdateDate).set(userType).equalTo(record::getUserType)
				.where(username, isEqualTo(record::getUsername)));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3428623+10:00", comments = "Source Table: users")
	default int updateByPrimaryKeySelective(Users record) {
		return update(c -> c.set(password).equalToWhenPresent(record::getPassword).set(phone)
				.equalToWhenPresent(record::getPhone).set(email).equalToWhenPresent(record::getEmail).set(regDate)
				.equalToWhenPresent(record::getRegDate).set(updateDate).equalToWhenPresent(record::getUpdateDate)
				.set(userType).equalToWhenPresent(record::getUserType).where(username, isEqualTo(record::getUsername)));
	}
}
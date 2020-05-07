package jokes.dao.mybatismapper;

import static jokes.dao.mybatismapper.UserRoleDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import jokes.beans.daobeans.UserRole;
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
public interface UserRoleMapper {

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3458624+10:00", comments = "Source Table: user_role")
	BasicColumn[] selectList = BasicColumn.columnList(username, password, role);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3448634+10:00", comments = "Source Table: user_role")
	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	long count(SelectStatementProvider selectStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3448634+10:00", comments = "Source Table: user_role")
	@DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
	int delete(DeleteStatementProvider deleteStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3448634+10:00", comments = "Source Table: user_role")
	@InsertProvider(type = SqlProviderAdapter.class, method = "insert")
	int insert(InsertStatementProvider<UserRole> insertStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3448634+10:00", comments = "Source Table: user_role")
	@InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
	int insertMultiple(MultiRowInsertStatementProvider<UserRole> multipleInsertStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3448634+10:00", comments = "Source Table: user_role")
	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@ResultMap("UserRoleResult")
	Optional<UserRole> selectOne(SelectStatementProvider selectStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3448634+10:00", comments = "Source Table: user_role")
	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@Results(id = "UserRoleResult", value = {
			@Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR, id = true),
			@Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
			@Result(column = "role", property = "role", jdbcType = JdbcType.VARCHAR) })
	List<UserRole> selectMany(SelectStatementProvider selectStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3458624+10:00", comments = "Source Table: user_role")
	@UpdateProvider(type = SqlProviderAdapter.class, method = "update")
	int update(UpdateStatementProvider updateStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3458624+10:00", comments = "Source Table: user_role")
	default long count(CountDSLCompleter completer) {
		return MyBatis3Utils.countFrom(this::count, userRole, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3458624+10:00", comments = "Source Table: user_role")
	default int delete(DeleteDSLCompleter completer) {
		return MyBatis3Utils.deleteFrom(this::delete, userRole, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3458624+10:00", comments = "Source Table: user_role")
	default int deleteByPrimaryKey(String username_) {
		return delete(c -> c.where(username, isEqualTo(username_)));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3458624+10:00", comments = "Source Table: user_role")
	default int insert(UserRole record) {
		return MyBatis3Utils.insert(this::insert, record, userRole, c -> c.map(username).toProperty("username")
				.map(password).toProperty("password").map(role).toProperty("role"));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3458624+10:00", comments = "Source Table: user_role")
	default int insertMultiple(Collection<UserRole> records) {
		return MyBatis3Utils.insertMultiple(this::insertMultiple, records, userRole, c -> c.map(username)
				.toProperty("username").map(password).toProperty("password").map(role).toProperty("role"));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3458624+10:00", comments = "Source Table: user_role")
	default int insertSelective(UserRole record) {
		return MyBatis3Utils.insert(this::insert, record, userRole,
				c -> c.map(username).toPropertyWhenPresent("username", record::getUsername).map(password)
						.toPropertyWhenPresent("password", record::getPassword).map(role)
						.toPropertyWhenPresent("role", record::getRole));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3458624+10:00", comments = "Source Table: user_role")
	default Optional<UserRole> selectOne(SelectDSLCompleter completer) {
		return MyBatis3Utils.selectOne(this::selectOne, selectList, userRole, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3458624+10:00", comments = "Source Table: user_role")
	default List<UserRole> select(SelectDSLCompleter completer) {
		return MyBatis3Utils.selectList(this::selectMany, selectList, userRole, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3468611+10:00", comments = "Source Table: user_role")
	default List<UserRole> selectDistinct(SelectDSLCompleter completer) {
		return MyBatis3Utils.selectDistinct(this::selectMany, selectList, userRole, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3468611+10:00", comments = "Source Table: user_role")
	default Optional<UserRole> selectByPrimaryKey(String username_) {
		return selectOne(c -> c.where(username, isEqualTo(username_)));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3468611+10:00", comments = "Source Table: user_role")
	default int update(UpdateDSLCompleter completer) {
		return MyBatis3Utils.update(this::update, userRole, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3468611+10:00", comments = "Source Table: user_role")
	static UpdateDSL<UpdateModel> updateAllColumns(UserRole record, UpdateDSL<UpdateModel> dsl) {
		return dsl.set(username).equalTo(record::getUsername).set(password).equalTo(record::getPassword).set(role)
				.equalTo(record::getRole);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3468611+10:00", comments = "Source Table: user_role")
	static UpdateDSL<UpdateModel> updateSelectiveColumns(UserRole record, UpdateDSL<UpdateModel> dsl) {
		return dsl.set(username).equalToWhenPresent(record::getUsername).set(password)
				.equalToWhenPresent(record::getPassword).set(role).equalToWhenPresent(record::getRole);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3468611+10:00", comments = "Source Table: user_role")
	default int updateByPrimaryKey(UserRole record) {
		return update(c -> c.set(password).equalTo(record::getPassword).set(role).equalTo(record::getRole)
				.where(username, isEqualTo(record::getUsername)));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.3468611+10:00", comments = "Source Table: user_role")
	default int updateByPrimaryKeySelective(UserRole record) {
		return update(c -> c.set(password).equalToWhenPresent(record::getPassword).set(role)
				.equalToWhenPresent(record::getRole).where(username, isEqualTo(record::getUsername)));
	}
}
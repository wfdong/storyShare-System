package jokes.dao.mybatismapper;

import static jokes.dao.mybatismapper.TableStatisticsDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import jokes.beans.daobeans.TableStatistics;
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
public interface TableStatisticsMapper {

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.4158652+10:00", comments = "Source Table: table_statistics")
	BasicColumn[] selectList = BasicColumn.columnList(id, fromaddress, time);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.4148624+10:00", comments = "Source Table: table_statistics")
	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	long count(SelectStatementProvider selectStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.4148624+10:00", comments = "Source Table: table_statistics")
	@DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
	int delete(DeleteStatementProvider deleteStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.4148624+10:00", comments = "Source Table: table_statistics")
	@InsertProvider(type = SqlProviderAdapter.class, method = "insert")
	int insert(InsertStatementProvider<TableStatistics> insertStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.4148624+10:00", comments = "Source Table: table_statistics")
	@InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
	int insertMultiple(MultiRowInsertStatementProvider<TableStatistics> multipleInsertStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.4148624+10:00", comments = "Source Table: table_statistics")
	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@ResultMap("TableStatisticsResult")
	Optional<TableStatistics> selectOne(SelectStatementProvider selectStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.4148624+10:00", comments = "Source Table: table_statistics")
	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@Results(id = "TableStatisticsResult", value = {
			@Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "fromaddress", property = "fromaddress", jdbcType = JdbcType.VARCHAR),
			@Result(column = "time", property = "time", jdbcType = JdbcType.TIMESTAMP) })
	List<TableStatistics> selectMany(SelectStatementProvider selectStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.4158652+10:00", comments = "Source Table: table_statistics")
	@UpdateProvider(type = SqlProviderAdapter.class, method = "update")
	int update(UpdateStatementProvider updateStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.4158652+10:00", comments = "Source Table: table_statistics")
	default long count(CountDSLCompleter completer) {
		return MyBatis3Utils.countFrom(this::count, tableStatistics, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.4158652+10:00", comments = "Source Table: table_statistics")
	default int delete(DeleteDSLCompleter completer) {
		return MyBatis3Utils.deleteFrom(this::delete, tableStatistics, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.4158652+10:00", comments = "Source Table: table_statistics")
	default int deleteByPrimaryKey(Long id_) {
		return delete(c -> c.where(id, isEqualTo(id_)));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.4158652+10:00", comments = "Source Table: table_statistics")
	default int insert(TableStatistics record) {
		return MyBatis3Utils.insert(this::insert, record, tableStatistics, c -> c.map(id).toProperty("id")
				.map(fromaddress).toProperty("fromaddress").map(time).toProperty("time"));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.4158652+10:00", comments = "Source Table: table_statistics")
	default int insertMultiple(Collection<TableStatistics> records) {
		return MyBatis3Utils.insertMultiple(this::insertMultiple, records, tableStatistics, c -> c.map(id)
				.toProperty("id").map(fromaddress).toProperty("fromaddress").map(time).toProperty("time"));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.4158652+10:00", comments = "Source Table: table_statistics")
	default int insertSelective(TableStatistics record) {
		return MyBatis3Utils.insert(this::insert, record, tableStatistics,
				c -> c.map(id).toPropertyWhenPresent("id", record::getId).map(fromaddress)
						.toPropertyWhenPresent("fromaddress", record::getFromaddress).map(time)
						.toPropertyWhenPresent("time", record::getTime));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.4158652+10:00", comments = "Source Table: table_statistics")
	default Optional<TableStatistics> selectOne(SelectDSLCompleter completer) {
		return MyBatis3Utils.selectOne(this::selectOne, selectList, tableStatistics, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.4158652+10:00", comments = "Source Table: table_statistics")
	default List<TableStatistics> select(SelectDSLCompleter completer) {
		return MyBatis3Utils.selectList(this::selectMany, selectList, tableStatistics, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.4158652+10:00", comments = "Source Table: table_statistics")
	default List<TableStatistics> selectDistinct(SelectDSLCompleter completer) {
		return MyBatis3Utils.selectDistinct(this::selectMany, selectList, tableStatistics, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.4158652+10:00", comments = "Source Table: table_statistics")
	default Optional<TableStatistics> selectByPrimaryKey(Long id_) {
		return selectOne(c -> c.where(id, isEqualTo(id_)));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.4158652+10:00", comments = "Source Table: table_statistics")
	default int update(UpdateDSLCompleter completer) {
		return MyBatis3Utils.update(this::update, tableStatistics, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.4158652+10:00", comments = "Source Table: table_statistics")
	static UpdateDSL<UpdateModel> updateAllColumns(TableStatistics record, UpdateDSL<UpdateModel> dsl) {
		return dsl.set(id).equalTo(record::getId).set(fromaddress).equalTo(record::getFromaddress).set(time)
				.equalTo(record::getTime);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.4158652+10:00", comments = "Source Table: table_statistics")
	static UpdateDSL<UpdateModel> updateSelectiveColumns(TableStatistics record, UpdateDSL<UpdateModel> dsl) {
		return dsl.set(id).equalToWhenPresent(record::getId).set(fromaddress).equalToWhenPresent(record::getFromaddress)
				.set(time).equalToWhenPresent(record::getTime);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.4158652+10:00", comments = "Source Table: table_statistics")
	default int updateByPrimaryKey(TableStatistics record) {
		return update(c -> c.set(fromaddress).equalTo(record::getFromaddress).set(time).equalTo(record::getTime)
				.where(id, isEqualTo(record::getId)));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-24T13:03:44.4168645+10:00", comments = "Source Table: table_statistics")
	default int updateByPrimaryKeySelective(TableStatistics record) {
		return update(c -> c.set(fromaddress).equalToWhenPresent(record::getFromaddress).set(time)
				.equalToWhenPresent(record::getTime).where(id, isEqualTo(record::getId)));
	}
}
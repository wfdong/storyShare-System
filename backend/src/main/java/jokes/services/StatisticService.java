package jokes.services;

import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jokes.beans.daobeans.JokeTransactionBean;
import jokes.dao.mybatismapper.TableStatisticsMapper;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import static jokes.dao.mybatismapper.TableStatisticsDynamicSqlSupport.*;

@Component
public class StatisticService {

	@Autowired
	TableStatisticsMapper tableStatisticsMapper;
	
	public void pageStatistic(HttpServletRequest httpServletRequest,
			JokeTransactionBean jokeTransactionBean) {
		String address = httpServletRequest.getRemoteAddr();
		jokes.beans.daobeans.TableStatistics record = new jokes.beans.daobeans.TableStatistics();
		record.setFromaddress(address);
		record.setTime(new Date());
		InsertStatementProvider<jokes.beans.daobeans.TableStatistics> insertStatement = insert(record).into(tableStatistics)
				.map(jokes.dao.mybatismapper.TableStatisticsDynamicSqlSupport.fromaddress).toProperty("fromaddress")
				.map(jokes.dao.mybatismapper.TableStatisticsDynamicSqlSupport.time).toProperty("time")
				.build()
				.render(RenderingStrategies.MYBATIS3);
		tableStatisticsMapper.insert(insertStatement);
	}
}

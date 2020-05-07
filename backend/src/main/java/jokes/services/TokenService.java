package jokes.services;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jokes.beans.auth.TokenEntity;
import jokes.beans.daobeans.UserRole;
import jokes.dao.mybatismapper.TokenCacheMapper;
import jokes.dao.mybatismapper.UserRoleMapper;

import static jokes.dao.mybatismapper.TokenCacheDynamicSqlSupport.*;
import static jokes.dao.mybatismapper.UserRoleDynamicSqlSupport.*;
//import the SQL builder
import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Component
public class TokenService {

	@Autowired
	private TokenCacheMapper tokenCacheMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;

	public boolean checkToken(String resttoken) {
		if (resttoken == null || resttoken.isEmpty() || !resttoken.contains("_")) {
			return false;
		}
		String[] tokenAndUsername = resttoken.split("_");
		String extractedToken = tokenAndUsername[0];
		byte[] asBytes = Base64.getDecoder().decode(tokenAndUsername[1]);
		try {
			String extractedUsername = new String(asBytes, "utf-8");
			SelectStatementProvider selectStatement = select(
					jokes.dao.mybatismapper.TokenCacheDynamicSqlSupport.username, token, updatetime)
							.from(tokenCache)
							.where(jokes.dao.mybatismapper.TokenCacheDynamicSqlSupport.username,
									isEqualTo(extractedUsername))
							.and(token, isEqualTo(resttoken)).limit(1).build().render(RenderingStrategies.MYBATIS3);
//			Optional<jokes.beans.daobeans.TokenCache> tokenCacheResult = tokenCacheMapper.selectOne(selectStatement);
			List<jokes.beans.daobeans.TokenCache> tokenCacheResult = tokenCacheMapper.selectMany(selectStatement);
			if (null != tokenCacheResult && !tokenCacheResult.isEmpty()) {
				UpdateStatementProvider updateStatement = update(tokenCache).set(updatetime).equalTo(new Date())
						.where(jokes.dao.mybatismapper.TokenCacheDynamicSqlSupport.username,
								isEqualTo(extractedUsername))
						.and(token, isEqualTo(resttoken)).build().render(RenderingStrategies.MYBATIS3);
				tokenCacheMapper.update(updateStatement);
				return true;
			} else {
				return false;
			}
		} catch (UnsupportedEncodingException e) {
			return false;
		}
	}

	public TokenEntity createToken(String userName) {
		String asB64;
		try {
			asB64 = Base64.getEncoder().encodeToString(userName.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			asB64 = userName;
		}
		String newToken = UUID.randomUUID().toString().replaceAll("_", "") + "_" + asB64;
		TokenEntity tokenEntity = new TokenEntity(userName, newToken);
		SelectStatementProvider selectStatement = select(jokes.dao.mybatismapper.TokenCacheDynamicSqlSupport.username,
				token, updatetime, gmtCreated, gmtModified).from(tokenCache)
						.where(jokes.dao.mybatismapper.TokenCacheDynamicSqlSupport.username, isEqualTo(userName))
						.build().render(RenderingStrategies.MYBATIS3);
//		Optional<jokes.beans.daobeans.TokenCache> tokenCacheResult = tokenCacheMapper.selectOne(selectStatement);
		List<jokes.beans.daobeans.TokenCache> tokenCacheResult = tokenCacheMapper.selectMany(selectStatement);
		if (null != tokenCacheResult && !tokenCacheResult.isEmpty()) {
			String tokenExisted = tokenCacheResult.get(0).getToken();
			//tokenEntity.setToken(tokenExisted);
			tokenEntity.setToken(newToken);
			// update
			UpdateStatementProvider updateStatement = update(tokenCache).set(updatetime).equalTo(new Date()).set(token).equalTo(newToken)
					.where(jokes.dao.mybatismapper.TokenCacheDynamicSqlSupport.username, isEqualTo(userName))
					.and(token, isEqualTo(tokenExisted)).build().render(RenderingStrategies.MYBATIS3);
			tokenCacheMapper.update(updateStatement);
		} else {
			// insert
			jokes.beans.daobeans.TokenCache newTokenCache = new jokes.beans.daobeans.TokenCache();
			newTokenCache.setUsername(userName);
			newTokenCache.setToken(newToken);
			newTokenCache.setUpdatetime(new Date());
			newTokenCache.setGmtCreated(new Date());
			newTokenCache.setGmtModified(new Date());
			InsertStatementProvider<jokes.beans.daobeans.TokenCache> insertStatement = insert(newTokenCache)
					.into(tokenCache).map(jokes.dao.mybatismapper.TokenCacheDynamicSqlSupport.username)
					.toProperty("username").map(token).toProperty("token").map(updatetime).toProperty("updatetime")
					.map(gmtCreated).toProperty("gmtCreated").map(gmtModified).toProperty("gmtModified").build()
					.render(RenderingStrategies.MYBATIS3);
			tokenCacheMapper.insert(insertStatement);
		}
		return tokenEntity;
	}

	public List<String> getUsernameAndRolebyToken(String token) {
		if (token == null || token.isEmpty() || !token.contains("_")) {
			return null;
		}
		String[] tokenAndUsername = token.split("_");
		byte[] asBytes = Base64.getDecoder().decode(tokenAndUsername[1]);
		try {
			String extractedUsername = new String(asBytes, "utf-8");
			List<String> result = new ArrayList<String>();
			result.add(extractedUsername);
//            String role = crudOperations.getRolebyUsername(extractedUsername);
			SelectStatementProvider selectStatement = select(jokes.dao.mybatismapper.UserRoleDynamicSqlSupport.role)
					.from(userRole)
					.where(jokes.dao.mybatismapper.UserRoleDynamicSqlSupport.username, isEqualTo(extractedUsername))
					.limit(1).build().render(RenderingStrategies.MYBATIS3);
			List<UserRole> userRole = userRoleMapper.selectMany(selectStatement);
			if (null != userRole && !userRole.isEmpty()) {
				result.add(userRole.get(0).getRole());
				return result;
			} else {
				return null;
			}
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
}

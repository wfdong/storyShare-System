package jokes.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import jokes.services.TokenService;
import jokes.beans.auth.UserBean;
import jokes.beans.daobeans.UserRole;
import jokes.beans.requests.AddedUserInfo;
import jokes.dao.mybatismapper.UserRoleMapper;
import jokes.dao.mybatismapper.UsersMapper;

import static jokes.dao.mybatismapper.UserRoleDynamicSqlSupport.*;
import static jokes.dao.mybatismapper.UsersDynamicSqlSupport.*;
//import the SQL builder
import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Component
public class UserService implements UserDetailsService {

	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
    private TokenService tokenService;
	
	@Autowired
	UserRoleMapper userRoleMapper;
	
	@Autowired
	UsersMapper usersMapper;
	
	@Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		SelectStatementProvider selectStatement = select(jokes.dao.mybatismapper.UserRoleDynamicSqlSupport.username, jokes.dao.mybatismapper.UserRoleDynamicSqlSupport.password, role).from(userRole)
				.where(jokes.dao.mybatismapper.UserRoleDynamicSqlSupport.username, isEqualTo(userName)).limit(1).build().render(RenderingStrategies.MYBATIS3);
		//Optional<UserRole> userRoleResult = userRoleMapper.selectOne(selectStatement);
		List<UserRole> userRoleResult = userRoleMapper.selectMany(selectStatement);
		if(null != userRoleResult && !userRoleResult.isEmpty()) {
			List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
			UserRole userRole = userRoleResult.get(0);
			String roles = userRole.getRole();
			if(roles.contains("|")) {
	            String[] allRoles = roles.split("\\|");
	            for(String role:allRoles) {
	                authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
	            }
	        }else {
	            authorities.add(new SimpleGrantedAuthority("ROLE_" + roles));
	        }
			logger.info("User {} find role {}", userRole.getUsername(), userRole.getRole());
			return new org.springframework.security.core.userdetails.User(userRole.getUsername(), userRole.getPassword(), authorities);
		}else {
			return null;
		}
//		if(userRoleResult.isPresent()) {
//			List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
//			String roles = userRoleResult.get().getRole();
//			if(roles.contains("|")) {
//	            String[] allRoles = roles.split("\\|");
//	            for(String role:allRoles) {
//	                authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
//	            }
//	        }else {
//	            authorities.add(new SimpleGrantedAuthority("ROLE_" + roles));
//	        }
//			logger.info("User {} find role {}", userRoleResult.get().getUsername(), userRoleResult.get().getRole());
//			return new org.springframework.security.core.userdetails.User(userRoleResult.get().getUsername(), userRoleResult.get().getPassword(), authorities);
//		}else {
//			return null;
//		}
    }
	
	public String[] getUserRoleByName(String userName){
		SelectStatementProvider selectStatement = select(jokes.dao.mybatismapper.UserRoleDynamicSqlSupport.username, jokes.dao.mybatismapper.UserRoleDynamicSqlSupport.password, role).from(userRole)
				.where(jokes.dao.mybatismapper.UserRoleDynamicSqlSupport.username, isEqualTo(userName)).limit(1).build().render(RenderingStrategies.MYBATIS3);
//		Optional<jokes.beans.daobeans.UserRole> userRoleResult = userRoleMapper.selectOne(selectStatement);
		List<jokes.beans.daobeans.UserRole> userRoles = userRoleMapper.selectMany(selectStatement);
		if(null != userRoles && !userRoles.isEmpty()) {
			UserRole userRole = userRoles.get(0);
			String roles = userRole.getRole();
			if(roles==null||roles.length()<=0) {
	            return null;
	        }else if(!roles.contains("\\|")) {
	            String[] returnRoles = {roles};
	            return returnRoles;
	        }else {
	            String[] returnRoles = roles.split("\\|");
	            return returnRoles;
	        }
		}else {
			return null;
		}
//		if(userRoleResult.isPresent()) {
//			String roles = userRoleResult.get().getRole();
//			if(roles==null||roles.length()<=0) {
//	            return null;
//	        }else if(!roles.contains("\\|")) {
//	            String[] returnRoles = {roles};
//	            return returnRoles;
//	        }else {
//	            String[] returnRoles = roles.split("\\|");
//	            return returnRoles;
//	        }
//		}else {
//			return null;
//		}
    }
	
	/*
     * @parameter
     * String username
     * @parameter
     * String password
     * @parameter
     * String role
     * @parameter
     * String phone  (optional)
     * @parameter
     * String email  (optional)
     * @Parameter
     * String region
     * 
     * @return
     * >0 success
     * 0 failed
     */
    public int createUser(String username,
                          String password,
                          String role,
                          String phone,
                          String email,
                          String userType,
                          String realName,
                          String idNumber) {
        if (phone == null) {
            phone = "";
        }
        if (email == null) {
            email = "";
        }
        jokes.beans.daobeans.Users newUser = new jokes.beans.daobeans.Users();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setPhone(phone);
        newUser.setUserType(userType);
        newUser.setEmail(email);
        newUser.setRegDate(new Date());
        newUser.setUpdateDate(new Date());
        InsertStatementProvider<jokes.beans.daobeans.Users> insertStatement = insert(newUser).into(jokes.dao.mybatismapper.UsersDynamicSqlSupport.users).
        		map(jokes.dao.mybatismapper.UsersDynamicSqlSupport.username).toProperty("username").
        		map(jokes.dao.mybatismapper.UsersDynamicSqlSupport.password).toProperty("password").
        		map(jokes.dao.mybatismapper.UsersDynamicSqlSupport.phone).toProperty("phone").
        		map(jokes.dao.mybatismapper.UsersDynamicSqlSupport.userType).toProperty("userType").
        		map(jokes.dao.mybatismapper.UsersDynamicSqlSupport.email).toProperty("email").
        		map(jokes.dao.mybatismapper.UsersDynamicSqlSupport.regDate).toProperty("regDate").
        		map(jokes.dao.mybatismapper.UsersDynamicSqlSupport.updateDate).toProperty("updateDate").build().render(RenderingStrategies.MYBATIS3);
        int insertUser = usersMapper.insert(insertStatement);
        
        jokes.beans.daobeans.UserRole userRole = new jokes.beans.daobeans.UserRole();
        userRole.setUsername(username);
        userRole.setPassword(password);
        userRole.setRole(role);
        InsertStatementProvider<jokes.beans.daobeans.UserRole> insertStatementUserRole = insert(userRole).into(jokes.dao.mybatismapper.UserRoleDynamicSqlSupport.userRole).
        		map(jokes.dao.mybatismapper.UserRoleDynamicSqlSupport.username).toProperty("username").
        		map(jokes.dao.mybatismapper.UserRoleDynamicSqlSupport.password).toProperty("password").
        		map(jokes.dao.mybatismapper.UserRoleDynamicSqlSupport.role).toProperty("role").build().render(RenderingStrategies.MYBATIS3);
        int insertUserRole = userRoleMapper.insert(insertStatementUserRole);
        return insertUser+insertUserRole;
    }
    
//    public List<AddedUserInfo> getAllUserList(String name,String username_1,String idNumber_1,String realName_1,Long regionID_1,Long institutionID_1,Integer limit_1,Integer page_1) {
//        return crudOperations.getAllUsersList(name,username_1,idNumber_1,realName_1,regionID_1,institutionID_1,limit_1,page_1);
//    }
    
    public AddedUserInfo getSingleUserInfo(String userName) {
    	SelectStatementProvider selectStatementUsers = select(jokes.dao.mybatismapper.UsersDynamicSqlSupport.username, jokes.dao.mybatismapper.UsersDynamicSqlSupport.password, 
    			jokes.dao.mybatismapper.UsersDynamicSqlSupport.phone, jokes.dao.mybatismapper.UsersDynamicSqlSupport.email, jokes.dao.mybatismapper.UsersDynamicSqlSupport.userType,
    			jokes.dao.mybatismapper.UsersDynamicSqlSupport.regDate, jokes.dao.mybatismapper.UsersDynamicSqlSupport.updateDate).from(jokes.dao.mybatismapper.UsersDynamicSqlSupport.users)
				.where(jokes.dao.mybatismapper.UsersDynamicSqlSupport.username, isEqualTo(userName)).limit(1).build().render(RenderingStrategies.MYBATIS3);
		List<jokes.beans.daobeans.Users> userResult = usersMapper.selectMany(selectStatementUsers);
    	
    	SelectStatementProvider selectStatementUserRole = select(jokes.dao.mybatismapper.UserRoleDynamicSqlSupport.username, jokes.dao.mybatismapper.UserRoleDynamicSqlSupport.password, role).from(userRole)
				.where(jokes.dao.mybatismapper.UserRoleDynamicSqlSupport.username, isEqualTo(userName)).limit(1).build().render(RenderingStrategies.MYBATIS3);
		List<jokes.beans.daobeans.UserRole> userRoleResult = userRoleMapper.selectMany(selectStatementUserRole);
		
		if(null != userResult && !userResult.isEmpty() && null != userRoleResult && !userRoleResult.isEmpty()) {
			AddedUserInfo addedUserInfo = new AddedUserInfo();
			addedUserInfo.setUsername(userResult.get(0).getUsername());
			addedUserInfo.setPassword(userResult.get(0).getPassword());
			addedUserInfo.setPhone(userResult.get(0).getPhone());
			addedUserInfo.setEmail(userResult.get(0).getEmail());
			addedUserInfo.setUserType(userResult.get(0).getUserType());
			addedUserInfo.setUpdateTime(userResult.get(0).getUpdateDate());
			addedUserInfo.setRole(userRoleResult.get(0).getRole());
			return addedUserInfo;
		}else {
			return null;
		}
    }
    
    public boolean isEmailInUse(String email) {
    	SelectStatementProvider selectStatementUsers = select(jokes.dao.mybatismapper.UsersDynamicSqlSupport.username).from(jokes.dao.mybatismapper.UsersDynamicSqlSupport.users)
				.where(jokes.dao.mybatismapper.UsersDynamicSqlSupport.email, isEqualTo(email)).limit(1).build().render(RenderingStrategies.MYBATIS3);
    	List<jokes.beans.daobeans.Users> users = usersMapper.selectMany(selectStatementUsers);
    	if(null == users || users.isEmpty()) {
    		return false;
    	}else {
    		return true;
    	}
    }
    
    public boolean isUsernameInUse(String userName) {
    	SelectStatementProvider selectStatementUsers = select(jokes.dao.mybatismapper.UsersDynamicSqlSupport.username).from(jokes.dao.mybatismapper.UsersDynamicSqlSupport.users)
				.where(jokes.dao.mybatismapper.UsersDynamicSqlSupport.username, isEqualTo(userName)).limit(1).build().render(RenderingStrategies.MYBATIS3);
    	List<jokes.beans.daobeans.Users> users = usersMapper.selectMany(selectStatementUsers);
    	if(null == users || users.isEmpty()) {
    		return false;
    	}else {
    		return true;
    	}
    }
    
    
    /*
     * @parameter
     * String username
     * @return
     * >0 success
     * 0 failed
     */
//    public int deleteUser(String username, String role,String idnumber,String realname,String admin_region,String institution,String user_type) {
//        return crudOperations.deleteUser(username,role,idnumber,realname,admin_region,institution,user_type);
//    }
    
//    public int cancelUser(String username) {
//        return crudOperations.cancelUser(username);
//    }
}

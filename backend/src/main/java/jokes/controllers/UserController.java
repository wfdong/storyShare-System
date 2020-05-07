package jokes.controllers;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jokes.beans.auth.RestAuthenticationSuccessHandler;
import jokes.beans.daobeans.JokeTransactionBean;
import jokes.beans.requests.RegisterUserinfo;
import jokes.beans.response.Response;
import jokes.beans.response.RestfullPage;
import jokes.controllers.UserController;
import jokes.controllers.UserController.UserInfo;
import jokes.services.TokenService;
import jokes.services.UserService;
import jokes.utils.RestUtil;

@RestController
@CrossOrigin
public class UserController {

	private static final String USER_PATH = "/jokes";

    private static final String GET_USER_PATH = USER_PATH + "/userinfo";

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    TokenService tokenService;
    
    @Autowired
    UserService userService;

    public static class UserInfo {
        public String token;

        public String introduction;

        public String avatar;

        public String name;

        public String[] roles;
    }

    private static UserInfo defaultUser = new UserInfo();

    static {
        defaultUser.avatar = "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif";
        defaultUser.introduction = "";
        defaultUser.roles = new String[1];
        defaultUser.roles[0] = "EMPTY_ROLE";
        defaultUser.name = "ANNOYMOUS";
        defaultUser.token = "EMPTY_TOKEN";
    }

    private UserInfo getDefaultUser() {
        UserInfo userInfo = new UserInfo();
        userInfo.avatar = "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif";
        userInfo.introduction = "我是后端超级管理员";
        userInfo.roles = new String[1];
        userInfo.roles[0] = "super_admin_user";
        userInfo.name = "Backend Admin";
        userInfo.token = "admin";
        return userInfo;
    }

    public UserInfo getUserInfo(HttpServletRequest httpServletRequest) {
//        logger.info("Get user info called");
//        UserInfo userInfo = getDefaultUser();
//        return RestUtil.toJson(userInfo);
//
        if(httpServletRequest.getCookies() == null) {
            return defaultUser;
        }
        String token = "";
        for (Cookie cookie : httpServletRequest.getCookies()) {
            if(cookie.getName().equals(RestAuthenticationSuccessHandler.JOKE_TOKEN_NAME)) {
                token = cookie.getValue();
            }
        }
        List<String> results = tokenService.getUsernameAndRolebyToken(token);
        if(CollectionUtils.isEmpty(results)) {
            return defaultUser;
        }

        UserInfo userInfo = new UserInfo();
        userInfo.avatar = "";
        userInfo.introduction = "";
        userInfo.roles = new String[7];
        if(results.get(1)!=null&&results.get(1).length()>=0&&!results.get(1).contains("\\|")) {
            String[] splitedRoles = results.get(1).split("\\|");
            for(int i=0;i<splitedRoles.length;i++) {
                userInfo.roles[i] = splitedRoles[i];
            }
        }else {
            userInfo.roles[0] = results.get(1);
        }
        userInfo.name =results.get(0);
        userInfo.token = "";
        return userInfo;

    }

    @GetMapping(GET_USER_PATH)
    public String getUserPath(HttpServletRequest httpServletRequest) {
//        logger.info("Get user info called");
//        UserInfo userInfo = getDefaultUser();
//        return RestUtil.toJson(userInfo);
//
//        if(httpServletRequest.getCookies() == null) {
//            return RestUtil.toJson(defaultUser);
//        }
//        String token = "";
//        for (Cookie cookie : httpServletRequest.getCookies()) {
//            if(cookie.getName().equals(RestAuthenticationSuccessHandler.SMALL_LOAN_TOKEN_NAME)) {
//                token = cookie.getValue();
//            }
//        }
//        List<String> results = tokenService.getUsernameAndRolebyToken(token);
//        if(CollectionUtils.isEmpty(results)) {
//            return RestUtil.toJson(defaultUser);
//        }
//
//        UserInfo userInfo = new UserInfo();
//        userInfo.avatar = "";
//        userInfo.introduction = "";
//        userInfo.roles = new String[1];
//        userInfo.roles[0] = results.get(1);
//        userInfo.name =results.get(0);
//        userInfo.token = "";
        return RestUtil.toJson(getUserInfo(httpServletRequest));

    }
    
    
    @RequestMapping({"/jokes/userRegister"})
	@ResponseBody
	public Response registerPage(HttpServletRequest httpServletRequest,
//			JokeTransactionBean jokeTransactionBean,
			@RequestBody RegisterUserinfo registerUserInfo
//			@RequestParam(value = "email", required = false) String email,
//			@RequestParam(value = "username", required = false) String username,
//			@RequestParam(value = "password", required = false) String password,
//			@RequestParam(value = "confirmpassword", required = false) String confirmpassword
			) throws ParseException {
		RestfullPage restfullPage = new RestfullPage();
		
		String email = registerUserInfo.getEmail();
		String username = registerUserInfo.getUsername();
		String password = registerUserInfo.getPassword();
		String confirmpassword = registerUserInfo.getConfirmpassword();
		
		if(null == password || password.isEmpty() || password.length()<6) {
			restfullPage.setCode(-1);
			restfullPage.setData("The password can not be less than 6 digits");
		}else {
			if(password.equals(confirmpassword)) {
				EmailValidator emailValidator = new EmailValidator();
				if(emailValidator.isValid(email, null)) {
					if(userService.isEmailInUse(email)) {
						restfullPage.setCode(-4);
						restfullPage.setData("email in use");
						restfullPage.setMsg("failed!");
					}else if(userService.isUsernameInUse(username)) {
						restfullPage.setCode(-5);
						restfullPage.setData("username in use");
						restfullPage.setMsg("failed!");
					}else {
						if(userService.createUser(username, password, "general", "", email, "general", "", null)>1) {
							restfullPage.setCode(20000);
							restfullPage.setData("congratulations "+username+"! user created successfully!");
							restfullPage.setMsg("success!");
						}else {
							restfullPage.setCode(-6);
							restfullPage.setData("sorry network error, please try later");
							restfullPage.setMsg("failed!");
						}
					}
				}else {
					restfullPage.setCode(-3);
					restfullPage.setData("email not valid");
					restfullPage.setMsg("failed!");
				}
			}else {
				restfullPage.setCode(-2);
				restfullPage.setData("The password mismatch");
				restfullPage.setMsg("failed!");
			}
		}
		
		restfullPage.setTotal(1);
		
		return restfullPage;
	}
}

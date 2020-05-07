package jokes.beans.auth;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import jokes.services.TokenService;
import jokes.beans.auth.TokenEntity;
import jokes.utils.RestUtil;

@Component
public class RestAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	public static final String JOKE_TOKEN_NAME = "joke-auth-token";
	
	@Autowired
	TokenService tokenService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String userRole = "";
        if (!authentication.getAuthorities().isEmpty()) {
            Iterator<GrantedAuthority> iteratorAuth = (Iterator<GrantedAuthority>) authentication.getAuthorities().iterator();
            while (iteratorAuth.hasNext()) {
                userRole = userRole + iteratorAuth.next().getAuthority();
            }
        }

        User user = (User) authentication.getPrincipal();


        TokenEntity tokenEntity = tokenService.createToken(user.getUsername());

        String body = "code:0,token=" + tokenEntity.getToken() + ",message:success";
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setContentType("application/json;charset=utf-8");

        Cookie authCookie = new Cookie(JOKE_TOKEN_NAME, tokenEntity.getToken());
        authCookie.setMaxAge(86400);
        authCookie.setHttpOnly(false);
        authCookie.setPath("/");
        response.addCookie(authCookie);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", tokenEntity.getToken());

        response.getWriter().print(RestUtil.toSuccessResult(null, jsonObject, 0));
        response.getWriter().flush();
		
	}
}

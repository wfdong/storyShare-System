package jokes.beans.auth;

import java.io.IOException;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alibaba.fastjson.JSON;

import jokes.services.TokenService;
import jokes.utils.RestUtil;

@Component
public class BasicTokenFilter extends OncePerRequestFilter {

	private Set<String> ignoredUriSet = new HashSet<>();
	
	@Autowired
	private TokenService tokenService;
	
	private static Pattern imagesPattern = Pattern.compile(
		      "^/image/\\d{4}-\\d{2}-\\d{2}/");
	
	public BasicTokenFilter() {
		this.ignoredUriSet.add("/jokes/login");
		this.ignoredUriSet.add("/jokes/userRegister");
		this.ignoredUriSet.add("/jokes/logout");
		this.ignoredUriSet.add("/jokes/userinfo");
		this.ignoredUriSet.add("/jokes/index");
		this.ignoredUriSet.add("/jokes/getSpecificItem");
//		this.ignoredUriSet.add("/favicon.ico");
//		this.ignoredUriSet.add("/jokes");
		this.ignoredUriSet.add("/rest/v1/file/preview");
//		this.ignoredUriSet.add("/");
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
//		if("/jokes/userRegister".equals(httpServletRequest.getRequestURI())) {
////			response.setHeader("Access-Control-Allow-Origin", "*");
////			response.setHeader("Cache-Control", "no-cache");
//			response.setHeader("Access-Control-Allow-Origin", "*");
//			response.setHeader("Access-Control-Allow-Credentials", "true");
//			response.setHeader("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT");
//			response.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
////            filterChain.doFilter(httpServletRequest, response);
//            return;
//        }
		
		if(HttpMethod.OPTIONS.name().equalsIgnoreCase(httpServletRequest.getMethod())) {
            filterChain.doFilter(httpServletRequest, response);
            return;
        }
		
		if(imagesPattern.matcher(httpServletRequest.getRequestURI()).lookingAt()) {
//			response.setHeader("Access-Control-Allow-Origin", "*");
//			response.setContentType("text/html");
			filterChain.doFilter(httpServletRequest, response);
            return;
		}
		
        if(this.ignoredUriSet.contains(httpServletRequest.getRequestURI())) {
            filterChain.doFilter(httpServletRequest, response);
            return;
        }
        Cookie cookie = getCookie(httpServletRequest, RestAuthenticationSuccessHandler.JOKE_TOKEN_NAME);
        if(cookie == null) {
            sendErrorResponse(httpServletRequest, response);
            return;
        }
        
        if(! tokenService.checkToken(cookie.getValue())) {
            sendErrorResponse(httpServletRequest, response);
            return;
        }
        
        filterChain.doFilter(httpServletRequest, response);
	}
	
	private String getUserName(String resttoken) {
        try {
            String[] tokenAndUsername = resttoken.split("_");
            String extractedToken =tokenAndUsername[0];
            byte[] asBytes = Base64.getDecoder().decode(tokenAndUsername[1]);
            String extractedUsername = new String(asBytes, "utf-8");
            return extractedUsername;
        } catch (Exception e) {
            logger.error("ERROR ", e);
            return "NULL";
        }
    }
	
	private String getSimpleIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
	
	private void sendErrorResponse(HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException {
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Origin",httpServletRequest.getHeader("Origin"));
        response.addHeader("Access-Control-Request-Method","*");
        response.addHeader("Access-Control-Request-Headers","Origin, X-Requested-With, Content-Type, Accept, Authorization");

        response.getWriter().print(RestUtil.toFailedResult(RestUtil.ERROR_CODE_ILLEGAL_INNER_TOKEN, "Wrong username or password", null));
        response.getWriter().flush();
    }
	
	private Cookie getCookie(HttpServletRequest httpServletRequest, String name) {
        if(httpServletRequest.getCookies() == null) {
            return null;
        }
        for (Cookie cookie : httpServletRequest.getCookies()) {
            if(cookie.getName().equals(name)) {
                return cookie;
            }
        }

        return null;
    }

}

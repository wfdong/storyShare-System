package jokes.beans.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jokes.utils.RestUtil;

/**
 * 该handler处理用户已经登录，但某个api无权限的情况
 */
@Component
public class RestAuthDeniedHandler implements AccessDeniedHandler {

	 @Override
	    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
//	        response.addHeader("Access-Control-Allow-Credentials", "true");
//	        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
	        response.addHeader("Access-Control-Request-Method","*");
	        response.addHeader("Access-Control-Request-Headers","Origin, X-Requested-With, Content-Type, Accept, Authorization");

//	        response.getWriter().print("{\"code\":400,\"message\":\""+exception.getMessage()+"\"}");
	        response.getWriter().print(RestUtil.toFailedResult(RestUtil.ERROR_CODE_NO_PERMISSION, "No permission for this page", null));
	        response.getWriter().flush();
	    }
}

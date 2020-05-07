package jokes.beans.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jokes.utils.RestUtil;

@Component
public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        response.addHeader("Access-Control-Request-Method","*");
        response.addHeader("Access-Control-Request-Headers","Origin, X-Requested-With, Content-Type, Accept, Authorization");

//        response.getWriter().print("{\"code\":400,\"message\":\""+exception.getMessage()+"\"}");
        response.getWriter().print(RestUtil.toFailedResult(RestUtil.ERROR_CODE_NO_PERMISSION, "Wrong username or password", null));
        response.getWriter().flush();
		
	}

}

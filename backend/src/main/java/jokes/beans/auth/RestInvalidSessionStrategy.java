package jokes.beans.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Component;

import jokes.utils.RestUtil;

@Component
public class RestInvalidSessionStrategy implements InvalidSessionStrategy {

	@Override
	public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
//		if (!"/jokes/userRegister".equals(request.getRequestURI())) {
			response.addHeader("Access-Control-Allow-Credentials", "true");
			response.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
			response.addHeader("Access-Control-Request-Method", "*");
			response.addHeader("Access-Control-Request-Headers",
					"Origin, X-Requested-With, Content-Type, Accept, Authorization");
//      response.getWriter().print("{\"code\":400,\"message\":\""+exception.getMessage()+"\"}");
			response.getWriter()
					.print(RestUtil.toFailedResult(RestUtil.ERROR_CODE_ILLEGAL_TOKEN, "Token expired", null));
			response.getWriter().flush();

//		}else {
//			response.setHeader("Access-Control-Allow-Origin", "*");
//			response.setHeader("Cache-Control", "no-cache");
//		}

	}

}

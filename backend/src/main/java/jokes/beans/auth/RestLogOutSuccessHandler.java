package jokes.beans.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import jokes.utils.RestUtil;

@Component
public class RestLogOutSuccessHandler implements LogoutSuccessHandler {

	@Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Origin",httpServletRequest.getHeader("Origin"));
        response.addHeader("Access-Control-Request-Method","*");
        response.addHeader("Access-Control-Request-Headers","Origin, X-Requested-With, Content-Type, Accept, Authorization");

//        response.getWriter().print("{\"code\":400,\"message\":\""+exception.getMessage()+"\"}");
        response.getWriter().print(RestUtil.toFailedResult(RestUtil.SUCCESS_CODE, "success logout", null));
        response.getWriter().flush();
    }
}

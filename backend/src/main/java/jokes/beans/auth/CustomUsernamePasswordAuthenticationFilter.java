package jokes.beans.auth;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import jokes.beans.auth.UserBean;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private String jsonUsername;
    private String jsonPassword;
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        ObjectMapper mapper = new ObjectMapper();
        UsernamePasswordAuthenticationToken authRequest = null;
        try (InputStream is = request.getInputStream()) {
            UserBean authenticationBean = mapper.readValue(is, UserBean.class);
            logger.info("User:" + authenticationBean.getUsername());
            authRequest = new UsernamePasswordAuthenticationToken(
                    authenticationBean.getUsername(), authenticationBean.getPassword());
        } catch (IOException e) {
            logger.error("ERROR ", e);
            authRequest = new UsernamePasswordAuthenticationToken(
                    "", "");
        } finally {
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }
}

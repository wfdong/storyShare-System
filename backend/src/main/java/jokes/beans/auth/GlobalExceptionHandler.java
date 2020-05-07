package jokes.beans.auth;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import jokes.utils.RestUtil;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        logger.error("ERROR ", e);

        return RestUtil.toFailedResult(RestUtil.ERROR_CODE_EXCEPTION, e.getMessage(), null);
    }
}

package stu.napls.copdmanage.core.security.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import stu.napls.copdmanage.core.constant.Code;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Yepeng Ding
 */
@Service("customAuthenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final static Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        logger.info("User (" + request.getParameter("username") + ") " + "login from " + request.getRemoteAddr());
        this.returnJson(response);
    }

    private void returnJson(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        String message = "Login successfully";
        response.getWriter().println("{\"code\":" + Code.SUCCESS + ",\"message\":\"" + message + "\",\"serverTime\": " + System.currentTimeMillis() + "}");
    }
}

package stu.napls.copdmanage.core.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Service;
import stu.napls.copdmanage.core.response.Response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Yepeng Ding
 */
@Service("customLogoutSuccessHandler")
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        clearToken(authentication, httpServletRequest);
        this.returnJson(httpServletResponse);
    }

    private void clearToken(Authentication authentication, HttpServletRequest request) {
        if (authentication == null)
            return;
//        UserDetails user = (UserDetails) authentication.getPrincipal();
        HttpSession session = request.getSession();
        String sessionToken = String.valueOf(session.getAttribute("token"));
        String sessionUsername = String.valueOf(session.getAttribute("username"));
        String sessionRoles = String.valueOf(session.getAttribute("roles"));
        if (authentication.getPrincipal() != null) {
            if (!sessionToken.equals("null")) {
                session.removeAttribute("token");
            }
            if (!sessionUsername.equals("null")) {
                session.removeAttribute("username");
            }
            if (!sessionRoles.equals("null")) {
                session.removeAttribute("roles");
            }
            session.invalidate();
        }
    }

    private void returnJson(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        String message = "Logout successfully";
        response.getWriter().println(mapper.writeValueAsString(Response.success(message)));
    }
}

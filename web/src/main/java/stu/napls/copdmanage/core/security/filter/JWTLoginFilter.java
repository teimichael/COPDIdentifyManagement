package stu.napls.copdmanage.core.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import stu.napls.copdmanage.config.GlobalConstantKey;
import stu.napls.copdmanage.core.response.Response;
import stu.napls.copdmanage.core.security.model.LoginParam;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * @author Yepeng Ding
 */
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    // Accept and parse authentication
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            LoginParam user = new ObjectMapper().readValue(req.getInputStream(), LoginParam.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword(),
                            new ArrayList<>()
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        // builder the token
        String token = null;
        try {
            Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
            List<String> roleList = new ArrayList<>();
            for (GrantedAuthority grantedAuthority : authorities) {
                roleList.add(grantedAuthority.getAuthority());
            }
            Calendar calendar = Calendar.getInstance();
            Date now = calendar.getTime();
            calendar.setTime(new Date());
            calendar.add(Calendar.HOUR, 24);// 24 hours
            Date time = calendar.getTime();
            token = Jwts.builder()
                    .setSubject(auth.getName() + "-" + roleList)
                    .setIssuedAt(now)
                    .setExpiration(time)
                    .signWith(SignatureAlgorithm.HS512, GlobalConstantKey.JWT_SIGNING_KEY)
                    .compact();

            saveToSession(request, token, auth.getName(), roleList);
            constructResponse(response, token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveToSession(HttpServletRequest request, String token, String username, List<String> roles) {
        HttpSession session = request.getSession();
        session.setAttribute("token", token);
        session.setAttribute("username", username);
        session.setAttribute("roles", roles);
    }

    private void constructResponse(HttpServletResponse response, String token) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.addHeader("Authorization", "Bearer " + token);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        String message = "Login successfully";
        String resToken = "Bearer " + token;
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().println(mapper.writeValueAsString(Response.success(message,resToken)));
    }

}

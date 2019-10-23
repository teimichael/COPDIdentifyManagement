package stu.napls.copdmanage.core.security.filter;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import stu.napls.copdmanage.config.GlobalConstantKey;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yepeng Ding
 */
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        long start = System.currentTimeMillis();
        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty()) {
//            throw new TokenException("Token为空");
            logger.info("Token is empty");
            return null;
        }

        String sessionToken = String.valueOf(request.getSession().getAttribute("token"));
        if (sessionToken.equals("null")) {
            logger.info("Session token has expired.");
            return null;
        }


        // parse the token.
        String user = null;
        try {
            user = Jwts.parser()
                    .setSigningKey(GlobalConstantKey.JWT_SIGNING_KEY)
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody()
                    .getSubject();
            long end = System.currentTimeMillis();

            logger.info("Authentication consumed", (end - start) + " ms");
            if (user != null) {
                String[] split = user.split("-")[1].split(",");
                List<GrantedAuthority> authorities = new ArrayList<>();
                for (int i = 0; i < split.length; i++) {
                    authorities.add(new SimpleGrantedAuthority(split[i]));
                }
                return new UsernamePasswordAuthenticationToken(user, null, authorities);
            }

        } catch (ExpiredJwtException e) {
            logger.info("Token expired: " + e.getMessage());
//            throw new TokenException("Token已过期");
        } catch (UnsupportedJwtException e) {
            logger.info("Token is unsupported: " + e.getMessage());
//            throw new TokenException("Token格式错误");
        } catch (MalformedJwtException e) {
            logger.info("Token is malformed" + e.getMessage());
//            throw new TokenException("Token没有被正确构造");
        } catch (SignatureException e) {
            logger.info("Token signature failed: " + e.getMessage());
//            throw new TokenException("签名失败");
        } catch (IllegalArgumentException e) {
            logger.info("Illegal argument: " + e.getMessage());
//            throw new TokenException("非法参数异常");
        }

        return null;
    }

}

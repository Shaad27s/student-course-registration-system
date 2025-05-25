package SCRMS.major.example.SCRM.project.JWTconfigraution;

import SCRMS.major.example.SCRM.project.SecurityConfig.UserDtl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter  extends OncePerRequestFilter {
    private static  final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    @Autowired
    private jwtService jwtService;
    @Autowired
    private ApplicationContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       String authHeader = request.getHeader("Authorization");
        logger.debug("Authorization header received: {}", authHeader);
        // System.out.println("Auth Header: " + authHeader);
        String token;
        String userName;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            userName = jwtService.extractUsername(token);
            logger.debug("Username extracted from token: {}", userName);

           // System.out.println("Extracted username: " + userName);


            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = context.getBean(UserDtl.class).loadUserByUsername(userName);
                logger.debug("UserDetails loaded for username: {}", userName);

                if (jwtService.validToken(token, userDetails)) {

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    logger.info("Authentication set in SecurityContextHolder for user: {}", userName);
                   // System.out.println("Authorities set: " + authToken.getAuthorities());
                  }
                }
            }

            filterChain.doFilter(request, response);
        }
    }

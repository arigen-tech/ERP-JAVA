//package com.erp.jwt;
//
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.MalformedJwtException;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//@RequiredArgsConstructor
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//
//    private final Logger LOGGER= LoggerFactory.getLogger(JwtAuthenticationFilter.class);
//    private final TokenBlackListService blackListService;
//    private final UserDetailsService userDetailsService;
//    private final  JwtHelper jwtHelper;
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        try {
//            if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
//                handlePreflightRequest(request,response);
//                return;
//            }
//            String authorizationHeader=request.getHeader("Authorization");
//
//            if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")){
//                String token = authorizationHeader.substring(7);
//                //Check if token is blacklisted or not
//                if(blackListService.isBlackListed(token)){
//                    LOGGER.info("Token is blacklisted");
//                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                    return;
//                }
//                //Extract Username
//                String username = extractUsernameFromToken(token);
//                if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
//                    authenticateUser(request,token,username);
//                }
//
//            }else{
//                LOGGER.info("Invalid or missing Authorization header.");
//            }
//            filterChain.doFilter(request,response);
//
//        } catch (Exception e) {
//            logger.error("Error occurred while processing the JWT token.", e);
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//        }
//    }
//
////Extract the username
//    private String extractUsernameFromToken(String token){
//        try {
//            return  jwtHelper.getUserNameFromToken(token);
//        }catch (IllegalArgumentException e){
//            LOGGER.error("Illegal argument while fetching username from token .",e);
//        }catch (ExpiredJwtException e){
//            LOGGER.warn("Jwt token is expired .",e);
//        } catch (MalformedJwtException e) {
//            LOGGER.error("Jwt token is Malformed .",e);
//        } catch (Exception e) {
//          LOGGER.error("Unexpected error while fetching error .",e);
//        }
//        return  null;
//    }
////Authentication user
//    private void authenticateUser(HttpServletRequest req,String token,String username){
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        if(jwtHelper.validateToken(token,userDetails)){
//            UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
//            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            LOGGER.info("User authenticated successfully {}",username);
//        }else{
//            LOGGER.warn("Token validation failed for user {}",username);
//        }
//    }
////    Sent by browser automatically to check if it can make a cross-origin request
//    private void handlePreflightRequest(HttpServletRequest request,HttpServletResponse response){
//        String origin=request.getHeader("Origin");
//        if(origin !=null && !origin.isEmpty()){
//            response.setHeader("Access-Control-Allow-Origin", origin); // allow only known origins;
//        }
//
//        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
//        response.setHeader("Access-Control-Allow-Credentials", "true");// if needed for cookies
//        response.setStatus(HttpServletResponse.SC_OK);
//    }
//}

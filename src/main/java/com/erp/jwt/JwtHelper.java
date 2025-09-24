//package com.erp.jwt;
//
//import com.erp.entity.UserEn;
//import com.erp.exception.ERPException;
//import com.erp.repo.UserRepo;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//@RequiredArgsConstructor
//@Component
//public class JwtHelper {
//
//    private final UserRepo userRepo;
//    private final HttpServletRequest request;
//
//    private final String SECRET="1KCrT4BFo9EMUNJjQ0y8VswrKFSJmIHp1jZJVP1IU5999EOqb3E1gmNpf5FzYXIZrwpPDHLhRcORigN84ftPfuOt2Q2IKTmRfJP5RRhRCfJJ2wJ4vlMK70fWFeIT5QBE";  //128 char
//    public static final long JWT_TOKEN_VALIDITY = 7 * 24 * 60 * 60; // 30 minutes in seconds
//    public static final long REFRESH_TOKEN_VALIDITY = 7 * 24 * 60 * 60; // 7 days in seconds
////Generating new token through private method
//    public String generateAccessToken(UserDetails userDetails){
//        Map<String,Object> claims=new HashMap<>();
//        return  doGenerateToken(claims,userDetails.getUsername(),JWT_TOKEN_VALIDITY);
//    }
////Generating Refresh token through same private method
//    public String generateRefreshToken(UserDetails userDetails){
//        Map<String,Object> claims=new HashMap<>();
//        return  doGenerateToken(claims,userDetails.getUsername(),REFRESH_TOKEN_VALIDITY);
//    }
////to generate token
//    private String doGenerateToken(Map<String,Object> claims,String subject,long validity){
//
//        return Jwts.builder()
//                   .setClaims(claims)
//                   .setSubject(subject)
//                   .setIssuedAt(new Date(System.currentTimeMillis()))
//                   .setExpiration(new Date(System.currentTimeMillis()+validity*1000))
//                   .signWith(SignatureAlgorithm.HS512,SECRET)
//                   .compact();
//    }
////get the bearer token from header
//    public String getTokenFromHeader(){
//        String token = request.getHeader("Authorization");
//        String tokenWithoutBearer;
//        if(token==null){
//            throw  new ERPException(HttpStatus.UNAUTHORIZED.value(), "INVALID TOKEN LOGIN AGAIN.IN-001");
//        }
//        if(!token.startsWith("Bearer ")){
//            throw  new ERPException(HttpStatus.UNAUTHORIZED.value(), "INVALID TOKEN LOGIN AGAIN.IN-002");
//        }
//        try {
//            tokenWithoutBearer=token.substring(7);
//        }catch (Exception e){
//            throw  new ERPException(HttpStatus.UNAUTHORIZED.value(), "INVALID TOKEN LOGIN AGAIN.IN-003");
//        }
//        return tokenWithoutBearer;
//    }
////Get all the details regarding  token
//    public Claims  getAllClaimsFromToken(String token){
//        return  Jwts.parser()
//                    .setSigningKey(SECRET)
//                    .parseClaimsJws(token)
//                    .getBody();
//    }
////Get individual property regarding token like expDate,username....
//    public <T> T getClaimsFromToken(String token, Function<Claims,T> claimResolver){
//        final  Claims claims=getAllClaimsFromToken(token);
//        return  claimResolver.apply(claims);
//    }
////get user entity from token
//    public UserEn getUserObject(String token){
//        String user = getClaimsFromToken(token, Claims::getSubject);
//        return userRepo.findByUsername(user);
//    }
////get username from the token
//    public String getUserNameFromToken(String token){
//        return getClaimsFromToken(token,Claims::getSubject);
//    }
////get token expire date
//    public Date getExpDateFromToken(String token){
//        return  getClaimsFromToken(token,Claims::getExpiration);
//    }
////get token expire time
//    public long getExpirationTime(String token){
//        return  getExpDateFromToken(token).getTime();
//    }
////check token expired or not
//    public Boolean isTokenExpired(String token){
//        return getExpDateFromToken(token).before(new Date());
//    }
////Validate token
//    public Boolean validateToken(String token,UserDetails userDetails){
//        try {
//            Claims claims = Jwts.parser()
//                                .setSigningKey(SECRET)
//                                .parseClaimsJws(token)
//                                .getBody();
//
//            Jwts.parser()
//                    .setSigningKey(SECRET)
//                    .parseClaimsJws(token);
//
//            final String username=getUserNameFromToken(token);
//            return  username.equals(userDetails.getUsername()) && !isTokenExpired(token);
//        }catch (JwtException | IllegalArgumentException e){
//            return  false;
//        }
//    }
//
//
//
//
//
//
//}

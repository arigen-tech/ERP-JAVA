//package com.erp.jwt;
//
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//
//@Component
//public class TokenBlackListService {
//
//    private final Map<String,Long>  blackListedTokens= new ConcurrentHashMap<>();
////adding black list
//    public void addToBlackList(String token,Long expTime){
//        blackListedTokens.put(token,expTime);
//    }
////validating token
//    public Boolean isBlackListed(String token){
//        Long expiry=blackListedTokens.get(token);
//        if(expiry!=null && expiry > System.currentTimeMillis()){
//            return  true;
//        }
//        blackListedTokens.remove(token);//clean up expired tokens
//        return false;
//    }
//}

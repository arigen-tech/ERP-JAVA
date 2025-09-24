//package com.erp.jwt;
//
//import com.erp.entity.UserEn;
//import com.erp.repo.UserRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomUserDetailsImpl implements UserDetailsService {
//
//    @Autowired
//    private UserRepo userRepo;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        UserEn user = userRepo.findByUsernameAndStatus(username, "y");
//        if(user==null){
//            throw  new UsernameNotFoundException("User Not Found...!!");
//        }
//        System.out.println("CustomUserDetailsImpl is Executed");
//        return user;
//    }
//}

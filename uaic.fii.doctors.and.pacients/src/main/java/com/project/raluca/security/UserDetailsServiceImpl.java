//package com.project.raluca.security;
//
//import com.project.raluca.model.Role;
//import com.project.raluca.model.User;
//import com.project.raluca.repository.UserRepository;
//import java.util.ArrayList;
//import java.util.List;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//
//@Service("userDetailsService")
//public class UserDetailsServiceImpl implements UserDetailsService {
//    @Autowired
//    private UserRepository userRepository;
//
////    @Override
////    @Transactional(readOnly = true)
////    public UserDetails loadUserByUsername(String username) {
////        User user = userRepository.findByUsername(username);
////        if (user == null) throw new UsernameNotFoundException(username);
////
////        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//////        for (Role role : user.getRoles()){
//////            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
//////        }
////
////        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
////    }
//}
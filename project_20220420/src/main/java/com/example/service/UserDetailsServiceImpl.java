package com.example.service;

import java.util.Collection;

import com.example.entity.entity1.Member;
import com.example.entity.entity1.MemberPersonal;
import com.example.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 로그인에서 버튼을 누르면 서비스로 이메일이 전달됨
@Service
public class UserDetailsServiceImpl implements 
    UserDetailsService{

    @Autowired MemberRepository mRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("UserDetailsService: " + username);
        Member member = mRepository.getById(username);
        System.out.println(member.toString());

        // 권한 정보를 문자열 배열로 만듦
        String[] strRole = { member.getMaddress() };

        // String 배열 권한을 Collection<Granted...>로 변환함
        Collection<GrantedAuthority> roles = 
            AuthorityUtils.createAuthorityList(strRole);

        // UserDetails -> User
        User user = 
        new User(member.getMid(), member.getMpw(), roles);
        return user;
    }
    
}

package com.example.config;

import com.example.service.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    // 1. 직접 만든 detailservice 객체 가져오기
    @Autowired
    UserDetailsServiceImpl detailsService;

    // 회원가입시 암호화했던 방법의 객체 생성
    // 2. 암호화 방법 객체 생성
    @Bean 
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 3. 직접 만든 detailsService에 암호화 방법 적용
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailsService)
            .passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // super.configure(http);
        http.csrf().ignoringAntMatchers("/h2-console/**");
        http.headers().frameOptions().sameOrigin();

        // // rest controller 사용
        // http.csrf().ignoringAntMatchers("/api/**");

        // // 페이지별 접근 권한 설정
        // http.authorizeRequests()
        // .antMatchers("/security_admin", "/security_admin/**")
        // .hasAuthority("ADMIN")
        // .antMatchers("/security_seller", "/security_seller/**")
        // .hasAnyAuthority("ADMIN", "SELLER")
        // .antMatchers("/security_customer", "/security_customer/**")
        // .hasAuthority("CUSTOMER")
        // .anyRequest().permitAll();

        // // 로그인 페이지 설정, 단 POST는 직접 만들지 않음
        // http.formLogin()
        //     .loginPage("/member/login")
        //     .loginProcessingUrl("/member/loginaction")
        //     .usernameParameter("uemail")
        //     .passwordParameter("upw")
        //     .defaultSuccessUrl("/home")
        //     .permitAll();

        // // 로그아웃 페이지 설정, url에 맞게 post로 호출하면 됨.
        // http.logout()
        // .logoutUrl("/member/security_logout")
        // .logoutSuccessUrl("/home")
        // // .logoutSuccessHandler(new MyLogoutSeccessHandler())
        // .invalidateHttpSession(true)
        // .clearAuthentication(true)
        // .permitAll();

		// // 접근권한불가 403
		// http.exceptionHandling().accessDeniedPage("/page403");
    }    
}

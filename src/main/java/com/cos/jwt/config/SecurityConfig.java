package com.cos.jwt.config;

import com.cos.jwt.Filter.MyFilter1;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;

    @Override
    protected void configure(HttpSecurity http)throws Exception {
        // BasicAuthenticationFilter 가 실행되기 전에 MyFilter1 가 실행된다
        http.addFilterBefore(new MyFilter1(), BasicAuthenticationFilter.class);
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //세션을 사용하지 않겠다
                .and()
                .addFilter(corsFilter) //인증이 있을때 시큐리티 필터에 등록
                .formLogin().disable()  // 폼태그 만들어서 로그인하지 않겠다
                .httpBasic().disable()  // http basic 방식 안쓰겠다 -> jwt 토큰을 만들어주고 로그인 요청이오면 그걸 돌려줘서 토큰을 통해서 로그인하는 방식으로 진행하겠다
                .authorizeRequests()
                .antMatchers("/api/v1/user/**") //이쪽으로 주소가 들어오면
                .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/manager/**")
                .access("hasRole('ROLE_MANAGER') or ('ROLE_ADMIN')")
                .antMatchers("/api/v1/admin/**")
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll(); //다른 요청은 전부 들어갈 수 있게
    }

}

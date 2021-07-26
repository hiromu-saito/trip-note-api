package com.example.trip_note_api;


import com.example.trip_note_api.domain.security.SimpleAuthenticationEntryPoint;
import com.example.trip_note_api.domain.security.SimpleAuthenticationFailureHandler;
import com.example.trip_note_api.domain.security.SimpleAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //認証
                .authorizeRequests()
                    .mvcMatchers("/preSignin","/signout","/signup")
                        .permitAll()
                    .anyRequest()
                        .authenticated()
                .and()
                //例外
                .exceptionHandling()
                //未認証のユーザーが認証の必要なAPIにアクセスしたときの処理を設定
                    .authenticationEntryPoint(authenticationEntryPoint())
                //ユーザーは認証済みだが未認可のリソースへアクセスしたときの処理を設定
//                    .accessDeniedHandler()
                .and()
                //ログイン
                .formLogin()
                    .loginProcessingUrl("/signin").permitAll()
                        .usernameParameter("mailAddress")
                        .passwordParameter("password")
                    .successHandler(authenticationSuccessHandler())
                    .failureHandler(authenticationFailureHandler())
                .and()
                //ログアウト
                .logout()
                    .logoutUrl("/signout")
                    .invalidateHttpSession(true)
                    //JSESSIONIDってどこからでてきた？
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessHandler(logoutSuccessHandler())
                .and()
                // CSRF
                .csrf()
                    //.disable()
                    //.ignoringAntMatchers("/login")
                    .ignoringAntMatchers("/signup")
                    .csrfTokenRepository(new CookieCsrfTokenRepository());
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth,
                                @Qualifier("simpleUserDetailsService") UserDetailsService userDetailsService,
                                PasswordEncoder passwordEncoder) throws Exception {

        auth.eraseCredentials(true)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    AuthenticationEntryPoint authenticationEntryPoint() {
        return new SimpleAuthenticationEntryPoint();
    }
    AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SimpleAuthenticationSuccessHandler();
    }
    AuthenticationFailureHandler authenticationFailureHandler() {
        return new SimpleAuthenticationFailureHandler();
    }
    LogoutSuccessHandler logoutSuccessHandler() {
        return new HttpStatusReturningLogoutSuccessHandler();
    }
}

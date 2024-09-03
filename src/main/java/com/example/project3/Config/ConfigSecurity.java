package com.example.project3.Config;

import com.example.project3.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@RequiredArgsConstructor
public class ConfigSecurity {
    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("api/v1/customer/register", "api/v1/employee/register").permitAll()
                .requestMatchers("api/v1/customer/updateCustomer/",
                        "api/v1/customer/deleteCustomer",
                        "api/v1/customer/getAllCustomers",
                        "api/v1/employee/deleteEmployee",
                        "api/v1/employee/updateEmployee",
                        "api/v1/employee/getAllEmployees",
                        "api/v1/account/deactivateAccount/").hasAuthority("ADMIN")
                .requestMatchers("api/v1/account/creatAccount",
                        "api/v1/account/activateAccount/",
                        "api/v1/account/getAccount/",
                        "api/v1/account/getAccounts",
                        "api/v1/account/deposit/",
                        "api/v1/account/withdraw/",
                        "api/v1/account/transfer/").hasAuthority("CUSTOMER")
//                .requestMatchers("").hasAuthority("EMPLOYEE")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/user/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();
    }
}

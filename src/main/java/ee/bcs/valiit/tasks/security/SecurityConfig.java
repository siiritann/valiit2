package ee.bcs.valiit.tasks.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity // ütleme springile et pane see security tööle
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{ // autoriseeri päringuid üle http
        http.authorizeRequests()
                .antMatchers("/","/home", "/pic.jpg", "/img/**").permitAll() // juhul kui päring tehakse /home pihta luba sinna kõik ligi
                .anyRequest().authenticated() // kõik muud päringud peavad olema kasutaja audenditud
                .and()
                // meil on selline login page:
                .formLogin()
                // võimalda sega login page-i kõigile useritele:
                .permitAll()
                .and()
                .logout()
                .permitAll();

    }

    @Bean // sellega loome beane
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}

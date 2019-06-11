package com.project.raluca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class EmbeddedDatabaseUsageDemo extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(EmbeddedDatabaseUsageDemo.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(EmbeddedDatabaseUsageDemo.class, args);
    }

  /*  public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repo) throws Exception{
        builder.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
                return new CustomUserDetails((repo.findByUsername(s)));
            }
        });
    }*/

}

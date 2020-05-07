package jokes.beans.auth;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsUtils;

import jokes.beans.auth.RestAuthDeniedHandler;
import jokes.beans.auth.RestAuthenticationFailureHandler;
import jokes.beans.auth.RestAuthenticationSuccessHandler;
import jokes.beans.auth.RestInvalidSessionStrategy;
import jokes.beans.auth.BasicTokenFilter;
import jokes.beans.auth.RestLogOutSuccessHandler;
import jokes.services.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConf extends WebSecurityConfigurerAdapter{

	@Autowired
    DataSource dataSource;
	
	@Autowired
    UserService userService;
	
	@Autowired
    RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;
    
    @Autowired
    RestAuthenticationFailureHandler restAuthenticationFailureHandler;

    @Autowired
    RestAuthDeniedHandler restAuthDeniedHandler;

    @Autowired
    RestInvalidSessionStrategy restInvalidSessionStrategy;
    
    @Autowired
    BasicTokenFilter basicTokenFilter;

    @Autowired
    RestLogOutSuccessHandler restLogOutSuccessHandler;
    
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
      return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
    
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserService();
    }
    
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {

        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	String[] permitAllUrls = {"/jokes/userRegister", "/jokes/index","/favicon.ico" ,"/jokes/getSpecificItem?detailrecord=**", "/image/**"};
//    	http.authorizeRequests().antMatchers("/jokes/userRegister").permitAll();
//    	http.authorizeRequests().antMatchers("/jokes/index").permitAll();
//    	http.authorizeRequests().antMatchers("/jokes/getSpecificItem?detailrecord=*").permitAll();
//    	http.authorizeRequests().antMatchers("/image/**").permitAll();
    	http.authorizeRequests().antMatchers(permitAllUrls).permitAll();
        http.csrf().disable()
                .authorizeRequests()
                // 所有 / 的所有请求 都放行
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                
//                .and().authorizeRequests().antMatchers(permitAllUrls).permitAll()
//                .and() // 1
//                .authorizeRequests()
//                .antMatchers("/jokes/index").permitAll().anyRequest().authenticated() 
                
//                .and() // 1
//                .authorizeRequests()
//                .antMatchers("/jokes/userRegister").permitAll().anyRequest().permitAll() 
//                .and() // 1
//                .authorizeRequests()
//                .antMatchers("/jokes").permitAll().anyRequest().authenticated() 
//                .and() // 1
////                .authorizeRequests()
////                .antMatchers("/jokes/login").permitAll().anyRequest().authenticated() 
////                .and() // 1
////                .authorizeRequests()
////                .antMatchers("/jokes/logout").permitAll().anyRequest().authenticated() 
////                .and() // 1
//                .authorizeRequests()
//                .antMatchers("/").permitAll().anyRequest().authenticated() 
                
                .and() // 1
                .authorizeRequests()
                .antMatchers("/jokes/addJokes")
                .authenticated()
                .and() // 2
                .authorizeRequests()
                .antMatchers("/jokes/addComments")
                .authenticated()
                
                .and() // 1
                .authorizeRequests()
                .antMatchers("/jokes/addALike")
                .authenticated()
//                .and() // 1
//                .authorizeRequests()
//                .antMatchers("/jokes/getSpecificItem")
//                .authenticated()
                .and() // 1
                .authorizeRequests()
                .antMatchers("/jokes/addADisLike")
                .authenticated()
                .and()
                .logout()
                .logoutUrl("/jokes/logout")
                .logoutSuccessHandler(restLogOutSuccessHandler)
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(restAuthDeniedHandler)
                .and();

        //session 过期处理
        http.sessionManagement().invalidSessionStrategy(restInvalidSessionStrategy);

        //用重写的Filter替换掉原有的UsernamePasswordAuthenticationFilter
        http.addFilterAt(customAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class);

        http.addFilterAfter(basicTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
    
  //注册自定义的UsernamePasswordAuthenticationFilter
    @Bean
    CustomUsernamePasswordAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomUsernamePasswordAuthenticationFilter filter = new CustomUsernamePasswordAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(restAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(restAuthenticationFailureHandler);
        filter.setFilterProcessesUrl("/jokes/login");
        RequestMatcher requestMatcher = new RequestMatcher() {
            @Override
            public boolean matches(HttpServletRequest request) {
                return request.getMethod().contains("POST") && "/jokes/login".equals(request.getServletPath());
            }
        };
        filter.setRequiresAuthenticationRequestMatcher(requestMatcher);
        filter.setPostOnly(true);

        //这句很关键，重用WebSecurityConfigurerAdapter配置的AuthenticationManager，不然要自己组装AuthenticationManager
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }
    
}

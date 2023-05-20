package server.bookmanagement.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;
import server.bookmanagement.security.auths.filter.JwtAuthenticationFilter;
import server.bookmanagement.security.auths.filter.JwtVerificationFilter;
import server.bookmanagement.security.auths.handler.MemberAuthenticationFailureHandler;
import server.bookmanagement.security.auths.handler.MemberAuthenticationSuccessHandler;
import server.bookmanagement.security.auths.jwt.JwtTokenizer;
import server.bookmanagement.security.auths.utils.CustomAuthorityUtils;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;
    private final CorsFilter corsFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .headers().frameOptions().sameOrigin() //H2 웹 콘솔을 정상적으로 사용할 수 있도록. 동일 출처로부터 들어오는 request만 페이지 렌더링을 허용
                .and()
                .csrf().disable()
                .cors(withDefaults()) //corsConfigurationSource라는 이름으로 등록된 Bean을 이용합니다.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .apply(new CustomFilterConfigurer())
                .and()
                .authorizeRequests(authorize -> authorize
                        .antMatchers(HttpMethod.GET, "/books/**").permitAll()
                        .antMatchers( "/books/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.GET, "/libraries/**").permitAll()
                        .antMatchers( "/libraries/**").hasRole("ADMIN")
                        .antMatchers("/libraries/inventories/**").hasRole("ADMIN")
                        .antMatchers("/loan/**").hasRole("USER")
                        .antMatchers("/return/**").hasRole("USER")
                        .antMatchers(HttpMethod.DELETE,"/members/**").hasRole("USER")
                        .antMatchers( "/members/**").permitAll()
                        .anyRequest().permitAll());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity>{
        @Override //configure() 메서드를 오버라이드 Configuration을 커스터마이징.
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);


            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer);
            jwtAuthenticationFilter.setFilterProcessesUrl("/auth/login");
            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new MemberAuthenticationSuccessHandler());
            jwtAuthenticationFilter.setAuthenticationFailureHandler(new MemberAuthenticationFailureHandler());

            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils);

            builder
                    .addFilter(corsFilter)
                    .addFilter(jwtAuthenticationFilter) //Authentication 이후에 verification 동작.
                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);

        }
    }
}

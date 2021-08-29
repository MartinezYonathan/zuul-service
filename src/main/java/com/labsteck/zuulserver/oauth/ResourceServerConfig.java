package com.labsteck.zuulserver.oauth;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author Manuel Perez Garrido
 * @since 16/09/20
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore());
    }

    /**
     * Video de Referencia:
     * https://www.udemy.com/course/microservicios-con-spring-boot-y-spring-cloud/learn/lecture/15373436#overview
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/V1/service-cmp-access/oauth/**").permitAll()
                .antMatchers("/api/V1/service-cmp-access/country/**").permitAll()
                .antMatchers("/api/V1/service-cmp-access/region/**").permitAll()
                .antMatchers("/api/V1/service-cmp-access/user/**").permitAll()
                // .antMatchers("/api/V1/service-cmp-access/rol/**").hasAnyAuthority(Roles.ROLE_ROOT)
                .antMatchers("/api/V1/service-cmp-access/rol/**").permitAll()
                .antMatchers("/api/V1/service-cmp-access/core_config_data/**").permitAll()
                .antMatchers("/api/V1/service-cmp-access/signin").permitAll()
                .antMatchers("/api/V1/service-cmp-medicos/medico/**").permitAll()
                .antMatchers("/api/V1/service-cmp-medicos/root/**").permitAll()
                .antMatchers("/api/V1/service-cmp-vendedores/vendedor/**").permitAll()
                .antMatchers("/api/V1/service-cmp-vendedores/root/**").permitAll()
                .antMatchers("/api/V1/service-cmp-productos/**").permitAll()
                .antMatchers("/api/V1/service-cmp-clientes/**").permitAll()
                .antMatchers("/api/V1/service-cmp-ventas/**").permitAll()
                .antMatchers("/api/V1/service-cmp-results/**").permitAll()
                .antMatchers("/api/V1/service-cmp-agenda/**").permitAll()
                .antMatchers("/api/V1/service-cmp-clinicas/**").permitAll()
                .antMatchers("/api/V1/service-cmp-newsletter/**").permitAll()
                .antMatchers("/api/V1/service-cmp-seguros/**").permitAll()
                .antMatchers("/api/V1/service-cmp-messenger/**").permitAll()
                .antMatchers("/api/V1/service-cmp-empresas/**").permitAll()
                .antMatchers("/api/V1/service-cmp-caja/**").permitAll()
                .antMatchers("/api/V1/service-cmp-documents/**").permitAll()
                //.anyRequest().authenticated()
                // Cors
                .and().cors().configurationSource(corsConfiguration());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        tokenConverter.setSigningKey("algun_codigo_secreto");
        return tokenConverter;
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public CorsConfigurationSource corsConfiguration() {
        CorsConfiguration config = new CorsConfiguration();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterFilterRegistrationBean() {
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfiguration()));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

}

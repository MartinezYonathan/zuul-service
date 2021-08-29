package mx.uacm.edu.incidencias.zuul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import mx.uacm.edu.incidencias.zuul.filter.ErrorFilter;
import mx.uacm.edu.incidencias.zuul.filter.PostFilter;
import mx.uacm.edu.incidencias.zuul.filter.PreFilter;
import mx.uacm.edu.incidencias.zuul.filter.RouteFilter;

/**
 * Created by fengdaqing on 2018/6/21.
 */
@Configuration
public class FilterConfig {
    @Bean
    public PreFilter preFilter() {
        return new PreFilter();
    }
    @Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }
    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }
    @Bean
    public RouteFilter routeFilter() {
        return new RouteFilter();
    }
}

//package ams.config;
//
//import java.util.Arrays;
//import java.util.Collections;
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.core.env.ConfigurableEnvironment;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
//@Configuration
//public class ApplicationConfig {
//
//	@Bean
//	public FilterRegistrationBean<CorsFilter> simpleCorsFilter(ConfigurableEnvironment environment) {
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		CorsConfiguration configuration = new CorsConfiguration();
//		configuration.setAllowCredentials(true);
//		String corsAllowOrigins = environment.getProperty("cors.allow.origins");
//		if (corsAllowOrigins != null) {
//			configuration.setAllowedOrigins(Arrays.asList(corsAllowOrigins.split(",")));
//		}
//		configuration.setAllowedMethods(Collections.singletonList(("*")));
//		configuration.setAllowedHeaders(Collections.singletonList(("*")));
//		source.registerCorsConfiguration("/**", configuration);
//		CorsFilter corsFilter = new CorsFilter(source);
//		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(corsFilter);
//		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//		return bean;
//	}
//}
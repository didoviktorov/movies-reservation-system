package org.movies.system.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private StartApplicationInterceptor startApplicationInterceptor;

    @Autowired
    public MvcConfig(StartApplicationInterceptor startApplicationInterceptor) {
        this.startApplicationInterceptor = startApplicationInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.startApplicationInterceptor)
                .addPathPatterns("/");
    }
}

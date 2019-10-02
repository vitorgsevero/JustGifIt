package vitorgsevero.io.justgifit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.FormContentFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.filter.RequestContextFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


import javax.annotation.PostConstruct;
import java.io.File;
import java.util.logging.Filter;


@SpringBootApplication(exclude = {JacksonAutoConfiguration.class, JmxAutoConfiguration.class, WebSocketServletAutoConfiguration.class})
public class JustGifItApplication {

     @Value("{$multipart.location}/gif/")
     private String gifLocation;

	public static void main(String[] args) {
		SpringApplication.run(JustGifItApplication.class, args);
	}

	@PostConstruct
    private void init(){
	    File gifFolder = new File(gifLocation);
	    if(!gifFolder.exists()){
	        gifFolder.mkdir();
        }
    }

    @Bean
    public FilterRegistrationBean deRegisterHttpMethodFilter(HiddenHttpMethodFilter filter){
	    FilterRegistrationBean bean = new FilterRegistrationBean(filter);
	    bean.setEnabled(false);
	    return bean;
    }


   @Bean
    public FilterRegistrationBean deRegisterFormContentFilter(FormContentFilter filter){
        FilterRegistrationBean bean = new FilterRegistrationBean(filter);
        bean.setEnabled(false);
        return bean;
    }

    @Bean
    public FilterRegistrationBean deRegisterRequestContextFilter(RequestContextFilter filter){
        FilterRegistrationBean bean = new FilterRegistrationBean(filter);
        bean.setEnabled(false);
        return bean;
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
	    return new WebMvcConfigurer() {
	        @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/gif/**")
                        .addResourceLocations("file:" + gifLocation);
            }
        };
    }

}

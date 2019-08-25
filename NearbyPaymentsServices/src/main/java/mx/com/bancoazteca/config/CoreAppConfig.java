package mx.com.bancoazteca.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.maps.GeoApiContext;

@Configuration
@ComponentScan(basePackages = {"mx.com.bancoazteca"})
@PropertySource(value = { "classpath:application.properties" }, ignoreResourceNotFound = true)
public class CoreAppConfig {
	
	private ClientHttpRequestFactory getClientHttpRequestFactory() {
	    int timeout = 120000;
	    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
	    clientHttpRequestFactory.setConnectTimeout(timeout);
	    clientHttpRequestFactory.setReadTimeout(timeout);
	    return clientHttpRequestFactory;
	}
	
	@Bean
    public RestTemplate restTemplate() {
        RestTemplate template = new RestTemplate();
        template.setRequestFactory(getClientHttpRequestFactory());
        
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new StringHttpMessageConverter());
        
        template.setMessageConverters(messageConverters);
        return template;
    }
	
	@Bean
	public Gson gson() {
        return new Gson();
	}
	
	@Bean
	public GeoApiContext geoApiContext() {
		return new GeoApiContext.Builder()
				.apiKey("AIzaSyCxiZkLEnp3xiwF31Ej1Gtmq5rUMHIx0Nc")
				.build();
	}
}

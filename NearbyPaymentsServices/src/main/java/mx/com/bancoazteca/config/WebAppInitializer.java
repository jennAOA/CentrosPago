package mx.com.bancoazteca.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import mx.com.bancoazteca.commons.Mappings;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	  @Override
	  protected Class<?>[] getRootConfigClasses() {
	    return new Class[] {CoreAppConfig.class};
	  }
	
	  @Override
	  protected Class<?>[] getServletConfigClasses() {
	    return new Class[] {SpringMVCConfig.class};
	  }
	
	  @Override
	  protected String[] getServletMappings() {
	    return new String[]{Mappings.EXPOSING_URL};
	  }
}

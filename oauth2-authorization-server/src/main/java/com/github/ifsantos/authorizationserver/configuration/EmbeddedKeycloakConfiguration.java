package com.github.ifsantos.authorizationserver.configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.naming.CompositeName;
import javax.naming.InitialContext;
import javax.naming.Name;
import javax.naming.NameParser;
import javax.naming.NamingException;
import javax.naming.spi.NamingManager;
import javax.sql.DataSource;

import org.jboss.resteasy.plugins.server.servlet.HttpServlet30Dispatcher;
import static org.jboss.resteasy.plugins.server.servlet.ResteasyContextParameters;
import org.keycloak.platform.Platform;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.ifsantos.authorizationserver.application.EmbeddedKeycloakApplication;
import com.github.ifsantos.authorizationserver.filter.EmbeddedKeycloakRequestFilter;

@Configuration
public class EmbeddedKeycloakConfiguration {
  @Bean
  ServletRegistrationBean keycloakJaxRsApplication(KeycloakServerProperties kcsProps, DataSource dataSource) throws Exception {
    mockJndiEnvironment(dataSource);
    EmbeddedKeycloakApplication.keycloakServerProperties = kcsProps;
    ServletRegistrationBean servlet = new ServletRegistrationBean<>(new HttpServlet30Dispatcher());
    servlet.addInitParameter("javax.ws.rs.Application", EmbeddedKeycloakApplication.class.getName());
    servlet.addInitParameter(RESTEASY_SERVLET_MAPPING_PREFIX, kcsProps.getContextPath());
    servlet.addInitParameter(RESTEASY_USE_CONTAINER_FORM_PARAMS, "true");
    servlet.addUrlMappings(kcsProps.getContextPath() + "/*");
    servlet.setLoadOnStartup(1);
    servlet.setAsyncSupported(true);
    return servlet;
  }

  @Bean
  FilterRegistrationBean keycloakSessionManagement(KeycloakServerProperties kcsProps) {
    FilterRegistrationBean filter = new FilterRegistrationBean<>();
    filter.setName("Keycloak Session Management");
    filter.setFilter(new EmbeddedKeycloakRequestFilter());
    filter.addUrlPatterns(kcsProps.getContextPath() + "/*");

    return filter;
  }

  private void mockJndiEnvironment(DataSource dataSource) throws NamingException {
    NamingManager.setInitialContextFactoryBuilder(
        (env) -> (environment) -> new InitialContext() {
          @Override
          public Object lookup(Name name) {
            return lookup(name.toString());
          }

          @Override
          public Object lookup(String name) {
            if ("spring/datasource".equals(name)) {
              return dataSource;
            } else if (name.startsWith("java:jboss/ee/concurrency/executor/")) {
              return fixedThreadPool();
            }
            return null;
          }

          @Override
          public NameParser getNameParser(String name) {
            return CompositeName::new;
          }

          @Override
          public void close() {
          }
        });
  }

  @Bean("fixedThreadPool")
  public ExecutorService fixedThreadPool() {
    return Executors.newFixedThreadPool(5);
  }

  @Bean
  @ConditionalOnMissingBean(name = "springBootPlatform")
  protected SimplePlatformProvider springBootPlatform() {
    return (SimplePlatformProvider) Platform.getPlatform();
  }
}

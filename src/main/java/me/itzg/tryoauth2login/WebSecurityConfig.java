package me.itzg.tryoauth2login;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private RestTemplateBuilder restTemplateBuilder;
  private ObjectMapper objectMapper;

  @Autowired
  public WebSecurityConfig(RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper) {
    this.restTemplateBuilder = restTemplateBuilder;
    this.objectMapper = objectMapper;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
          .anyRequest().fullyAuthenticated()
        .and()
          .oauth2Login()
            .userInfoEndpoint()
            .userService(userService());
  }

  private OAuth2UserService<OAuth2UserRequest, OAuth2User> userService() {
    DefaultOAuth2UserService userService = new DefaultOAuth2UserService();

    MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter(
        objectMapper);
    messageConverter.setSupportedMediaTypes(Collections.singletonList(
        new MediaType("text", "javascript", StandardCharsets.UTF_8
    )));

    userService.setRestOperations(
        restTemplateBuilder
            .additionalMessageConverters(messageConverter)
            .build()
    );

    return userService;
  }
}

package com.springblade.system.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class EnterpriseConfig {
  @Bean
  public LocalValidatorFactoryBean mylValidtor() {
    LocalValidatorFactoryBean localValidtorFactoryBean = new LocalValidatorFactoryBean();
    localValidtorFactoryBean.setProviderClass(HibernateValidator.class);
    return localValidtorFactoryBean;
  }
}

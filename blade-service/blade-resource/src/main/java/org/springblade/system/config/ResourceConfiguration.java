package org.springblade.system.config;


import org.springblade.core.secure.auth.AuthFun;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ResourceConfiguration {

	@Bean
	public AuthFun authFun() {
		return new AuthFun();
	}

}

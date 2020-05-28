package org.springblade.system.config;

import lombok.AllArgsConstructor;
import org.springblade.core.secure.registry.SecureRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class RegistryConfiguration implements WebMvcConfigurer {

    @Bean
    public SecureRegistry secureRegistry() {
        SecureRegistry secureRegistry = new SecureRegistry();
        secureRegistry.excludePathPatterns(new String[]{"/token/**"});
        secureRegistry.setEnable(false);
        return secureRegistry;
    }
}

package org.springblade.system.config;


import org.springblade.core.secure.auth.AuthFun;
import org.springblade.system.converter.ValuedEnumConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class ResourceConfiguration implements WebMvcConfigurer {

	@Bean
	public AuthFun authFun() {
		return new AuthFun();
	}

	/**
	 * value 值 序列化
	 *
	 * @return RedisSerializer
	 */
	@Bean
	public RedisSerializer redisSerializer() {
		return new GenericJackson2JsonRedisSerializer();
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverterFactory(new ValuedEnumConverter());
	}

}

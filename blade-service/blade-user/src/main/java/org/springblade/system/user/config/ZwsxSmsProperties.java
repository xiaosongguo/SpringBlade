package org.springblade.system.user.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties("zwsx.sms")
public class ZwsxSmsProperties {

  private String apName;

  private String apPassword;

  private String url;

}

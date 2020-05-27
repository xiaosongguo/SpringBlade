package com.springblade.system;

import org.springblade.core.launch.BladeApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringCloudApplication
@EnableFeignClients({"org.springblade"})
@EnableScheduling
public class EnterpriseApplication {
    public EnterpriseApplication() {
    }

    public static void main(String[] args) {
        BladeApplication.run("zwsx-enterprise", EnterpriseApplication.class, args);
    }
}

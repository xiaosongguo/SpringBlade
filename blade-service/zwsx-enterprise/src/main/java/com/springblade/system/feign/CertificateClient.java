//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.springblade.system.feign;

import com.springblade.system.service.ICertificateService;
import lombok.AllArgsConstructor;
import org.springblade.system.entity.Certificate;
import org.springblade.system.feign.ICertificateClient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CertificateClient implements ICertificateClient {
    private ICertificateService service;

    @GetMapping({"/certificate/getValue"})
    @Cacheable(
        cacheNames = {"DICT_CERTIFICATE_VALUE"},
        key = "#tpye"
    )
    public String getCertificateValue(Integer tpye) {
        return service.lambdaQuery().<Certificate>select(Certificate::getName).eq(Certificate::getType, tpye).one().getName();

	}

    @GetMapping({"/certificate/getList"})
    @Cacheable(
        cacheNames = {"DICT_CERTIFICATE_LIST"}
    )
    public List<Certificate> getCertificateList() {
        return this.service.list();
    }

}

package org.springblade.system.feign;

import org.springblade.system.entity.Certificate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("zwsx-enterprise")
public interface ICertificateClient {
  public static final String API_PREFIX = "/certificate";

  @GetMapping({"/certificate/getValue"})
  String getCertificateValue(@RequestParam("tpye") Integer paramInteger);

  @GetMapping({"/certificate/getList"})
  List<Certificate> getCertificateList();
}

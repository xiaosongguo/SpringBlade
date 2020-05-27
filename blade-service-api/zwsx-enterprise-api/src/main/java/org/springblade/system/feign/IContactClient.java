package org.springblade.system.feign;

import org.springblade.system.entity.Contact;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("zwsx-enterprise")
public interface IContactClient {
  public static final String API_PREFIX = "/contact";

  @GetMapping({"/contact/byID"})
  List<Contact> list(@RequestParam("userId") Integer paramInteger);

	@GetMapping({"/contact/one"})
	public Contact one(Integer userId);
}

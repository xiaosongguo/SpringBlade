package org.springblade.system.vo;

import lombok.Data;
import org.springblade.system.entity.Contact;
import org.springblade.system.entity.Enterprise;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class EnterpriseVO extends Enterprise {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "用户证书至少一个")
	@Valid
	private List<UserCertificateVO> userCertificates;

	@NotEmpty(message = "银行至少一个")
	@Valid
	private List<BankVO> banks;

	@NotEmpty(message = "联系人至少一个")
	@Valid
	private List<Contact> contacts;
}

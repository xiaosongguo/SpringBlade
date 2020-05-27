package org.springblade.system.vo;

import lombok.Data;
import org.springblade.system.entity.FileManager;
import org.springblade.system.entity.UserCertificate;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class UserCertificateVO extends UserCertificate {
  private static final long serialVersionUID = 1L;

  @Size(min = 1, message = "附件至少一个")
  @Valid
  private List<FileManager> fileManagers;

}

package org.springblade.system.vo;

import lombok.Data;
import org.springblade.system.entity.Bank;
import org.springblade.system.entity.FileManager;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class BankVO extends Bank {
  private static final long serialVersionUID = 1L;

  @NotEmpty(message = "附件至少一个")
  @Valid
  private List<FileManager> fileManagers;

}

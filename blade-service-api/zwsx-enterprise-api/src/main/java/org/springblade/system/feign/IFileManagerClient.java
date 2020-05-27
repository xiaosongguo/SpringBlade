package org.springblade.system.feign;

import org.springblade.core.tool.api.R;
import org.springblade.system.entity.FileManager;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.List;

@FeignClient("zwsx-enterprise")
public interface IFileManagerClient {
  public static final String API_PREFIX = "/file";

  @PostMapping({"/file/upload"})
  R upload(@RequestBody FileManager paramFileManager);

  @PostMapping({"/file/update"})
  Collection<FileManager> updateBatchById(@RequestBody Collection<FileManager> paramCollection);

  @PostMapping({"/file/list"})
  List<FileManager> list(@RequestBody FileManager paramFileManager);

  @PostMapping({"/file/remove"})
  Collection<FileManager> delBatchById(@RequestBody List<FileManager> paramList);
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.springblade.system.feign;

import com.springblade.system.service.IFileManagerService;
import lombok.AllArgsConstructor;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.tool.api.R;
import org.springblade.system.entity.FileManager;
import org.springblade.system.feign.IFileManagerClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@AllArgsConstructor
public class FileManagerClient implements IFileManagerClient {
    private IFileManagerService service;

    @PostMapping({"/file/upload"})
    public R upload(FileManager fileManager) {
        return this.service.save(fileManager) ? R.data(fileManager, "上传成功") : R.fail("上传失败");
    }

    @PostMapping({"/file/update"})
    public Collection<FileManager> updateBatchById(Collection<FileManager> fileManagers) {
        this.service.updateBatchById(fileManagers);
        return fileManagers;
    }

    @PostMapping({"/file/list"})
    public List<FileManager> list(FileManager fileManager) {
        return this.service.list(Condition.getQueryWrapper(fileManager));
    }

    @PostMapping({"/file/remove"})
    public Collection<FileManager> delBatchById(List<FileManager> delFiles) {
        this.service.removeByIds(delFiles);
        return delFiles;
    }

}

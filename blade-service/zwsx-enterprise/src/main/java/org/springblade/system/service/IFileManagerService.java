//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springblade.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.system.entity.FileManager;
import org.springblade.system.vo.FileManagerVO;

public interface IFileManagerService extends IService<FileManager> {
    IPage<FileManagerVO> selectFileManagerPage(IPage<FileManagerVO> page, FileManagerVO fileManager);
}

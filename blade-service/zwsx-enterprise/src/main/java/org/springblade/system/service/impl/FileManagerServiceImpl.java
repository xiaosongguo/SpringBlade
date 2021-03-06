//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springblade.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.system.mapper.FileManagerMapper;
import org.springblade.system.service.IFileManagerService;
import lombok.AllArgsConstructor;
import org.springblade.system.entity.FileManager;
import org.springblade.system.vo.FileManagerVO;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FileManagerServiceImpl extends ServiceImpl<FileManagerMapper, FileManager> implements IFileManagerService {

    public IPage<FileManagerVO> selectFileManagerPage(IPage<FileManagerVO> page, FileManagerVO fileManager) {
        return page.setRecords(baseMapper.selectFileManagerPage(page, fileManager));
    }
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.springblade.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.system.entity.FileManager;
import org.springblade.system.vo.FileManagerVO;

import java.util.List;

public interface FileManagerMapper extends BaseMapper<FileManager> {
    List<FileManagerVO> selectFileManagerPage(IPage page, FileManagerVO fileManager);
}

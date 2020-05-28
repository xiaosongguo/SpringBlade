package org.springblade.system.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.system.entity.FileManager;
import org.springblade.system.feign.IDictClient;
import org.springblade.system.vo.FileManagerVO;

public class FileManagerWrapper extends BaseEntityWrapper<FileManager, FileManagerVO> {
    private IDictClient dictClient;

    public FileManagerVO entityVO(FileManager fileManager) {
        FileManagerVO fileManagerVO = (FileManagerVO)BeanUtil.copy(fileManager, FileManagerVO.class);
        return fileManagerVO;
    }

    public FileManagerWrapper(final IDictClient dictClient) {
        this.dictClient = dictClient;
    }
}

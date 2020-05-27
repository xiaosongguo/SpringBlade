//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.springblade.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.springblade.system.service.IFileManagerService;
import com.springblade.system.wrapper.FileManagerWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.entity.FileManager;
import org.springblade.system.feign.IDictClient;
import org.springblade.system.vo.FileManagerVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping({"/filemanager"})
@Api(
    value = "",
    tags = {"接口"}
)
public class FileManagerController extends BladeController {
    private IFileManagerService fileManagerService;
    private IDictClient dictClient;

    @GetMapping({"/detail"})
    @ApiOperation(
        value = "详情",
        notes = "传入fileManager",
        position = 1
    )
    public R<FileManagerVO> detail(FileManager fileManager) {
        FileManager detail = (FileManager)this.fileManagerService.getOne(Condition.getQueryWrapper(fileManager));
        FileManagerWrapper fileManagerWrapper = new FileManagerWrapper(this.dictClient);
        return R.data(fileManagerWrapper.entityVO(detail));
    }

    @GetMapping({"/list"})
    @ApiOperation(
        value = "分页",
        notes = "传入fileManager",
        position = 2
    )
    public R<IPage<FileManagerVO>> list(FileManager fileManager, Query query) {
        IPage<FileManager> pages = this.fileManagerService.page(Condition.getPage(query), Condition.getQueryWrapper(fileManager));
        FileManagerWrapper fileManagerWrapper = new FileManagerWrapper(this.dictClient);
        return R.data(fileManagerWrapper.pageVO(pages));
    }

    @GetMapping({"/page"})
    @ApiOperation(
        value = "分页",
        notes = "传入fileManager",
        position = 3
    )
    public R<IPage<FileManagerVO>> page(FileManagerVO fileManager, Query query) {
        IPage<FileManagerVO> pages = this.fileManagerService.selectFileManagerPage(Condition.getPage(query), fileManager);
        return R.data(pages);
    }

    @PostMapping({"/save"})
    @ApiOperation(
        value = "新增",
        notes = "传入fileManager",
        position = 4
    )
    public R save(@Valid @RequestBody FileManager fileManager) {
        return R.status(this.fileManagerService.save(fileManager));
    }

    @PostMapping({"/update"})
    @ApiOperation(
        value = "修改",
        notes = "传入fileManager",
        position = 5
    )
    public R update(@Valid @RequestBody FileManager fileManager) {
        return R.status(this.fileManagerService.updateById(fileManager));
    }

    @PostMapping({"/submit"})
    @ApiOperation(
        value = "新增或修改",
        notes = "传入fileManager",
        position = 6
    )
    public R submit(@Valid @RequestBody FileManager fileManager) {
        return R.status(this.fileManagerService.saveOrUpdate(fileManager));
    }

    @PostMapping({"/remove"})
    @ApiOperation(
        value = "删除",
        notes = "传入ids",
        position = 7
    )
    public R remove(@ApiParam(value = "主键集合",required = true) @RequestParam String ids) {
        return R.status(this.fileManagerService.removeByIds(Func.toIntList(ids)));
    }

    public FileManagerController(final IFileManagerService fileManagerService, final IDictClient dictClient) {
        this.fileManagerService = fileManagerService;
        this.dictClient = dictClient;
    }
}

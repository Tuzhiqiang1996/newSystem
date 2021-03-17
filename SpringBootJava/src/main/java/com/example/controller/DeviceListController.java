package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.lang.Result;
import com.example.entity.DeviceList;
import com.example.service.DeviceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tu
 * @Package com.example.controller
 * @date 2021/3/16-14:21
 * 前端控制器
 * 设备log
 */
@RestController
public class DeviceListController {
    @Autowired
    DeviceListService deviceListService;

    @GetMapping("/devList")
    public Result logiest(Integer currentPage) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Page page = new Page(currentPage, 100);
        IPage pageData = deviceListService.page(page, new QueryWrapper<DeviceList>().orderByAsc("Id"));

        return Result.succ("操作成功！", pageData);
    }
}

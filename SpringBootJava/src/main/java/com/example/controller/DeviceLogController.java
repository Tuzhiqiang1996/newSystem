package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.lang.Result;
import com.example.entity.DeviceLogList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.service.DeviceLogService;

import java.util.List;

/**
 * @author Tu
 * @Package com.example.controller
 * @date 2021/3/16-14:21
 * 前端控制器
 * 设备log
 */
@RestController
public class DeviceLogController {
    @Autowired
    DeviceLogService deviceLogService;

    @GetMapping("/devlogList")
    public Result logiest(Integer currentPage) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Page page = new Page(currentPage, 100);
        QueryWrapper<DeviceLogList> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("Id");

//        Date dNow = new Date();
//        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        String endtime = ft.format(dNow);
//        String starttime = ft.format(new Date(dNow.getTime() - 1 * 24 * 60 * 60 * 1000));
//        queryWrapper.between("addtime", starttime, endtime);
//        System.out.println("当前时间为: " + endtime+""+starttime); //默认24小时之前

        IPage pageData = deviceLogService.page(page,queryWrapper);

        return Result.succ("操作成功！", pageData);
    }

    /**
     * [java.lang.String]
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/3/18 11:06
     * @message 查询
     */
    @GetMapping("/searchlog")
    public Result searchData(String deviceid, Integer currentPage, String starttime, String endtime) {

        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Page page = new Page(currentPage, 100);
        QueryWrapper<DeviceLogList> queryWrapper = new QueryWrapper<>();
        if (deviceid != null && deviceid.length() != 0) {
            queryWrapper.like("deviceid", deviceid);
        }
        if (starttime != null && starttime.length() != 0) {
            queryWrapper.between("addtime", starttime, endtime);
        }
        queryWrapper.orderByDesc("addtime");//时间降序  orderByDesc降序 orderByAsc升序

        List<DeviceLogList> deviceLogLists = deviceLogService.list(queryWrapper);
        if (deviceLogLists == null || deviceLogLists.size() == 0) {
            return Result.fail("没有数据！");
        }
        IPage devLists = deviceLogService.page(page, queryWrapper);
        if (devLists == null ) {
            return Result.fail("没有数据！");
        }
        return Result.succ("操作成功", devLists);
    }
}

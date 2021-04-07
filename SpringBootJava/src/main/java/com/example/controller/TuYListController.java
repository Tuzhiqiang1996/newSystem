package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.lang.Result;
import com.example.entity.TuYList;
import com.example.service.TuYListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Tu
 * @Package com.example.controller
 * @date 2021/4/7-15:16
 */
@RestController
public class TuYListController {
    @Autowired
    TuYListService tuYListService;

    @GetMapping("/TYlist")

    public Result xiaojlist(Integer currentPage) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Page page = new Page(currentPage, 100);
        QueryWrapper<TuYList> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("test_datetime");
        IPage pageData = tuYListService.page(page, queryWrapper);
        return Result.succ("操作成功！", pageData);
    }

    @GetMapping("/searchTYlist")
    public Result searchData(String deviceid, Integer currentPage, String orderId, String starttime, String endtime) {

        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Page page = new Page(currentPage, 100);
        QueryWrapper<TuYList> queryWrapper = new QueryWrapper<>();
        if (deviceid != null && deviceid.length() != 0) {
            queryWrapper.like("deviceid", deviceid);
        }
        if (orderId != null && orderId.length() != 0) {
            queryWrapper.like("order_id", orderId);
        }
        if (starttime != null && starttime.length() != 0) {
            queryWrapper.between("test_datetime", starttime, endtime);
        }
        queryWrapper.orderByDesc("test_datetime");//时间降序  orderByDesc降序 orderByAsc升序

        List<TuYList> deviceLogLists = tuYListService.list(queryWrapper);

        if (deviceLogLists == null || deviceLogLists.size() == 0) {
            return Result.fail("没有数据！");
        }
        IPage devLists = tuYListService.page(page, queryWrapper);

        return Result.succ("操作成功", devLists);
    }

    @GetMapping("/tytatistical")
    public Result statisticalNum(Integer num, Integer currentPage, String orderId, String starttime, String endtime) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Page page = new Page(currentPage, 100);
        QueryWrapper<TuYList> queryWrapper = new QueryWrapper<>();
        if (orderId != null && orderId.length() != 0) {
            queryWrapper.eq("order_id", orderId);
        }
        Integer d = 3;
        if (num != null) {
            if (num >= d) {
                queryWrapper.ge("check_count", num);
            } else {
                queryWrapper.eq("check_count", num);
            }
        }

//        if(starttime != null && starttime.length() != 0) {
//            queryWrapper.between("test_datetime", starttime, endtime);
//        } else {
//            Date dNow = new Date();
//            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            endtime = ft.format(dNow);
//            starttime = ft.format(new Date(dNow.getTime() - 1 * 24 * 60 * 60 * 1000));
//            queryWrapper.between("test_datetime", starttime, endtime);
//        }

        IPage devLists = tuYListService.page(page, queryWrapper);
        if (devLists == null) {
            return Result.fail("没有数据！");
        }
        return Result.succ("操作成功", devLists);
    }
}

package com.example.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.lang.Result;
import com.example.entity.DeviceList;
import com.example.service.DeviceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
        QueryWrapper<DeviceList> queryWrapper = new QueryWrapper<>();


        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String endtime = ft.format(dNow);
        String starttime = ft.format(new Date(dNow.getTime() - 1 * 24 * 60 * 60 * 1000));

//        System.out.println("当前时间为: " + endtime+""+starttime); 默认24小时之前
        queryWrapper.between("test_datetime", starttime, endtime);
        queryWrapper.orderByDesc("test_datetime");
        IPage pageData = deviceListService.page(page, queryWrapper);
        return Result.succ("操作成功！", pageData);
    }

    /**
     * [java.lang.String]
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/3/18 10:53
     * @message 查询 一个或者多个查询条件
     */
    @GetMapping("/searchlist")
    public Result searchData(String deviceid, Integer currentPage, String orderId, String starttime, String endtime) {

        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Page page = new Page(currentPage, 100);
        QueryWrapper<DeviceList> queryWrapper = new QueryWrapper<>();
        if (deviceid.trim() == null || "".equals(deviceid.trim())) {
        } else {
            queryWrapper.like("deviceid", deviceid);
        }
        if (orderId.trim() == null || "".equals(orderId.trim())) {
        } else {
            queryWrapper.like("order_id", orderId);
        }
        if (starttime.trim() == null || "".equals(starttime.trim())) {
        } else {
            queryWrapper.between("test_datetime", starttime, endtime);
        }
        queryWrapper.orderByDesc("test_datetime");//时间降序  orderByDesc降序 orderByAsc升序

        List<DeviceList> deviceLogLists = deviceListService.list(queryWrapper);

        if (deviceLogLists == null || deviceLogLists.size() == 0) {
            return Result.fail("没有数据！");
        }
        IPage devLists = deviceListService.page(page, queryWrapper);

        return Result.succ("操作成功", devLists);
    }

    /**
     * [java.lang.Integer, java.lang.Integer]
     *
     * @return com.example.common.lang.Result
     * http://localhost:8081/statistical?num=5&currentPage=
     * @author Tu
     * @date 2021/3/19 10:43
     * @message 统计次数
     */
    @GetMapping("/statistical")
    public Result statisticalNum(Integer num, Integer currentPage, String orderId, String starttime, String endtime) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Page page = new Page(currentPage, 100);
        QueryWrapper<DeviceList> queryWrapper = new QueryWrapper<>();
        if (orderId != null && orderId.length() != 0) {
            queryWrapper.eq("order_id", orderId);
        }
        if (num != null ) {
            queryWrapper.eq("check_count", num);
            if (num >= 3) {
                queryWrapper.ge("check_count", num);
            }
        }

        if(starttime != null && starttime.length() != 0) {
            queryWrapper.between("test_datetime", starttime, endtime);
        } else {
            Date dNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            endtime = ft.format(dNow);
            starttime = ft.format(new Date(dNow.getTime() - 1 * 24 * 60 * 60 * 1000));
            queryWrapper.between("test_datetime", starttime, endtime);
        }

        IPage devLists = deviceListService.page(page, queryWrapper);
        if (devLists == null ) {
            return Result.fail("没有数据！");
        }
        return Result.succ("操作成功", devLists);
    }
}

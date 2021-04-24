package com.example.controller;

import cn.hutool.core.bean.BeanUtil;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
        Integer d = 3;
        if (num != null ) {
            if (num >= d) {
                queryWrapper.ge("check_count", num);
            }else{

            queryWrapper.eq("check_count", num);
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
    /**
     * [java.util.HashMap<java.lang.String,java.lang.Object>]
     * @author Tu
     * @date 2021/3/31 15:06
     * @message 循环插入数据库 第一种
     * 在接收前端数据是  只传入 {id:12,savefiles:["1","2","3",....]}
     * 此时后端接收  @RequestBody HashMap<String,Object> map
     * 接受一个List类型和Integer类型参数
     * 然后 获取数据
     *    // 接收List
     *         List<Savefile> savefiledata = (List<Savefile>) map.get("savefiles");
     *         // 接收另外一个参数
     *         Integer id = (Integer) map.get("id");
     *         在循环插入时候 一个错误 就是将新建的对象放在循环外
     *         正确的做法是放在循环内 让她每次循环创建新的对象 而不是只更改一条数据
     *
     * @return com.example.common.lang.Result
     */

    @PostMapping("/receivefilehas")
//    public Result receivefilehas( @RequestParam(value = "id",required = false) Integer id, @RequestParam(value = "savefiles",required = false) List<String> savefiles) {
    public Result receivefilehas(@RequestBody HashMap<String, Object> map) {
        // 接收List
        List<DeviceList> savefiledata = (List<DeviceList>) map.get("savefiles");
        // 接收另外一个参数
        Integer id = (Integer) map.get("id");
        List<DeviceList> savefiles = new ArrayList<>(savefiledata);
        for (int i = 0; i < savefiledata.size(); i++) {

            DeviceList savefile = new DeviceList();
            savefile.setOrderId(id);
            savefile.setDeviceid(String.valueOf(savefiledata.get(i)));
            BeanUtil.copyProperties(savefiledata.get(i), savefile, "order_id", "deviceid");
//            System.out.println(savefile);
            deviceListService.saveOrUpdate(savefile);
        }
        return Result.succ("插入成功！");
    }
    /**
     * [java.util.List<com.example.entity.Savefile>]
     * @author Tu
     * @date 2021/3/31 15:13
     * @message 第二种 接收一个list 数据
     * 前端传入数据 [{Id=null, sn=null, orderId=55,},{Id=null, sn=null, orderId=55,}] 每条中的数据与新建的数据结构相似
     * List<Savefile> savefileList = new ArrayList<>(savefiles);
     * 拿到数据进行巴拉巴拉..
     *
     * @return com.example.common.lang.Result
     */
    @PostMapping("/receivefile")
    public Result receivefile(@RequestBody List<DeviceList> savefiles) {
        if (savefiles.size() == 0 || savefiles == null) {
            return Result.fail("插入数据为空！");
        }
        DeviceList savefile = new DeviceList();
        List<DeviceList> savefileList = new ArrayList<>(savefiles);
        for (int i = 0; i < savefileList.size(); i++) {
            BeanUtil.copyProperties((savefiles.get(i)), savefile);
            //System.out.println(savefileList.get(i).getDeviceid());
            //System.out.println(savefile);
            deviceListService.saveOrUpdate(savefile);
        }
        return Result.succ("插入成功！", savefile);
    }
}

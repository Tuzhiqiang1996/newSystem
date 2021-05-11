package com.example.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.lang.Result;
import com.example.entity.DeviceList;
import com.example.entity.XiaoJList;
import com.example.service.XiaoJListService;
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
 * @date 2021/4/7-15:16
 */
@RestController
public class XiaoJListController {
    @Autowired
    XiaoJListService xiaoJListService;

    @GetMapping("/xiaojlist")

    public Result xiaojlist(Integer currentPage) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Page page = new Page(currentPage, 100);
        QueryWrapper<XiaoJList> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("test_datetime");
        IPage pageData = xiaoJListService.page(page, queryWrapper);
        return Result.succ("操作成功！", pageData);
    }

    @GetMapping("/searchXjlist")
    public Result searchData(String deviceid, Integer currentPage, String orderId, String starttime, String endtime, String sn) {

        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Page page = new Page(currentPage, 100);
        QueryWrapper<XiaoJList> queryWrapper = new QueryWrapper<>();
        if (deviceid != null && deviceid.length() != 0) {
            queryWrapper.like("deviceid", deviceid);
        }
        if (orderId != null && orderId.length() != 0) {
            queryWrapper.like("order_id", orderId);
        }
        if (sn != null && sn.length() != 0) {
            queryWrapper.like("sn", sn);
        }
        if (starttime != null && starttime.length() != 0) {
            queryWrapper.between("test_datetime", starttime, endtime);
        }

        queryWrapper.orderByDesc("test_datetime");//时间降序  orderByDesc降序 orderByAsc升序

        List<XiaoJList> deviceLogLists = xiaoJListService.list(queryWrapper);

        if (deviceLogLists == null || deviceLogLists.size() == 0) {
            return Result.fail("没有数据！");
        }
        IPage devLists = xiaoJListService.page(page, queryWrapper);

        return Result.succ("操作成功", devLists);
    }

    @GetMapping("/xjtatistical")
    public Result statisticalNum(Integer num, Integer currentPage, String orderId, String starttime, String endtime) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Page page = new Page(currentPage, 100);
        QueryWrapper<XiaoJList> queryWrapper = new QueryWrapper<>();
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

        IPage devLists = xiaoJListService.page(page, queryWrapper);
        if (devLists == null) {
            return Result.fail("没有数据！");
        }
        return Result.succ("操作成功", devLists);
    }

    @PostMapping("/listFix")
    public Result listFix(@RequestBody XiaoJList xiaoJList) {
        XiaoJList xiaoJList1 = xiaoJListService.getById(xiaoJList.getId());
        xiaoJList1.setDeviceid(xiaoJList.getDeviceid());
        xiaoJList1.setAddr1(xiaoJList.getAddr1());
        xiaoJList1.setAddr2(xiaoJList.getAddr2());
        xiaoJList1.setCreatetime(xiaoJList.getCreatetime());
        xiaoJList1.setTestResult(xiaoJList.getTestResult());
        xiaoJList1.setTestDatetime(xiaoJList.getTestDatetime());
        xiaoJList1.setPackageDatetime(xiaoJList.getPackageDatetime());
        xiaoJList1.setPackages(xiaoJList.getPackages());
        xiaoJList1.setCheckDatetime(xiaoJList.getCheckDatetime());
        xiaoJList1.setCheckCount(xiaoJList.getCheckCount());
        xiaoJListService.saveOrUpdate(xiaoJList1);
        return Result.succ("修改成功！");
    }
    /**
     * [java.util.List<com.example.entity.Savefile>]
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/3/31 15:13
     * @message 第二种 接收一个list 数据
     * 前端传入数据 [{Id=null, sn=null, orderId=55,},{Id=null, sn=null, orderId=55,}] 每条中的数据与新建的数据结构相似
     * List<Savefile> savefileList = new ArrayList<>(savefiles);
     * 拿到数据进行巴拉巴拉..
     * 使用中
     */
    @PostMapping("/xjlist")
    public Result xjlist(@RequestBody List<XiaoJList> xjlists) {
        if (xjlists.size() == 0 || xjlists == null) {
            return Result.fail("插入数据为空！");
        }
        XiaoJList savefile = new XiaoJList();
        List<XiaoJList> savefileList = new ArrayList<>(xjlists);
        /**
         * 第一种时间长
         * 1000/1min
         * 1000每次
         */

//        for (int i = 0; i < savefileList.size(); i++) {
//            BeanUtil.copyProperties((xjlists.get(i)), savefile);
//            xiaoJListService.saveOrUpdate(savefile);
//        }
        /**
         * 第二种时间明显减少
         * 1000/78ms
         * 1000每次
         */
        xiaoJListService.saveBatch(savefileList);
        return Result.succ("插入成功！", savefile);
    }
    /**
     * [java.util.HashMap<java.lang.String,java.lang.Object>]
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/3/31 15:06
     * @message 循环插入数据库 第一种
     * 在接收前端数据是  只传入 {id:12,savefiles:["1","2","3",....]}
     * 此时后端接收  @RequestBody HashMap<String,Object> map
     * 接受一个List类型和Integer类型参数
     * 然后 获取数据
     * // 接收List
     * List<Savefile> savefiledata = (List<Savefile>) map.get("savefiles");
     * // 接收另外一个参数
     * Integer id = (Integer) map.get("id");
     * 在循环插入时候 一个错误 就是将新建的对象放在循环外
     * 正确的做法是放在循环内 让她每次循环创建新的对象 而不是只更改一条数据
     * 未使用
     */
    @PostMapping("/xjreceivefilehas")
//    public Result receivefilehas( @RequestParam(value = "id",required = false) Integer id, @RequestParam(value = "savefiles",required = false) List<String> savefiles) {
    public Result tyreceivefilehas(@RequestBody HashMap<String, Object> map) {
        // 接收List
        List<XiaoJList> savefiledata = (List<XiaoJList>) map.get("savefiles");
        // 接收另外一个参数
        Integer id = (Integer) map.get("id");
        List<XiaoJList> savefiles = new ArrayList<>(savefiledata);
        for (int i = 0; i < savefiledata.size(); i++) {

            XiaoJList savefile = new XiaoJList();
            savefile.setOrderId(id);
            savefile.setDeviceid(String.valueOf(savefiledata.get(i)));
            BeanUtil.copyProperties(savefiledata.get(i), savefile, "order_id", "deviceid");
//            System.out.println(savefile);
            xiaoJListService.saveOrUpdate(savefile);
        }
        return Result.succ("插入成功！");
    }
}

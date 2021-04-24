package com.example.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.lang.Result;
import com.example.entity.OrderList;
import com.example.service.OrderService;
import com.example.util.ShiroUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Tu
 * @Package com.example.controller
 * @date 2021/3/13-11:48
 * 前端控制器
 * orderByDesc 倒序
 * orderByAsc 顺序
 */
@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/order")
    public Result order(Integer currentPage) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Page page = new Page(currentPage, 8);
        IPage pageData = orderService.page(page, new QueryWrapper<OrderList>().orderByAsc("id"));
        return Result.succ("成功", pageData);
    }

    /**
     * [com.example.entity.OrderList]
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/3/15 17:24
     * @message 新增功能
     */

    @PostMapping("/orderadd")
    public Result orderadd(@RequestBody OrderList orderList) {
        OrderList ord = null;
        ord = new OrderList();

        BeanUtil.copyProperties(orderList, ord);

        orderService.saveOrUpdate(ord);
        System.out.println(orderList);
        return Result.succ("新增成功！");
    }

    /**
     * [com.example.entity.OrderList]
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/3/15 17:44
     * @message 编辑功能
     */
    @PostMapping("/editorder")
    public Result editorder(@RequestBody OrderList orderList) {
        OrderList ord = null;
        ord = new OrderList();
        ord.setId(ShiroUtil.getProfile().getId());
        BeanUtil.copyProperties(orderList, ord);
        orderService.saveOrUpdate(ord);
        return Result.succ("操作成功！");
    }

    /**
     * [java.lang.Integer, java.lang.Integer]
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/4/24 13:51
     * @message
     */
    @GetMapping("/deleteorder")
    public Result deleteord(@RequestParam Integer id, @RequestParam Integer status) {
        if (status != 0) {
            return Result.fail("没有权限");
        }
        OrderList listid = orderService.getById(id);

        orderService.removeById(listid);

        return Result.succ("删除成功！");
    }

    /**
     * [java.lang.String]
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/4/24 13:51
     * @message查询类似的
     */
    @GetMapping("getlistnumber")
    public Result getlistnumber(String orderNumber) {

        QueryWrapper<OrderList> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("order_number", orderNumber);
        List<OrderList> files = orderService.list(queryWrapper);
        //判断数据是否为空
        if (files == null || files.size() == 0) {
            return Result.fail("没有数据！");
        }
        return Result.succ("查询成功！", files);
    }
    /**
     * [com.example.entity.Setfile]
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/3/30 18:27
     * @message 自增id 系统自动
     *  BeanUtil.copyProperties(setfile, s);
     *  将
     */
    @PostMapping("/saveorderfile")
    public Result saveorder(@RequestBody OrderList setfile) {
        OrderList s = new OrderList();
        BeanUtil.copyProperties(setfile, s);
        orderService.saveOrUpdate(s);
        return Result.succ("插入数据成功！", s);
    }
}

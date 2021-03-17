package com.example.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.lang.Result;
import com.example.entity.SystemList;
import com.example.service.SystemService;
import com.example.util.ShiroUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tu
 * @Package com.example.controller
 * @date 2021/3/13-9:40
 * 控制器
 */
@RestController
public class SystemController {
    @Autowired
    SystemService systemService;

    /**
     * [java.lang.Integer]
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/3/15 10:53
     * @message 获取列表信息
     */
    @GetMapping("/message")
    public Result messages(Integer currentPage) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Page page = new Page(currentPage, 8);
        IPage pageData = systemService.page(page, new QueryWrapper<SystemList>().orderByAsc("id"));

        return Result.succ("操作成功！", pageData);
    }

    /**
     * []
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/3/15 11:11
     * @message BeanUtil.copyProperties 数据赋值 （a,b） a
     * a 要赋值到目标数据 b 源数据
     */
    @PostMapping("/cmdadd")
    public Result addList(@Validated @RequestBody SystemList systemList) {
        SystemList sys = null;
        sys = new SystemList();
//        sys.setActtimer(systemList.getActtimer());
//        sys.setCmd(systemList.getCmd());
//        sys.setDisplay(systemList.getDisplay());
//        sys.setFailed(systemList.getFailed());
//        sys.setPass(systemList.getPass());
//.....
        BeanUtil.copyProperties(systemList, sys);
//        System.out.println(sys);
//        System.out.println(systemList);

        systemService.saveOrUpdate(sys);
        return Result.succ("新增成功！");

    }

    /**
     * [java.lang.Integer, java.lang.Integer]
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/3/15 14:48
     * @message 删除功能
     */
    @GetMapping("/deleteCmd")
    public Result deletecmd(@RequestParam Integer id, @RequestParam Integer status) {
        if (status != 0) {
            return Result.fail("没有权限");
        }
        SystemList listid = systemService.getById(id);
        systemService.removeById(listid);
//        System.out.println(listid);
        return Result.succ("删除成功！");
    }

    /**
     * [com.example.entity.SystemList]
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/3/15 14:52
     * @message 编辑功能 带id修改
     */
    @PostMapping("/editlist")
    public Result editsyslist(@Validated @RequestBody SystemList systemList) {
        SystemList sys = null;
        sys = new SystemList();
        //获取当前内容id 可以修改当前内容
        sys.setId(ShiroUtil.getProfile().getId());
//        sys.setActtimer(systemList.getActtimer());
//        sys.setCmd(systemList.getCmd());
//        sys.setDisplay(systemList.getDisplay());
//        sys.setFailed(systemList.getFailed());
//        sys.setPass(systemList.getPass());
//.....
        BeanUtil.copyProperties(systemList, sys);
//        System.out.println(sys);
//        System.out.println(systemList);

        systemService.saveOrUpdate(sys);
        return Result.succ("操作成功！");
    }
}

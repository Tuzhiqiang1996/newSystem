package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.WtwdList;
import com.example.mapper.WtwdMapper;
import com.example.service.WtwdService;
import org.springframework.stereotype.Service;

/**
 * @author Tu
 * @Package com.example.service.impl
 * @date 2021/3/16-14:22
 */
@Service
public class WtwdServiceImpl extends ServiceImpl<WtwdMapper, WtwdList> implements WtwdService {
}

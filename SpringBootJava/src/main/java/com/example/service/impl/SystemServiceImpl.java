package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.SystemList;
import com.example.mapper.SystemMapper;
import com.example.service.SystemService;
import org.springframework.stereotype.Service;

/**
 * @author Tu
 * @Package com.example.service.impl
 * @date 2021/3/13-9:27
 */
@Service
public class SystemServiceImpl extends ServiceImpl<SystemMapper, SystemList> implements SystemService {
}

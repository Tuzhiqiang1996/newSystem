package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.DeviceLogList;
import com.example.mapper.DeviceLogMapper;
import com.example.service.DeviceLogService;
import org.springframework.stereotype.Service;

/**
 * @author Tu
 * @Package com.example.service.impl
 * @date 2021/3/16-14:22
 */
@Service
public class DeviceLogServiceImpl extends ServiceImpl<DeviceLogMapper, DeviceLogList> implements DeviceLogService {
}

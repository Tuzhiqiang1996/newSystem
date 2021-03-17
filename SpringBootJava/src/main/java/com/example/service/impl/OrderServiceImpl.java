package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.OrderList;
import com.example.mapper.OrderMapper;
import com.example.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @author Tu
 * @Package com.example.service.impl
 * @date 2021/3/13-11:53
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderList> implements OrderService {
}

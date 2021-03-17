package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Tu
 * @Package com.example.entity
 * @date 2021/3/13-11:49
 * 连接数据库
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_order")
public class OrderList implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String orderNumber;
    private String projectName;
    private String productName;
    private String swVersion;
    private String hwVersion;
    private String userSwVersion;
    private Integer orderQuantity;
    private String cloudPlatform;
    private String info;
    private String bak;
    private String atTable;
}

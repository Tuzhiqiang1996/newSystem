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
 * @date 2021/3/16-14:21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_device_list")
public class DeviceList implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "Id", type = IdType.AUTO)
    private Long Id;
    private String sn;
    private Integer orderId;
    private String deviceid;
    private String addr1;
    private String addr2;
    private String createtime;
    private Integer testResult;
    private String testDatetime;
    private Integer checkResult;
    private String checkDatetime;
    private Integer checkCount;


}

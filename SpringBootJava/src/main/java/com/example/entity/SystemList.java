package com.example.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.scheduling.support.SimpleTriggerContext;

import javax.validation.constraints.NotBlank;

/**
 * @author Tu
 * @Package com.example.entity
 * @date 2021/3/4-17:18
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_atcmd")
public class SystemList implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "Id", type = IdType.AUTO)
    private Long Id;
    private String flag;
    private String cmd;
    private Integer acttimer;
    private String filter;
    private String display;
    private String pass;
    private String failed;
    private String end;
    private String function;

}

package com.sky.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
/**
 * OrdersPageQueryDTO 是用于订单分页查询的传输对象（DTO）。
 * 该类封装了分页信息、筛选条件及用户身份信息，
 * 用于在前端和后端之间传递订单查询的参数。
 */
public class OrdersPageQueryDTO implements Serializable {

    /**
     * 当前查询页码
     */
    private int page;

    /**
     * 每页显示的记录数
     */
    private int pageSize;

    /**
     * 订单编号，用于查询指定订单
     */
    private String number;

    /**
     * 用户电话号码，用于通过手机号进行查询订单
     */
    private String phone;

    /**
     * 订单状态（例如：待处理、已完成等）
     * 这里用 Integer 类型，可以为 null，表示不按状态过滤
     */
    private Integer status;

    /**
     * 查询起始时间
     * 注解 @DateTimeFormat 用于在参数绑定时，将字符串格式的时间转化为 LocalDateTime 类型，
     * 格式为 "yyyy-MM-dd HH:mm:ss"
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime beginTime;

    /**
     * 查询结束时间
     * 同样通过 @DateTimeFormat 注解指定时间格式
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    /**
     * 当前用户的ID，用于限定只查询该用户的订单数据
     */
    private Long userId;

}

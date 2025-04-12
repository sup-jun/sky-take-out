package com.sky.controller.user;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("userShopController")
@RequestMapping("/user/shop")
@Api(tags = "店铺相关接口")
@Slf4j
public class ShopController {

    public static final String KEY = "SHOP_STATUS";

    @Autowired
    private RedisTemplate redisTemplate;


    @GetMapping("/status")
    @ApiOperation("获取店铺的营业状态")
    public Result<Integer> getStatus() {
        // 从 Redis 中获取名为 "SHOP_STATUS" 的值，并将其转换为 Integer
        Integer status = (Integer) redisTemplate.opsForValue().get(KEY);
        // 输出日志信息
        log.info("获取到店铺的营业状态为: {}，status == 1 ? '营业中' : '打烊'", status);
        // 返回包含 status 的成功结果
        return Result.success(status);
    }

}

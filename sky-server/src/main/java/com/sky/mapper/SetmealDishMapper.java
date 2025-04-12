package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    //根据菜品id查询对应的套餐id
    List<Long> getSetmealIdsByDishIds(List<Long> dishIds);

    //批量保存套餐和菜品的关联关系
    void insertBatch(List<SetmealDish> setmealDishes);

    //根据套餐id删除套餐和菜品的关联关系
    @Delete("delete from setmeal_dish where setmeal_id = #{setmealId}")
    void deleteBySetmealId(Long setmealId);

    @Select("select * from setmeal_dish where setmeal_id = #{setmealId}")
    List<SetmealDish> getBySetmealId(Long setmealId);
}

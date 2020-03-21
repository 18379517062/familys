package com.zy.family1.mapper;

import com.zy.family1.entity.item;

public interface itemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(item record);

    int insertSelective(item record);

    item selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(item record);

    int updateByPrimaryKey(item record);
}
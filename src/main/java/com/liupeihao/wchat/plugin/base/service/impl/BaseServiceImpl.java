package com.liupeihao.wchat.plugin.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.liupeihao.wchat.plugin.base.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by @author fww on 2019-05-31.
 */
public class BaseServiceImpl<T> implements IBaseService<T> {

    @Autowired
    private BaseMapper<T> baseMapper;

    @Override
    public int insert(T entity){
        return baseMapper.insert(entity);
    }

    @Override
    public int deleteById(Serializable id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int deleteByMap(Map<String, Object> columnMap) {
        return baseMapper.deleteByMap(columnMap);
    }

    @Override
    public int delete(Wrapper<T> wrapper) {
        return baseMapper.delete(wrapper);
    }

    @Override
    public int deleteBatchIds(Collection<? extends Serializable> idList) {
        return baseMapper.deleteBatchIds(idList);
    }

    @Override
    public int updateById(T entity) {
        return baseMapper.updateById(entity);
    }

    @Override
    public int update(T entity, Wrapper<T> updateWrapper) {
        return baseMapper.update(entity,updateWrapper);
    }

    @Override
    public T selectById(Serializable id) {
        return baseMapper.selectById(id);
    }

    @Override
    public List<T> selectBatchIds(Collection<? extends Serializable> idList) {
        return baseMapper.selectBatchIds(idList);
    }

    @Override
    public List<T> selectByMap(Map<String, Object> columnMap) {
        return baseMapper.selectByMap(columnMap);
    }

    @Override
    public T selectOne(Wrapper<T> queryWrapper) {
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public Integer selectCount(Wrapper<T> queryWrapper) {
        return baseMapper.selectCount(queryWrapper);
    }

    @Override
    public List<T> selectList(Wrapper<T> queryWrapper) {
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> selectMaps(Wrapper<T> queryWrapper) {
        return baseMapper.selectMaps(queryWrapper);
    }

    @Override
    public List<Object> selectObjs(Wrapper<T> queryWrapper) {
        return baseMapper.selectObjs(queryWrapper);
    }

    @Override
    public IPage<T> selectPage(IPage<T> page, Wrapper<T> queryWrapper) {
        return baseMapper.selectPage(page,queryWrapper);
    }

    @Override
    public IPage<Map<String, Object>> selectMapsPage(IPage<T> page, Wrapper<T> queryWrapper) {
        return baseMapper.selectMapsPage(page,queryWrapper);
    }
}

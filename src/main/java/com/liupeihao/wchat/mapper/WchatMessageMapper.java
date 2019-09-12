package com.liupeihao.wchat.mapper;


import com.liupeihao.wchat.entity.WchatMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
  *  Dao 接口
 * </p>
 *
 * @author LPH
 * @since 2019-09-09
 */
@Mapper
public interface WchatMessageMapper extends BaseMapper<WchatMessage> {
	  
}
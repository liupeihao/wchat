package com.liupeihao.wchat.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * @author LPH
 * @since 2019-09-09
 */
@Data
@Accessors(chain = true)
@JsonSerialize
@Builder
public class WchatMessage{
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */

    @TableId(value="id",type= IdType.UUID)
    private String id;

    /**
     * 发送人(openId)
     */

    private String fromUserOpenId;


    private String createTime;

        /**
         * 消息类型，text为文本。image为图片，语音为voice，视频为video，小视频为shortvideo，地理位置为location，链接为link
         */

    private String msgType;

    private String fromUserNamr;

}
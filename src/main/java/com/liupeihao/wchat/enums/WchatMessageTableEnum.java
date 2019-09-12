package com.liupeihao.wchat.enums;


/**
 * @author LPH
 * @since 2019-09-09
 */
public enum WchatMessageTableEnum  {


    /**
     * 主键
     */
    id("id"),

    /**
     * 发送人(openId)
     */
    fromUserName("from_user_name"),

    createTime("create_time"),

    /**
     * 消息类型，text为文本。image为图片，语音为voice，视频为video，小视频为shortvideo，地理位置为location，链接为link
     */
    msgType("msg_type"),
    ;

    private final String column;

    WchatMessageTableEnum(String column) {
        this.column = column;
    }

    public String column() {
        return column;
    }

}
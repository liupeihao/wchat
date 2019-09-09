package com.liupeihao.wchat.dto.wchatmessage;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @ProjectName: giuhub
 * @Package: com.liupeihao.wchat.dto
 * @ClassName: WchatMessageDto
 * @Description:
 * @Author: LPH
 * @CreateDate: 2019/9/6 18:26
 * @UpdateUser:
 * @UpdateDate: 2019/9/6 18:26
 * @UpdateRemark:
 * @Version: 1.0
 */
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WchatMessageDto {
    //开发者微信
    private String ToUserName;
    //发送者账号
    private String FromUserName;
    //发送时间
    private Long CreateTime;
    //消息类型
    private String MsgType;
    //消息内容
    private String Content;
    //消息ID
    private Long MsgId;
    //图片链接（由系统生成）  当消息类型为图片时存在
    private String PicUrl;
    //图片消息媒体id，可以调用获取临时素材接口拉取数据。 当消息类型为图片时存在
    private String MediaId;
}

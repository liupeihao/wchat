package com.liupeihao.wchat.service;

import com.liupeihao.wchat.dto.wchatmessage.WchatMessageDto;
import com.liupeihao.wchat.plugin.base.service.IBaseService;

import javax.xml.bind.JAXBException;

/**
 *  Service
 * @author LPH
 * @since 2019-09-09
 */
public interface IWchatMessageService<WchatMessage> extends IBaseService<WchatMessage> {


    String receiveMessage(WchatMessageDto dto) throws JAXBException;
}

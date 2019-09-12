package com.liupeihao.wchat.plugin.utils.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

/**
 * @ProjectName: giuhub
 * @Package: com.liupeihao.wchat.plugin.utils
 * @ClassName: XmlParseUtils
 * @Description:
 * @Author: LPH
 * @CreateDate: 2019/9/7 2:04
 * @UpdateUser:
 * @UpdateDate: 2019/9/7 2:04
 * @UpdateRemark:
 * @Version: 1.0
 */
public class XmlParseUtils {


    /**
     * @Author LPH
     * @Date 2019/9/9 13:59
     * @Description
     *  将bean转化为 xml
     *
     */
    public static String beanToXml(Object obj) throws JAXBException {
        if(null != obj){
            StringWriter sw=new StringWriter();
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING,"UTF-8");//编码格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);//是否格式化生成的xml串
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);//是否省略xml头信息（<?xml version="1.0" encoding="gb2312" standalone="yes"?>）
            marshaller.marshal(obj,sw);
            return sw.toString();
        }
        return null;
    }
}

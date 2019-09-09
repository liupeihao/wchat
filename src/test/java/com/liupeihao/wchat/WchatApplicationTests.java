package com.liupeihao.wchat;

import com.liupeihao.wchat.dto.wchatmessage.WchatMessageDto;
import com.liupeihao.wchat.plugin.utils.encrypt.SHA1Utils;
import com.liupeihao.wchat.plugin.utils.encrypt.SortUtils;
import com.liupeihao.wchat.plugin.utils.http.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.security.NoSuchAlgorithmException;

@RunWith(SpringRunner.class)
//@SpringBootTest
@Slf4j
public class WchatApplicationTests {

    String token = "liupeihao";

    @Test
    public void contextLoads() throws NoSuchAlgorithmException {
        String signature = "2f46bd25d611268dd1a218743355d6994feb010a";
        long timestamp = 1567764318;
        String nonce = "1674787944";
        String echostr = "8210491490187082703";
        String[] arr = new String[] {token, timestamp+"", nonce };
        SortUtils.sort(arr);
        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(s);
        }
        log.info(sb.toString());
        String result = SHA1Utils.sha1(sb.toString());
        log.info(result);
    }


    @Test
    public void xmlAndBean() throws JAXBException {
        beanToXml();
    }


    public  void beanToXml() throws JAXBException {
        StringWriter sw=new StringWriter();
        JAXBContext context = JAXBContext.newInstance(WchatMessageDto.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING,"UTF-8");//编码格式
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);//是否格式化生成的xml串
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);//是否省略xml头信息（<?xml version="1.0" encoding="gb2312" standalone="yes"?>）
        WchatMessageDto dto = WchatMessageDto.builder()
                .FromUserName("o10-bv-2e-l0Q0R8IHyV0ZEP8Acw")
                .ToUserName("gh_b109e13a3a89")
                .MsgType("text")
                .CreateTime(System.currentTimeMillis())
                .Content("你好")
                .build();
        marshaller.marshal(dto,sw);
        log.info("result is :{}",sw.toString());
    }


    @Test
    public  void tuRingRobot() {

        String result = HttpClientUtils.postJSONAndReturnJSON("http://openapi.tuling123.com/openapi/api/v2", "{\n" +
                "\t\"reqType\":0,\n" +
                "    \"perception\": {\n" +
                "        \"inputText\": {\n" +
                "            \"text\": \"你在干嘛\"\n" +
                "        },\n" +
                "        \"inputImage\": {\n" +
                "            \"url\": \"\"\n" +
                "        },\n" +
                "        \"selfInfo\": {\n" +
                "            \"location\": {\n" +
                "                \"city\": \"\",\n" +
                "                \"province\": \"\",\n" +
                "                \"street\": \"\"\n" +
                "            }\n" +
                "        }\n" +
                "    },\n" +
                "    \"userInfo\": {\n" +
                "        \"apiKey\": \"a952b2569f794389a2fdcec96148ffac\",\n" +
                "        \"userId\": \"123456789\"\n" +
                "    }\n" +
                "}");
        log.info(result);


    }



}

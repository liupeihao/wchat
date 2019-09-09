package com.liupeihao.wchat.plugin.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.liupeihao.wchat.plugin.utils.string.StringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by @author fww on 2019-05-27.
 */
@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
public class LogIpConfig extends ClassicConverter {

    private String port;

    private String ip;

    /**
     * 获得linux中 ip:端口
     *
     * @param iLoggingEvent
     * @return
     */
    @Override
    public String convert(ILoggingEvent iLoggingEvent) {
        if (StringUtils.isNotBlank(this.ip)) {
            return this.ip ;
        }
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                String name = intf.getName();
                if (name.contains("docker") || name.contains("lo")) {
                    continue;
                }
                Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
                while (enumIpAddr.hasMoreElements()) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (inetAddress.isLoopbackAddress()) {
                        continue;
                    }
                    String ipAddress = inetAddress.getHostAddress();
                    if (!ipAddress.contains("::") && !ipAddress.contains("0:0:") && !ipAddress.contains("fe80")) {
                        this.ip = ipAddress;
                        break;
                    }
                }
            }
        } catch (SocketException ex) {
            this.ip = "127.0.0.1";
            log.error("获取ip地址信息异常", ex);
        }
        return this.ip;
    }


}

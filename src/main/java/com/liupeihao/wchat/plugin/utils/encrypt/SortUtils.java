package com.liupeihao.wchat.plugin.utils.encrypt;

/**
 * @ProjectName: giuhub
 * @Package: com.liupeihao.wchat.plugin.utils
 * @ClassName: SortUtils
 * @Description:
 * @Author: LPH
 * @CreateDate: 2019/9/6 18:10
 * @UpdateUser:
 * @UpdateDate: 2019/9/6 18:10
 * @UpdateRemark:
 * @Version: 1.0
 */
public class SortUtils {
    public static void sort(String a[]) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[j].compareTo(a[i]) < 0) {
                    String temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }
}

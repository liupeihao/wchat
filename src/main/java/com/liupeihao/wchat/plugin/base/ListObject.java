package com.liupeihao.wchat.plugin.base;

import java.io.Serializable;

/**
 * @author LPH
 * @Title: ListObject
 * @ProjectName zyzh-zz-idaccess
 * @Description: TODO
 * @date 2018/11/13 001310:39
 */

/**
 * 分页返回对象
 */
public class ListObject implements Serializable {
    //总条数
    private int total;
    //分页数据
    private Object r_list;

    public ListObject(int total, Object r_list) {
        this.total = total;
        this.r_list = r_list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getR_list() {
        return r_list;
    }

    public void setR_list(Object r_list) {
        this.r_list = r_list;
    }
}

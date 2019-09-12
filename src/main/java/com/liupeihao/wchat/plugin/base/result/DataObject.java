package com.liupeihao.wchat.plugin.base.result;

/**
 * @author LPH
 * @Title: DataObject
 * @ProjectName zyzh-zz-idaccess
 * @Description: TODO
 * @date 2018/11/13 001311:44
 */

public class DataObject {

    //单个对象
    private Object r_data;

    private Object r_list;

    private Integer total;

    public Object getR_data() {
        return r_data;
    }

    public void setR_data(Object r_data) {
        this.r_data = r_data;
    }

    public Object getR_list() {
        return r_list;
    }

    public void setR_list(Object r_list) {
        this.r_list = r_list;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}

package com.neuedu.common;

//这个类为了确保返回给前端的对象，都是同一个类的对象，然后把数据封装到这个类里
public class ResponseCode<T> {
    private Integer status;
    private T data;
    private String mag;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMag() {
        return mag;
    }

    public void setMag(String mag) {
        this.mag = mag;
    }

    //成功的时候只要返回状态码和成功获取的数据就可以了
    public static <T> ResponseCode successRS(Integer status, T data) {
        ResponseCode rs = new ResponseCode();
        rs.setStatus(status);
        rs.setData(data);
        return rs;
    }

    //状态码为0
    public static <T> ResponseCode successRS(T data) {
        ResponseCode rs = new ResponseCode();
        rs.setStatus(0);
        rs.setData(data);
        return rs;
    }

    //失败的时候只要返回状态码和失败的信息就可以了
    public static <T> ResponseCode defeatedRS(Integer status, String mag) {
        ResponseCode rs = new ResponseCode();
        rs.setStatus(status);
        rs.setMag(mag);
        return rs;
    }

    @Override
    public String toString() {
        return "ResponseCode{" +
                "status=" + status +
                ", data=" + data +
                ", mag='" + mag + '\'' +
                '}';
    }
}

package com.neuedu.pojo;


public class Product {
    private Integer id;
    private String pname;
    private Integer parentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", pname='" + pname + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}

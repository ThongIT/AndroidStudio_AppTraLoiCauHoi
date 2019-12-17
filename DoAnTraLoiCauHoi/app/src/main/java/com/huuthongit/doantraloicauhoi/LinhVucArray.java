package com.huuthongit.doantraloicauhoi;

public class LinhVucArray {
    private Integer id;
    private String tenLinhVuc;

    public LinhVucArray(LinhVucCauHoi linhVucCauHoi) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenLinhVuc() {
        return tenLinhVuc;
    }

    public void setTenLinhVuc(String tenLinhVuc) {
        this.tenLinhVuc = tenLinhVuc;
    }
    public LinhVucArray(Integer id,String tenLinhVuc){
        this.id=id;
        this.tenLinhVuc=tenLinhVuc;
    }

}

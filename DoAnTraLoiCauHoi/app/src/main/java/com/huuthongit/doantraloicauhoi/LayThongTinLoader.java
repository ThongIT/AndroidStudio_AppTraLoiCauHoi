package com.huuthongit.doantraloicauhoi;

import android.os.AsyncTask;

import java.util.HashMap;

public class LayThongTinLoader  extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
        String TaiKhoan = strings[0];
        String Email = strings[1];
        String MatKhau = strings[2];
        HashMap<String, String> params = new HashMap<>();
        params.put("ten_dang_nhap", TaiKhoan);
        params.put("email", Email);
        params.put("password", MatKhau);
        return CallAPI.doRequest("laythongtin", "GET", null, null);
    }
}

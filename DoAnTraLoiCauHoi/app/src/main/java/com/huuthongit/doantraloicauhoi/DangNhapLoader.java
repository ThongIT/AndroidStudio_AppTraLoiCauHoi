package com.huuthongit.doantraloicauhoi;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.HashMap;

public class DangNhapLoader extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
        String TaiKhoan = strings[0];
        String MatKhau = strings[1];
        HashMap<String, String> params = new HashMap<>();
        params.put("ten_dang_nhap", TaiKhoan);
        params.put("mat_khau", MatKhau);
        return CallAPI.doRequest("dangnhap", "POST", params, null);
    }
}

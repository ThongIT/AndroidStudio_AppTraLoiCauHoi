package com.huuthongit.doantraloicauhoi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class QuanLiTaiKhoan extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_li_tai_khoan);
        TextView editText_ten=findViewById(R.id.editText_taikhoan_tendangnhap);
        String token = MainActivity.sharedPreferences.getString("token", "");
        if (token =="") {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        String ten_dang_nhap = MainActivity.sharedPreferences.getString("ten_dang_nhap", "");
        editText_ten.setText(ten_dang_nhap);
        editText_ten.setEnabled(false);
    }

    public void CapNhat(View view) {
    }
}

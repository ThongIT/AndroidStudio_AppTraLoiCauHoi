package com.huuthongit.doantraloicauhoi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Menu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button button_trochoimoi;
        Button button_taikhoan;
        Button button_muacredit;
        Button button_bangxephang;
        Button button_lichsuchoi;
        TextView textView_diem;
        TextView textView_credit;
        TextView textView_tendangnhap;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        String token = MainActivity.sharedPreferences.getString("token", "");
        if (token == "") {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        String ten_dang_nhap = MainActivity.sharedPreferences.getString("ten_dang_nhap", "");
        String credit = MainActivity.sharedPreferences.getString("credit", "");
        String diem_cao_nhat = MainActivity.sharedPreferences.getString("diem_cao_nhat", "");
//            MediaPlayer song = MediaPlayer.create(Menu.this, R.raw.ailatrieuphu);
//            song.start();
        textView_credit=findViewById(R.id.textView_menu_credit);
        textView_tendangnhap=findViewById(R.id.textView_menu_tendangnhap);
        textView_diem=findViewById(R.id.textView_menu_diem);
        textView_tendangnhap.setText("Xin Chào:  "+ten_dang_nhap);
        textView_credit.setText(credit);
        textView_diem.setText("Điểm:"+diem_cao_nhat);
        button_trochoimoi=(Button)findViewById(R.id.button_menu_choimoi);
        button_taikhoan=(Button)findViewById(R.id.button_menu_taikhoan);
        button_muacredit=(Button)findViewById(R.id.button_menu_muacredit);
        button_bangxephang=(Button)findViewById(R.id.button_menu_bangxephang);
        button_lichsuchoi=(Button)findViewById(R.id.button_menu_lichsu);

        button_trochoimoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent trochoimoi=new Intent(Menu.this,LinhVucCauHoi.class);
                startActivity(trochoimoi);
            }
        });
        button_taikhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent taikhoan=new Intent(Menu.this,QuanLiTaiKhoan.class);
                startActivity(taikhoan);
            }
        });
        button_muacredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent credit=new Intent(Menu.this,MuaCredit.class);
                startActivity(credit);
            }
        });
        button_bangxephang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bangxephang=new Intent(Menu.this,BangXepHang.class);
                startActivity(bangxephang);
            }
        });
        button_lichsuchoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lichsuchoi=new Intent(Menu.this,LichSuChoi.class);
                startActivity(lichsuchoi);
            }
        });
    }

    public void DangXuat(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Bạn có chắc muốn đăng xuất?");
        alertDialogBuilder.setPositiveButton("Đăng Xuất",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        MainActivity.editor.clear();
                        MainActivity.editor.apply();
                        Intent intent = new Intent (Menu.this, MainActivity.class);
                        startActivity(intent);

                    }
                });

        alertDialogBuilder.setNegativeButton("Không",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}

package com.huuthongit.doantraloicauhoi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button button_trochoimoi;
        Button button_taikhoan;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
            MediaPlayer song = MediaPlayer.create(Menu.this, R.raw.ailatrieuphu);
            song.start();
        button_trochoimoi=(Button)findViewById(R.id.button_menu_choimoi);
        button_taikhoan=(Button)findViewById(R.id.button_menu_taikhoan);
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
    }
}

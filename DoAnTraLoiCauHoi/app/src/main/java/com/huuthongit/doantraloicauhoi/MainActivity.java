package com.huuthongit.doantraloicauhoi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button buttondangnhap;
        Button buttondangki;
        Button buttonquenmatkhau;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttondangnhap=(Button)findViewById(R.id.button_dangnhap_dangnhap);
        buttondangki=(Button)findViewById(R.id.button_dangnhap_dangki);
        buttonquenmatkhau=(Button)findViewById(R.id.button_dangnhap_quenmatkhau);
        buttondangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dangnhap=new Intent(MainActivity.this,Menu.class);
                startActivity(dangnhap);
            }
        });
    }
}

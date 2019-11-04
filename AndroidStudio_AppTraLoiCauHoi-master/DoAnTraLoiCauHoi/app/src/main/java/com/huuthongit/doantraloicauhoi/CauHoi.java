package com.huuthongit.doantraloicauhoi;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CauHoi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button button_a;
        Button button_b;
        Button button_c;

        button_a=(Button)findViewById(R.id.button_cauhoi_a);
        button_b=(Button)findViewById(R.id.button_cauhoi_b);
        button_c=(Button)findViewById(R.id.button_cauhoi_c);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau_hoi);
//        button_a=(Button)findViewById(R.id.button_cauhoi_a);
//
//        button_a.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent a=new Intent(CauHoi.this,TraLoiSai.class);
//                startActivity(a);
//            }
//        });
//        button_d.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent d=new Intent(CauHoi.this,TraLoiDung.class);
//                startActivity(d);
//            }
//        });
    }

    public void d(View view) {
        Button button_d;
        button_d=(Button)findViewById(R.id.button_cauhoi_d);
        button_d.setBackgroundColor(Color.GREEN);
        Toast.makeText(this, "Bạn chọn Đúng", Toast.LENGTH_SHORT).show();
    }

    public void a(View view) {
        Button button_a;
        Button button_d;
        button_d=(Button)findViewById(R.id.button_cauhoi_d);
        button_a=(Button)findViewById(R.id.button_cauhoi_a);
        button_d.setBackgroundColor(Color.GREEN);
        button_a.setBackgroundColor(Color.RED);
        Toast.makeText(this, "Bạn chọn Sai", Toast.LENGTH_SHORT).show();
    }

    public void b(View view) {
        Button button_b;
        Button button_d;
        button_d=(Button)findViewById(R.id.button_cauhoi_d);
        button_b=(Button)findViewById(R.id.button_cauhoi_b);
        button_d.setBackgroundColor(Color.GREEN);
        button_b.setBackgroundColor(Color.RED);
        Toast.makeText(this, "Bạn chọn Sai", Toast.LENGTH_SHORT).show();
    }

    public void c(View view) {
        Button button_c;
        Button button_d;
        button_d=(Button)findViewById(R.id.button_cauhoi_d);
        button_c=(Button)findViewById(R.id.button_cauhoi_c);
        button_d.setBackgroundColor(Color.GREEN);
        button_c.setBackgroundColor(Color.RED);
        Toast.makeText(this, "Bạn chọn Sai", Toast.LENGTH_SHORT).show();
    }
}


package com.huuthongit.doantraloicauhoi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LinhVucCauHoi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button button_thethao;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linh_vuc_cau_hoi);
        button_thethao=(Button)findViewById(R.id.button_linhvuccauhoi_1);
        button_thethao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent thethao=new Intent(LinhVucCauHoi.this,CauHoi.class);
                startActivity(thethao);
            }
        });
    }
}


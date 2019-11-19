package com.huuthongit.doantraloicauhoi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class LinhVucCauHoi extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button button_thethao;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linh_vuc_cau_hoi);
//        button_thethao=(Button)findViewById(R.id.button_linhvuccauhoi_1);
//        button_thethao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent thethao=new Intent(LinhVucCauHoi.this,CauHoi.class);
//                startActivity(thethao);
//            }
       // });
        this.btn1 = findViewById(R.id.button_linhvuccauhoi_1);
        this.btn2 = findViewById(R.id.button_linhvuccauhoi_2);
        this.btn3 = findViewById(R.id.button_linhvuccauhoi_3);
        this.btn4 = findViewById(R.id.button_linhvuccauhoi_4);
        this.btn5 = findViewById(R.id.button_linhvuccauhoi_5);
        if (getSupportLoaderManager().getLoader(0) != null) {
            getSupportLoaderManager().initLoader(0, null, this);
        }
        getSupportLoaderManager().restartLoader(0, null, this);


    }


    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new LinhVucLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray itemsArray = jsonObject.getJSONArray("data");
            this.btn1.setText(itemsArray.getJSONObject(0).getString("ten_linh_vuc"));
            this.btn2.setText(itemsArray.getJSONObject(1).getString("ten_linh_vuc"));
            this.btn3.setText(itemsArray.getJSONObject(2).getString("ten_linh_vuc"));
            this.btn4.setText(itemsArray.getJSONObject(3).getString("ten_linh_vuc"));
            this.btn5.setText(itemsArray.getJSONObject(4).getString("ten_linh_vuc"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}


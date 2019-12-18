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
    private Button linhVuc1;
    private Button linhVuc2;
    private Button linhVuc3;
    private Button linhVuc4;
    private Button linhVuc5;
    TextView textView_Tim;
    public int soTim=5;
    public static String linhVucDuocChon;
    private ArrayList<LinhVucArray> linhVucArray = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button button_thethao;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linh_vuc_cau_hoi);
        textView_Tim=findViewById(R.id.textView_linhvuccauhoi_tim);
        linhVuc1 = findViewById(R.id.button_linhvuccauhoi_1);
        linhVuc2 = findViewById(R.id.button_linhvuccauhoi_2);
        linhVuc3 = findViewById(R.id.button_linhvuccauhoi_3);
        linhVuc4 = findViewById(R.id.button_linhvuccauhoi_4);
        linhVuc5 = findViewById(R.id.button_linhvuccauhoi_5);
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
            JSONObject json = new JSONObject(data);
            JSONArray itemsArray = json.getJSONArray("data");
            for (int i = 0; i < itemsArray.length(); i++){
                JSONObject jsonObject = itemsArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String ten = jsonObject.getString("ten_linh_vuc");
                linhVucArray.add( new LinhVucArray(Integer.valueOf(id),ten));
            }
            linhVuc1.setText(String.valueOf(linhVucArray.get(0).getTenLinhVuc()));
            linhVuc2.setText(String.valueOf(linhVucArray.get(1).getTenLinhVuc()));
            linhVuc3.setText(String.valueOf(linhVucArray.get(2).getTenLinhVuc()));
            linhVuc4.setText(String.valueOf(linhVucArray.get(3).getTenLinhVuc()));
            linhVuc5.setText(String.valueOf(linhVucArray.get(4).getTenLinhVuc()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    public void linhvuc1(View view) {
        linhVucDuocChon = String.valueOf(linhVucArray.get(0).getId());
        startActivity(new Intent(this, CauHoi.class));
    }

    public void linhvuc5(View view) {
        linhVucDuocChon = String.valueOf(linhVucArray.get(4).getId());
        startActivity(new Intent(this, CauHoi.class));
    }

    public void linhvuc3(View view) {
        linhVucDuocChon = String.valueOf(linhVucArray.get(2).getId());
        startActivity(new Intent(this, CauHoi.class));
    }

    public void linhvuc4(View view) {
        linhVucDuocChon = String.valueOf(linhVucArray.get(3).getId());
        startActivity(new Intent(this, CauHoi.class));
    }

    public void linhvuc2(View view) {
        linhVucDuocChon = String.valueOf(linhVucArray.get(1).getId());
        startActivity(new Intent(this, CauHoi.class));
    }
}


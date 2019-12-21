package com.huuthongit.doantraloicauhoi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BangXepHang extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private RecyclerView recyclerView;
    private final static ArrayList<NguoiChoi> mlstNguoiChoi = new ArrayList<>();
    private NguoiChoiAdapter nguoiChoiAdapter;
    private BangXepHangLoader bangXepHangLoader;
    Button bxh;
    Button menu;

    private final static int PAGE_SIZE = 25;
    private int currentPage = 1;
    private int totalPage;
    private boolean isLoadingPage = false;
    private boolean isLastPage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bang_xep_hang);
        recyclerView = findViewById(R.id.rcv_main);
        bxh = findViewById(R.id.button_menu_bangxephang);
        menu = findViewById(R.id.btn_back);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BangXepHang.this, Menu.class);
                startActivity(intent);}});
        nguoiChoiAdapter = new NguoiChoiAdapter(mlstNguoiChoi, this);
        recyclerView.setAdapter(nguoiChoiAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LoadData(null);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int first = layoutManager.findFirstVisibleItemPosition();

                if (!isLoadingPage && !isLastPage) {
                    if ((visibleItemCount + first) >= totalItemCount
                            && first >= 0
                            && totalItemCount >= PAGE_SIZE) {
                        isLoadingPage = true;
                        currentPage++;
                        mlstNguoiChoi.add(null);
                        nguoiChoiAdapter.notifyItemInserted(mlstNguoiChoi.size() - 1);

                        Bundle data = new Bundle();
                        data.putInt("page", currentPage);
                        data.putInt("limit", PAGE_SIZE);
                        LoadData(data);
                    }

                }
            }
        });
    }

    private void LoadData(@Nullable Bundle data) {

        if (getSupportLoaderManager().getLoader(0) != null) {
            getSupportLoaderManager().restartLoader(0, data, this);
        }
        getSupportLoaderManager().initLoader(0, data, this);
    }

    @NonNull

    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        int page = 1;
        int limit = 25;
        if (args != null) {
            page = args.getInt("page");
            limit = args.getInt("limit");
        }
        return new BangXepHangLoader(this, page, limit);
    }


    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            int total = jsonObject.getInt("total");
            totalPage = total / PAGE_SIZE;
            if (total % PAGE_SIZE != 0) {
                totalPage++;
            }
            JSONArray itemArray = jsonObject.getJSONArray("data");
            if (mlstNguoiChoi.size() > 0) {
                mlstNguoiChoi.remove(mlstNguoiChoi.size() - 1);
                int scrollPosition = mlstNguoiChoi.size();
                nguoiChoiAdapter.notifyItemRemoved(scrollPosition);
            }
            for (int i = 0; i < itemArray.length(); i++) {
                int id = itemArray.getJSONObject(i).getInt("id");
                String tenDangNhap = itemArray.getJSONObject(i).getString("ten_dang_nhap");
                int diemCaoNhat = itemArray.getJSONObject(i).getInt("diem_cao_nhat");

                mlstNguoiChoi.add(new NguoiChoi(id, tenDangNhap, diemCaoNhat));
            }
            nguoiChoiAdapter.notifyDataSetChanged();

            isLoadingPage = false;
            isLoadingPage = (currentPage == totalPage);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    public void bxh(View view) {
        startActivity(new Intent(this, BangXepHang.class));
    }

}

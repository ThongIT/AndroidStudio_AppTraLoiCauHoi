package com.huuthongit.doantraloicauhoi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CauHoi extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    Button button_a,button_b,button_c,button_d,button_KhanGia,button_50,button_doicauhoi,button_goinguoithan;
    private TextView noi_dung, cau_hoi_so, diem,thoiGian,tim;
    private static final String TAG = "LinhVuc";
    private int stt, mdiem, color;
    public static int soTim=5;
    private Thread thread = new Thread();
    private ArrayList<CauHoiArray> cauHoiArrays = new ArrayList<>();
    public static String DapAnDuocChon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau_hoi);
        tim=findViewById(R.id.textView_cauhoi_tim);
        noi_dung = findViewById(R.id.textView_cauhoi_cauhoi);
        cau_hoi_so = findViewById(R.id.textView_cauhoi_socau);
        thoiGian=findViewById(R.id.textView_cauhoi_time);
        diem = findViewById(R.id.textView_cauhoi_diem);
        button_a = (Button) findViewById(R.id.button_cauhoi_a);
        button_b = (Button) findViewById(R.id.button_cauhoi_b);
        button_c = (Button) findViewById(R.id.button_cauhoi_c);
        button_d = (Button) findViewById(R.id.button_cauhoi_d);
        button_KhanGia=(Button)findViewById(R.id.button_cauhoi_khangia);
        //countdowntimer
        CountDownTimer countDownTimer=new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long dem=millisUntilFinished / 1000;
                    thoiGian.setText( dem+" s");
                    if(dem==0)
                    {
                        openDialogKetThuc();
                    }
            }

            @Override
            public void onFinish() {

            }
        }.start();
        button_KhanGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        if(getSupportLoaderManager().getLoader(0)!=null){
            getSupportLoaderManager().initLoader(0,null,this);
        }
        getSupportLoaderManager().restartLoader(0,null,this);



        if (savedInstanceState != null){
            mdiem = savedInstanceState.getInt("Diem");
            stt = savedInstanceState.getInt("Stt");
        }
        diem.setText("" + mdiem);
        cau_hoi_so.setText("" + stt);
        tim.setText(""+soTim);

    }
    //dialog trơ giúp khán giả
    public void openDialog(){
        Dialog dialog_khanGia=new Dialog(this);
        dialog_khanGia.setContentView(R.layout.dialog_khangia);
        dialog_khanGia.getWindow().setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        dialog_khanGia.setTitle("hihi");
        dialog_khanGia.show();


    }
    public void chuyenManHinhSangLinhVuc()
    {
        startActivity(new Intent(this, LinhVucCauHoi.class));
    }
    public void chuyenManHinhMenu()
    {
        startActivity(new Intent(this, Menu.class));
        Toast.makeText(this,"Bạn chơi lại từ đầu",Toast.LENGTH_SHORT).show();
    }
    //dialog kết thúc thời gian
    public void openDialogKetThuc(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Bạn đã hết thời gian trả lời");
                alertDialogBuilder.setPositiveButton("Chơi tiếp",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                soTim=soTim-1;
                                if(soTim>0)
                                {
                                    tim.setText(""+soTim);
                                    chuyenManHinhSangLinhVuc();
                                }
                                else
                                {

                                    chuyenManHinhMenu();

                                }

                            }
                        });

        alertDialogBuilder.setNegativeButton("Dừng",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                chuyenManHinhMenu();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }
    public void openDialogTraLoiSai(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Bạn đã trả lời sai");
        alertDialogBuilder.setPositiveButton("Chơi tiếp",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        soTim=soTim-1;
                        if(soTim>0)
                        {
                            tim.setText(""+soTim);
                            chuyenManHinhSangLinhVuc();
                        }
                        else
                        {

                            chuyenManHinhMenu();

                        }

                    }
                });

        alertDialogBuilder.setNegativeButton("Dừng",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                chuyenManHinhMenu();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new CauHoiLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try{
            JSONObject json = new JSONObject(data);
            JSONArray itemsArrays = json.getJSONArray("data");
            for (int i = 0; i < itemsArrays.length(); i++) {
                JSONObject jsonObject = itemsArrays.getJSONObject(i);
                String id = jsonObject.getString("id");
                String noi_dung = jsonObject.getString("noi_dung");
                String phuong_an_a = jsonObject.getString("phuong_an_a");
                String phuong_an_b = jsonObject.getString("phuong_an_b");
                String phuong_an_c = jsonObject.getString("phuong_an_c");
                String phuong_an_d = jsonObject.getString("phuong_an_d");
                String dap_an = jsonObject.getString("dap_an");
                cauHoiArrays.add(new CauHoiArray(Integer.valueOf(id), noi_dung, phuong_an_a, phuong_an_b, phuong_an_c, phuong_an_d, dap_an));
            }
            noi_dung.setText(String.valueOf(cauHoiArrays.get(0).getNoi_dung()));
            button_a.setText(String.valueOf(cauHoiArrays.get(0).getPhuong_an_a()));
            button_b.setText(String.valueOf(cauHoiArrays.get(0).getPhuong_an_b()));
            button_c.setText(String.valueOf(cauHoiArrays.get(0).getPhuong_an_c()));
            button_d.setText(String.valueOf(cauHoiArrays.get(0).getPhuong_an_d()));


        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
    private void traLoiDungCauHoi_ThoiGian(){
        CountDownTimer countDownTimer=new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long dem=millisUntilFinished / 1000;
                if(dem==0)
                {
                    chuyenManHinhSangLinhVuc();
                }
            }

            @Override
            public void onFinish() {


            }
        }.start();
    }

    public void a(View view) {
        DapAnDuocChon = cauHoiArrays.get(0).getDap_an();
        String a="A";
        if(a.equals(DapAnDuocChon))
        {
            Toast.makeText(this,"Bạn chọn đúng",Toast.LENGTH_SHORT).show();
            button_a.setBackgroundResource(R.drawable.bg_caudung);
            traLoiDungCauHoi_ThoiGian();

        }
        else
        {
            button_a.setBackgroundResource(R.drawable.bg_causai);
            openDialogTraLoiSai();
        }
    }

    public void b(View view) {
        DapAnDuocChon =cauHoiArrays.get(0).getDap_an();
        String b="B";
        if(b.equals(DapAnDuocChon))
        {
            Toast.makeText(this,"Bạn chọn đúng",Toast.LENGTH_SHORT).show();
            button_b.setBackgroundResource(R.drawable.bg_caudung);
            traLoiDungCauHoi_ThoiGian();
        }
        else
        {
            button_b.setBackgroundResource(R.drawable.bg_causai);
            openDialogTraLoiSai();
        }
    }

    public void c(View view) {
        DapAnDuocChon = cauHoiArrays.get(0).getDap_an();
        String c="C";
        if(c.equals(DapAnDuocChon))
        {
            Toast.makeText(this,"Bạn chọn đúng",Toast.LENGTH_SHORT).show();
            button_c.setBackgroundResource(R.drawable.bg_caudung);
            traLoiDungCauHoi_ThoiGian();
        }
        else
        {
            button_c.setBackgroundResource(R.drawable.bg_causai);
            openDialogTraLoiSai();
        }
    }

    public void d(View view) {
        DapAnDuocChon =cauHoiArrays.get(0).getDap_an();
        String d="D";
        if(d.equals(DapAnDuocChon))
        {
            Toast.makeText(this,"Bạn chọn đúng",Toast.LENGTH_SHORT).show();
            button_d.setBackgroundResource(R.drawable.bg_caudung);
            traLoiDungCauHoi_ThoiGian();
        }
        else
        {
            button_d.setBackgroundResource(R.drawable.bg_causai);
            openDialogTraLoiSai();
        }
    }
}


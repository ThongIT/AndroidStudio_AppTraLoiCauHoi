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
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class CauHoi extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    Button button_a,button_b,button_c,button_d,button_KhanGia,button_50,button_doicauhoi,button_goinguoithan;
    private TextView noi_dung, cau_hoi_so, diem,thoiGian,tim;
    private static final String TAG = "LinhVuc";
    private int stt, mdiem, color;
    public static int soTim=5;
    public static int cauHoiSo=0;
    public static int soDiem=0;
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
        button_50=findViewById(R.id.button_cauhoi_50);
        button_a = (Button) findViewById(R.id.button_cauhoi_a);
        button_b = (Button) findViewById(R.id.button_cauhoi_b);
        button_c = (Button) findViewById(R.id.button_cauhoi_c);
        button_d = (Button) findViewById(R.id.button_cauhoi_d);
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
//        button_KhanGia.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openDialog();
//            }
//        });
        if(getSupportLoaderManager().getLoader(0)!=null){
            getSupportLoaderManager().initLoader(0,null,this);
        }
        getSupportLoaderManager().restartLoader(0,null,this);



        if (savedInstanceState != null){
//            mdiem = savedInstanceState.getInt("Diem");
//            stt = savedInstanceState.getInt("Stt");
        }
        diem.setText(""+soDiem);
        cauHoiSo=cauHoiSo+1;
        cau_hoi_so.setText("" + cauHoiSo);
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
        cauHoiSo=0;
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
                                    cauHoiSo=0;
                                    soDiem=0;
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
                            cauHoiSo=0;
                            soDiem=0;
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
            soDiem=soDiem+10;

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
            soDiem=soDiem+10;
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
            soDiem=soDiem+10;
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
            soDiem=soDiem+10;
        }
        else
        {
            button_d.setBackgroundResource(R.drawable.bg_causai);
            openDialogTraLoiSai();
        }
    }
    //đổi câu hỏi theo lĩnh vực
    public void Doi(View view) {
        cauHoiSo=cauHoiSo-1;
        startActivity(new Intent(this, LinhVucCauHoi.class));
        Toast.makeText(this,"Bạn đã đồi thành công",Toast.LENGTH_SHORT).show();

    }
    // 50 50
    public void namMuoinamMuoi(View view) {
        DapAnDuocChon =cauHoiArrays.get(0).getDap_an();
        String d="D";
        String a="A";
        String b="B";
        String c="C";
        if (d.equals(DapAnDuocChon))
        {
            button_a.setVisibility(View.INVISIBLE);
            button_c.setVisibility(View.INVISIBLE);
            button_50.setEnabled(false);
        }
        if (a.equals(DapAnDuocChon))
        {
            button_b.setVisibility(View.INVISIBLE);
            button_c.setVisibility(View.INVISIBLE);
            button_50.setEnabled(false);
        }
        if (c.equals(DapAnDuocChon))
        {
            button_b.setVisibility(View.INVISIBLE);
            button_d.setVisibility(View.INVISIBLE);
            button_50.setEnabled(false);
        }
        if (b.equals(DapAnDuocChon))
        {
            button_d.setVisibility(View.INVISIBLE);
            button_c.setVisibility(View.INVISIBLE);
            button_50.setEnabled(false);
        }


    }
    // gọi điện thoại cho nguoi thân
    public void phone(View view) {
        DapAnDuocChon =cauHoiArrays.get(0).getDap_an();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Trợ giúp gọi điện thoại cho người thân");
        builder.setMessage("Câu trả lời giúp bạn là: "+DapAnDuocChon);
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),
                                "Cảm ơn bạn",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        builder.setCancelable(false);
        builder.show();
    }

    public void KhanGia(View view) {
//        DapAnDuocChon =cauHoiArrays.get(0).getDap_an();
//        int tong=100;
//        int min=0;
//        int dung=25;
//
//        int dapAnDung=new Random().nextInt(((tong-dung)-min+1)+min);
//        dapAnDung=dung+dapAnDung;
//        int sai1=new Random().nextInt(((tong-dapAnDung)-min+1)+min);
//        int sai2=new Random().nextInt(((tong-dapAnDung-sai1)-min+1)+min);
//        int sai3=tong-dapAnDung-sai1-sai2;
//        DapAnDuocChon =cauHoiArrays.get(0).getDap_an();
//        String d="D";
//        String a="A";
//        String b="B";
//        String c="C";
//        String[] items=new String[0];
//        if (d.equals(DapAnDuocChon))
//        {
//            items = new String[]{"Đáp án A: " + sai3 + "%", "Đáp án B: " + sai2 + "%", "Đáp án C: " + sai1 + "%", "Đáp án D: " + dapAnDung + "%"};
//        }
//        if (a.equals(DapAnDuocChon))
//        {
//            items = new String[]{"Đáp án A: "+ dapAnDung+"%", "Đáp án B: "+ sai2+"%", "Đáp án C: "+ sai1+"%","Đáp án D: "+ sai3+"%"};
//        }
//        if (c.equals(DapAnDuocChon))
//        {
//            items = new String[]{"Đáp án A: "+ sai1+"%", "Đáp án B: "+ sai2+"%", "Đáp án C: "+ dapAnDung+"%","Đáp án D: "+ sai3+"%"};
//        }
//        if (b.equals(DapAnDuocChon)) {
//            items = new String[]{"Đáp án A: " + sai2 + "%", "Đáp án B: " + dapAnDung + "%", "Đáp án C: " + sai1 + "%", "Đáp án D: " + sai3 + "%"};
//        }
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("List of Items")
//
//                .setItems(items, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                });
//
//        builder.setPositiveButton("OK", null);
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//        Button button = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
//        button.setBackgroundColor(Color.BLACK);
//        button.setPadding(0, 0, 20, 0);
//        button.setTextColor(Color.WHITE);

        final Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialog_chartbar);

        dialog.setCanceledOnTouchOutside(false);

        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);

        Button button = dialog.findViewById(R.id.button_d);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        veBieuDo(dialog);

        dialog.show();
    }
    public void veBieuDo(Dialog dialog) {
        DapAnDuocChon =cauHoiArrays.get(0).getDap_an();
        int tong=100;
        int min=0;
        int dung=30;

        int dapAnDung=new Random().nextInt(((tong-dung)-min+1)+min);
        dapAnDung=dung+dapAnDung;
        int sai1=new Random().nextInt(((tong-dapAnDung)-min+1)+min);
        int sai2=new Random().nextInt(((tong-dapAnDung-sai1)-min+1)+min);
        int sai3=tong-dapAnDung-sai1-sai2;
        DapAnDuocChon =cauHoiArrays.get(0).getDap_an();
        String d="D";
        String a="A";
        String b="B";
        String c="C";
        Integer[] items=new Integer[0];

        if (a.equals(DapAnDuocChon))
        {
            items = new Integer[]{dapAnDung ,sai2 ,sai1 , sai3 };
        }
        if (b.equals(DapAnDuocChon)) {
            items = new Integer[]{sai2 ,dapAnDung ,sai1 , sai3 };
        }
        if (c.equals(DapAnDuocChon))
        {
            items = new Integer[]{sai1 ,sai2 ,dapAnDung , sai3 };
        }
        if (d.equals(DapAnDuocChon))
        {
            items = new Integer[]{sai3 ,sai2 ,sai1 , dapAnDung };
        }
        BarChart barChart = dialog.findViewById(R.id.barChart);
        ArrayList<BarEntry> datas = new ArrayList<>();
        datas.add(new BarEntry(0,items[0]));
        datas.add(new BarEntry(1, items[1]));
        datas.add(new BarEntry(2, items[2]));
        datas.add(new BarEntry(3, items[3]));

        BarDataSet barDataSet = new BarDataSet(datas, "");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextSize(20f);

        BarData barData = new BarData(barDataSet);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        final String[] labels = new String[]{"A", "B", "C", "D"};
        IndexAxisValueFormatter formatter = new IndexAxisValueFormatter(labels);
        xAxis.setTextSize(20f);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(formatter);

        barChart.setData(barData);
        //barChart.setFitBars(true);

        // Không vẽ lưới trên biểu đồ
        xAxis.setDrawGridLines(false);
        barChart.getAxisLeft().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);

        barChart.getLegend().setEnabled(false);
        barChart.getDescription().setEnabled(false);

        barChart.setDoubleTapToZoomEnabled(false);

        barChart.setTouchEnabled(false);

        barChart.animateXY(5000, 5000);

        barChart.invalidate();
    }
}


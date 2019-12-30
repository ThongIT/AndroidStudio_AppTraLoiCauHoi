package com.huuthongit.doantraloicauhoi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    private EditText editTextTenDangNhap;
    private EditText editTextMatKhau;
    private final static String FILE_NAME_SHAREREF = "com.huuthongit.doantraloicauhoi";
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
        editTextMatKhau=findViewById(R.id.editText_danghap_matkhau);
        editTextTenDangNhap=findViewById(R.id.editText_danghap_tendangnhap);
        sharedPreferences = getSharedPreferences(FILE_NAME_SHAREREF, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        buttondangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dangki=new Intent(MainActivity.this,DangKi.class);
                startActivity(dangki);
            }
        });
    }
    public void launchActivityMenu() { Intent intent = new Intent(this, Menu.class);
        startActivity(intent); }
    public void a() { Intent intent = new Intent(this, QuanLiTaiKhoan.class);
        startActivity(intent); }
    public void DangNhap(View view) {
        EditText txtUsername = findViewById(R.id.editText_danghap_tendangnhap);
        EditText txtPassword = findViewById(R.id.editText_danghap_matkhau);

        String TaiKhoan = txtUsername.getText().toString();
        String MatKhau = txtPassword.getText().toString();

        new DangNhapLoader(){
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONObject json = new JSONObject(s);
                    boolean success = json.getBoolean("status");
                    if(!success) {
                        String mess = json.getString("mess");
                        editTextMatKhau.setText("");
                        AlertDialog.Builder builder = new AlertDialog.Builder(
                                MainActivity.this);
                        builder.setTitle("Thông báo");
                        builder.setMessage(""+mess);
                        builder.setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {

                                    }
                                });

                        builder.show();
                    }
                    if (success) {
                        String token = json.getString("token");
                        String credit = json.getString("credit");
                        String ten_dang_nhap = json.getString("ten_dang_nhap");
                        String id = json.getString("id");
                        String diem_cao_nhat = json.getString("diem_cao_nhat");
                        editor.putString("token", token);
                        editor.putString("credit", credit);
                        editor.putString("ten_dang_nhap", ten_dang_nhap);
                        editor.putString("id", id);
                        editor.putString("diem_cao_nhat", diem_cao_nhat);
                        editor.commit();
                        launchActivityMenu();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }.execute(TaiKhoan, MatKhau);
    }
}

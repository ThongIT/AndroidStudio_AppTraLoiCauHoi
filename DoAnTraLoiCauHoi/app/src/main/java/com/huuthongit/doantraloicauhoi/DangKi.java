package com.huuthongit.doantraloicauhoi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class DangKi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);
    }
    public void launchActivityMenu() { Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(this,"Đăng kí thành công",Toast.LENGTH_SHORT).show();
    }
    public void DangKi(View view) {
        EditText txtUsername = findViewById(R.id.editText_dangki_tendangnhap);
        EditText txtPassword = findViewById(R.id.editText_dangki_matkhau);
        EditText txtRePassword = findViewById(R.id.editText_dangki_xacnhanmatkhau);
        EditText email = findViewById(R.id.editText_dangki_email);

        final String TaiKhoan = txtUsername.getText().toString();
        final String MatKhau = txtPassword.getText().toString();
        final String XatNhanMatKhau = txtRePassword.getText().toString();
        final String Email = email.getText().toString();
        new DangKiLoader(){
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONObject json = new JSONObject(s);
                    boolean success = json.getBoolean("status");
                    if(success==false)
                    {
                        String emtry="";
                        if (TaiKhoan.equals(emtry)||Email.equals(emtry)||MatKhau.equals(emtry)) {
                            if(!MatKhau.equals(XatNhanMatKhau))
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(
                                        DangKi.this);
                                builder.setTitle("Thông báo");
                                builder.setMessage("Mật khẩu nhập lại không khớp");
                                builder.setPositiveButton("OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,
                                                                int which) {

                                            }
                                        });

                                builder.show();
                            }
                            AlertDialog.Builder builder = new AlertDialog.Builder(
                                    DangKi.this);
                            builder.setTitle("Thông báo");
                            builder.setMessage("Không được bỏ trống,Xin thử lại!");
                            builder.setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,
                                                            int which) {

                                        }
                                    });

                            builder.show();
                        }
                        else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(
                                    DangKi.this);
                            builder.setTitle("Thông báo");
                            builder.setMessage("Đăng kí không thành công,Xin thử lại!");
                            builder.setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,
                                                            int which) {

                                        }
                                    });

                            builder.show();
                        }
                    }
                    else
                    {
                        launchActivityMenu();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }.execute(TaiKhoan, Email,MatKhau);
    }
}

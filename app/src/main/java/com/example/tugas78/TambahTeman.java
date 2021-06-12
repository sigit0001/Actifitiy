package com.example.mysqlapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TambahTeman extends AppCompatActivity {
    EditText edNama,edTelpon;
    Button simpanBtn;
    String nm,tlp;
    int sukses;

    private static final String TAG =TambahTeman.class.getSimpleName();
    private static String url_insert = "http://10.0.2.2/umyTi/insertData.php";
    private static final String TAG_SUCCESS = "success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_teman);

        edNama = findViewById(R.id.editNama);
        edTelpon = findViewById(R.id.editTelpon);
        simpanBtn = findViewById(R.id.btnSimpan);

        simpanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edNama.getText().toString().equals("") || edTelpon.getText().toString().equals("")){
                    Toast.makeText(TambahTeman.this,"Data tidak boleh kosong",Toast.LENGTH_SHORT).show();
                }
                else {
                    nm = edNama.getText().toString();
                    tlp = edTelpon.getText().toString();

                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                    StringRequest strReq = new StringRequest(Request.Method.POST, url_insert, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, "Response: " + response.toString());
                            try {
                                JSONObject obj = new JSONObject(response);
                                sukses = obj.getInt(TAG_SUCCESS);
                                if (sukses == 1) {
                                    Toast.makeText(TambahTeman.this, "Sukses menyimpan data", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(TambahTeman.this, "Gagal menyimpan data", Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e(TAG, "Gagal simpan data: " + error.getMessage());
                        }
                    }){
                        @Override
                        protected Map<String,String> getParams(){
                            Map<String,String> params = new HashMap<>();

                            params.put("nama",nm);
                            params.put("telpon",tlp);

                            return params;
                        }
                    };

                    requestQueue.add(strReq);
                }
            }
        });

    }
}

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TambahTeman extends AppCompatActivity {
    EditText edNama,edTelpon;
    Button simpanBtn;
    String nm,tlp;
    int sukses;

    private static final String TAG =TambahTeman.class.getSimpleName();
    private static String url_insert = "http://10.0.2.2/umyTi/insertData.php";
    private static final String TAG_SUCCESS = "success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_teman);

        edNama = findViewById(R.id.editNama);
        edTelpon = findViewById(R.id.editTelpon);
        simpanBtn = findViewById(R.id.btnSimpan);

        simpanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edNama.getText().toString().equals("") || edTelpon.getText().toString().equals("")){
                    Toast.makeText(TambahTeman.this,"Data tidak boleh kosong",Toast.LENGTH_SHORT).show();
                }
                else {
                    nm = edNama.getText().toString();
                    tlp = edTelpon.getText().toString();

                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                    StringRequest strReq = new StringRequest(Request.Method.POST, url_insert, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, "Response: " + response.toString());
                            try {
                                JSONObject obj = new JSONObject(response);
                                sukses = obj.getInt(TAG_SUCCESS);
                                if (sukses == 1) {
                                    Toast.makeText(TambahTeman.this, "Sukses menyimpan data", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(TambahTeman.this, "Gagal menyimpan data", Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e(TAG, "Gagal simpan data: " + error.getMessage());
                        }
                    }){
                        @Override
                        protected Map<String,String> getParams(){
                            Map<String,String> params = new HashMap<>();

                            params.put("nama",nm);
                            params.put("telpon",tlp);

                            return params;
                        }
                    };

                    requestQueue.add(strReq);
                }
            }
        });

    }
}

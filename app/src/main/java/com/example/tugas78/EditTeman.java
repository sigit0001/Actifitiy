package com.example.tugas78;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mysqlapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditTeman extends AppCompatActivity {
    TextView idText;
    EditText edNama,edTelpon;
    Button editBtn;
    String id,nm,tlp,nameEd,telponEd;
    int sukses;

    private static final String TAG = MainActivity.class.getSimpleName();
    private static String url_select = "http://10.0.2.2/umyTi/updateData.php";
    private static final String TAG_SUCCES = "success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_teman);

        idText = findViewById(R.id.textid);
        edNama = findViewById(R.id.editNm);
        edTelpon = findViewById(R.id.editTlp);
        editBtn = findViewById(R.id.buttonEdit);

        Bundle bundle = getIntent().getExtras();
        id  = bundle.getString("kunci1");
        nm  = bundle.getString("kunci2");
        tlp = bundle.getString("kunci3");

        idText.setText("Id: "+id);
        edNama.setText(nm);
        edTelpon.setText(tlp);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditData();
            }
        });

    }

    public void EditData(){
        nameEd = edNama.getText().toString();
        telponEd = edTelpon.getText().toString();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringReq = new StringRequest(Request.Method.POST, url_select, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Respon: " + response.toString());
                try {
                    JSONObject jObj = new JSONObject(response);
                    sukses = jObj.getInt(TAG_SUCCES);
                    if (sukses == 1) {
                        Toast.makeText(EditTeman.this, "Sukses mengedit data", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(EditTeman.this, "Gagal", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(Volley error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(EditTeman.this,"Gagal Edit Data",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("id",id);
                params.put("nama",nameEd);
                params.put("telpon",telponEd);

                return params;
            }
        };
        requestQueue.add(stringReq);
        CallHomeActivity();
    }

    public void CallHomeActivity() {
        Intent inten = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(inten);
        finish();
    }

}

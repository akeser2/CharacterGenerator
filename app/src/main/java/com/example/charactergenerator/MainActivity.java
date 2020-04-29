package com.example.charactergenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button generate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Logic logic = new Logic();
        generate = findViewById(R.id.gen_btn);
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int classRandom = logic.getRandom(0, 11);
                task(classRandom);
            }
        });
    }
    private void task(final int selection) {
        // Instantiate the RequestQueue.
        final TextView textView = findViewById(R.id.text_space);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://www.dnd5eapi.co/api/classes";

// Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    //Variables needed to get down to specific item
                    JSONArray classList;
                    JSONObject classInfo;
                    String className;


                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //needed to see if item exists
                            classList = response.getJSONArray("results");
                            classInfo = classList.getJSONObject(selection);
                            className = classInfo.getString("name");
                        } catch (JSONException e) {
                            textView.setText("Response: " + e);
                        }
                        textView.setText("Response: " + className);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
                textView.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
    }
}

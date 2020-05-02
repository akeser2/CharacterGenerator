package com.example.charactergenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

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

    private Button generate;
    private String classURL;
    private String raceURL;
    private boolean classLock;
    private boolean raceLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AddInfo info = new AddInfo();
        generate = findViewById(R.id.gen_btn);
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int classRandom = info.getRandom(0, 11);
                int raceRandom = info.getRandom(0, 8);
                if (!isClassLocked()) {
                    genClass(classRandom);
                }
                if (!isRaceLocked()) {
                    genRace(raceRandom);
                }
            }
        });
    }

    private void genClass(final int selection) {
        // Instantiate the RequestQueue.
        final TextView setClass = findViewById(R.id.class_name);
        RequestQueue queue = Volley.newRequestQueue(this);
        classURL ="https://www.dnd5eapi.co/api/classes";
        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, classURL, null, new Response.Listener<JSONObject>() {
                    //Variables needed to get down to specific item
                    JSONArray classList;
                    JSONObject classInfo;
                    String className;
                    String classDetails;

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //needed to see if item exists
                            classList = response.getJSONArray("results");
                            classInfo = classList.getJSONObject(selection);
                            className = classInfo.getString("name");
                            classDetails = classURL+="/" + className.toLowerCase();
                            setHitDie(classDetails);
                            setAbilities();
                        } catch (JSONException e) {
                            setClass.setText("Error" + e);
                        }
                        setClass.setText("Class: " + className);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
                setClass.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
    }
    private void setHitDie(String url) {
        final TextView setHitDie = findViewById(R.id.hit_die);
        RequestQueue queue = Volley.newRequestQueue(this);
        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    //Variables needed to get down to specific item
                    int hitDie;

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            hitDie = response.getInt("hit_die");
                            //needed to see if item exists
                        } catch (JSONException e) {
                            setHitDie.setText("Error" + e);
                        }
                        setHitDie.setText("HP: " + hitDie);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                        setHitDie.setText("That didn't work!");
                    }
                });

// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
    }
    private void genRace(final int selection) {
        // Instantiate the RequestQueue.
        final TextView setClass = findViewById(R.id.race_name);
        RequestQueue queue = Volley.newRequestQueue(this);
        raceURL ="https://www.dnd5eapi.co/api/races";
        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, raceURL, null, new Response.Listener<JSONObject>() {
                    //Variables needed to get down to specific item
                    JSONArray raceList;
                    JSONObject raceInfo;
                    String raceName;;

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //needed to see if item exists
                            raceList = response.getJSONArray("results");
                            raceInfo = raceList.getJSONObject(selection);
                            raceName = raceInfo.getString("name");
                        } catch (JSONException e) {
                            setClass.setText("Error" + e);
                        }
                        setClass.setText("Race: " + raceName);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                        setClass.setText("That didn't work!");
                    }
                });

// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
    }
    public boolean isClassLocked() {
        final ToggleButton toggle = findViewById(R.id.class_lock);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    classLock = true;
                } else {
                    classLock = false;
                }
            }
        });
        return classLock;
    }
    public boolean isRaceLocked() {
        final ToggleButton toggle = findViewById(R.id.race_lock);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    raceLock = true;
                } else {
                    raceLock = false;
                }
            }
        });
        return raceLock;
    }
    public void setAbilities() {
        AddInfo info = new AddInfo();
        int[] abilities = info.abilityScores();
        TextView str = findViewById(R.id.attribute_0);
        str.setText("STR: " + abilities[0]);

        TextView dex = findViewById(R.id.attribute_1);
        dex.setText("DEX: " + abilities[1]);

        TextView con = findViewById(R.id.attribute_2);
        con.setText("CON: " + abilities[2]);

        TextView inte = findViewById(R.id.attribute_3);
        inte.setText("INT: " + abilities[3]);

        TextView wis = findViewById(R.id.attribute_4);
        wis.setText("WIS: " + abilities[4]);

        TextView cha = findViewById(R.id.attribute_5);
        cha.setText("CHA: " + abilities[5]);
    }
}

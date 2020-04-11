package org.tensorflow.lite.examples.detection;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class ViewDetected extends AppCompatActivity {

    ArrayList<HashMap<String, String>> arraylist;
    ListView listview;
    CustomViewDetectedList adapter;
    private RequestQueue rQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewdetected);


        listview = (ListView)findViewById(R.id.lv);
        arraylist = new ArrayList<HashMap<String, String>>();
        adapter = new CustomViewDetectedList(this, arraylist);
        listview.setAdapter(adapter);

        arraylist.clear();
        new Task().execute();

    }

    private class Task extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @SuppressLint("CommitTransaction")
        protected Void doInBackground(Void... params) {

            //      try {

            String registerURL = "103.51.20.9/aiapi/getdetails";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, registerURL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            try {

                                Log.d("vt",response);

                                JSONObject jsonObject = new JSONObject(response);

                                JSONArray arr = (JSONArray) jsonObject.get("data");



                                for (int i = 0; i < arr.length(); i++) {

                                    HashMap<String, String> map = new HashMap<String, String>();
                                    JSONObject obj = arr.getJSONObject(i);

                                    // Log.d("vt",obj.getString("comment"));
                                    map.put("get_object", obj.getString("get_object"));
                                    map.put("get_time", obj.getString("get_time"));


                                    arraylist.add(map);

                                }



                                adapter.notifyDataSetChanged();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("vt","error "+error.getMessage());
                        }
                    }) {
//                @Override
//                public Map<String, String> getHeaders() throws AuthFailureError {
//                    Map<String, String> params = new HashMap<String, String>();
//                    params.put("api_key", "c7ad3e44a02d132392ec845cc125a6a1");
//                    return params;
//                }

//                @Override
//                protected Map<String, String> getParams() {
//                    Map<String, String> params = new HashMap<String, String>();
//                    params.put("employeeid", empId);
//                    //   params.put("fromDate", password);
//                    //   params.put("toDate", password);
//
//                    return params;
//                }

            };

            rQueue = Volley.newRequestQueue(ViewDetected.this);
            rQueue.add(stringRequest);



//            } catch (JSONException e) {
//                e.printStackTrace();
//            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            adapter.notifyDataSetChanged();
        }
    }
}
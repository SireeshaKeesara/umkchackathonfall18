package diet.smsd.hackathon.smsddiet;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView captionText = (TextView) findViewById(R.id.textview_caption);
        final TextView contentText = (TextView) findViewById(R.id.content);
        captionText.setText("ANALYSIS AND REPORT");
        final ArrayList<String> foodArr = new ArrayList<String>();
        //Rest API Call
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL="https://api.edamam.com/search?q=beef&app_id=c45592f3&app_key=47fbef4a59ce1e90d9fbe3378815bd40&health=vegan";
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Rest Response", response.toString());
                        try {
                            JSONArray jsonArray= response.getJSONArray("hits");
                            Log.e("Rest Response", response.getString("hits"));
                            for (int i=0; i < jsonArray.length(); i++)
                            {
                                try {
                                    JSONObject oneObject = jsonArray.getJSONObject(i);
                                    // Pulling items from the array
                                    JSONObject oneObjectsItem = oneObject.getJSONObject("recipe");
                                    String dishName = oneObjectsItem.getString("label");
                                    Log.e("Loop Response", dishName.toString());
                                    foodArr.add(dishName);
                                    //String oneObjectsItem2 = oneObject.getString("anotherSTRINGNAMEINtheARRAY");
                                } catch (JSONException e) {
                                    // Oops
                                }
                            }
                            for(int i = 0; i < foodArr.size(); i++) {
                                contentText.append(foodArr.get(i));
                                contentText.append("\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest Response Error", error.toString());
                    }
                }

        );
        requestQueue.add(objectRequest);


    }
}

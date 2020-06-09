package noel.example.com.jsonparsingwithvideo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

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
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public  static  final String TAG="TAG";
    RecyclerView videolist;
    VideoAdapter adapter;
    List<Video> allvideos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        allvideos=new ArrayList<>();

        videolist=findViewById(R.id.videolist);
        videolist.setLayoutManager(new LinearLayoutManager(this));
        adapter=new VideoAdapter(this,allvideos);
        videolist.setAdapter(adapter);
        parsedata();
    }

    private void parsedata() {

        String URL= "https://raw.githubusercontent.com/bikashthapa01/myvideos-android-app/master/data.json";

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest =new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray categories=response.getJSONArray("categories");
                    JSONObject categoriesdata = categories.getJSONObject(0);
                    JSONArray videos = categoriesdata.getJSONArray("videos");

                    for(int i=0;i<videos.length();i++){

                        JSONObject video = videos.getJSONObject(i);
                        Video v= new Video();
                        v.setTitle(video.getString("title"));
                        v.setDescription(video.getString("description"));
                        v.setAuthor(video.getString("subtitle"));
                        v.setImageurl(video.getString("thumb"));
                        JSONArray videoUrl = video.getJSONArray("sources");
                        v.setVideourl(videoUrl.getString(0));
                        allvideos.add(v);
                        adapter.notifyDataSetChanged();
                    }

                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,"onErrorResponse:" +error.getMessage());
            }
        });

        requestQueue.add(objectRequest);
    }


}

package com.astroweb;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;

// ./gradlew cleanBuildCache clean && ./gradlew installDebug
// cd ~/Android/Sdk/emulator && ./emulator -avd $(./emulator -list-avds)
// heroku logs --tail --app stijanic-astroweb
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    String resultGet;
    String resultPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            callAstrowebServer();
            Snackbar.make(view, "Get you Natal Chart!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void callAstrowebServer() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String urlPost = "https://stijanic-astroweb.herokuapp.com/api/post/" + Math.floor(Math.random() * 100);
        String urlGet = "https://stijanic-astroweb.herokuapp.com/api/get/" + Math.floor(Math.random() * 100);

        // Request a string response from the provided URL.
        StringRequest stringGetRequest = new StringRequest(
                Request.Method.GET,
                urlGet,
                response -> {
                    // Display the response string.
                    resultGet = "Get response is:\n" + response;
                    ((TextView) findViewById(R.id.textview_first)).setText(resultGet + "\n" + resultPost);
                },
                error -> Log.i(TAG, "That didn't work -> " + urlGet));

        // Request a string response from the provided URL.
        StringRequest stringPostRequest = new StringRequest(
                Request.Method.POST,
                urlPost,
                response -> {
                    // Display the response string.
                    resultPost = "Post response is:\n" + response;
                    ((TextView) findViewById(R.id.textview_first)).setText(resultGet + "\n" + resultPost);
                },
                error -> Log.i(TAG, "That didn't work!")) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(Math.floor(Math.random() * 100)));
                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringGetRequest);
        queue.add(stringPostRequest);
    }
}

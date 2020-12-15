package com.astroweb;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Date;
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
            Snackbar.make(view, "Get you Natal Chart! " + new Date(), Snackbar.LENGTH_LONG)
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // MotionEvent object holds X-Y values
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            String text = "You click at x = " + event.getX() + " and y = " + event.getY();
            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        }

        return super.onTouchEvent(event);
    }

    public void callAstrowebServer() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String urlPost = "https://stijanic-astroweb.herokuapp.com/api/post/" + Math.floor(Math.random() * 100);
        //String urlPost = "http://192.168.245.130:8080/api/post/" + Math.floor(Math.random() * 100);
        String urlGet = "https://stijanic-astroweb.herokuapp.com/api/get/1"; // + Math.floor(Math.random() * 100);
        //String urlGet = "http://10.0.2.2:8080/api/get/1"; // + Math.floor(Math.random() * 100);

        // Request a string response from the provided URL.
        StringRequest stringGetRequest = new StringRequest(
                Request.Method.GET,
                urlGet,
                response -> {
                    // Display the response string.
                    resultGet = "Natal Chart of Person with id 1 at " + new Date() + " is:\n" + response;
                    ((TextView) findViewById(R.id.textview_first)).setText(resultGet + "\n\n" + resultPost);
                    Log.i(TAG, "*" + resultGet + "*" + resultPost + "*");
                },
                error -> Log.i(TAG, "That didn't work -> " + urlGet));

        // Request a string response from the provided URL.
        StringRequest stringPostRequest = new StringRequest(
                Request.Method.POST,
                urlPost,
                response -> {
                    // Display the response string.
                    resultPost = "Echo Post response is:\n" + response;
                    ((TextView) findViewById(R.id.textview_first)).setText(resultGet + "\n\n" + resultPost);
                    Log.i(TAG, "*" + resultGet + "*" + resultPost + "*");
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

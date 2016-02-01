package in.windowsgeekpro.logger;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
    }

    public void onConnect(View view) {
        new Thread(){
            public void run() {
                HttpClient myClient = new DefaultHttpClient();
                HttpPost post = new HttpPost("http://localhost/login.php");
                try {
                    List<NameValuePair> myArgs = new ArrayList<NameValuePair>();
                    myArgs.add(new BasicNameValuePair("username", "admin"));
                    myArgs.add(new BasicNameValuePair("password", "admin"));
                    post.setEntity(new UrlEncodedFormEntity(myArgs));
                    HttpResponse myResponse = myClient.execute(post);
                    BufferedReader br = new BufferedReader( new InputStreamReader(myResponse.getEntity().getContent()));
                    String line = "";
                    while ((line = br.readLine()) != null)
                    {
                        Log.d("mytag", line);
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
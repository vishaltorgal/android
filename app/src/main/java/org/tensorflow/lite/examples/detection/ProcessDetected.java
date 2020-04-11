package org.tensorflow.lite.examples.detection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProcessDetected extends Activity {

    TextView tv_display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.processdetected);

        tv_display = findViewById(R.id.tv_display);

        Bundle bundle = getIntent().getExtras();
        bundle.getIntegerArrayList("");

        if (bundle != null)
        {
            String title = bundle.getString("key_1");
            Log.d("vt","ddd "+title);
            tv_display.setText(title);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
      //  Log.d("vt","back");
          Intent intent = new Intent(ProcessDetected.this,DetectorActivity.class);
          startActivity(intent);
    }

}
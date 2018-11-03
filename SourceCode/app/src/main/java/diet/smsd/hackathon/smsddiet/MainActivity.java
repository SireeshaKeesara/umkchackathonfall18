package diet.smsd.hackathon.smsddiet;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView captionText = (TextView) findViewById(R.id.textview_caption);
        captionText.setText("ANALYSIS AND REPORT");
    }
}

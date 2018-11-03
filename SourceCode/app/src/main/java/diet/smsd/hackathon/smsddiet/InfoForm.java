package diet.smsd.hackathon.smsddiet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class InfoForm extends AppCompatActivity {
    Button mSubmitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_form);
        mSubmitBtn = (Button) findViewById(R.id.submitBtn);
        mSubmitBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoForm.this, MainActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }

}

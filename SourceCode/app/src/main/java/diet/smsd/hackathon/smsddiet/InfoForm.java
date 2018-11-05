package diet.smsd.hackathon.smsddiet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InfoForm extends AppCompatActivity {
    Button mSubmitBtn;
    Spinner spinner;
    Spinner spinner2;
    String pDiseaseStr;
    String fDiseaseStr;
    int pDiseaseID;
    int fDiseaseID;
    String pRecommend;
    String fRecommend;
    Disease disease = new Disease();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_form);
        mSubmitBtn = (Button) findViewById(R.id.submitBtn);
        mSubmitBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Diseases");
                //pRecommend = current_user_db.child(pDiseaseStr);
                current_user_db.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //To-be implemented: have a spinner and s
                        for(DataSnapshot ds: dataSnapshot.getChildren()){
                            //Toast.makeText(getApplicationContext(), ds.getKey() + pDiseaseStr, Toast.LENGTH_LONG).show();
                            if (ds.getKey().equals(pDiseaseStr)){
                                pRecommend = ds.getValue(String.class);

                            }

                            //pRecommend = disease.getDisease();
                            //list.add(td.getName().toString()+"\n"+td.getEmail().toString()+"\n"+td.getContact().toString()+"\n "+td.getType().toString()+"\n"+td.getZipcode().toString());
                        }
                        Toast.makeText(getApplicationContext(), "Recommended: " + pRecommend, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(InfoForm.this, MainActivity.class);
                        intent.putExtra("diet_param", pRecommend);
                        startActivity(intent);
                        finish();
                        //lv.setAdapter(ad);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                return;
            }
        });


        //Spinner
        spinner = (Spinner) findViewById(R.id.pDisease_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Disease, android.R.layout.simple_spinner_item); //specify the actual array to bind to
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter); //attach the adapter to the spinner to make it alive

        //This is the method that listens to the user's behavior whenever there is a change in the selected item. This will determine
        // the proper behavior. There are two mandatory methods to include:
        // onItemSelected and onNothingSelected()
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pDiseaseStr = (String)parent.getItemAtPosition(position);
                pDiseaseID = (int) parent.getItemIdAtPosition(position);
                //Toast.makeText(parent.getContext(), "Selected: " + pDiseaseStr + pDiseaseID, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //Similar to the notion of spinner1, spinner2 takes in the languagesOut_array instead of using the same array.
        //The reason is maybe the translator is only limited to writing certain languages or maybe able to read more.
        //Therefore, they should be separate from another.
        spinner2 = (Spinner) findViewById(R.id.fDisease_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.Disease, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fDiseaseStr = (String)parent.getItemAtPosition(position);
                fDiseaseID = (int) parent.getItemIdAtPosition(position);
                //Toast.makeText(parent.getContext(), "Output Selected: " + fDiseaseStr + fDiseaseID, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}


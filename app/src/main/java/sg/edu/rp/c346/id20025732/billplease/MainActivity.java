package sg.edu.rp.c346.id20025732.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    TextView amtTextview;
    EditText amteditText;
    TextView amtofpplTextview;
    EditText amtofppleditText;
    ToggleButton svs;
    ToggleButton gst;
    TextView disctextview;
    EditText disceditText;
    RadioGroup grouppaymentrg;
    RadioButton cashrb;
    RadioButton paynowrb;
    Button buttonforsplit;
    Button buttontoreset;
    TextView totalamtTextview;
    TextView splitamtTextview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amtTextview = findViewById(R.id.AmounttextView);
        amteditText = findViewById(R.id.edittextforamount);
        amtofpplTextview = findViewById(R.id.numofppltextview);
        amtofppleditText = findViewById(R.id.edittextfornumofpax);
        svs = findViewById(R.id.svsbutton);
        gst = findViewById(R.id.gstbutton);
        disctextview = findViewById(R.id.discounttextview);
        disceditText = findViewById(R.id.edittextfordiscount);
        grouppaymentrg = findViewById(R.id.radiogrouppaymentmethod);
        cashrb = findViewById(R.id.cashradiobutton);
        paynowrb = findViewById(R.id.paynowradiobutton);
        buttonforsplit = findViewById(R.id.amtperpersonbutton);
        buttontoreset = findViewById(R.id.resetamtbutton);
        totalamtTextview = findViewById(R.id.totalbilltextview);
        splitamtTextview = findViewById(R.id.splitamounttextview);

        buttonforsplit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Integer nopax =
                        Integer.parseInt(amtofppleditText.getText().toString());
                double totalamount =
                        Double.parseDouble(amteditText.getText().toString());

                if(svs.isChecked()){
                    totalamount = totalamount*1.10;
                }
                else if (gst.isChecked()){
                    totalamount = totalamount*1.07;
                }
                else if (svs.isChecked() && gst.isChecked()){
                    totalamount = totalamount *1.17;
                }
                else{
                    totalamount = totalamount;
                }

                double discamount =
                        Double.parseDouble(disceditText.getText().toString());
                if(discamount != 0){
                    totalamount = (1 - (discamount / 100) * totalamount);
                }
                totalamtTextview.setText("Your Total Bill is: $" + totalamount);
                String perperson = "Each person pays: $" + totalamount/nopax;

                if(grouppaymentrg.getCheckedRadioButtonId() == R.id.cashradiobutton){
                    perperson = perperson + " in cash";
                }
                else{
                    perperson = perperson + " via paynow to 9123 4567";
                }
                splitamtTextview.setText(perperson);

                buttontoreset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        amteditText.setText("");
                        amtofppleditText.setText("");
                        svs.setChecked(false);
                        gst.setChecked(false);
                        disceditText.setText("");
                        totalamtTextview.setText("");
                        splitamtTextview.setText("");
                    }
                });


            }
        });


    }
}
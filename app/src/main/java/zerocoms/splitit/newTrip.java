package zerocoms.splitit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class newTrip extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);
        EditText etdestination=findViewById(R.id.etDestination);
        EditText etdays=findViewById(R.id.etDays);

        String destination=etdestination.getText().toString();
        int Days=Integer.parseInt(etdays.getText().toString());

    }
}

package zerocoms.splitit;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

public class DashBoard extends Activity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.more_tab_menu, menu);

        // return true so that the menu pop up is opened
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        Button btCreate=findViewById(R.id.btcreatetrip);
        Button btreview=findViewById(R.id.btprevious);
        Button btFriend=findViewById(R.id.btfriends);
        ImageButton backd=findViewById(R.id.backD);

        backd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DashBoard.super.onBackPressed();
            }
        });

        //Call the activities

        btCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoard.this,newTrip.class));
            }
        });

        btFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoard.this,mkfriends.class));
            }
        });

        btreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoard.this,previoustrips.class));
            }
        });

    }
}

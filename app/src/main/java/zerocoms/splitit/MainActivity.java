package zerocoms.splitit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btLogin=findViewById(R.id.btLogin);
        Button btReg=findViewById(R.id.btReg);

        //Now to initialise the FIrebase Authentication

        mAuth = FirebaseAuth.getInstance();

        //To check if the user is already logged in

        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        btReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,RegActivity.class);
                startActivity(intent);

            }
        });

    }

    private void updateUI(FirebaseUser currentUser) {
        //the user is already logged in
        if(currentUser!=null)
        startActivity(new Intent(MainActivity.this,DashBoard.class));
    }
}

package zerocoms.splitit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    String memail, mpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        final EditText Username,Email,ConfirmPassword;
        final android.support.design.widget.TextInputEditText Password;
        //initializing the object of Firebase Authentication

        mAuth = FirebaseAuth.getInstance();

        //Checking if the user is logged on

        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

        //Defining the i/o elements

        Button btReg=(Button)findViewById(R.id.btsignup);

        //Setting the button as Disabled

        btReg.setClickable(false);

         Username=findViewById(R.id.etUsername);
         Email=findViewById(R.id.etEmail);
         Password=findViewById(R.id.etpassword);
         ConfirmPassword=findViewById(R.id.etConfirmPassword);


        //Setting the Onclick Listener

        btReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                memail=Email.getText().toString();
                mpassword=Password.getText().toString();
                //ConfirmPassword.getText().toString();
                mAuth.createUserWithEmailAndPassword(memail, mpassword)
                        .addOnCompleteListener(RegActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("Reg Status", "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                   // updateUI(user);
                                    Toast.makeText(RegActivity.this,"Successfull",Toast.LENGTH_LONG).show();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("Reg Status", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(RegActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                   // updateUI(null);
                                }

                                // ...
                            }
                        });

            }
        });


    }

    private void updateUI(FirebaseUser currentUser) {

        if(currentUser!=null)
        {
            //the user is already logged in
            startActivity(new Intent(RegActivity.this,DashBoard.class));
        }
    }
}

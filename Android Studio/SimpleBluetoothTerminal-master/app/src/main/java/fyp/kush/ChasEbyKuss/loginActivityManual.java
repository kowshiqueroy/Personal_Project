package fyp.kush.ChasEbyKuss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class loginActivityManual extends AppCompatActivity {


    Button enter;
    EditText emailf;
    EditText pass;

    DatabaseReference reff,p;
    int vari=0;
    Member member;

    public static boolean loginToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_manual);
        if (loginToken) {

            Intent intent = new Intent(loginActivityManual.this, CloudMenu.class);
            startActivity(intent);
            finish();

        }

        enter=findViewById(R.id.EnterBtn);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: logintok "+loginToken);



                emailf= findViewById(R.id.emailb);
                pass= findViewById(R.id.passb);
                Log.d("TAG", "onCreate: ");
member= new Member();
                reff= FirebaseDatabase.getInstance().getReference().child("Member");

                String pa=pass.getText().toString();
                String em=emailf.getText().toString();
                member.setPassdb(pass.getText().toString());
               member.setEmaildb(emailf.getText().toString());
                vari++;








                reff.child(member.getEmaildb()).child("emaildb").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (!task.isSuccessful()) {
                            Log.e("firebase", "Error getting data", task.getException());

                            reff.child(member.getEmaildb()).setValue(member);


                        }
                        else {
                            Log.d("firebase Get Data: ", String.valueOf(task.getResult().getValue()));

                            if (String.valueOf(task.getResult().getValue()).equals(member.getEmaildb()))
                            {


                                reff.child(member.getEmaildb()).child("passdb").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DataSnapshot> task) {


                                        if (String.valueOf(task.getResult().getValue()).equals(member.getPassdb()))
                                        {


                                            Intent intent = new Intent(loginActivityManual.this, CloudMenu.class);
                                            startActivity(intent);
                                            loginToken=true;
                                            finish();
                                            
                                        }
                                        
                                        else{


                                            Toast.makeText(getApplicationContext(),
                                                    "Invalid Pass",
                                                    Toast.LENGTH_SHORT).show();
                                            
                                            
                                            
                                        }
                                        
                                        
                                        
                                        
                                        
                                    }
                                });






                            }
                            else{

                                reff.child(member.getEmaildb()).setValue(member);


                            }

                        }
                    }
                });

                //reff.push().setValue(member.getName() + " " + member.getEmail());
                //reff.push().setValue("Hello");
                //reff.setValue("Hi");




            }
        });






    }
}
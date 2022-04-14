package fyp.kush.ChasEbyKuss;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class loginActivityManual extends AppCompatActivity {


    Button enter;
    EditText emailf;
    EditText pass;
    TextView t;

    public  static  String em;

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

        enter=findViewById(R.id.login);
        t=findViewById(R.id.textView2);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: logintok " + loginToken);


                emailf = findViewById(R.id.username);
                pass = findViewById(R.id.password);


                if (!emailf.getText().toString().equals("") && !pass.getText().toString().equals("")) {
                    Log.d("TAG", "onCreate: ");
                member = new Member();
                reff = FirebaseDatabase.getInstance().getReference().child("Member");
                String salt = emailf.getText().toString();
                StringBuffer buffer = new StringBuffer(salt);


                salt = buffer.reverse().toString() + emailf.getText().toString() + salt;
                String paa = pass.getText().toString() + salt;

                String pa = myHashMaker(paa);
                em = emailf.getText().toString();
                member.setPassdb(pa);
                member.setEmaildb(emailf.getText().toString());
                member.setDatas("");
                vari++;


                reff.child(member.getEmaildb()).child("emaildb").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (!task.isSuccessful()) {
                            Log.e("firebase", "Error getting data", task.getException());

                            reff.child(member.getEmaildb()).setValue(member);


                        } else {
                            Log.d("firebase Get Data: ", String.valueOf(task.getResult().getValue()));

                            if (String.valueOf(task.getResult().getValue()).equals(member.getEmaildb())) {


                                reff.child(member.getEmaildb()).child("passdb").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                    @RequiresApi(api = Build.VERSION_CODES.O)
                                    @Override
                                    public void onComplete(@NonNull Task<DataSnapshot> task) {


                                        if (String.valueOf(task.getResult().getValue()).equals(pa)) {
                                            t.setText("Success");
                                            enter.setText("Login");

                                            Intent intent = new Intent(loginActivityManual.this, CloudMenu.class);
                                            startActivity(intent);
                                            loginToken = true;
                                            finish();

                                        } else {


                                            Toast.makeText(getApplicationContext(),
                                                    "Invalid Pass",
                                                    Toast.LENGTH_SHORT).show();
                                            t.setText("Wrong Pass");


                                        }


                                    }
                                });


                            } else {

                                reff.child(member.getEmaildb()).setValue(member);


                            }

                        }
                    }
                });

                //reff.push().setValue(member.getName() + " " + member.getEmail());
                //reff.push().setValue("Hello");
                //reff.setValue("Hi");

            }
            else {


                t.setText("Empty UserName Pass");
            }



            }
        });






    }



    public String myHashMaker(String s) {
        try {

            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();


            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
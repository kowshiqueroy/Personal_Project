package fyp.kush.ChasEbyKuss;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class CloudMenu extends AppCompatActivity {

    Button logout, pslisting, senddatas, viewdatas, chat, getsug,setsug;
    DatabaseReference reff2,reff3;
    EditText e,sol,con;
    TextView t;
    String ee="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_menu);



        pslisting= findViewById(R.id.button);
        senddatas= findViewById(R.id.button2);
        viewdatas= findViewById(R.id.button3);
        chat= findViewById(R.id.button4);
        getsug= findViewById(R.id.button5);
        setsug= findViewById(R.id.button9);
        logout= findViewById(R.id.button6);
        e= findViewById(R.id.editt);
        sol= findViewById(R.id.edits);
        con= findViewById(R.id.editc);
        t= findViewById(R.id.textView);

         ee=e.getText().toString();
        Log.d("e", "onCreate: "+e+ee);

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                reff2= FirebaseDatabase.getInstance().getReference().child("chats");
                reff2.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {





                        if (!task.isSuccessful()) {

                            Log.d("e", "on!Complete: ");
                        }

                        else {
                            Log.d("e", "onCreate: "+e+ee);

                                if (!e.getText().toString().equals("")){
                                    Date currentTime = Calendar.getInstance().getTime();

                                    String a=currentTime+" "+loginActivityManual.em+": "+ e.getText().toString()+"\n"+String.valueOf(Objects.requireNonNull(task.getResult()).getValue());
                                    reff2.setValue(a);
                                    t.setText("Succes\n"+a);
                                    e.setText("");


                                }
                                else {

                                    t.setText(String.valueOf(Objects.requireNonNull(task.getResult()).getValue()));


                                }



                        }

                    }
                });




            }
        });



        pslisting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                reff2= FirebaseDatabase.getInstance().getReference().child("pslist");
                reff2.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {





                        if (!task.isSuccessful()) {

                            Log.d("e", "on!Complete: ");
                        }

                        else {
                            Log.d("e", "onCreate: "+e+ee);
                            if (!e.getText().toString().equals("")){


                            String a=String.valueOf(loginActivityManual.em+": Prob:"+e.getText().toString()+" Sol:"+sol.getText().toString()+ "\n" +Objects.requireNonNull(task.getResult()).getValue()) ;
                            reff2.setValue(a);
                                e.setText("");
                            t.setText("Succes, other problems/solutions:\n"+a);}

                            else {
                                t.setText("Other problems/solutions:\n" + (String.valueOf(Objects.requireNonNull(task.getResult()).getValue())));
                            }

                            if (String.valueOf(Objects.requireNonNull(task.getResult()).getValue()).equals("null")){

                                t.setText("Not Available. Try Few Days later");
                                reff2.setValue("Not Available. Try Few Days later");


                            }

                        }

                    }
                });









            }
        });



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginActivityManual.loginToken=false;
                startActivity(new Intent(CloudMenu.this,MainActivity.class));
                finish();
            }
        });


        senddatas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] a = {""};
                reff2= FirebaseDatabase.getInstance().getReference().child("Member").child(loginActivityManual.em).child("datas");
                reff2.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (!task.isSuccessful()) {

                            reff2.setValue(TerminalFragment.info);
                        }
                        else {





                            String b = Objects.requireNonNull(task.getResult().getValue()).toString();
                            Log.d("getDatas", "onClick: "+ b);
                            a[0] =b;
                            Date currentTime = Calendar.getInstance().getTime();

                            reff2.setValue( currentTime+" "+TerminalFragment.info+" for "+e.getText().toString()+ "\n"+b);


                        }



                    }
                });

                t.setText("Successfully Send");

                Log.d("getDatas", "onClick1: "+reff2.get().toString());




            }
        });


        viewdatas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                reff2= FirebaseDatabase.getInstance().getReference().child("Member").child(loginActivityManual.em).child("datas");
                reff2.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (!task.isSuccessful()) {

                            reff2.setValue(TerminalFragment.info);
                        }
                        else {
                            
                            String b = Objects.requireNonNull(task.getResult().getValue()).toString();
                            Log.d("getDatas", "onClick: "+ b);
                            t.setText(b);



                        }



                    }



                });






            }
        });


        getsug.setOnClickListener(new View.OnClickListener() {
            String m;
            @Override
            public void onClick(View v) {


                reff2= FirebaseDatabase.getInstance().getReference().child("getS").child(e.getText().toString()).child(con.getText().toString()).child(sol.getText().toString());
                reff2.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {


                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {


                        if (task.isSuccessful()) {


                            m=(task.getResult().getValue().toString());



                            String replace = m.replaceAll(", ", "\n");
                            String substring = replace.substring(1, replace.length() - 1);
                            int count = 0;

                            for(int i=0; i < substring.length(); i++)
                            {    if(substring.charAt(i)=='=')
                                count++;
                            }


                            t.setText(count+" Person gives this sug\n"+substring);



                           // listener.failure();


                        } else{

                            Log.d("crash1", "onClick: not getting1");
                            //reff2.setValue("error");
                            //t.setText("no");



                      }

                    }






                } );


                Log.d("crash1", "onClick: not getting2");
                //t.setText("no");
               // reff2.setValue("error");

               // t.setText(m);
               // reff2.child("Searched").child(loginActivityManual.em).setValue("True");


            }

        });

        setsug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff2= FirebaseDatabase.getInstance().getReference().child("getS").child(e.getText().toString()).child(con.getText().toString()).child(sol.getText().toString()).child(loginActivityManual.em);
            reff2.setValue(TerminalFragment.info);
            t.setText("Succes");


            }
        });





    }
}
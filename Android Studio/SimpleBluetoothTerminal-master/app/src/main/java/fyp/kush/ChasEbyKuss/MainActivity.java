package fyp.kush.ChasEbyKuss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


DatabaseReference reff;
Member member;
int vari=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button cloudBtn=findViewById(R.id.btncloud);
        Button connect=findViewById(R.id.btnonnect);
        member= new Member();
        reff= FirebaseDatabase.getInstance().getReference().child("Member");



        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BTCon.class);
                startActivity(intent);
            }
        });


        cloudBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                member.setName("Kowshique");
                member.setEmail("Data"+ vari);
                vari++;


                reff.child(member.getEmail()).setValue(member);

                //reff.push().setValue(member.getName() + " " + member.getEmail());
                //reff.push().setValue("Hello");
                //reff.setValue("Hi");
                //reff.setValue("Hi2");





            }
        });






    }
}

package fyp.kush.ChasEbyKuss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import fyp.kush.ChasEbyKuss.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {



Button cloudBtn, connect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        cloudBtn=findViewById(R.id.btncloud);
        connect=findViewById(R.id.btnonnect);




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






               Intent intent = new Intent(MainActivity.this, loginActivityManual.class);
                startActivity(intent);
                Log.d("TAG", "onClick: ");










            }
        });






    }
}

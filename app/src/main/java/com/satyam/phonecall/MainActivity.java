package com.satyam.phonecall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button button;
EditText editText;
String  PhoneNo;
static int requestCode=123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        editText=findViewById(R.id.edit_text);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED)
{
    Toast.makeText(MainActivity.this,"Please provide Phone Call permission to continue",Toast.LENGTH_SHORT).show();
    ActivityCompat.requestPermissions(
            MainActivity.this,
            new String[]{
                    Manifest.permission.CALL_PHONE,
            },requestCode);
}
else {
    PhoneNo = editText.getText().toString();
    if(PhoneNo.isEmpty()){
        Toast.makeText(MainActivity.this,"Please fill the Phone Number",Toast.LENGTH_SHORT).show();
    }
    else {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + PhoneNo));

        if (ActivityCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(MainActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(callIntent);
    }
}
            }
        });
    }
}
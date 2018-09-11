package com.example.elmo7tram.dssproject2;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class Register extends AppCompatActivity implements View.OnClickListener{
EditText etUsername,etPassword,etemail,etAddress,etPhone;
Button buttonRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUsername=(EditText)findViewById(R.id.Username);
        etPassword=(EditText)findViewById(R.id.Password);
        etemail=(EditText)findViewById(R.id.E_mail);
        etAddress=(EditText)findViewById(R.id.address);
        etPhone=(EditText)findViewById(R.id.Phone);
        buttonRegister=(Button)findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==buttonRegister){
            int flag = 1;
            DataBaseHelper userDbHelper = new DataBaseHelper(this);
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();
            String email = etemail.getText().toString();
            String address = etAddress.getText().toString();
            String phone = etPhone.getText().toString();
            List<User> users =userDbHelper.getAllUsers();
            if(username.equals("")||password.equals("")||email.equals("")||address.equals("")||phone.equals("")){
                Toast.makeText(this, "Missing Data!", Toast.LENGTH_SHORT).show();
                flag=0;
            }
            for (int i=0;i<users.size();i++)
            {
                if(users.get(i)._name.equals(username)){
                    Toast.makeText(this, "Invalid Username", Toast.LENGTH_SHORT).show();
                    flag=0;
                }
            }
            if(flag==1){
                userDbHelper.addUser(username,email,password,address,phone);
                Toast.makeText(this,"Done!!",Toast.LENGTH_LONG).show();
                userDbHelper.close();
                Intent intent=new Intent(this,Profile.class);
                intent.putExtra("name",username);
                this.startActivity(intent);
            }

        }
    }
}

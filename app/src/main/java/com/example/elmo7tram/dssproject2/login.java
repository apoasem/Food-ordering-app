package com.example.elmo7tram.dssproject2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class login extends AppCompatActivity implements View.OnClickListener {
    EditText etName, etpass;
    Button buttonLogin,buttonRegister;
    public static int LoginedUserID=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etName = (EditText)findViewById(R.id.input_username);
        etpass = (EditText)findViewById(R.id.input_password);
        buttonLogin = (Button)findViewById(R.id.btn_login);
        buttonRegister = (Button)findViewById(R.id.btn_signup);
        buttonLogin.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==buttonLogin){
            int flag=0;
            DataBaseHelper userDbHelper = new DataBaseHelper(this);
            List<User> users =userDbHelper.getAllUsers();

            if(etName.getText().toString().equals("")||etpass.getText().toString().equals(""))
            {
                Toast.makeText(this,"Empty Field(s)",Toast.LENGTH_LONG).show();
                flag=1;
            }else if(!etName.getText().toString().equals("")&&!etpass.getText().toString().equals("")){
                for (int i = 0;i<users.size();i++){
                    if(etName.getText().toString().equals(users.get(i)._name)){
                        if(etpass.getText().toString().equals(users.get(i)._password)){
                            Toast.makeText(this, "User Found", Toast.LENGTH_SHORT).show();
                            flag =1;
                            LoginedUserID=users.get(i)._id;
                            Intent intent=new Intent(this,Profile.class);
                            intent.putExtra("name",etName.getText().toString());
                            this.startActivity(intent);
                            finish();

                        }
                    }
                }

            }if (flag==0){
                Toast.makeText(this, "Wrong Username or password", Toast.LENGTH_SHORT).show();

            }

        }

        else if(view==buttonRegister){
            Intent intent=new Intent(this,Register.class);
            this.startActivity(intent);
        }
    }
}
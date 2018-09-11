package com.example.elmo7tram.dssproject2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class Profile extends AppCompatActivity implements View.OnClickListener{

    EditText etUsername,etPassword,etEmail,etAddress,etPhone;
    Button buttonSave,makeOrder,foodOptions,mngOrders,buttonLogout;
    String CurrentUserID=Integer.toString(login.LoginedUserID);
    public static String CurrentUsername,CurrentUserpassword,CurrentEmail,CurrentAddress,Currentphone,status;
    DataBaseHelper userDbHelper = new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        etUsername=(EditText)findViewById(R.id.edit_username);
        etUsername.setEnabled(false);
        etPassword=(EditText)findViewById(R.id.edit_password);
        etEmail=(EditText)findViewById(R.id.edit_email);
        etAddress=(EditText)findViewById(R.id.edit_address);
        etPhone=(EditText)findViewById(R.id.edit_phone);
        buttonSave=(Button)findViewById(R.id.saveButton) ;
        makeOrder=(Button)findViewById(R.id.makeOrder) ;
        foodOptions=(Button)findViewById(R.id.foodOptions) ;
        mngOrders =(Button)findViewById(R.id.MngOrders) ;
        buttonLogout=(Button)findViewById(R.id.SignOutButton) ;
        List<User> users;
        DataBaseHelper userDbHelper = new DataBaseHelper(this);

        users = userDbHelper.getAllUsers();
        Intent intent= getIntent();
        String Username= intent.getStringExtra("name");
        for(int i = 0;i<users.size();i++){
            if(Username.equals(users.get(i)._name)){
                CurrentUsername = users.get(i)._name;
                CurrentUserpassword = users.get(i)._password;
                CurrentEmail = users.get(i)._email;
                CurrentAddress = users.get(i)._address;
                Currentphone = users.get(i)._phone;
                status = Integer.toString(users.get(i)._status);

            }
        }

        if (status.equals("0")){
            foodOptions.setVisibility(View.GONE);
            mngOrders.setVisibility(View.GONE);
            makeOrder.setVisibility(View.VISIBLE);

        }
        else if (status.equals("1")){
            foodOptions.setVisibility(View.VISIBLE);
            mngOrders.setVisibility(View.VISIBLE);
            makeOrder.setVisibility(View.GONE);
        }

        etUsername.setText(CurrentUsername);
        etPassword.setText(CurrentUserpassword);
        etAddress.setText(CurrentAddress);
        etEmail.setText(CurrentEmail);
        etPhone.setText(Currentphone);
        buttonSave.setOnClickListener(this);
        foodOptions.setOnClickListener(this);
        mngOrders.setOnClickListener(this);
        makeOrder.setOnClickListener(this);
        buttonLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == buttonSave){
            String newUsername =etUsername.getText().toString();
            String newPassword = etPassword.getText().toString();
            String newAddress = etAddress.getText().toString();
            String newEmail = etEmail.getText().toString();
            String newPhone = etPhone.getText().toString();
            userDbHelper.updateUser(CurrentUserID,newUsername,newEmail,newPassword,newAddress,newPhone);
            Toast.makeText(this, "Data is updated", Toast.LENGTH_SHORT).show();
        }

        else if (view== makeOrder){
            Intent intent=new Intent(this,UserFoodList.class);
            this.startActivity(intent);
        }
        else if (view== foodOptions){
            Intent intent=new Intent(this,FoodActivity.class);
            this.startActivity(intent);
        }else if (view == mngOrders){
            Intent intent=new Intent(this,AdminOrders.class);
            this.startActivity(intent);
        }else if(view == buttonLogout){
            Intent intent=new Intent(this,login.class);
            this.startActivity(intent);
        }

    }
}

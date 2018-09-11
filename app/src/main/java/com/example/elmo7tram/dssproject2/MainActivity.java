package com.example.elmo7tram.dssproject2;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText etname,etemail,etpassword,etaddress,etphone;
    Button btn;
    Button btnFood;
    Button btnUserFood;
    Button AdmOrders;

    Context context=this;
    DataBaseHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=(Button)findViewById(R.id.BtnDone);
        etname=(EditText) findViewById(R.id.ET_name);
        etemail=(EditText) findViewById(R.id.ET_email);
        etpassword=(EditText) findViewById(R.id.ET_password);
        etaddress=(EditText) findViewById(R.id.ET_address);
        etphone=(EditText) findViewById(R.id.ET_phone);
        btn.setOnClickListener(this);


        userDbHelper=new DataBaseHelper(context);
        sqLiteDatabase=userDbHelper.getWritableDatabase();
        List<User> users = userDbHelper.getAllUsers();
        if(users.size() != 0) {
            for (int i = 0; i < users.size(); i++) {
                System.out.println("id " + users.get(i)._id + " /" + "name " + users.get(i)._name + " /" + "email " + users.get(i)._email + "/ " + "pass " + users.get(i)._password + "/ " + "address " + users.get(i)._address + "/ " + "ph. " + users.get(i)._phone + "/ " + "stat " + users.get(i)._status);
            }
            System.out.println("**********************************************");
            userDbHelper.close();
        }else {
            System.out.println("No data in database");
        }

        btnFood = (Button) findViewById(R.id.btnFood);
        btnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToOrder = new Intent(MainActivity.this, FoodActivity.class);
                startActivity(goToOrder);
            }
        });


        btnUserFood = (Button) findViewById(R.id.btnUserFood);
        btnUserFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToFood = new Intent(MainActivity.this, UserFoodList.class);
                startActivity(goToFood);
            }
        });



        AdmOrders = (Button) findViewById(R.id.AdmOrders);
        AdmOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToOrders = new Intent(MainActivity.this, AdminOrders.class);
                startActivity(goToOrders);
            }
        });

    }


    @Override
    public void onClick(View view) {
    /*    //ADDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD

        String name=etname.getText().toString();
        String email=etemail.getText().toString();
        String password=etpassword.getText().toString();
        String address=etaddress.getText().toString();
        String phone=etphone.getText().toString();
        userDbHelper=new DataBaseHelper(context);
        sqLiteDatabase=userDbHelper.getWritableDatabase();
        userDbHelper.addUser(name,email,password,address,phone,Integer.toString(0));
        Toast.makeText(getBaseContext(),"Data saved",Toast.LENGTH_LONG).show();
        userDbHelper.close();
*/
  //LogINNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN
 /*       userDbHelper=new DataBaseHelper(context);
        sqLiteDatabase=userDbHelper.getReadableDatabase();
        String record=etname.getText().toString();
        String record2=etpassword.getText().toString();
        if(record.equals("")||record2.equals("")){
            Toast.makeText(this,"Empty Field(s)",Toast.LENGTH_LONG).show();
        }else {
            int flag = userDbHelper.loginUser(record,record2);
            if (flag == 1){
                ///login
            }else {
                Toast.makeText(this,"Wrong Name or Password",Toast.LENGTH_LONG).show();
            }
        }*/

  ///RETRIEVEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE

     userDbHelper=new DataBaseHelper(context);
        sqLiteDatabase=userDbHelper.getWritableDatabase();
        List<User> users = userDbHelper.getAllUsers();
        for(int i=0;i<users.size();i++){
            System.out.println("id "+users.get(i)._id +" /"+"name "+users.get(i)._name+" /"+"email "+users.get(i)._email+"/ "+"pass "+users.get(i)._password+"/ "+"address "+users.get(i)._address+"/ "+"ph. "+users.get(i)._phone+"/ "+"stat "+users.get(i)._status);
        }
        System.out.println("**********************************************");
        userDbHelper.close();
    //DELETEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE

  /*      String record=etname.getText().toString();
        System.out.println(record);
        userDbHelper=new DataBaseHelper(context);
        userDbHelper.deleteUser(record);
        Toast.makeText(getBaseContext(),"Data Deleted",Toast.LENGTH_LONG).show();
        userDbHelper.close();
     ///UPDATEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
        String record3= etname.getText().toString();
        String record2=etemail.getText().toString();
        userDbHelper=new DataBaseHelper(context);
        userDbHelper.updateUser(record3,record2,"","","","");
        userDbHelper.close();*/
    }
}

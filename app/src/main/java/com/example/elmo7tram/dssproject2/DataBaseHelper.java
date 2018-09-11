package com.example.elmo7tram.dssproject2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class DataBaseHelper extends SQLiteOpenHelper {
    private final static int DATABASE_VERSION=1;
    private final static String DATABASE_NAME="restourant";
    private final static String TABLE_USER_DETAILS="userdetails";
    private final static String TABLE_FOOD="food";
    private final static String ORDER_TABLE = "orders";

    private final static String KEY_ID="id";
    private final static String KEY_NAME="name";
    private final static String KEY_EMAIL="email";
    private final static String KEY_PASSWORD="password";
    private final static String KEY_ADDRESS="address";
    private final static String KEY_PHONE="phone";
    private final static String KEY_STATUS="status";
    public static String UserID ="";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_DETAILS_TABLE="CREATE TABLE "+TABLE_USER_DETAILS+"("
                +KEY_ID +" INTEGER PRIMARY KEY,"
                +KEY_NAME +" TEXT UNIQUE,"
                +KEY_EMAIL + " TEXT,"
                +KEY_PASSWORD +" TEXT,"
                +KEY_ADDRESS +" TEXT,"
                +KEY_PHONE +" TEXT,"
                +KEY_STATUS +" INTEGER "+")";
        db.execSQL(CREATE_USERS_DETAILS_TABLE);


        String CREATE_FOOD_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_FOOD+"("
                +"id" + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name" + " TEXT," +
                "price" + " TEXT," +
                "image" + " BLOB" + ")";
        db.execSQL(CREATE_FOOD_TABLE);


        String CREATE_ORDER_TABLE = "CREATE TABLE "+ORDER_TABLE+"("
                +"id" + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "user_id" + " INTEGER," +
                "food_id" + " INTEGER," +
                "FOREIGN KEY(user_id) REFERENCES userdetails(id)," +
                "FOREIGN KEY(Food_id) REFERENCES food(id)" + ")";
        db.execSQL(CREATE_ORDER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER_DETAILS);
        onCreate(db);
    }


    public void addUser(String name,String email,String password,String address,String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_EMAIL, email);
        values.put(KEY_PASSWORD, password);
        values.put(KEY_ADDRESS, address);
        values.put(KEY_PHONE, phone);
        values.put(KEY_STATUS, 1);

        db.insert(TABLE_USER_DETAILS, null, values);
        db.close();

    }
    public List getAllUsers() {
        List UsersList = new ArrayList();
        String selectQuery = "SELECT * FROM " + TABLE_USER_DETAILS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //if TABLE has rows
        if (cursor.moveToFirst()) {
            //Loop through the table rows
            do {
                User user = new User();
                user.set_id(cursor.getInt(0));
                user.set_name(cursor.getString(1));
                user.set_email(cursor.getString(2));
                user.set_password(cursor.getString(3));
                user.set_address(cursor.getString(4));
                user.set_phone(cursor.getString(5));
                user.set_status(cursor.getInt(6));
                //Add movie details to list
                UsersList.add(user);
            } while (cursor.moveToNext());
        }
        db.close();

        return UsersList;
    }
    public void deleteUser(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER_DETAILS, KEY_ID + " = ?", new String[] { id });
        db.close();
    }

    public void updateUser(String id,String name,String email,String password,String address,String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_EMAIL, email);
        values.put(KEY_PASSWORD, password);
        values.put(KEY_ADDRESS, address);
        values.put(KEY_PHONE, phone);

        db.update(TABLE_USER_DETAILS,values,"id = ?",new String[]{id });
        db.close();
    }



    public void insertData(String name, String price, byte[] image){
//        SQLiteDatabase db = this.getWritableDatabase();                  // ashraf working method
//        ContentValues values = new ContentValues();
//        values.put("name", name);
//        values.put("price", price);
//        values.put("image", image);
//
//        db.insert(TABLE_FOOD, null, values);
//        db.close();


        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO FOOD VALUES (NULL, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindString(2, price);
        statement.bindBlob(3, image);

        statement.executeInsert();
    }

    public Cursor getData(String sql) {                          // get images
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }


    public void updateData(String name, String price, byte[] image, int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE food SET name = ?, price = ?, image = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, name);
        statement.bindString(2, price);
        statement.bindBlob(3, image);
        statement.bindDouble(4, (double)id);

        statement.execute();
        database.close();
    }

    public  void deleteData(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM FOOD WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }

    public  void deleteOrder(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM orders WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }

    public void insertOrder(int user_id, int order_id){

        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO orders VALUES (NULL, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindDouble(1, (double) user_id);
        statement.bindDouble(2, (double) order_id);
        statement.executeInsert();
    }

    public int loginUser(String UserName,String UserPassword){
        List<User> users=getAllUsers();
        for(int i=0;i<users.size();i++)
        {
            if(users.get(i)._name.equals(UserName)&&users.get(i)._password.equals(UserPassword))
            {
                System.out.println("Found "+UserName);
                UserID=UserName;
                System.out.println(UserID);
                return 1;
            }
        }
        return 0;
    }
    public void logout(){
        UserID="";
    }
}

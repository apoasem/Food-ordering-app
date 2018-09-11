package com.example.elmo7tram.dssproject2;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.elmo7tram.dssproject2.Food;
import com.example.elmo7tram.dssproject2.FoodActivity;
import com.example.elmo7tram.dssproject2.FoodListAdapter;
import com.example.elmo7tram.dssproject2.R;
import com.example.elmo7tram.dssproject2.UserFoodList;

import java.util.ArrayList;

public class AdminOrders extends AppCompatActivity{

    GridView gridView;
    ArrayList<Food> list;
    FoodListAdapter adapter = null;
    public DataBaseHelper sqliteHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_list_activity);
        sqliteHelper = new DataBaseHelper(this);                // initialize database class


        gridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new FoodListAdapter(this, R.layout.food_items, list);
        gridView.setAdapter(adapter);

        // get all data from sqlite

        //Cursor cursor = sqliteHelper.getData("SELECT * FROM FOOD WHERE id == " + UserFoodList.order_id);
        Cursor cursor = sqliteHelper.getData("SELECT food.id, food.name, food.price, food.image FROM food, userdetails, orders" +
                " WHERE userdetails.id == orders.user_id and food.id == orders.food_id");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            byte[] image = cursor.getBlob(3);
            list.add(new Food(name, price, image, id));
        }
        adapter.notifyDataSetChanged();


        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                CharSequence[] items = {"Confirm"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(AdminOrders.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            // delete
                            Cursor c = sqliteHelper.getData("SELECT id FROM orders");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            showDialogDelete(arrID.get(position));
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });
    }


    private void showDialogDelete(final int idOrder){
        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(AdminOrders.this);

        dialogDelete.setTitle("Warning!!");
        dialogDelete.setMessage("Are you sure you want to this delete?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    sqliteHelper.deleteOrder(idOrder);
                    Toast.makeText(getApplicationContext(), "Deleted successfully!!!",Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Log.e("error", e.getMessage());
                }
                updateOrdersList();
            }
        });

        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDelete.show();
    }

    private void updateOrdersList(){
// get all data from sqlite

    //Cursor cursor = sqliteHelper.getData("SELECT * FROM FOOD WHERE id == " + UserFoodList.order_id);
    Cursor cursor = sqliteHelper.getData("SELECT food.id, food.name, food.price, food.image FROM food, userdetails, orders" +
            " WHERE userdetails.id == orders.user_id and food.id == orders.food_id");
        list.clear();
        while (cursor.moveToNext()) {
        int id = cursor.getInt(0);
        String name = cursor.getString(1);
        String price = cursor.getString(2);
        byte[] image = cursor.getBlob(3);
        list.add(new Food(name, price, image, id));
    }
        adapter.notifyDataSetChanged();
    }
}

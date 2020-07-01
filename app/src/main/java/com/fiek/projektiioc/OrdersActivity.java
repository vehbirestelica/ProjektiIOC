package com.fiek.projektiioc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrdersActivity extends AppCompatActivity {
    private static final String TAG = "activity order";

    private FirebaseDatabase mDatabase;
    private DatabaseReference myRef;
    Spinner spinner;
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String> arrayList1 = new ArrayList<String>();
    FirebaseUser auth;
    TextView userID,marresi;
    ImageView img;
    List<NewOrders> ordersList;
    private DatabaseReference mRef;
    ListViewOnClickListener onClick = new ListViewOnClickListener();
    private ListView mListView;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        userID = findViewById(R.id.userID);

        ordersList = new ArrayList<>();

        mListView = findViewById(R.id.listView);

        auth = FirebaseAuth.getInstance().getCurrentUser();
        String currentUser = auth.getUid();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent onClickintent = new Intent(OrdersActivity.this,ListViewOnClickListener.class);
                onClickintent.putExtra("orders",mListView.getItemAtPosition(position).toString());
                startActivity(onClickintent);
                Toast.makeText(OrdersActivity.this,"dwdwddwdwd",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart () {
        super.onStart();
        Query query = FirebaseDatabase.getInstance().getReference("addOrder").orderByChild("statusi").equalTo("Paguar");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange (@NonNull DataSnapshot snapshot) {
                ordersList.clear();
                for(DataSnapshot ordersSnapshot : snapshot.getChildren()){
                    NewOrders orders = ordersSnapshot.getValue(NewOrders.class);

                    ordersList.add(orders);
                }

                ListAdapter adapter = new ListAdapter(OrdersActivity.this, ordersList);
                mListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled (@NonNull DatabaseError error) {

            }
        });
    }
}
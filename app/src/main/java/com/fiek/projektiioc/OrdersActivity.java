package com.fiek.projektiioc;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class OrdersActivity extends AppCompatActivity {
    private static final String TAG = "activity order";

    EditText emri, data, statusi;
    private FirebaseDatabase mDatabase;
    private DatabaseReference myRef, mRef;
    private List<Orders> orders = new ArrayList<>();
    Spinner spinner;
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String> arrayList1 = new ArrayList<String>();
    FirebaseUser auth;
    TextView userID;
    ImageView img;
    Orders order = new Orders();

    private ListView mListView;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

//        mDatabase = FirebaseDatabase.getInstance();
//        myRef = mDatabase.getReference("Orders");
        userID = (TextView) findViewById(R.id.userID);

        mListView = (ListView) findViewById(R.id.listView);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.adapterview,R.id.textView3,arrayList);
        //final ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this,R.layout.adapterview,R.id.textView2,arrayList1);
        mListView.setAdapter(arrayAdapter);
        //mListView.setAdapter(arrayAdapter1);

//        spinner = (Spinner) findViewById(R.id.derguesi);
//        String derguesi = spinner.getSelectedItem().toString();

        auth = FirebaseAuth.getInstance().getCurrentUser();
        String currentUser = auth.getUid();


        Query query  = FirebaseDatabase.getInstance().getReference("addOrder")
                .orderByChild("statusi")
                .equalTo("Paguar");
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded (@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String emri = snapshot.child("derguesi").getValue(String.class);
                String data = snapshot.child("data").getValue(String.class);
                String statusi = snapshot.child("statusi").getValue(String.class);
             //   img.setImageResource(R.drawable.mann);
                String value = emri + " " + data + " " + statusi ;

                arrayList.add(statusi);
 //               arrayList1.add(data);
//                arrayList.add(data);
//                arrayList.add(statusi);
                arrayAdapter.notifyDataSetChanged();
                //arrayAdapter1.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged (@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved (@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved (@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled (@NonNull DatabaseError error) {

            }
        });
    }

//    public View getView(int position, View convertView, ViewGroup parent){
//        OrderListAdapter.ViewHolder holder = null;
//        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        convertView = inflater.inflate(R.layout.adapterview,null);
//
//        holder.emri=(TextView)convertView.findViewById(R.id.textView1);
//        holder.data=(TextView)convertView.findViewById(R.id.textView2);
//        holder.statusi=(TextView)convertView.findViewById(R.id.textView3);
//        convertView.setTag(holder);
//
//        Orders orders = getView(position);
//
//        holder.emri.setText(Orders.getEmri());
//
//        return convertView;
//    }

}
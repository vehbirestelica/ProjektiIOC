package com.fiek.projektiioc;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class OrdersActivity extends AppCompatActivity {
    private FirebaseFirestore firebaseFirestore;
    private FirestoreRecyclerAdapter adapterdb;
    private static final String TAG = "OrdersActivity";
        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_orders);
            Log.d(TAG, "onCreate: Started");
            ListView mListView = (ListView) findViewById(R.id.listView);

            firebaseFirestore = FirebaseFirestore.getInstance();
            //Query
            Query query = firebaseFirestore.collection("Orders");
            FirestoreRecyclerOptions<Orders> options = new FirestoreRecyclerOptions.Builder<Orders>()
                    .setQuery(query,Orders.class)
                    .build();

            adapterdb = new FirestoreRecyclerAdapter<Orders,ProductsViewHolder>(options) {

                @NonNull
                @Override
                public ProductsViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapterview, parent, false);
                    return new ProductsViewHolder(view);
                }

                @Override
                protected void onBindViewHolder (@NonNull ProductsViewHolder productsViewHolder, int i, @NonNull Orders orders) {

                    productsViewHolder.textView1.setText(orders.getEmri());
                    productsViewHolder.textView2.setText(orders.getData());
                    productsViewHolder.textView3.setText(orders.getStatusi());
                }
            };

            // QITU OSHT NI ERROR I VOCERRRRRRR
//            mListView.setHasFixedSize(true);
//            mListView.setLayoutManager(new LinearLayoutManager(this));
            mListView.setAdapter((ListAdapter) adapterdb);
//            Orders one = new Orders("Vehbi", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders two = new Orders("Vehbi2", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders three = new Orders("Vehbi3", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders four = new Orders("Vehbi4", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders five = new Orders("Vehbi5", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders six = new Orders("Vehbi6", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders seven = new Orders("Vehbi7", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders eight = new Orders("Vehbi8", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders nine = new Orders("Vehbi9", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders ten = new Orders("Vehbi10", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders eleven = new Orders("Vehbi", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders twelve = new Orders("Vehbi2", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders threeteen = new Orders("Vehbi3", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders fourteen = new Orders("Vehbi4", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders fiveteen = new Orders("Vehbi5", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders sixteen = new Orders("Vehbi6", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders seventeen = new Orders("Vehbi7", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders eighteen = new Orders("Vehbi8", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders nineteen = new Orders("Vehbi9", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders twenty = new Orders("Vehbi10", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//
//            ArrayList<Orders> ordersList = new ArrayList<>();
//            ordersList.add(one);
//            ordersList.add(two);
//            ordersList.add(three);
//            ordersList.add(four);
//            ordersList.add(five);
//            ordersList.add(six);
//            ordersList.add(seven);
//            ordersList.add(eight);
//            ordersList.add(nine);
//            ordersList.add(ten);
//            ordersList.add(eleven);
//            ordersList.add(twelve);
//            ordersList.add(threeteen);
//            ordersList.add(fourteen);
//            ordersList.add(fiveteen);
//            ordersList.add(sixteen);
//            ordersList.add(seventeen);
//            ordersList.add(eighteen);
//            ordersList.add(nineteen);
//            ordersList.add(twenty);
//
//            OrderListAdapter adapter = new OrderListAdapter(this, R.layout.adapterview,ordersList);
//            mListView.setAdapter(adapter);

        }


    private class ProductsViewHolder extends RecyclerView.ViewHolder{

            private TextView textView1;
            private TextView textView2;
            private TextView textView3;

        public ProductsViewHolder (@NonNull View itemView) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
        }
    }

    @Override
    protected void onStop () {
        super.onStop();
        adapterdb.stopListening();
    }

    @Override
    protected void onStart () {
        super.onStart();
        adapterdb.startListening();
    }
}
package com.fiek.projektiioc;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class All_InvoicesActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    FirestoreRecyclerAdapter adapter;

    private RecyclerView mAllMyInvoices_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__invoices);

        mAllMyInvoices_list = findViewById(R.id.allInvoices_list);

        db = FirebaseFirestore.getInstance();

        CollectionReference invoicesCollectionRef = db.collection("Invoices");

        Query invoicesQuery = invoicesCollectionRef.orderBy("timestamp",Query.Direction.DESCENDING);


        FirestoreRecyclerOptions<Invoice> options = new FirestoreRecyclerOptions.Builder<Invoice>().setQuery(invoicesQuery, Invoice.class).build();

        adapter = new FirestoreRecyclerAdapter<Invoice, All_InvoicesActivity.InvoicesViewHolder>(options) {

            @NonNull
            @Override
            public All_InvoicesActivity.InvoicesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.allinvoices_item,parent,false);
                return new All_InvoicesActivity.InvoicesViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull All_InvoicesActivity.InvoicesViewHolder invoicesViewHolder, int i, @NonNull Invoice invoice) {
                invoicesViewHolder.list_title.setText(invoice.getInvTitle());
                invoicesViewHolder.list_sum.setText(invoice.getInvSum());
                invoicesViewHolder.list_usersname.setText(invoice.getUsersName());
            }
        };
        mAllMyInvoices_list.setHasFixedSize(true);
        mAllMyInvoices_list.setLayoutManager(new LinearLayoutManager(this));
        mAllMyInvoices_list.setAdapter(adapter);



    }

    private class InvoicesViewHolder extends  RecyclerView.ViewHolder{

        private TextView list_title;
        private TextView list_sum;
        private TextView list_usersname;

        public InvoicesViewHolder(@NonNull View itemView) {
            super(itemView);

            list_title = itemView.findViewById(R.id.allinv_item_title);
            list_sum = itemView.findViewById(R.id.allinv_item_sum);
            list_usersname = itemView.findViewById(R.id.allinv_item_usersname);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    onItemClick((DocumentSnapshot) adapter.getSnapshots().getSnapshot(position),position);

                }
            });
        }

    }

    public void onItemClick (DocumentSnapshot documentSnapshot, int position){
        Invoice invoice = documentSnapshot.toObject(Invoice.class);
        Intent intent = new Intent(All_InvoicesActivity.this,Invoice_details.class);
        intent.putExtra("invoice",invoice);
        startActivity(intent);
//        String id = documentSnapshot.getId();
//        Toast.makeText(MyInvoiceActivity.this,""+id,Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }


}
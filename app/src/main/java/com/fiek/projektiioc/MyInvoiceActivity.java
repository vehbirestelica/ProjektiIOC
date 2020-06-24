package com.fiek.projektiioc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class MyInvoiceActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    FirestoreRecyclerAdapter adapter;

    private RecyclerView mMyInvoices_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_invoice);

        mMyInvoices_list = findViewById(R.id.myInvoices_list);

        db = FirebaseFirestore.getInstance();

        CollectionReference invoicesCollectionRef = db.collection("Invoices");

        Query invoicesQuery = invoicesCollectionRef.whereEqualTo("userId", FirebaseAuth.getInstance().getCurrentUser().getUid())
                .orderBy("timestamp",Query.Direction.DESCENDING);


        FirestoreRecyclerOptions<Invoice> options = new FirestoreRecyclerOptions.Builder<Invoice>().setQuery(invoicesQuery, Invoice.class).build();

         adapter = new FirestoreRecyclerAdapter<Invoice, InvoicesViewHolder>(options) {
            @NonNull
            @Override
            public InvoicesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myinvoices_item,parent,false);
                return new InvoicesViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull InvoicesViewHolder invoicesViewHolder, int i, @NonNull Invoice invoice) {
                invoicesViewHolder.list_title.setText(invoice.getInvTitle());
                invoicesViewHolder.list_sum.setText(invoice.getInvSum());
            }
        };
         mMyInvoices_list.setHasFixedSize(true);
         mMyInvoices_list.setLayoutManager(new LinearLayoutManager(this));
         mMyInvoices_list.setAdapter(adapter);



    }

    private class InvoicesViewHolder extends  RecyclerView.ViewHolder{

        private TextView list_title;
        private TextView list_sum;

        public InvoicesViewHolder(@NonNull View itemView) {
            super(itemView);

            list_title = itemView.findViewById(R.id.inv_item_title);
            list_sum = itemView.findViewById(R.id.inv_item_sum);
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
        Intent intent = new Intent(MyInvoiceActivity.this,Invoice_details.class);
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
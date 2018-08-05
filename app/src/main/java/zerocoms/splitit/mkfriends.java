package zerocoms.splitit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class mkfriends extends AppCompatActivity {
    private String myfriends[],friendages[];
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mkfriends);
        ImageButton addbt=findViewById(R.id.btplus);
        EditText etname=findViewById(R.id.etName);
        final EditText etAge=findViewById(R.id.etage);
        addbt.setEnabled(false);
        addbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    int x = Integer.parseInt(etAge.getText().toString()); // Parse string into number
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                //Code for validation of the fields

                //adding the list entires

            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.rec_friend);

        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new MyAdapter(myfriends);

        startlayout();
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private String[] mDataset;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public  class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView mTextView;
            public ViewHolder(TextView v) {
                super(v);
                mTextView = v;
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public MyAdapter(String[] myDataset) {
            mDataset = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
            // create a new view
            TextView v = (TextView) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.my_layout_text, parent, false);

            ViewHolder vh = new ViewHolder(v);

            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.mTextView.setText(mDataset[position]);
            holder.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mkfriends.this,"Registered",Toast.LENGTH_LONG).show();
                }
            });

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.length;
        }
    }
        public void startlayout()
        {
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mAdapter);
        }

}

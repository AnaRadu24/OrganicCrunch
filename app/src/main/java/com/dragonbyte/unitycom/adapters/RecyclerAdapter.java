package com.dragonbyte.unitycom.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dragonbyte.unitycom.Product;
import com.dragonbyte.unitycom.R;
import com.dragonbyte.unitycom.User;
import com.dragonbyte.unitycom.activities.UserInformation;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Antonia on 4/19/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.UserHolder> {

    private ArrayList<Product> myUsers;

    public RecyclerAdapter(ArrayList<Product> myUsers) {
        this.myUsers = myUsers;
    }


    @Override
    public RecyclerAdapter.UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_view_template, parent, false);
        return new UserHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.UserHolder holder, int position) {
        Product itemUser = myUsers.get(position);
        holder.bindUser(itemUser);
    }

    @Override
    public int getItemCount() {
        return myUsers.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class UserHolder extends RecyclerView.ViewHolder {

        private TextView profileName;
        private TextView profileEmail;
        private TextView profilePhone;
        private Product myUser;

        private static final String USER_KEY = "USER";

        public UserHolder(View v) {
            super(v);

            profileName = (TextView) v.findViewById(R.id.farmerName);
            profileEmail = (TextView) v.findViewById(R.id.farmerEmail);
            profilePhone = (TextView) v.findViewById(R.id.farmerPhone);

            v.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Log.e("RecyclerView", "CLICK!");

                    String em = profileEmail.getText().toString();

                    Context context = v.getContext();
                    Intent showUserIntent = new Intent(context, UserInformation.class);
                    showUserIntent.putExtra(USER_KEY, em);
                    Toast.makeText(v.getContext(), "Please wait...", Toast.LENGTH_SHORT).show();
                    context.startActivity(showUserIntent);
                }
            });
        }

        public void bindUser(Product user) {
            myUser = user;
            profileName.setText(user.getName());
            profileEmail.setText(user.getemail());
            profilePhone.setText(user.getContact());

        }
    }
}

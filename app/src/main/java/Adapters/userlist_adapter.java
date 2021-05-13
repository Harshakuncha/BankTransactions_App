package Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybankproject.Activities.UserData;
import com.example.mybankproject.R;

import java.util.ArrayList;

import data.user;

public class userlist_adapter  extends RecyclerView.Adapter<userlist_adapter.ViewHolder> {
    private ArrayList<user> userArrayList;

    public userlist_adapter(Context context, ArrayList<user> list) {
        userArrayList = list;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        TextView userName, userAccountBalance;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.username);
            userAccountBalance = itemView.findViewById(R.id.amount);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // still to be implemented
                }
            });
        }
    }

    @NonNull
    @Override
    public userlist_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from (viewGroup.getContext()).inflate(R.layout.user_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull userlist_adapter.ViewHolder viewHolder, int position) {
        viewHolder.itemView.setTag(userArrayList.get(position));
        viewHolder.userName.setText(userArrayList.get(position).getName());
        viewHolder.userAccountBalance.setText(String.format("%d", userArrayList.get(position).getBalance()));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UserData.class);
                intent.putExtra("ACCOUNT_NO", userArrayList.get(position).getAccountNumber());
                intent.putExtra("NAME", userArrayList.get(position).getName());
                intent.putExtra("EMAIL", userArrayList.get(position).getEmail());
                intent.putExtra("IFSC_CODE", userArrayList.get(position).getIfscCode());
                intent.putExtra("PHONE_NO", userArrayList.get(position).getPhoneNumber());
                intent.putExtra("BALANCE", String.valueOf(userArrayList.get(position).getBalance()));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }


}

package hari.database;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Hari on 04/02/16.
 */
public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {
    private Context context;
    private List<UserModel> users;

    public UsersAdapter(Context context, List<UserModel> users) {
        this.users = users;
        this.context = context;
    }

    public void notifyAdapter(List<UserModel> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_user, parent, false);
        UsersAdapter.ViewHolder viewHolder = new UsersAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final UsersAdapter.ViewHolder holder, final int position) {
        UserModel userModel = users.get(position);
        holder.name.setText(userModel.getName());
        holder.email.setText(userModel.getEmail());
        holder.mobile.setText(userModel.getMobile());
        holder.options_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.options_iv);
                popupMenu.inflate(R.menu.user_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.delete_opt:
                                ((MainActivity) context).getDbHelper().deleteUser(
                                        ((MainActivity) context).getSqLiteDatabase(),
                                        users.get(position).getId());
                                users.remove(position);
                                notifyItemRemoved(position);
                                return true;
                            case R.id.edit_opt:
                                Intent intent = new Intent(context, DetailActivity.class);
                                intent.putExtra(Constants.ID, users.get(position).getId());
                                intent.putExtra(Constants.NAME, users.get(position).getName());
                                intent.putExtra(Constants.EMAIL, users.get(position).getEmail());
                                intent.putExtra(Constants.MOBILE, users.get(position).getMobile());
                                context.startActivity(intent);
                                return true;
                            default:
                                return true;
                        }
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name, email, mobile;
        ImageView options_iv;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name_tv);
            email = (TextView) itemView.findViewById(R.id.email_tv);
            mobile = (TextView) itemView.findViewById(R.id.mobile_tv);
            options_iv = (ImageView) itemView.findViewById(R.id.options_iv);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}

package rekkeitrainning.com.networking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import rekkeitrainning.com.networking.R;
import rekkeitrainning.com.networking.model.User;

/**
 * Created by hoang on 8/2/2018.
 */

public class UserAdapter extends BaseAdapter {
    public UserAdapter(Context mContext, ArrayList<User> mListUser) {
        this.mContext = mContext;
        this.mListUser = mListUser;
    }

    Context mContext;
    ArrayList<User> mListUser;
    @Override
    public int getCount() {
        return mListUser != null ? mListUser.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mListUser.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list, null);
            mViewHolder = new ViewHolder();
            mViewHolder.tvUserId = convertView.findViewById(R.id.tvUserId);
            mViewHolder.tvTitile = convertView.findViewById(R.id.tvTitle);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        User mUser = mListUser.get(position);
        mViewHolder.tvUserId.setText(mUser.getUserId());
        mViewHolder.tvTitile.setText(mUser.getTitle());
        return convertView;
    }
    class ViewHolder {
        TextView tvUserId;
        TextView tvTitile;
    }
}

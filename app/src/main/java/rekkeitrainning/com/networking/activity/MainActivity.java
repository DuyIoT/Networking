package rekkeitrainning.com.networking.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import rekkeitrainning.com.networking.adapter.UserAdapter;
import rekkeitrainning.com.networking.model.User;
import rekkeitrainning.com.networking.utils.ILoadData;
import rekkeitrainning.com.networking.utils.LoadData;
import rekkeitrainning.com.networking.R;

public class MainActivity extends AppCompatActivity implements ILoadData {
    ListView lvUser;
    ArrayList<User> mListUser = new ArrayList<>();
    UserAdapter mAdapter;
    public static String USER = "user";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListenner();
        new LoadData(MainActivity.this, this).execute("https://jsonplaceholder.typicode.com/posts");
    }

    private void initListenner() {
        lvUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User mUser = mListUser.get(position);
                Intent mIntent = new Intent(MainActivity.this, DetailUser.class);
                mIntent.putExtra(USER, mUser);
                startActivity(mIntent);
            }
        });
    }

    private void initView() {
        lvUser = findViewById(R.id.lvEmail);
    }

    @Override
    public void loadSuccessData(String mData) {
        try {
            JSONArray mJSONArray = new JSONArray(mData);
            for (int i = 0 ; i < mJSONArray.length() ; i ++){
                JSONObject mJSONObject = (JSONObject) mJSONArray.get(i);
                String userId = mJSONObject.getString("userId");
                String id = mJSONObject.getString("id");
                String title = mJSONObject.getString("title");
                String body = mJSONObject.getString("body");
                User mUser = new User(userId, id, title, body);
                mListUser.add(mUser);
            }
            mAdapter = new UserAdapter(MainActivity.this, mListUser);
            lvUser.setAdapter(mAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

package rekkeitrainning.com.networking.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import rekkeitrainning.com.networking.R;
import rekkeitrainning.com.networking.model.User;

public class DetailUser extends AppCompatActivity {
    TextView tvTitle;
    TextView tvBody;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);
        initView();
        getData();
    }

    private void getData() {
        Intent mIntent = getIntent();
        User mUser = (User) mIntent.getSerializableExtra(MainActivity.USER);
        tvTitle.setText(mUser.getTitle());
        tvBody.setText(mUser.getBody());
    }

    private void initView() {
        tvTitle = findViewById(R.id.tvtitle);
        tvBody = findViewById(R.id.tvbody);
    }
}

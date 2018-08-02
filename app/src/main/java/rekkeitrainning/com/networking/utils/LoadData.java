package rekkeitrainning.com.networking.utils;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by hoang on 8/2/2018.
 */

public class LoadData extends AsyncTask<String, Void, String> {
    Context mContext;
    ILoadData mILoadData;

    public LoadData(Context mContext, ILoadData mILoadData) {
        this.mContext = mContext;
        this.mILoadData = mILoadData;
    }

    @Override
    protected String doInBackground(String... strings) {
        StringBuilder mData = new StringBuilder();
        try {
            URL url = new URL(strings[0]);
            HttpsURLConnection mHttpURLConnection = (HttpsURLConnection) url.openConnection();
            mHttpURLConnection.setConnectTimeout(20000);
            mHttpURLConnection.connect();
            InputStream inputStream = mHttpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null){
                mData.append(line);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(mData);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mILoadData.loadSuccessData(s);
    }
}

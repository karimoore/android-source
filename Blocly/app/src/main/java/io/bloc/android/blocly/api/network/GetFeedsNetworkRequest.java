package io.bloc.android.blocly.api.network;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.transform.Result;

/**
 * Created by Kari on 11/4/2015.
 */
public class GetFeedsNetworkRequest extends NetworkRequest {

    String [] feedUrls;
    public GetFeedsNetworkRequest(String... feedUrls){
        this.feedUrls = feedUrls;
    }
    @Override
    public Result performRequest() {
        for (String feedUrlString : feedUrls){
            InputStream inputStream = openStream(feedUrlString);
            if (inputStream == null) {
                return null;
            }
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = bufferedReader.readLine();
                int count = 0;
                int index = -1;
                String countLine = null;
                while (line != null) {
                    Log.v(getClass().getSimpleName(), "Line: " + line);
                    countLine = line;
                    line = bufferedReader.readLine();
                }
                index = countLine.indexOf("</item>");
                while (index != -1) {
                    count++;
                    countLine = countLine.substring(index + 6);
                    index = countLine.indexOf("</item>");
                }
                Log.v(getClass().getSimpleName(), "Number of items is: " + count);
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
                setErrorCode(ERROR_IO);
                return null;
            }
        }
        return null;
    }

}

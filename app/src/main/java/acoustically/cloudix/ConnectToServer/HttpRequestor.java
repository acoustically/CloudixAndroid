package acoustically.cloudix.ConnectToServer;

import android.os.Message;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by acoustically on 17. 10. 3.
 */

public class HttpRequestor {
  String mUrl;

  public HttpRequestor(String url) {
    this.mUrl = url;
  }
  public void addParams(String key, String value) {
    if (mUrl.indexOf('?') == -1) {
      mUrl += '?';
    } else {
      mUrl += "&";
    }
    mUrl += key + "=" + value;
  }
  public void get(final HttpResponseListener listener) {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        try {
          HttpURLConnection httpURLConnection = getConnection();
          try {
            httpURLConnection.setRequestMethod("GET");
            InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            String data = readData(inputStream);
            listener.httpResponse(new JSONObject(data));
            inputStream.close();
          } catch (Exception e) {
            Log.e("ERROR", "Http request failed");
            e.printStackTrace();
            listener.httpExcepted();
          } finally {
            httpURLConnection.disconnect();
          }
        } catch (Exception e) {
          Log.e("ERROR", "Http request failed");
        }
      }
    };
    Thread thread = new Thread(runnable);
    thread.start();
  }
  public void post(final JSONObject json, final HttpResponseListener listener) {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        try {
          HttpURLConnection httpURLConnection = getConnection();
          try {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestProperty("Content-type","application/json");

            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(json.toString().getBytes("UTF-8"));

            InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            String data = readData(inputStream);
            Message message = new Message();
            message.what = 1;
            try {
              message.obj = new JSONObject(data);
            } catch (JSONException e) {
              JSONArray  jsonArray = new JSONArray(data);
              JSONObject jsonObject = new JSONObject();
              jsonObject.put("response", jsonArray);
              message.obj = jsonObject;
            }
            listener.sendMessage(message);
            inputStream.close();
            outputStream.close();
          } catch (Exception e) {
            Log.e("ERROR", "Http request failed");
            Message message = new Message();
            message.what = 2;
            listener.sendMessage(message);
            e.printStackTrace();
          } finally {
            httpURLConnection.disconnect();
          }
        } catch (Exception e) {
          e.printStackTrace();
          Log.e("ERROR", "Http request failed");
        }
      }
    };
    Thread thread = new Thread(runnable);
    thread.start();
  }
  public boolean put(String url) {
    return false;
  }
  public boolean delete(String url) {
    return false;
  }
  private HttpURLConnection getConnection() throws Exception{
    URL url = new URL(mUrl);
    return (HttpURLConnection)url.openConnection();
  }

  public String getUrl() {
    return mUrl;
  }
  private String readData(InputStream inputStream) throws Exception {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    int count = 0;
    while (true) {
      byte[] buffer = new byte[4096];
      int length = inputStream.read(buffer);
      if (length < 1) {
        break;
      }
      outputStream.write(buffer, 0, length);
    }
    String data = new String(outputStream.toByteArray(), "EUC-KR");
    return data;
  }
}

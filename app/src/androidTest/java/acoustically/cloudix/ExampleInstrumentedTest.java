package acoustically.cloudix;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.HttpURLConnection;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
  @Test
  public void useAppContext() throws Exception {
    // Context of the app under test.
    Context appContext = InstrumentationRegistry.getTargetContext();
    assertEquals("acoustically.cloudix", appContext.getPackageName());
  }
  @Test
  public void testAddParams() {
    String mUrl = "http://test.com/";
    HttpConnector connector = new HttpConnector(mUrl);
    connector.addParams("test", "test");
    assertEquals("http://test.com/?test=test",connector.getUrl());

    mUrl = "http://test.com/?test=test";
    connector = new HttpConnector(mUrl);
    connector.addParams("test", "test");
    assertEquals("http://test.com/?test=test&test=test", connector.getUrl());
  }/*
  @Test
  public void testHttpGet() {
    String url = "http://naver.com";
    HttpConnector connector = new HttpConnector(url);
    String data = connector.get();
    assertEquals("test", data);
  }*/
}

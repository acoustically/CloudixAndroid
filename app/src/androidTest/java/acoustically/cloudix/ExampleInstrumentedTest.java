package acoustically.cloudix;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import acoustically.cloudix.ConnectToServer.HttpRequestor;

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
    HttpRequestor requestor = new HttpRequestor(mUrl);
    requestor.addParams("test", "test");
    assertEquals("http://test.com/?test=test",requestor.getUrl());

    mUrl = "http://test.com/?test=test";
    requestor = new HttpRequestor(mUrl);
    requestor.addParams("test", "test");
    assertEquals("http://test.com/?test=test&test=test", requestor.getUrl());
  }/*
  @Test
  public void testHttpGet() {
    String url = "http://naver.com";
    HttpRequestor connector = new HttpRequestor(url);
    String data = connector.get();
    assertEquals("test", data);
  }*/
}

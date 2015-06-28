package nyc.c4q;

import android.Manifest;
import android.widget.Button;
import android.widget.TextView;

import org.assertj.android.api.Assertions;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.robolectric.AndroidManifest;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 18)
public class Part2NetworkActivityTests {
    static final String urlParams = "custname=james+dean&custtel=347-841-6090&custemail=hello%40c4q.nyc&size=" +
            "small&topping=cheese&delivery=18%3A15&comments=Leave+it+by+the+garage+door.+Don't+ask+any+questions.";
    private NetworkActivity networkActivity;

    @Before
    public void setUp() {
        networkActivity = Robolectric.buildActivity(NetworkActivity.class).setup().get();
    }

    @Test
    public void test11AppHasInternetPermissions() {
        AndroidManifest manifest = Robolectric.getShadowApplication().getAppManifest();
        List<String> usedPermissions = manifest.getUsedPermissions();
        assertThat(usedPermissions, hasItem(Manifest.permission.INTERNET));
    }

    @Test
    public void test12NetworkActivityHTTPUrlConnectionGET() {
        Button httpbinget = (Button) Helpers.findViewByIdString(networkActivity, "httpbinget");
        TextView httptextlog = (TextView) Helpers.findViewByIdString(networkActivity, "httptextlog");
        httpbinget.callOnClick();

        Assertions.assertThat(httptextlog).containsText(urlParams);
    }

    @Test
    public void test13NetworkActivityHTTPUrlConnectionGETOKHTTP() throws Exception {
        Button httpbingetokhttp = (Button) Helpers.findViewByIdString(networkActivity, "httpbingetokhttp");
        TextView httptextlog = (TextView) Helpers.findViewByIdString(networkActivity, "httptextlog");
        httpbingetokhttp.callOnClick();

        String replaced = urlParams.replaceAll("\\+"," ");
        Assertions.assertThat(httptextlog).containsText(replaced);
    }

    @Test
    public void test14Missing() {
        // TODO
        // FREE question for now.
    }

    @Test
    public void test15Missing() {
        // TODO
        // FREE question for now.
    }

}

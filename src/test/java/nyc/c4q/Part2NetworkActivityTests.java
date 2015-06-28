package nyc.c4q;

import android.Manifest;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;
import java.util.concurrent.Executor;
import nyc.c4q.model.FlickrResponse;
import nyc.c4q.rest.APIManager;
import nyc.c4q.rest.FlickrService;
import nyc.c4q.rest.MockFlickrService;
import org.assertj.android.api.Assertions;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.robolectric.AndroidManifest;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import retrofit.Callback;
import retrofit.MockRestAdapter;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static junit.framework.Assert.fail;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.MockitoAnnotations.initMocks;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 18)
public class Part2NetworkActivityTests {
    static final String urlParams = "custname=james+dean&custtel=347-841-6090&custemail=hello%40c4q.nyc&size=small&topping=cheese&delivery=18%3A15&comments=Leave+it+by+the+garage+door.+Don't+ask+any+questions.";
    private NetworkActivity networkActivity;

    @Mock
    private FlickrService flickrService;

    @Before
    public void setUp() {
        networkActivity = Robolectric.buildActivity(NetworkActivity.class).setup().get();
        initMocks(this);
        // this serializes execution for unit tests
        Executor syncExecutor = new SynchronousExecutor();

        RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(APIManager.API_URL)
            .setExecutors(syncExecutor, syncExecutor)
            .build();

        MockRestAdapter mockRestAdapter = MockRestAdapter.from(restAdapter);
        MockFlickrService mockFlickrService = new MockFlickrService();

        flickrService = mockRestAdapter.create(FlickrService.class, mockFlickrService);
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
    public void test14shouldReturnSomePhotos() {
        flickrService.getInterestingPhotos(Mockito.anyInt(), MockFlickrService.PAGE_0, new Callback<FlickrResponse>() {
            @Override
            public void success(FlickrResponse flickrResponse, Response response) {
                assertThat(flickrResponse.mPhotosInfo.mPhotos).isNotEmpty();
            }

            @Override
            public void failure(RetrofitError error) {
                fail(error.toString());
            }
        });
    }

    @Test
    public void test15shouldReturnNoPhotos() {
        flickrService.getInterestingPhotos(Mockito.anyInt(), MockFlickrService.PAGE_2, new Callback<FlickrResponse>() {
            @Override
            public void success(FlickrResponse flickrResponse, Response response) {
                assertThat(flickrResponse.mPhotosInfo.mPhotos).isEmpty();
            }

            @Override
            public void failure(RetrofitError error) {
                fail(error.toString());
            }
        });
    }

    private class SynchronousExecutor implements Executor {
        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }
}

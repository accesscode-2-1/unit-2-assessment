package nyc.c4q;

import android.Manifest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import nyc.c4q.model.FlickrResponse;
import nyc.c4q.model.Photo;
import nyc.c4q.model.PhotosInfo;
import nyc.c4q.rest.FlickrService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.robolectric.AndroidManifest;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;
import org.robolectric.util.ActivityController;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;
import retrofit.converter.ConversionException;

import static java.util.Collections.EMPTY_LIST;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 18)
public class Unit2AssessmentTests {

    @Mock FlickrService flickrService;

    @Captor
    ArgumentCaptor<Callback<FlickrResponse>> actionCaptor;

    NetworkActivity activity;

    @Before
    public void setUp() {
        initMocks(this);

        ActivityController<NetworkActivity> controller = Robolectric.buildActivity(NetworkActivity.class);
        activity = controller.get();
        activity.setApi(flickrService);

        controller.create();
    }

    @Test
    public void getOnMainCrashes() throws IOException {
        try {
            flickrService.getInterestingPhotos(Mockito.anyInt(), Mockito.anyInt(),
                (Callback<FlickrResponse>) Mockito.any());
            fail("Calling getInterestingPhotos() on main thread should throw exception");
        } catch (IllegalStateException ignored) {
        }
    }

    @Test
    public void appHasInternetPermission() {
        AndroidManifest manifest = Robolectric.getShadowApplication().getAppManifest();
        List<String> usedPermissions = manifest.getUsedPermissions();

        assertThat(usedPermissions).contains(Manifest.permission.INTERNET);
    }

    @Test
    public void ApiKeyExists() {
        // TODO
    }

    @Test
    public void getOnSomePhotosFillsAdapter() {
        verify(flickrService).getInterestingPhotos(Mockito.anyInt(), Mockito.anyInt(), actionCaptor.capture());
        FlickrResponse res = new FlickrResponse();
        res.mStatus = "ok";
        res.mPhotosInfo = new PhotosInfo();
        List<Photo> photos = new ArrayList<>();
        for (int i = 0; i < 10; i ++) {
            photos.add(new Photo());
        }
        res.mPhotosInfo.mPhotos = photos;
        actionCaptor.getValue().success(res, null);
        assertEquals(activity.getListAdapter().getCount(), 10);
    }

    @Test public void getOnNoPhotosDisplaysMessage() {
        verify(flickrService).getInterestingPhotos(Mockito.anyInt(), Mockito.anyInt(), actionCaptor.capture());
        FlickrResponse res = new FlickrResponse();
        res.mStatus = "ok";
        res.mPhotosInfo = new PhotosInfo();
        res.mPhotosInfo.mPhotos = EMPTY_LIST;
        actionCaptor.getValue().success(res, null);
        assertEquals(activity.getListAdapter().getCount(), 0);
        assertThat(ShadowToast.getTextOfLatestToast()).isEqualTo("No Photos");
    }

    @Test
    public void getToastsIfNetworkError() {
        verify(flickrService).getInterestingPhotos(Mockito.anyInt(), Mockito.anyInt(), actionCaptor.capture());
        RetrofitError error = RetrofitError.networkError(null, new IOException("Network Error"));
        actionCaptor.getValue().failure(error);

        assertThat(ShadowToast.getTextOfLatestToast()).isEqualTo("Network Error");
    }

    @Test
    public void getToastsIfHttpError() {
        verify(flickrService).getInterestingPhotos(Mockito.anyInt(), Mockito.anyInt(), actionCaptor.capture());
        RetrofitError error = RetrofitError.httpError(null,
                new Response("", 404, "Page not found", new ArrayList<Header>(), null), null, null);
        actionCaptor.getValue().failure(error);
        assertThat(ShadowToast.getTextOfLatestToast()).isEqualTo("Http Error");
    }

    @Test
    public void getToastsIfConversionError() {
        verify(flickrService).getInterestingPhotos(Mockito.anyInt(), Mockito.anyInt(), actionCaptor.capture());
        RetrofitError error = RetrofitError.conversionError(null, null, null, null, new ConversionException("Conversion Error"));
        actionCaptor.getValue().failure(error);
        assertThat(ShadowToast.getTextOfLatestToast()).isEqualTo("Conversion Error");
    }

    @Test
    public void getRethrowsUnexpectedError() throws Exception {
        try {
            verify(flickrService).getInterestingPhotos(Mockito.anyInt(), Mockito.anyInt(), actionCaptor.capture());
            RetrofitError error = RetrofitError.unexpectedError(null, new Exception("Unknown Error"));
            actionCaptor.getValue().failure(error);
            fail("An UnexpectedError should be rethrown, to crash the program");
        } catch (RetrofitError ignored) {
        }
    }

}

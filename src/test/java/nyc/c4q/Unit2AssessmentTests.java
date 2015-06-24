package nyc.c4q;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.widget.Button;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
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

import static junit.framework.Assert.fail;
import static org.assertj.android.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import static org.hamcrest.MatcherAssert.assertThat;

//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.equalTo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) @RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 18) public class Unit2AssessmentTests {

	private ActivityController<Unit2AssessmentActivity> activityController;
	private Activity activity;
	@Captor ArgumentCaptor<Callback<FlickrResponse>> actionCaptor;

	@Before public void setUp() {
		MockitoAnnotations.initMocks(this);
		activityController = Robolectric.buildActivity(Unit2AssessmentActivity.class);
		activityController.setup();
		activity = activityController.get();
	}

	@Test public void getOnMainCrashes() throws IOException {
		try {
			new FlickrService().getFlickrServiceInterface()
					.getInterestingPhotos(Mockito.anyInt(), Mockito.anyInt(), null);
			fail("Calling getInterestingPhotos() on main thread should throw exception");
		} catch (IllegalStateException ignored) {
		}
	}

	@Test public void appHasInternetPermission() {
		//Context testContext = activity.getContext();
		//PackageManager pm = testContext.getPackageManager();
		//
		//int expected = PackageManager.PERMISSION_GRANTED;
		//int actual = pm.checkPermission(Manifest.permission.<Permission.Internet>, testContext.getPackageName());
		//assertEquals(expected, actual);
	}

	@Test public void ApiKeyExists() {
		// TODO
	}

	@Test public void getCompletesWithResult() {
		Mockito.verify(new FlickrService())
				.GetInterestingPhotos(Mockito.anyInt(), Mockito.anyInt(), actionCaptor.capture());
		FlickrResponse res = new FlickrResponse();
		res.mStatus = "ok";
		res.mPhotosInfo = new PhotosInfo();
		res.mPhotosInfo.mPage = 10;
		res.mPhotosInfo.mPages = 50;
		res.mPhotosInfo.mPerPage = 10;
		res.mPhotosInfo.mTotal = 500;
		List<Photo> photos = new ArrayList<>();
		for (int i = 0; i < 10; i ++) {
			photos.add(new Photo());
		}
		res.mPhotosInfo.mPhotos = photos;

		actionCaptor.getValue().success(res, null);
		//TODO: Assert something, not sure
	}

	@Test public void getToastsIfNetworkError() {
		Mockito.verify(new FlickrService())
				.GetInterestingPhotos(Mockito.anyInt(), Mockito.anyInt(), actionCaptor.capture());
		RetrofitError error = RetrofitError.networkError(null, new IOException("Network Error"));
		actionCaptor.getValue().failure(error);
		assertThat(ShadowToast.getTextOfLatestToast(), CoreMatchers.containsString("Network Error"));
	}

	@Test public void getToastsIfHttpError() {
		Mockito.verify(new FlickrService())
				.GetInterestingPhotos(Mockito.anyInt(), Mockito.anyInt(), actionCaptor.capture());
		RetrofitError error = RetrofitError.httpError(null, new Response("", 404, "Page not found", new ArrayList<Header>(), null), null, null);
		actionCaptor.getValue().failure(error);
		assertThat(ShadowToast.getTextOfLatestToast(), CoreMatchers.containsString("Http Error"));
	}

	@Test public void getToastsIfConversionError() {
		Mockito.verify(new FlickrService())
				.GetInterestingPhotos(Mockito.anyInt(), Mockito.anyInt(), actionCaptor.capture());
		RetrofitError error = RetrofitError.conversionError(null, null, null, null, new ConversionException("Conversion Error"));
		actionCaptor.getValue().failure(error);
		assertThat(ShadowToast.getTextOfLatestToast(), CoreMatchers.containsString("Conversion Error"));
	}

	@Test public void getRethrowsUnexpectedError() throws Exception {
		try {
			Mockito.verify(new FlickrService())
					.GetInterestingPhotos(Mockito.anyInt(), Mockito.anyInt(), actionCaptor.capture());
			RetrofitError error = RetrofitError.unexpectedError(null, new Exception("Unknown Error"));
			actionCaptor.getValue().failure(error);
			fail("An UnexpectedError should be rethrown, to crash the program");
		}
		catch (RetrofitError ignored){
		}

	}
}

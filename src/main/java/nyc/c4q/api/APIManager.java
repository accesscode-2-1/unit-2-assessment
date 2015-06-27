package nyc.c4q.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class APIManager {

    public static final String API_URL = "https://api.flickr.com";
    public static final String API_KEY = "123eee6ea239416da1e9c05d7d192c46";
    private static final String TAG = "FlickrService";
    private static APIManager apiManager = null;

    private FlickrService flickrService;

    private APIManager() {
        setupRestClients();
    }

    private void setupRestClients() {
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

        // Create a very simple REST adapter which points the Flickr API endpoint.
        RestAdapter retrofit = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .setConverter(new GsonConverter(gson)).setRequestInterceptor(new RequestInterceptor() {
                    @Override public void intercept(RequestInterceptor.RequestFacade request) {
                        request.addQueryParam("api_key", API_KEY);
                        request.addQueryParam("format", "json");
                        request.addQueryParam("nojsoncallback",
                                "1"); //disable the JSONP callback stuff flickr seems to assume we want
                    }
                })
                .build();

        // Create an instance of our Flickr API interface.
        flickrService = retrofit.create(FlickrService.class);
    }

    public static FlickrService getFlickrService() {
        if (apiManager == null) {
            apiManager = new APIManager();
        }
        return apiManager.flickrService;
    }

}
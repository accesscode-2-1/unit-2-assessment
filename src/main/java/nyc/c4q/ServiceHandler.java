package nyc.c4q;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by c4q-joshelynvivas on 6/28/15.
 */

//TODO: Does this have something to do with the url params? It includes POST, GET and HTTP
    public class ServiceHandler {

        String response = null;
        public final static int GET = 1;
//    public final static int POST = 2;

        public ServiceHandler() {

        }

        /**
         * Making service call
         * @url - url to make request
         * @method - http request method
         * */
        public String makeServiceCall(String url, int method) {
            return this.makeServiceCall(url, method, null);
        }

        /**
         * Making service call
         * @url - url to make request
         * @method - http request method
         * @params - http request params
         * */
        public String makeServiceCall(String url, int method,
                                      List<NameValuePair> params) {
            try {
                // http client
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpEntity httpEntity = null;
                HttpResponse httpResponse = null;

//            // Checking http request method type
//            if (method == POST) {
//                HttpPost httpPost = new HttpPost(url);
                // adding post params
//                if (params != null) {
//                    httpPost.setEntity(new UrlEncodedFormEntity(params));
//                }
//
//                httpResponse = httpClient.execute(httpPost);}

                if (method == GET) {
                    // appending params to url
                    if (params != null) {
                        String paramString = URLEncodedUtils
                                .format(params, "utf-8");
                        url += "?" + paramString;
                    }
                    HttpGet httpGet = new HttpGet(url);

                    httpResponse = httpClient.execute(httpGet);

                }
                httpEntity = httpResponse.getEntity();
                response = EntityUtils.toString(httpEntity);

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return response;

        }
    }



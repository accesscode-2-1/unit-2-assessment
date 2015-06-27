package nyc.c4q.api;

import com.google.gson.annotations.SerializedName;

public class FlickrResponse {

    @SerializedName("photos") public PhotosInfo mPhotosInfo;

    @SerializedName("stat") public String mStatus;

    @Override public String toString() {
        return "GetRecentPhotosResponse{" +
                "mPhotosInfo=" + mPhotosInfo +
                ", mStatus='" + mStatus + '\'' +
                '}';
    }
}

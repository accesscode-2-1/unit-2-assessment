package nyc.c4q.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import nyc.c4q.api.Photo;

public class PhotosInfo {

    @SerializedName("photo")
    public List<Photo> mPhotos;
    @SerializedName("page")
    public int mPage;
    @SerializedName("pages")
    public int mPages;
    @SerializedName("perpage")
    public int mPerPage;
    @SerializedName("total")
    public int mTotal;

    @Override
    public String toString() {
        return String.format("PhotosInfo{mPhotosInfo=%s,mPage=%s,mPages=%s,mPerPage=%s,mTotal=%s}",
                mPhotos, mPage, mPages, mPerPage, mTotal);
    }
}


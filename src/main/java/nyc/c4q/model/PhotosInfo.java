package nyc.c4q.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import nyc.c4q.model.Photo;

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
		return "PhotosInfo{" +
				"mPhotosInfo=" + mPhotos +
				", mPage=" + mPage +
				", mPages=" + mPages +
				", mPerPage=" + mPerPage +
				", mTotal=" + mTotal +
				'}';
	}
}
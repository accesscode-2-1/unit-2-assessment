package nyc.c4q.api;

import com.google.gson.annotations.SerializedName;

public class Photo {

    @SerializedName("id")
    public String mId;

    @SerializedName("secret")
    public String mSecret;

    @SerializedName("title")
    public String mTitle;

    @SerializedName("server")
    public int mServer;

    public String toString() {
        return String.format("Photo{mId=%s,mSecret=%s,mTitle=%s,mServer=%s}",
                mId, mSecret, mTitle, mServer);
    }
}
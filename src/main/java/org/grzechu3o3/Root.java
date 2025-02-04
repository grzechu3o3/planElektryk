package org.grzechu3o3;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Root {
    @SerializedName("r")
    public Response response;

    public static class Response {
        @SerializedName("ttitems")
        public List<TT_template> ttItems;
    }
}

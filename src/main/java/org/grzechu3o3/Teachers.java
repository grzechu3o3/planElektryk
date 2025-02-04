package org.grzechu3o3;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Teachers {
    @SerializedName("r")
    public Teachers.Response response;

    public static class Response {
        @SerializedName("tables")
        public List<Table> tables;
    }

    public static class Table {
        @SerializedName("id")
        public String id;

        @SerializedName("data_rows")
        public List<TeacherTemplate> data;
    }

    public static class Column {
        @SerializedName("id")
        public String id;
        @SerializedName("type")
        public String type;
        @SerializedName("name")
        public String name;
    }

}

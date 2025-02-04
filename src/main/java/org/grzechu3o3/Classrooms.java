package org.grzechu3o3;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Classrooms {
    @SerializedName("r")
    public Classrooms.Response response;

    public static class Response {
        @SerializedName("tables")
        public List<Table> tables;
    }

    public static class Table {
        @SerializedName("id")
        public String id;

        @SerializedName("data_rows")
        public List<ClassTemplate> data;
    }

    public static class Column {
        @SerializedName("id")
        public int id;

        @SerializedName("name")
        public String name;
    }

}

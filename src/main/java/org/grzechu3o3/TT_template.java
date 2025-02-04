package org.grzechu3o3;

import com.google.gson.annotations.SerializedName;

public class TT_template {
    @SerializedName("date")
    String date;
    @SerializedName("starttime")
    String startTime;
    @SerializedName("endtime")
    String endTime;
    @SerializedName("subjectid")
    String subjectId;
    @SerializedName("classids")
    String[] classIds;
    @SerializedName("groupnames")
    String[] groupNames;
    @SerializedName("teacherids")
    String[] teacherIds;
    @SerializedName("classroomids")
    String[] classroomIds;
    @SerializedName("cellOrder")
    int cellOrder;
    @SerializedName("uniperiod")
    int uniPeriod;
}

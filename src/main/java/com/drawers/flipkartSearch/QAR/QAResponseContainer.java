package com.drawers.flipkartSearch.QAR;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nishant.pathak on 01/05/16.
 */
public class QAResponseContainer {
    @SerializedName("qar")
    private List<QAResponse> qaResponses = new ArrayList<>(10);
    @SerializedName("m")
    private String more;

    public QAResponseContainer(List<QAResponse> qaResponses, String more) {
        this.qaResponses = qaResponses;
        this.more = more;
    }

    private static Gson gson = new Gson();
    public String toJsonString() {
        return gson.toJson(QAResponseContainer.this);
    }
}

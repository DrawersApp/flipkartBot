package com.drawers.flipkartSearch.QAR;

import com.google.gson.annotations.SerializedName;

/**
 * Created by harshit on 1/5/16.
 */
public class QAResponse {
    @SerializedName("h")
    private String heroImage;

    @SerializedName("t")
    private String title;

    @SerializedName("d")
    private String description;

    @SerializedName("p")
    private ActionableItem primary;

    @SerializedName("s")
    private ActionableItem secondary;

    @SerializedName("r")
    private ActionableItem tertiary;

    public QAResponse(String heroImage, String title, String description, ActionableItem primary,
                      ActionableItem secondary, ActionableItem tertiary) {
        this.heroImage = heroImage;
        this.title = title;
        this.description = description;
        this.primary = primary;
        this.secondary = secondary;
        this.tertiary = tertiary;
    }

    public static class ActionableItem {
        @SerializedName("l")
        private String displayText;

        @SerializedName("r")
        private String reply;

        @SerializedName("t")
        private String replyType;

        public static class ReplyType {
            public static final String WEB = "W";
            public static final String REPLY = "R";
        }

        public ActionableItem(String displayText, String reply, String replyType) {
            this.displayText = displayText;
            this.reply = reply;
            this.replyType = replyType;
        }
    }
}
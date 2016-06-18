package com.drawers.flipkartSearch;

import com.drawers.flipkartSearch.QAR.MatchResult;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

/**
 * Created by nishant.pathak on 24/04/16.
 */
public interface IFlipkart {
    @Headers({
            "Accept: application/json",
            "Fk-Affiliate-Id: nishantpa8",
            "Fk-Affiliate-Token: b3f65c885e7b4c499706a6dc53a75998"
    })
    @GET("/affiliate/1.0/search.json")
    MatchResult search(@Query("query") String query, @Query("resultCount") Integer count);
}

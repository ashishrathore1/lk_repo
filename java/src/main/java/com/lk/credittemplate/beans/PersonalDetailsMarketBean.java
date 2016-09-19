package com.lk.credittemplate.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonalDetailsMarketBean {
        @JsonProperty("marketplaces")
        private String marketplaces;
	    @JsonProperty("commission")
	    private String commission="";
	    @JsonProperty("numOfRating")
	    private int numOfRating=0;
	    @JsonProperty("previousNoOfReviews")
	    private int previousNoOfReviews=0;
	    @JsonProperty("previousRating")
	    private int previousRating=0;
        @JsonProperty("marketId")
        private String marketId="";
		@JsonProperty("ratingScore")
	    private String ratingScore="";
	    @JsonProperty("url")
	    private String url="";
		public int getNumOfRating() {
			return numOfRating;
		}
		public void setNumOfRating(int numOfRating) {
			this.numOfRating = numOfRating;
		}
		public int getPreviousNoOfReviews() {
			return previousNoOfReviews;
		}
		public void setPreviousNoOfReviews(int previousNoOfReviews) {
			this.previousNoOfReviews = previousNoOfReviews;
		}
		public int getPreviousRating() {
			return previousRating;
		}
		public void setPreviousRating(int previousRating) {
			this.previousRating = previousRating;
		}
		
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getCommission() {
			return commission;
		}
		public void setCommission(String commission) {
			this.commission = commission;
		}
		public String getMarketplaces() {
			return marketplaces;
		}
		public void setMarketplaces(String marketplaces) {
			this.marketplaces = marketplaces;
		}
	    public String getRatingScore() {
			return ratingScore;
		}
		public void setRatingScore(String ratingScore) {
			this.ratingScore = ratingScore;
		}
		public String getMarketId() {
			return marketId;
		}
		public void setMarketId(String marketId) {
			this.marketId = marketId;
		}

		
	
	
	
}

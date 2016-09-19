package com.lk.credittemplate.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * @author Pooja Desai
 *
 */
@Entity
@Table(name = "LK_PD_MARKET_DETAILS")

@NamedQueries({

	      @NamedQuery(name = "getMarketDetailsById", query = "SELECT l FROM PersonalDetailsMarket l WHERE l.marketId=:marketId"),
	      @NamedQuery(name = "getSingleMarketById", query ="SELECT l FROM PersonalDetailsMarket l WHERE l.marketGuid=:marketGuid")

})
public class PersonalDetailsMarket implements Serializable {

	private static final long serialVersionUID = 2805838946323485598L;

	@Id
	@Column(name = "M_GUID")
	private String marketGuid;

	@Column(name = "LK_MARKET_APP_ID")
	private String marketId;

	@Column(name = "LK_URL")
	private String url;
	
	@Column(name = "LK_MARKET_PLACES")
	private String marketPlaces;

	@Column(name = "LK_COMMISSION")
	private String commission;
	
	@Column(name = "LK_RATING")
	private String rating;
	
	@Column(name = "LK_NUM_OF_RATING")
	private String numOfRating;
	
	@Column(name = "LK_PREVIOUS_RATING")
	private String previousRating;
	
	@Column(name = "LK_PREV_NUM_REVIEWS")
	private String prevNumReviews;

	public String getMarketGuid() {
		return marketGuid;
	}

	public void setMarketGuid(String marketGuid) {
		this.marketGuid = marketGuid;
	}

	public String getMarketId() {
		return marketId;
	}

	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMarketPlaces() {
		return marketPlaces;
	}

	public void setMarketPlaces(String marketPlaces) {
		this.marketPlaces = marketPlaces;
	}

	public String getCommission() {
		return commission;
	}

	public void setCommission(String commission) {
		this.commission = commission;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getNumOfRating() {
		return numOfRating;
	}

	public void setNumOfRating(String numOfRating) {
		this.numOfRating = numOfRating;
	}

	public String getPreviousRating() {
		return previousRating;
	}

	public void setPreviousRating(String previousRating) {
		this.previousRating = previousRating;
	}

	public String getPrevNumReviews() {
		return prevNumReviews;
	}

	public void setPrevNumReviews(String prevNumReviews) {
		this.prevNumReviews = prevNumReviews;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}




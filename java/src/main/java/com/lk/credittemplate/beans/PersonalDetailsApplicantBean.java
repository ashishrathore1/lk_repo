package com.lk.credittemplate.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonalDetailsApplicantBean {
        
        @JsonProperty("dirId")
        private String dirId="";
        @JsonProperty("dirTabName")
	    private String dirTabName="";
        @JsonProperty("fbUrl")
	    private String fbUrl="";
	    @JsonProperty("numOfFriends")
	    private String numOfFriends="";
	    @JsonProperty("linkedinUrl")
	    private String linkedinUrl="";
	    @JsonProperty("linkedinConnections")
	    private String linkedinConnections="";
//	    @JsonProperty("directors")
//	    private List<?>directors =new ArrayList<>();
		public String getDirId() {
			return dirId;
		}
		public void setDirId(String dirId) {
			this.dirId = dirId;
		}
		public String getDirTabName() {
			return dirTabName;
		}
		public void setDirTabName(String dirTabName) {
			this.dirTabName = dirTabName;
		}
		public String getFbUrl() {
			return fbUrl;
		}
		public void setFbUrl(String fbUrl) {
			this.fbUrl = fbUrl;
		}
		public String getNumOfFriends() {
			return numOfFriends;
		}
		public void setNumOfFriends(String numOfFriends) {
			this.numOfFriends = numOfFriends;
		}
		public String getLinkedinUrl() {
			return linkedinUrl;
		}
		public void setLinkedinUrl(String linkedinUrl) {
			this.linkedinUrl = linkedinUrl;
		}
		public String getLinkedinConnections() {
			return linkedinConnections;
		}
		public void setLinkedinConnections(String linkedinConnections) {
			this.linkedinConnections = linkedinConnections;
		}
	     
//	     public List<?> getDirectors() {
//			return directors;
//		}
//		public void setDirectors(List<?> directors) {
//			this.directors = directors;
//		}
	    



		
	
	
	
}

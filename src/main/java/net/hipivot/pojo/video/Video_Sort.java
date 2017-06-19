package net.hipivot.pojo.video;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Video_Sort {

	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	private Date pubDate;
	private Integer playTimes;
	private Integer downloadTimes;
	private Integer recom;

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public Integer getPlayTimes() {
		return playTimes;
	}

	public void setPlayTimes(Integer playTimes) {
		this.playTimes = playTimes;
	}

	public Integer getDownloadTimes() {
		return downloadTimes;
	}

	public void setDownloadTimes(Integer downloadTimes) {
		this.downloadTimes = downloadTimes;
	}

	public Integer getRecom() {
		return recom;
	}

	public void setRecom(Integer recom) {
		this.recom = recom;
	}

}

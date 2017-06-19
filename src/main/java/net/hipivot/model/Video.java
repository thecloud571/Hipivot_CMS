package net.hipivot.model;

import net.hipivot.pojo.video.Video_Detail;
import net.hipivot.pojo.video.Video_Info;
import net.hipivot.pojo.video.Video_Sort;
import net.hipivot.pojo.video.Video_Url;

public class Video {

	private String uuid;
	private Video_Detail detail;
	private Video_Info info;
	private Video_Sort sort;
	private Video_Url url;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Video_Detail getDetail() {
		return detail;
	}

	public void setDetail(Video_Detail detail) {
		this.detail = detail;
	}

	public Video_Info getInfo() {
		return info;
	}

	public void setInfo(Video_Info info) {
		this.info = info;
	}

	public Video_Sort getSort() {
		return sort;
	}

	public void setSort(Video_Sort sort) {
		this.sort = sort;
	}

	public Video_Url getUrl() {
		return url;
	}

	public void setUrl(Video_Url url) {
		this.url = url;
	}

}

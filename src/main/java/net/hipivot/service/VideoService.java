package net.hipivot.service;

import java.util.List;
import java.util.Map;

import net.hipivot.model.Video;

public interface VideoService {

	public List<Video> videoList(Map<String, Object> param);

	public Integer videoTotal(Map<String, Object> param);
	
}

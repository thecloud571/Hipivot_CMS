package net.hipivot.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.hipivot.dao.VideoDao;
import net.hipivot.model.Video;
import net.hipivot.service.VideoService;

@Service(value = "videoService")
public class VideoServiceImpl implements VideoService {
	
	@Autowired
	private VideoDao videoDao;

	public List<Video> videoList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return videoDao.videoList(param);
	}

	public Integer videoTotal(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return videoDao.videoTotal(param);
	}

}

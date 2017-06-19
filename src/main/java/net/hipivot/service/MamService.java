package net.hipivot.service;

import java.util.List;
import java.util.Map;

import net.hipivot.pojo.video.Video_Dir;

public interface MamService {

	public Integer totalCount();
	
	public Integer insert(String dirName);
	
	public List<Video_Dir> nameList(Map<String,Object> param);
	
	public Integer nameTotal(Map<String,Object> param);
}

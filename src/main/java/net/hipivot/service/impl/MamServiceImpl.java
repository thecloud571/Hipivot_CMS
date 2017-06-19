package net.hipivot.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.hipivot.dao.MamDao;
import net.hipivot.pojo.video.Video_Dir;
import net.hipivot.service.MamService;

@Service(value = "mamService")
public class MamServiceImpl implements MamService {

	@Autowired
	private MamDao mamDao;
	
	public Integer totalCount() {
		// TODO Auto-generated method stub
		return mamDao.totalCount();
	}

	public Integer insert(String dirName) {
		// TODO Auto-generated method stub
		return mamDao.insert(dirName);
	}

	public List<Video_Dir> nameList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return mamDao.nameList(param);
	}

	public Integer nameTotal(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return mamDao.nameTotal(param);
	}


}

package net.hipivot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.hipivot.model.PageBean;
import net.hipivot.pojo.video.Video_Dir;
import net.hipivot.service.MamService;
import net.hipivot.util.FTPUtil;

@Controller
@RequestMapping(value = "/mam")
public class MamController {

	@Autowired
	private MamService mamService;

	private FTPUtil ftpUtil = new FTPUtil("54.219.185.173", 21, "anonymous", "982190423@qq.com");

	@ResponseBody
	@RequestMapping(value = "updateDB")
	public void updateDB(@RequestParam(value = "directory", required = true) String directory) {
		try {
			ftpUtil.connect();
			Integer totalDir=ftpUtil.totalDir(directory);
			List<String> nameList = ftpUtil.listDir(directory);
			ftpUtil.disConnect();
			Integer totalCount=mamService.totalCount();
			if(totalCount<totalDir){
				for (String dirName : nameList) {
					mamService.insert(dirName);
				}
			}else {
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ftpUtil.disConnect();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="list")
	public Map<String,Object> list(@RequestParam(value="page")Integer page,@RequestParam(value="rows")Integer rows){
		Map<String,Object> result=new HashMap<String, Object>();
		Map<String,Object> param=new HashMap<String, Object>();
		PageBean pageBean=new PageBean(page, rows);
		param.put("start", pageBean.getStart());
		param.put("size", pageBean.getPageSize());
		List<Video_Dir> nameList=mamService.nameList(param);
		Integer totalCount=mamService.nameTotal(param);
		result.put("rows", nameList);
		result.put("total", totalCount);
		return result;
	}

}

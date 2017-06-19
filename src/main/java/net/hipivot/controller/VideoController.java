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
import net.hipivot.model.Video;
import net.hipivot.service.VideoService;

@Controller
@RequestMapping(value = "/video")
public class VideoController {

	@Autowired
	private VideoService videoService;

	// ���б�
	@ResponseBody
	@RequestMapping(value = "/list")
	public Map<String, Object> list(@RequestParam(value = "source", required = true) String source,
			@RequestParam(value = "page", required = true) Integer page,
			@RequestParam(value = "rows", required = true) Integer rows) {
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		PageBean pageBean = new PageBean(page, rows);
		param.put("start", pageBean.getStart());
		param.put("size", pageBean.getPageSize());
		param.put("source", source);
		List<Video> videoList = videoService.videoList(param);
		Integer totalCount = videoService.videoTotal(param);
		result.put("rows", videoList);
		result.put("total", totalCount);
		return result;
	}

	// �鵥��
	@ResponseBody
	@RequestMapping(value = "/detail")
	public Map<String, Object> detail(@RequestParam(value = "uuid") String uuid) {
		return null;
	}

	// ��
	public Map<String, Object> insert() {
		return null;
	}

	// ɾ
	public Map<String, Object> delect() {
		return null;
	}

	// ��
	public Map<String, Object> update() {
		return null;
	}

	// ��
	public Map<String, Object> select() {
		return null;
	}
}

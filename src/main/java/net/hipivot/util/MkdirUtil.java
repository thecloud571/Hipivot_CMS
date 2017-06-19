package net.hipivot.util;

import java.io.File;

public class MkdirUtil {

	public static void makeDir(String filePath)throws Exception{
		File file=new File(filePath);
		if(!file.exists()){
			file.mkdirs();
		}
	}
}

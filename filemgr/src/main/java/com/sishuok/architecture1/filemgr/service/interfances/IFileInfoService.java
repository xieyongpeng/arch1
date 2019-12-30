package com.sishuok.architecture1.filemgr.service.interfances;

import com.sishuok.architecture1.common.service.interfances.IBaseService;
import com.sishuok.architecture1.filemgr.bean.FileInfoModel;

public interface IFileInfoService extends IBaseService<FileInfoModel>{
	public FileInfoModel getByfileName(String fileName);
}

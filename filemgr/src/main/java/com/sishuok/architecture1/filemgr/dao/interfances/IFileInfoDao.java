package com.sishuok.architecture1.filemgr.dao.interfances;

import org.springframework.stereotype.Repository;

import com.sishuok.architecture1.common.dao.IBaseDao;
import com.sishuok.architecture1.filemgr.bean.FileInfoModel;

@Repository
public interface IFileInfoDao extends IBaseDao<FileInfoModel>{
	public FileInfoModel getByfileName(String fileName);
}

package com.sishuok.architecture1.filemgr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sishuok.architecture1.common.service.impl.BaseServiceImpl;
import com.sishuok.architecture1.filemgr.bean.FileInfoModel;
import com.sishuok.architecture1.filemgr.dao.interfances.IFileInfoDao;
import com.sishuok.architecture1.filemgr.service.interfances.IFileInfoService;

@Service
public class FileInfoServiceImpl extends BaseServiceImpl<FileInfoModel> implements IFileInfoService{
	
	private IFileInfoDao dao = null;
	@Autowired
	private void setDao(IFileInfoDao dao){
		this.dao = dao;
		super.setDAO(dao);
	}
	@Override
	public FileInfoModel getByfileName(String fileName) {
		// TODO Auto-generated method stub
		return dao.getByfileName(fileName);
	}
}

package com.sishuok;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.csource.fastdfs.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sishuok.architecture1.filemgr.bean.FileInfoModel;
import com.sishuok.architecture1.filemgr.common.FastDFSClient;
import com.sishuok.architecture1.filemgr.service.interfances.IFileInfoService;


@Service
public class ClientTest {
	
	@Autowired
	private FastDFSClient fastDFSClient;
	@Autowired
	private IFileInfoService fileService;
		
	public static void main(String[] args) throws Exception {
//		String propertiesPath = "classpath:/properties/fdfsClient.properties";
//		FastDFSClient fastDFSClient = new FastDFSClient(propertiesPath); 
//		String result=fastDFSClient.uploadFile("H:\\eclipse\\workSpaceTestMaven\\architecture1\\filemgr\\src\\main\\resources\\properties\\activeMQ.properties");
//		System.out.println(result);
		//group1/M00/00/00/wKiuA14C13GAbDDoAAABexIMaSQ2668819
		
//		int result = fastDFSClient.delete_file("group1/M00/00/00/wKiuA14C0NmAJtLMAAABexIMaSQ6160616");
//		System.out.println(result);
		
//		int downFlag=fastDFSClient.download_file("group1/M00/00/00/wKiuA14C0KCAWnrSAAABexIMaSQ2637028"
//				,new BufferedOutputStream(new FileOutputStream("bos.txt")));
//	    System.out.println("下载结果为：" +(downFlag==0?"下载文件成功":"下载文件失败"));
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ClientTest client = (ClientTest) context.getBean("clientTest");
//		FileInfo  file=client.fastDFSClient.getFile("group1","M00/00/00/wKiuA14C0KCAWnrSAAABexIMaSQ2637028");
//	    System.out.println("获取文件信息成功："+file.getFileSize());
//	    System.out.println("文件信息："+file.toString());
		

		
		FileInfoModel model = new FileInfoModel();
		String fileName = "fdfsClient.properties";
		model.setFileName(fileName);
		String remotePaths=client.fastDFSClient.uploadFile(ClientTest.class.getResource("/properties/fdfsClient.properties").getFile());
		model.setRemotePaths(remotePaths);
		client.fileService.create(model);
	}
}

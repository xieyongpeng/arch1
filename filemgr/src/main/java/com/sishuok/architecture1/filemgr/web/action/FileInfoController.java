package com.sishuok.architecture1.filemgr.web.action;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sishuok.architecture1.filemgr.bean.FileInfoModel;
import com.sishuok.architecture1.filemgr.common.FastDFSClient;
import com.sishuok.architecture1.filemgr.common.FileFDFSUploadUtils;
import com.sishuok.architecture1.filemgr.service.interfances.IFileInfoService;
import com.sishuok.architecture1.filemgr.web.webModel.FileInfoWebModel;
import com.sishuok.util.format.DateFormatHelper;
import com.sishuok.util.json.JsonHelper;

@Controller
@RequestMapping(value="/filemgr")
public class FileInfoController {
	@Autowired
	private IFileInfoService iservice = null;
	@Autowired
	private FastDFSClient fastDFSClient;
	
	private final int PAGE_NUM = 5;
	
	@RequestMapping(value="toAdd",method=RequestMethod.GET)
	public String toAdd(){
		
		return "filemgr/add";
	}
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String add(FileInfoModel m){
		iservice.create(m);
		return "filemgr/success";
	}
	@RequestMapping(value="toUpdate/{uuid}",method=RequestMethod.GET)
	public String toUpdate(Model model,@PathVariable("uuid") int uuid){
		FileInfoModel m = iservice.getByUuid(uuid);
		model.addAttribute("m", m);
		return "filemgr/update";
	}
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String post(@ModelAttribute("m") FileInfoModel m){
		iservice.update(m);
		return "filemgr/success";
	}
	@RequestMapping(value="toDelete/{uuid}",method=RequestMethod.GET)
	public String toDelete(Model model,@PathVariable("uuid") int uuid){
		FileInfoModel m = iservice.getByUuid(uuid);
		model.addAttribute("m", m);
		return "filemgr/delete";
	}
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String post(@RequestParam("uuid") int uuid){
		iservice.delete(uuid);
		return "filemgr/success";
	}
	@RequestMapping(value="toList",method=RequestMethod.GET)
	public String toList(FileInfoWebModel fileInfoWebModel,Model model){
		FileInfoModel fileInfoModel = null;
		//包括了查询结果和分页信息
		Page<FileInfoModel> pageList = null;
		if("".equals(fileInfoWebModel.getQueryJsonStr()) || fileInfoWebModel.getQueryJsonStr().length() < 1){
			fileInfoModel = new FileInfoModel();
		}else{
			fileInfoModel = (FileInfoModel)JsonHelper.str2Object(fileInfoWebModel.getQueryJsonStr(), FileInfoModel.class);
		}
		int nowPage = fileInfoWebModel.getNowPage();
		int pageShow = fileInfoWebModel.getPageShow();
		if(pageShow > 0){
			pageList = PageHelper.startPage(nowPage, pageShow);
		}else{
			pageList = PageHelper.startPage(nowPage, PAGE_NUM);
		}
		iservice.getByCondition(fileInfoModel);
		
		model.addAttribute("wm", fileInfoWebModel);
		model.addAttribute("page", pageList);
		
		return "filemgr/list";
	}
	@RequestMapping(value="toQuery",method=RequestMethod.GET)
	public String toQuery(){
		return "filemgr/query";
	}
	
	
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public String upload(            
	          HttpServletRequest request, @RequestParam(value = "myFile", required = false) MultipartFile[] files) {
	          try {
	          	for(int i=0;i<files.length;i++){
	          		//1：取得文件名
	          		String fileName = FileFDFSUploadUtils.upload(request, files[i]);
	          		//2：根据文件名去获取到对应的FileModel
	          		FileInfoModel fm = getOneFileModel(fileName);
	          		
	          		//3：把文件流式上传到 mogilefs里面
	          		//4：获得 远程paths
	          		String path = this.uploadToMogilefs(fm.getUuid(), files[i]);
	          		
	          		//5：把这些paths组织一下，设置到remotepaths里面
	          		
	          		fm.setRemotePaths(path);
	          		
	          		iservice.update(fm);
	          	}
			} catch (Exception e) {
				e.printStackTrace();
			}
	          return "filemgr/success";
	  }
	private String uploadToMogilefs(int uuid , MultipartFile file)throws Exception{
		byte[] bs = file.getBytes();
		return fastDFSClient.uploadFile(bs);
	}
	private FileInfoModel getOneFileModel(String fileName){
		//2：根据文件名去获取到对应的FileModel
		FileInfoModel fm = iservice.getByfileName(fileName);
		//2.1：如果存在就取出来
		if(fm==null){
		//2.2：如果不存在，就新增一个
			fm = new FileInfoModel();
			fm.setFileName(fileName);
			
			iservice.create(fm);
			
			fm = iservice.getByfileName(fileName);
		}
		return fm;
	}
}

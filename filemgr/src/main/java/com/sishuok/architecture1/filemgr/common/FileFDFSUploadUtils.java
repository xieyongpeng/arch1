package com.sishuok.architecture1.filemgr.common;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileFDFSUploadUtils {
	//默认大小 50M
    public static final long DEFAULT_MAX_SIZE = 52428800;
    //默认上传的地址
    public static  String defaultBaseDir = "upload";

    public static final String upload(HttpServletRequest request, MultipartFile file)throws Exception{
        String filename = extractFilename(file, defaultBaseDir);
        return filename;
    }
    public static final String extractFilename(MultipartFile file, String baseDir) throws UnsupportedEncodingException {
        String filename = file.getOriginalFilename();
        int slashIndex = filename.indexOf("/");
        if (slashIndex >= 0) {
            filename = filename.substring(slashIndex + 1);
        }
        return filename;
    }
    public static final String extractUploadDir(HttpServletRequest request) {
        return request.getServletContext().getRealPath("/");
    }
}    

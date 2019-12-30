package com.sishuok.architecture1.filemgr.bean;

public class FileInfoModel implements java.io.Serializable{
	private int uuid;
	private String fileName;
	private String remotePaths;
	public int getUuid() {
		return uuid;
	}
	public void setUuid(int uuid) {
		this.uuid = uuid;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getRemotePaths() {
		return remotePaths;
	}
	public void setRemotePaths(String remotePaths) {
		this.remotePaths = remotePaths;
	}
	@Override
	public String toString() {
		return "FileInfoModel [uuid=" + uuid + ", fileName=" + fileName
				+ ", remotePaths=" + remotePaths + "]";
	}
}

package com.sishuok.interactive.bean;

public class CsfModel {
	private String uuid;
	private String moduleId;
	private String moduleName;
	private String deployIP;
	private String deployPort;
	private String interactiveUrl;
	private String depends;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getDeployIP() {
		return deployIP;
	}
	public void setDeployIP(String deployIP) {
		this.deployIP = deployIP;
	}
	public String getDeployPort() {
		return deployPort;
	}
	public void setDeployPort(String deployPort) {
		this.deployPort = deployPort;
	}
	public String getInteractiveUrl() {
		return interactiveUrl;
	}
	public void setInteractiveUrl(String interactiveUrl) {
		this.interactiveUrl = interactiveUrl;
	}
	public String getDepends() {
		return depends;
	}
	public void setDepends(String depends) {
		this.depends = depends;
	}
	@Override
	public String toString() {
		return "CsfModel [uuid=" + uuid + ", moduleId=" + moduleId
				+ ", moduleName=" + moduleName + ", deployIP=" + deployIP
				+ ", deployPort=" + deployPort + ", interactiveUrl="
				+ interactiveUrl + ", depends=" + depends + "]";
	}
	
}

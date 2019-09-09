package com.liupeihao.wchat.plugin.utils.http;

import java.io.File;
import java.util.Map;

/**
 * Http请求参数封装.
 * @author LPH
 *
 */
public class HttpArguments {
	
	
	private String url;//要提交的url
	
	private String fileType;//文件类型
	
	private File file;//要上传的文件
	
	private boolean isCompress;//图片是否压缩    1压缩   0不压缩
	
	private boolean isRatio;//是否等比压缩    1等比    0不等比
	
	private String imgSizes;//图片压缩的尺寸  600,400|500|700,450|800|900
	
	private Map<String , String> otherParam;
	
	public HttpArguments(){super();}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public boolean isCompress() {
		return isCompress;
	}
	public void setCompress(boolean isCompress) {
		this.isCompress = isCompress;
	}
	public boolean isRatio() {
		return isRatio;
	}
	public void setRatio(boolean isRatio) {
		this.isRatio = isRatio;
	}
	public String getImgSizes() {
		return imgSizes;
	}
	public void setImgSizes(String imgSizes) {
		this.imgSizes = imgSizes;
	}
	public Map<String, String> getOtherParam() {
		return otherParam;
	}
	public void setOtherParam(Map<String, String> otherParam) {
		this.otherParam = otherParam;
	}
}

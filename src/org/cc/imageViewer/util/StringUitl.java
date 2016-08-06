package org.cc.imageViewer.util;

/**
 * 字符处理工具类
 * @author cc
 *
 */
public class StringUitl {

	
	//返回子串最后一个字母
	public static Character getLastLetter(String s){
		for(int i=s.length()-1;i>=0;i--){
			if(Character.isLetter(s.charAt(i))) return s.charAt(i);
		}
		return null;
	}
	
}

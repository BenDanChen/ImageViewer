package org.cc.imageViewer.action;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 *  这是Action他妈
 * 
 * @author cc
 *
 */
public class ActionFactory {

	private ActionFactory() {
		buffer=new HashMap<String,Action>();
	}
	
	public static ActionFactory instance;
	
	public static ActionFactory getInstance(){
		if(instance==null) instance=new ActionFactory();
		return instance;
	}
	
	//缓存
	private Map<String,Action> buffer;
	
	//工厂方法
	public Action getAction(String className){
		if(buffer.get(className)!=null) return buffer.get(className);
		Action action=null;
		try {
			action=(Action) Class.forName(className).newInstance();
			buffer.put(className,action);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return action;
	}
	
}

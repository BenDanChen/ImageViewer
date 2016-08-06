package org.cc.imageViewer.action;

import org.cc.imageViewer.core.ViewFrame;
import org.cc.imageViewer.core.ViewService;

//统一工具栏Button的行为
public interface Action {

	//如何响应吶？   干脆不理睬...
	public void execute(ViewService service,ViewFrame viewFrame);
	
}

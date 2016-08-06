package org.cc.imageViewer.action;

import org.cc.imageViewer.core.ViewFrame;
import org.cc.imageViewer.core.ViewService;

/**
 * 
 *  “缩小” 的行为
 *  
 *  调用者:
 *  	1. 工具 --> 缩小
 *  	2. 工具栏"缩小"按钮
 * 
 * @author cc
 *
 */
public class SmallerAction implements Action{

	@Override
	public void execute(ViewService service, ViewFrame viewFrame) {
		
		service.doBiggerAndSmaller(viewFrame,ViewService.SMALLER);
		
	}

}

package org.cc.imageViewer.action;

import org.cc.imageViewer.core.ViewFrame;
import org.cc.imageViewer.core.ViewService;

/**
 * 
 * “上一张图片” 的行为
 * 调用者：
 * 		1. 工具 --> 放大
 *  	2. 工具栏放大按钮 
 * @author cc
 *
 */
public class LastAction implements Action{

	@Override
	public void execute(ViewService service, ViewFrame viewFrame) {
		
		service.doLastOrNext(viewFrame,ViewService.LAST);
		service.doMatchWidth(viewFrame);
		
	}

}

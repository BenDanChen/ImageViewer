package org.cc.imageViewer.action;

import org.cc.imageViewer.core.ViewFrame;
import org.cc.imageViewer.core.ViewService;

/**
 * "下一张图片" 行为
 * 
 *  使用者：
 *  	1. 工具 --> 下一张
 *  	2. 工具栏下一张按钮
 * 
 * @author cc
 *
 */
public class NextAction implements Action {

	@Override
	public void execute(ViewService service, ViewFrame viewFrame) {
		service.doLastOrNext(viewFrame,ViewService.NEXT);
		service.doMatchWidth(viewFrame);
	}

}

package org.cc.imageViewer.action;

import org.cc.imageViewer.core.ViewFrame;
import org.cc.imageViewer.core.ViewService;

/*
 * 显示原图
 */
public class OriginalAction implements Action {

	@Override
	public void execute(ViewService service, ViewFrame viewFrame) {
		service.doOriginal(viewFrame);
	}

}

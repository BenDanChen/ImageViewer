package org.cc.imageViewer.action;

import org.cc.imageViewer.core.ViewFrame;
import org.cc.imageViewer.core.ViewService;

/*
 * 图片等窗口的宽
 */
public class MatchWidthAction implements Action {

	@Override
	public void execute(ViewService service, ViewFrame viewFrame) {
		service.doMatchWidth(viewFrame);
	}

}

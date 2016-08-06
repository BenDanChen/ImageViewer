package org.cc.imageViewer.action;

import org.cc.imageViewer.core.ViewFrame;
import org.cc.imageViewer.core.ViewService;

public class OpenAction implements Action {

	@Override
	public void execute(ViewService service, ViewFrame viewFrame) {
		
		service.doOpen(viewFrame);
		service.doMatchWidth(viewFrame);
		
	}

}

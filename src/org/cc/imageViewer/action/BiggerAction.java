package org.cc.imageViewer.action;

import org.cc.imageViewer.core.ViewFrame;
import org.cc.imageViewer.core.ViewService;

/**
 * "放大"的行为,菜单和工具栏均使用了此类。 
 * @author cc
 *
 */
public class BiggerAction implements Action {

	@Override
	public void execute(ViewService service, ViewFrame viewFrame) {

		service.doBiggerAndSmaller(viewFrame,ViewService.BIGGER);
		
	}

}

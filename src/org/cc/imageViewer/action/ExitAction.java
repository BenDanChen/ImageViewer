package org.cc.imageViewer.action;

import org.cc.imageViewer.core.ViewFrame;
import org.cc.imageViewer.core.ViewService;

/**
 * 
 * 文件 --> 退出  ，菜单的行为
 * @author cc
 *
 */
public class ExitAction implements Action {

	@Override
	public void execute(ViewService service, ViewFrame viewFrame) {

		//暂时直接咔掉
		System.exit(0);
		
		
	}

}

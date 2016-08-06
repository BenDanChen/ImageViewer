package org.cc.imageViewer.action;

import javax.swing.JOptionPane;

import org.cc.imageViewer.core.ViewFrame;
import org.cc.imageViewer.core.ViewService;

/**
 * “帮助” 菜单的行为
 * @author cc
 *
 */
public class HelpAction implements Action {

	@Override
	public void execute(ViewService service, ViewFrame viewFrame) {
		
		JOptionPane.showMessageDialog(viewFrame,"我不想写，就这样。","帮助",JOptionPane.PLAIN_MESSAGE);
		
		
	}

}

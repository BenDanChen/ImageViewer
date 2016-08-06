package org.cc.imageViewer.action;

import javax.swing.JOptionPane;

import org.cc.imageViewer.core.ViewFrame;
import org.cc.imageViewer.core.ViewService;

/**
 *  “关于” 菜单的行为
 * @author cc
 *
 */
public class AboutAction implements Action {

	@Override
	public void execute(ViewService service, ViewFrame viewFrame) {
		JOptionPane.showMessageDialog(viewFrame,"Code by cc","About",JOptionPane.PLAIN_MESSAGE);
	}

}

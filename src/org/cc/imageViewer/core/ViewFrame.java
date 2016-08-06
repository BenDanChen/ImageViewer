package org.cc.imageViewer.core;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import org.cc.imageViewer.action.Action;
import org.cc.imageViewer.action.ActionFactory;
import org.cc.imageViewer.util.ScreenUtil;
import org.cc.imageViewer.util.StringUitl;

public class ViewFrame extends JFrame {

	//菜单监听器
	private ActionListener menuListener = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			service.doMenu( ViewFrame.this, e.getActionCommand() );
		}
	};
	
	//默认窗体大小
	private int FRAME_WIDTH=1000;
	private int FRAME_HEIGHT=700;
	
	//JLabel放在JScrollPane中显示图片
	private JScrollPane jScrollPane;
	private JLabel label;
	
	private ViewService service;
	
	public ViewFrame() {
		init();
	}
	
	public JLabel getLabel(){
		return label;
	}
	
	public JScrollPane getScrollPane(){
		return jScrollPane;
	}
	
	public void init() {
		
		service=ViewService.getInstance();
		
		this.setTitle("图片浏览器");
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		setLocation(ScreenUtil.getScreenCenter(FRAME_WIDTH, FRAME_HEIGHT));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			this.setIconImage(ImageIO.read(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("org/cc/imageViewer/resource/images/icon.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//菜单和工具栏图标
		createMenuBar();
		JPanel toolBar = createToolPanel();
		this.add( toolBar, BorderLayout.NORTH );
		
		//图像显示面板
		label=new JLabel();
		jScrollPane=new JScrollPane(label);
		this.add(jScrollPane, BorderLayout.CENTER );
		
		this.setVisible(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		
//		label.addMouseListener(new MouseAdapter() {
//			
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				System.out.println(getScrollPane().getHorizontalScrollBar().isVisible());
//			}
//			
//		});
		
//		this.pack();
	}
	
	//设置菜单项
	private void createMenuBar(){
		
		JMenuBar menuBar=new JMenuBar();
		
		String menuArr[]={
						"文件(F)",
						"工具(T)",
						"帮助(H)"
					};
		String menuItemArr[][]={
						{"打开(O)","-","退出(X)"},
						{"放大(M)","缩小(O)","-","上一张(X)","下一张(P)"},
						{"手册(D)","关于(A)"}
					};
		String menuItemName[][]={
				{"org.cc.imageViewer.action.OpenAction","","org.cc.imageViewer.action.ExitAction"},
				{"org.cc.imageViewer.action.BiggerAction","org.cc.imageViewer.action.SmallerAction","","org.cc.imageViewer.action.LastAction","org.cc.imageViewer.action.NextAction"},
				{"org.cc.imageViewer.action.HelpAction","org.cc.imageViewer.action.AboutAction"}
		};
		
		for(int i=0;i<menuArr.length;i++){
			
			JMenu menu=new JMenu(menuArr[i]);
			menu.setMnemonic(StringUitl.getLastLetter(menuArr[i]));
			
			for(int j=0;j<menuItemArr[i].length;j++){
				if("-".equals(menuItemArr[i][j])){
					menu.addSeparator();
				}else{
					JMenuItem item=new JMenuItem(menuItemArr[i][j]);
					item.setActionCommand(menuItemName[i][j]);
					item.addActionListener(menuListener);
					
					Character keyAccessor=StringUitl.getLastLetter(menuItemArr[i][j]);
					if(keyAccessor!=null){
						item.setAccelerator(KeyStroke.getKeyStroke(Character.toLowerCase(keyAccessor)));
					}
					
					menu.add(item);
				}
			}
			menuBar.add(menu);
		}
		this.setJMenuBar(menuBar);
	}
	
	//创建工具条
	private JPanel createToolPanel(){
		
		JPanel panel=new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JToolBar toolBar=new JToolBar();
		toolBar.setFloatable(false);
		
		String images[]={
				"open",
				"last",
				"next",
				"bigger",
				"smaller",
				"matchWidth",
				"original"
				};
		String actionClass[]={
				"org.cc.imageViewer.action.OpenAction",
				"org.cc.imageViewer.action.LastAction",
				"org.cc.imageViewer.action.NextAction",
				"org.cc.imageViewer.action.BiggerAction",
				"org.cc.imageViewer.action.SmallerAction",
				"org.cc.imageViewer.action.MatchWidthAction",
				"org.cc.imageViewer.action.OriginalAction"
				};
		
		for(int i=0;i<images.length;i++){
			try {
				BufferedImage img=ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("org/cc/imageViewer/resource/images/"+images[i]+".png"));
				ViewerAction action=new ViewerAction(actionClass[i],new ImageIcon(img));
				JButton button=new JButton();
				toolBar.add(action);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		panel.add(toolBar);
		
		return panel;
	}
	
	private class ViewerAction extends AbstractAction {

		public ViewerAction(String name, Icon icon) {
			super(name,icon);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			ViewService service=ViewService.getInstance();
			String name=(String) getValue(NAME);
			
			ActionFactory.getInstance().getAction(name).execute(service,ViewFrame.this);
		}
		
	}

}

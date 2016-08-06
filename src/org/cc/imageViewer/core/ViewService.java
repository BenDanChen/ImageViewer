package org.cc.imageViewer.core;


import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.cc.imageViewer.action.ActionFactory;

//Controller 
public class ViewService {

	private JFileChooser fileChooser;
	
	private ViewService() {
		fileChooser=new JFileChooser();
		fileChooser.setName("打开图片");
		fileChooser.setCurrentDirectory(new File("E:/主题包"));
		//添加文件过滤器
		String filterDescribes[]=new String[]{
												"BMP(*.BMP)",
												"JPG,JPEG(*.JPG,*.JPEG)",
												"PNG(*.PNG)",
												"GIF(*.GIF)",
												"TIFF(*.TIFF)"
											};
		String filterExtensions[][]=new String[][]{
												{"BMP"},
												{"JPEG"},
												{"PNG"},
												{"GIF"},
												{"TIFF"}
											};
		for(int i=0;i<filterExtensions.length;i++){
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(filterDescribes[i],filterExtensions[i]));
		}
	}
	
	public static ViewService instance=null;
	
	//lazy-singleton
	public static ViewService getInstance(){
		if(instance==null){
			instance=new ViewService();
		}
		return instance;
	}
	
	//监听菜单
	public void doMenu(ViewFrame viewFrame,String command){
		ActionFactory.getInstance().getAction(command).execute(this,viewFrame);
	}
	
	private File currentFile;
	private File currentDirectory;
	private List<File> currentFileList;
	private int currentFileIndex;
	
	//每次缩放比例
	private double range=0.1;
	
	public static int BIGGER=1;
	public static int SMALLER=2;
	
	//放大或缩小 true:大大大   false:小小小
	public void doBiggerAndSmaller(ViewFrame viewFrame,int biggerOrSmaller){
		
		if(currentFile==null) return;
		
		double enlargeRange=0;
		if(biggerOrSmaller==BIGGER){
			enlargeRange=1+range;
		}else if(biggerOrSmaller==SMALLER){
			enlargeRange=1-range;
		}
		
		ImageIcon icon=(ImageIcon) viewFrame.getLabel().getIcon();
		if(icon!=null){
			int width=(int) (icon.getIconWidth()*enlargeRange);
			ImageIcon newIcon=new ImageIcon(icon.getImage().getScaledInstance(width,-1,Image.SCALE_DEFAULT));
			viewFrame.getLabel().setIcon(newIcon);
		}
		
	}
	
	public static int LAST=1;
	public static int NEXT=2;
	
	//切换到上一张或下一张图片
	public void doLastOrNext(ViewFrame viewFrame,int LastOrNext){
		if(currentFileList==null || currentFileList.isEmpty()) return ;
		
		int toIndex=-1;
		
		if(LastOrNext==LAST){
			toIndex=(currentFileIndex+currentFileList.size()-1)%currentFileList.size();
		}else if(LastOrNext==NEXT){
			toIndex=(currentFileIndex+1)%currentFileList.size();
		}
		
		if(toIndex>=0 && toIndex<currentFileList.size()){
			this.currentFileIndex=toIndex;
			this.currentFile=currentFileList.get(currentFileIndex);
			viewFrame.getLabel().setIcon(new ImageIcon(currentFile.getPath()));
		}
	}
	
	//打开动作
	public void doOpen(ViewFrame viewFrame){
		
		if(fileChooser.showOpenDialog(viewFrame)!=JFileChooser.APPROVE_OPTION) return ;

		this.currentFile=fileChooser.getSelectedFile();
		File pwd=fileChooser.getCurrentDirectory();
		
		//若目录已经改动,则更新保存的当前目录和当前文件集合信息
		if(currentDirectory==null || !currentDirectory.getPath().equals(pwd.getPath())){
			
			this.currentDirectory=pwd;
			this.currentFileList=new ArrayList<File>();
			
			FileFilter filters[]=fileChooser.getChoosableFileFilters();
			File files[]=pwd.listFiles();
			
			//更新当前文件集合
			for(int i=0;i<files.length;i++){
				for(int j=0;j<filters.length;j++){
					if(filters[j].accept(files[i])) this.currentFileList.add(files[i]);
				}
			}
		}
		
		//更新当前下标
		currentFileIndex=currentFileList.indexOf(currentFile);
		
		ImageIcon icon=new ImageIcon(this.currentFile.getPath());
		viewFrame.getLabel().setIcon(icon);
	}
	
	//匹配显示宽度
	public void doMatchWidth(ViewFrame viewFrame){

		if(currentFile==null) return ;
	
		//剪去可能出现的滚动条的宽度
		JScrollPane scrollPane=viewFrame.getScrollPane();
		int matchToWidth=scrollPane.getVerticalScrollBar().isVisible()?scrollPane.getWidth()-20:scrollPane.getWidth();
		
		ImageIcon icon=new ImageIcon(currentFile.getPath());
		icon=new ImageIcon(icon.getImage().getScaledInstance(matchToWidth,-1,Image.SCALE_DEFAULT));
		viewFrame.getLabel().setIcon(icon);
		
		//滚动到最底部
//		scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
	}
	
	//显示原图
	public void doOriginal(ViewFrame viewFrame){
		if(currentFile==null) return ;
		
		viewFrame.getLabel().setIcon(new ImageIcon(currentFile.getPath()));
	}
	
}

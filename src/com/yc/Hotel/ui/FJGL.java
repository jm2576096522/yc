package com.yc.Hotel.ui;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import com.yc.Hotel.Dao.RoomInfoDao;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


/**
 * 前台操作
 * @author Administrator
 *
 */
public class FJGL extends Composite {
	private int heigth;
	private int sum=0;
	private int count=0;
	private RoomInfoDao room=new RoomInfoDao();
	
	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public FJGL(Composite parent, int style) {
		super(parent, SWT.NONE);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		Composite composite = new Composite(this,SWT.NONE);

		show(composite);
	}



	public void show(Composite composite){
		
		//一楼房间
		count=room.roomCount(1);
		System.out.println(count);
		showRoom1(composite,count);

		//二楼房间
		count=room.roomCount(2);
		showRoom2(composite,count);

		//三楼房间
		count=room.roomCount(3);
		showRoom3(composite,count);

		//四楼房间
		count=room.roomCount(4);
		showRoom4(composite,count);

		//五楼房间
		count=room.roomCount(5);
		showRoom5(composite,count);

		//六楼房间
		count=room.roomCount(6);
		showRoom6(composite,count);



	}
	public void showRoom1(Composite composite,int count){
		sum=0;//当前行数
		int count1=100;
		Label label_11=new Label(composite, SWT.NONE);
		label_11.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_11.setText("一楼：");
		label_11.setBounds(30, 10, 55, 25);

		int fh=101;
		for(int i=0;i<count;i++){
			if(i>8){
				return;
			}
			//System.out.println(i);
			//每循环一次，new一个面板

			heigth=160*sum;	
			Composite composite_11 = new Composite(composite, SWT.NONE);
			composite_11.setBackgroundMode(SWT.INHERIT_DEFAULT);
			composite_11.setBounds(50+i*count1,40+heigth,90,107);

			Label text = new Label(composite_11, SWT.NONE);//房号
			text.setAlignment(SWT.CENTER);
			text.setBounds(25,5,40,20);

			Label text_1 = new Label(composite_11, SWT.NONE);//房间类型
			text_1.setAlignment(SWT.CENTER);
			text_1.setBounds(10,35,70,20);

			Label text_2 = new Label(composite_11, SWT.NONE);//顾客姓名
			text_2.setAlignment(SWT.CENTER);
			text_2.setBounds(15,70,60,20);

			showtext(fh,text,text_1,text_2);
			showimage(composite_11,fh);
			JTing(composite_11,fh,text,text_1,text_2);
			fh++;
		}
	}
	public void showRoom2(Composite composite,int count){
		sum=sum+1;//当前行数
		int count1=100;
		Label label_21=new Label(composite, SWT.NONE);
		label_21.setFont(SWTResourceManager.getFont("微软雅黑",11,SWT.NORMAL));
		label_21.setText("二楼：");
		label_21.setBounds(30, 60+sum*count1, 55, 25);


		int fh=201;
		for(int i=0;i<count;i++){
			if(i>8){
				return;
			}
			//每循环一次，new一个面板
			heigth=160*sum;	
			Composite composite_21 = new Composite(composite, SWT.NONE);
			composite_21.setBackgroundMode(SWT.INHERIT_DEFAULT);
			composite_21.setBounds(50+i*count1,30+heigth,90,107);

			Label text = new Label(composite_21, SWT.NONE);//房号
			text.setAlignment(SWT.CENTER);
			text.setBounds(25,5,40,20);

			Label text_1 = new Label(composite_21, SWT.NONE);//房间类型
			text_1.setAlignment(SWT.CENTER);
			text_1.setBounds(10,35,70,20);

			Label text_2 = new Label(composite_21, SWT.NONE);//顾客姓名
			text_2.setAlignment(SWT.CENTER);
			text_2.setBounds(15,70,60,20);

			showtext(fh,text,text_1,text_2);
			showimage(composite_21,fh);
			JTing(composite_21,fh,text,text_1,text_2);
			fh++;
		}
	}
	public void showRoom3(Composite composite,int count){
		sum=sum+1;//当前行数
		int count1=100;
		Label label_31=new Label(composite, SWT.NONE);
		label_31.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_31.setText("三楼：");
		label_31.setBounds(30, 110+sum*count1, 55, 25);


		int fh=301;
		for(int i=0;i<count;i++){
			if(i>8){
				return;
			}
			//每循环一次，new一个面板
			heigth=160*sum;	
			Composite composite_31 = new Composite(composite, SWT.NONE);
			composite_31.setBackgroundMode(SWT.INHERIT_DEFAULT);
			composite_31.setBounds(50+i*count1, 25+heigth, 90, 107);

			Label text = new Label(composite_31, SWT.NONE);//房号
			text.setAlignment(SWT.CENTER);
			text.setBounds(25, 5, 40, 20);

			Label text_1 = new Label(composite_31, SWT.NONE);//房间类型
			text_1.setAlignment(SWT.CENTER);
			text_1.setBounds(10, 35, 70, 20);

			Label text_2 = new Label(composite_31, SWT.NONE);//顾客姓名
			text_2.setAlignment(SWT.CENTER);
			text_2.setBounds(15, 70,60,20);

			showtext(fh,text,text_1,text_2);
			showimage(composite_31,fh);
			JTing(composite_31,fh,text,text_1,text_2);
			fh++;
		}
	}
	public void showRoom4(Composite composite,int count){
		sum=sum+1;//当前行数
		int count1=100;
		Label label_41=new Label(composite, SWT.NONE);
		label_41.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_41.setText("四楼：");
		label_41.setBounds(700, 10, 55, 25);


		int fh=401;
		for(int i=0;i<count;i++){
			if(i>8){
				return;
			}
			//每循环一次，new一个面板
			heigth=160*(sum-3);	
			Composite composite_41 = new Composite(composite, SWT.NONE);
			composite_41.setBackgroundMode(SWT.INHERIT_DEFAULT);
			composite_41.setBounds(720+i*count1, 40, 90, 107);

			Label text = new Label(composite_41, SWT.NONE);//房号
			text.setAlignment(SWT.CENTER);
			text.setBounds(25, 5, 40, 20);

			Label text_1 = new Label(composite_41, SWT.NONE);//房间类型
			text_1.setAlignment(SWT.CENTER);
			text_1.setBounds(10, 35, 70, 20);

			Label text_2 = new Label(composite_41, SWT.NONE);//顾客姓名
			text_2.setAlignment(SWT.CENTER);
			text_2.setBounds(15, 70,60,20);

			showtext(fh,text,text_1,text_2);
			showimage(composite_41,fh);
			JTing(composite_41,fh,text,text_1,text_2);
			fh++;
		}
	}
	public void showRoom5(Composite composite,int count){
		sum=sum+1;//当前行数
		int count1=100;
		Label label_51=new Label(composite, SWT.NONE);
		label_51.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_51.setText("五楼：");
		label_51.setBounds(700, 60+(sum-3)*count1, 55, 25);


		int fh=501;
		for(int i=0;i<count;i++){
			if(i>8){
				return;
			}
			//每循环一次，new一个面板
			heigth=160*(sum-3);	
			Composite composite_51 = new Composite(composite, SWT.NONE);
			composite_51.setBackgroundMode(SWT.INHERIT_DEFAULT);
			composite_51.setBounds(720+i*count1, 30+heigth, 90, 107);

			Label text = new Label(composite_51, SWT.NONE);//房号
			text.setAlignment(SWT.CENTER);
			text.setBounds(25, 5, 40, 20);

			Label text_1 = new Label(composite_51, SWT.NONE);//房间类型
			text_1.setAlignment(SWT.CENTER);
			text_1.setBounds(10, 35, 70, 20);

			Label text_2 = new Label(composite_51, SWT.NONE);//顾客姓名
			text_2.setAlignment(SWT.CENTER);
			text_2.setBounds(15, 70,60,20);

			showtext(fh,text,text_1,text_2);
			showimage(composite_51,fh);
			JTing(composite_51,fh,text,text_1,text_2);
			fh++;
		}
	}
	public void showRoom6(Composite composite,int count){
		sum=sum+1;//当前行数
		int count1=100;
		Label label_61=new Label(composite, SWT.NONE);
		label_61.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_61.setText("六楼：");
		label_61.setBounds(700, 110+(sum-3)*count1, 55, 25);


		int fh=601;
		for(int i=0;i<count;i++){
			if(i>8){
				return;
			}
			//每循环一次，new一个面板
			heigth=160*(sum-3);	
			Composite composite_61 = new Composite(composite, SWT.NONE);
			composite_61.setBackgroundMode(SWT.INHERIT_DEFAULT);
			composite_61.setBounds(720+i*count1, 25+heigth, 90, 107);

			Label text = new Label(composite_61, SWT.NONE);//房号
			text.setAlignment(SWT.CENTER);
			text.setBounds(25, 5, 40, 20);

			Label text_1 = new Label(composite_61, SWT.NONE);//房间类型
			text_1.setAlignment(SWT.CENTER);
			text_1.setBounds(10, 35, 70, 20);

			Label text_2 = new Label(composite_61, SWT.NONE);//顾客姓名
			text_2.setAlignment(SWT.CENTER);
			text_2.setBounds(15, 70,60,20);

			showtext(fh,text,text_1,text_2);
			showimage(composite_61,fh);
			JTing(composite_61,fh,text,text_1,text_2);
			fh++;
		}
	}
	public void showimage(Composite composite, int fh){//显示房间的状态
		int zt=room.roomZT(fh);
		try {
			switch (zt){
			case 0:composite.setBackgroundImage(SWTResourceManager.getImage(FJGL.class, "/images/fj_kong.jpg"));break;
			case 1:composite.setBackgroundImage(SWTResourceManager.getImage(FJGL.class, "/images/fj_zhu.jpg"));break;
			case 2:composite.setBackgroundImage(SWTResourceManager.getImage(FJGL.class, "/images/fj_yuding.jpg"));break;
			case 4:composite.setBackgroundImage(SWTResourceManager.getImage(FJGL.class, "/images/fj_qingli.jpg"));break;
			case 3:composite.setBackgroundImage(SWTResourceManager.getImage(FJGL.class, "/images/fj_weixu.jpg"));break;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
	public void showtext(int fh,Label text,Label text_1,Label text_2){
		int zt=room.roomZT(fh);//得到房间状态
		if(zt==0||zt==1||zt==2){
			try {
				int lx=room.roomLX(fh);//查询房号
				text.setText(fh+""); //显示房号 
				switch(lx){//显示房间类型
				case 1:text_1.setText("标准单人间");break;
				case 2:text_1.setText("标准双人间");break;
				case 3:text_1.setText("豪华单人间");break;
				case 4:text_1.setText("豪华双人间");break;
				case 5:text_1.setText("商务套房");break;
				case 6:text_1.setText("总统套房");break;
				}
				
			} catch (Exception e1) {
					e1.printStackTrace();
			}
			if(zt==1){
				String khm=room.roomKHM(fh);
				text_2.setText(khm);
			}else if(zt==2){
				//if(){
					String khm=room.roomKHM1(fh);
					System.out.println(fh);
					System.out.println(khm);
					text_2.setText(khm);
				//}
				
			}else{
				text_2.setText("");
			}
		}else{
				text.setText("");
				text_1.setText("");
				text_2.setText("");
			}
		}
		public void showComJM(int sj,int fh){
			switch(sj){
			case 0://入住
				FJGLsjjm sjjm=new FJGLsjjm(fh,0);
				sjjm.open();
				break;
			case 1://预定
				FJGLsjjm sjjm1=new FJGLsjjm(fh,1);
				sjjm1.open();
				break;
			case 2://修复
				if(!(room.roomGGZT(1,fh)>0)){
					MessageDialog.openError(getShell(), "错误提示", "该客房无需修理！");
				}
				break;
			case 3://清理
				if(!(room.roomGGZT(2,fh)>0)){
					MessageDialog.openError(getShell(), "错误提示", "该客房无需清理！");
				}
				break;
			case 4://结账
				FJGLsjjm sjjm2=new FJGLsjjm(fh,2);
				sjjm2.open();
				break;
			case 5://退房
				FJGLsjjm sjjm3=new FJGLsjjm(fh,3);
				sjjm3.open();
				break;
			case 6://损坏
				if(!(room.roomGGZT(3,fh)>0)){
					MessageDialog.openError(getShell(), "错误提示", "该客房已经损坏！");
				}
				break;
			}

		}
		public void JTing(final Composite composite,final int fh,final Label Text,final Label Text_1,final Label Text_2){//鼠标右键事件
			Menu menu = new Menu(composite.getShell(),SWT.POP_UP);
			composite.setMenu(menu);
			MenuItem mntmNewItem = new MenuItem(menu, SWT.NONE);
			mntmNewItem.setText("入住");
			mntmNewItem.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					showComJM(0,fh);
					showtext(fh,Text,Text_1,Text_2);
					showimage(composite,fh);
				}
			});

			MenuItem mntmNewItem_1 = new MenuItem(menu, SWT.NONE);
			mntmNewItem_1.setText("预定");
			mntmNewItem_1.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					showComJM(1,fh);
					showtext(fh,Text,Text_1,Text_2);
					showimage(composite,fh);
				}
			});

			MenuItem mntmNewItem_2 = new MenuItem(menu, SWT.NONE);
			mntmNewItem_2.setText("维修");
			mntmNewItem_2.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					showComJM(2,fh);
					showtext(fh,Text,Text_1,Text_2);
					showimage(composite,fh);
				}
			});

			MenuItem mntmNewItem_3 = new MenuItem(menu, SWT.NONE);
			mntmNewItem_3.setText("清理");
			mntmNewItem_3.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					showComJM(3,fh);
					showtext(fh,Text,Text_1,Text_2);
					showimage(composite,fh);
				}
			});

			MenuItem mntmNewItem_4 = new MenuItem(menu, SWT.NONE);
			mntmNewItem_4.setText("结账");
			mntmNewItem_4.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					showComJM(4,fh);
					showtext(fh,Text,Text_1,Text_2);
					showimage(composite,fh);
				}
			});

			MenuItem mntmNewItem_5 = new MenuItem(menu, SWT.NONE);
			mntmNewItem_5.setText("退房");
			mntmNewItem_5.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					showComJM(5,fh);
					showtext(fh,Text,Text_1,Text_2);
					showimage(composite,fh);
				}
			});

			MenuItem mntmNewItem_6 = new MenuItem(menu, SWT.NONE);
			mntmNewItem_6.setText("损坏");
			mntmNewItem_6.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					showComJM(6,fh);
					showtext(fh,Text,Text_1,Text_2);
					showimage(composite,fh);
				}
			});

		}

		protected void checkSubclass() {
		}
	}

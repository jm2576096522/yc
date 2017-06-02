package com.yc.Hotel.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import com.yc.utils.UICollection;

/**
 * 主菜单界面
 * 
 * @author Administrator
 *
 */

public class HotelMenu {

	protected Shell shell;
	private boolean isDown = false;
	private int x;
	private int y;
	private Display display;
	private SimpleDateFormat df;
	private Date date;
	//private static String aname;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	/*public HotelMenu(String aname) {
		this.aname = aname;
	}*/

	public static void main(String[] args) {
		try {
			//HotelMenu window = new HotelMenu(aname);
			HotelMenu window = new HotelMenu();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell(SWT.NONE);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setSize(1360, 720);
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		shell.setText("皇冠商务酒店管理");
		shell.setImage(SWTResourceManager.getImage(Reservation.class, "/images/0080.gif"));

		// 窗口居中
		shell.setLocation((display.getClientArea().width - shell.getSize().x) / 2,
				(display.getClientArea().height - shell.getSize().y) / 2);

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(composite, SWT.NONE);
		sashForm.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		sashForm.setOrientation(SWT.VERTICAL);

		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_1.setBackgroundImage(SWTResourceManager.getImage(HotelMenu.class, "/images/007.jpg"));
		composite_1.setBackgroundMode(SWT.INHERIT_FORCE);

		final Label label = new Label(composite_1, SWT.NONE);
		label.setImage(SWTResourceManager.getImage(HotelMenu.class, "/images/btn_close_normal.png"));
		label.setBounds(1319, 0, 39, 20);

		final Label label_2 = new Label(composite_1, SWT.NONE);
		label_2.setImage(SWTResourceManager.getImage(HotelMenu.class, "/images/btn_mini_normal.png"));
		label_2.setBounds(1291, 0, 28, 20);

		Label label_3 = new Label(composite_1, SWT.NONE);
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_3.setAlignment(SWT.CENTER);
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_3.setBounds(66, 22, 155, 26);
		label_3.setText("皇冠商务酒店管理系统");

		final Label label4 = new Label(composite_1, SWT.NONE);
		label4.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label4.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label4.setBounds(10, 105, 69, 57);
		label4.setAlignment(SWT.CENTER);
		label4.setText("前 台");

		final Label label_5 = new Label(composite_1, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_5.setBounds(85, 105, 62, 57);
		label_5.setText("房间管理");

		final Label label_7 = new Label(composite_1, SWT.NONE);
		label_7.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_7.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_7.setAlignment(SWT.CENTER);
		label_7.setBounds(153, 105, 69, 57);
		label_7.setText("入住查询");

		final Label label_6 = new Label(composite_1, SWT.NONE);
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_6.setBounds(228, 105, 69, 57);
		label_6.setText("预定查询");
		label_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_6.setAlignment(SWT.CENTER);

		final Label label_8 = new Label(composite_1, SWT.NONE);
		label_8.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_8.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_8.setAlignment(SWT.CENTER);
		label_8.setBounds(303, 105, 69, 62);
		label_8.setText("客户管理");

		Label label_10 = new Label(composite_1, SWT.NONE);
		label_10.setImage(SWTResourceManager.getImage(HotelMenu.class, "/images/0080.gif"));
		label_10.setBounds(10, 3, 45, 45);

		final Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setText("员工管理");
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_1.setAlignment(SWT.CENTER);
		label_1.setBounds(387, 105, 69, 57);

		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		//鼠标移动的时候
		composite.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent e) {
				if (isDown) {
					shell.setLocation(shell.getLocation().x + e.x - x, shell.getLocation().y + e.y - y);
				}
			}
		});
		// 面板拖动
		composite.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				isDown = true;
				x = e.x;
				y = e.y;
			}

			public void mouseUp(MouseEvent e) {
				isDown = false;
			}
		});
		// 关闭按钮
		label.addMouseListener(new MouseAdapter() {
			public void mouseUp(MouseEvent e) {
				if (MessageDialog.openConfirm(shell, "关闭提示", "确认退出吗？")) {
					shell.dispose(); // 关闭窗口
				}
			}

			public void mouseDown(MouseEvent e) {
				label.setImage(SWTResourceManager.getImage(HotelMenu.class, "/images/btn_close_down.png"));
			}
		});
		label.addMouseTrackListener(new MouseTrackAdapter() {
			public void mouseHover(MouseEvent e) {
				label.setImage(SWTResourceManager.getImage(HotelMenu.class, "/images/btn_close_highlight.png"));
			}

			public void mouseExit(MouseEvent e) {
				label.setImage(SWTResourceManager.getImage(HotelMenu.class, "/images/btn_close_normal.png"));
			}
		});

		// 最小化按钮
		label_2.addMouseTrackListener(new MouseTrackAdapter() {
			public void mouseExit(MouseEvent e) {
				label_2.setImage(SWTResourceManager.getImage(HotelMenu.class, "/images/btn_mini_normal.png"));
			}

			public void mouseHover(MouseEvent e) {
				label_2.setImage(SWTResourceManager.getImage(HotelMenu.class, "/images/btn_mini_highlight.png"));
			}
		});
		label_2.addMouseListener(new MouseAdapter() {
			public void mouseUp(MouseEvent e) {
				shell.setMinimized(true);
			}

			public void mouseDown(MouseEvent e) {
				label_2.setImage(SWTResourceManager.getImage(HotelMenu.class, "/images/btn_mini_down.png"));
			}
		});

		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm_1 = new SashForm(composite_2, SWT.VERTICAL);

		Composite composite_3 = new Composite(sashForm_1, SWT.NONE);
		composite_3.setLayout(new StackLayout());

		Composite composite_4 = new Composite(sashForm_1, SWT.NONE);
		composite_4.setBackground(SWTResourceManager.getColor(220, 220, 220));

		Label lblNewLabel = new Label(composite_4, SWT.NONE);
		lblNewLabel.setBounds(809, 19, 61, 17);
		lblNewLabel.setText("当前用户：");

		Label lblNewLabel_1 = new Label(composite_4, SWT.NONE);
		lblNewLabel_1.setBounds(900, 19, 83, 17);
		//lblNewLabel_1.setText(aname);
		//lblNewLabel_1.setText(UICollection.currentLoginUser.get("aname"));
		lblNewLabel_1.setText(UICollection.currentLoginUser.get("oname"));

		
		Label lblNewLabel_2 = new Label(composite_4, SWT.NONE);
		lblNewLabel_2.setBounds(1057, 19, 66, 17);
		lblNewLabel_2.setText("当前日期：");

		final Label lblNewLabel_3 = new Label(composite_4, SWT.NONE);
		lblNewLabel_3.setBounds(1123, 19, 186, 17);

		// 右侧的内容显示面板设置为堆栈模式布局
		composite_3.setLayout(UICollection.stackLayout);
		
		UICollection.reservation = new Reservation();// 初始化预定界面

		KHXX khxx = new KHXX(composite_3, SWT.NONE);// 客户信息
		UICollection.khxx = khxx;

		FJGL fjgl = new FJGL(composite_3, SWT.NONE);// 房间管理
		UICollection.fjgl = fjgl;

		CXKF cxkf = new CXKF(composite_3, SWT.NONE);// 预定查询
		UICollection.cxkf = cxkf;

		YGGL yggl = new YGGL(composite_3, SWT.NONE);// 员工管理
		UICollection.yggl = yggl;

		RuzhuInfo ruzhuInfo= new RuzhuInfo(composite_3, SWT.NONE);// 入住查询
		UICollection.ruzhuInfo=ruzhuInfo;
		
		sashForm_1.setWeights(new int[] { 449, 45 });
		sashForm.setWeights(new int[] { 162, 553 });

		// 设置在画板composite最上面
		UICollection.stackLayout.topControl = UICollection.fjgl;

		// 点击客户管理
		label_8.addMouseListener(new MouseAdapter() {
			
			public void mouseDown(MouseEvent e) {// 点击
				// 添加字体颜色变化
				label_8.setForeground(display.getSystemColor(SWT.COLOR_RED));
				label_7.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
				label4.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
				label_5.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
				label_6.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
				label_1.setForeground(display.getSystemColor(SWT.COLOR_WHITE));

			}
			
			public void mouseUp(MouseEvent e) {// 松开

				UICollection.fjgl.setVisible(false);
				UICollection.khxx.setVisible(true);
				UICollection.cxkf.setVisible(false);
			
				UICollection.yggl.setVisible(false);
				UICollection.ruzhuInfo.setVisible(false);
			}
		});

		//入住查询
		label_7.addMouseListener(new MouseAdapter() {
			
			public void mouseUp(MouseEvent e) {
				UICollection.fjgl.setVisible(false);
				UICollection.khxx.setVisible(false);
				UICollection.cxkf.setVisible(false);
				UICollection.ruzhuInfo.setVisible(true);
			
				UICollection.yggl.setVisible(false);
	
			}

			public void mouseDown(MouseEvent e) {
				label_7.setForeground(display.getSystemColor(SWT.COLOR_RED));
				label4.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
				label_5.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
				label_6.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
				label_8.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
				label_1.setForeground(display.getSystemColor(SWT.COLOR_WHITE));

			}
		});

		// 点击前台
		label4.addMouseListener(new MouseAdapter() {
			
			public void mouseUp(MouseEvent e) {// 鼠标松开
				UICollection.fjgl.setVisible(true);
				UICollection.khxx.setVisible(false);
				UICollection.cxkf.setVisible(false);
			
				UICollection.yggl.setVisible(false);
				UICollection.ruzhuInfo.setVisible(false);
			}
			
			public void mouseDown(MouseEvent e) {// 鼠标按下
				label4.setForeground(display.getSystemColor(SWT.COLOR_RED));
				label_7.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
				label_5.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
				label_6.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
				label_8.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
				label_1.setForeground(display.getSystemColor(SWT.COLOR_WHITE));

			}
		});
		// 预定查询
		label_6.addMouseListener(new MouseAdapter() {
			
			public void mouseUp(MouseEvent e) {
				UICollection.fjgl.setVisible(false);
				UICollection.khxx.setVisible(false);
				UICollection.cxkf.setVisible(true);
			
				UICollection.yggl.setVisible(false);
				UICollection.ruzhuInfo.setVisible(false);
			}
			
			public void mouseDown(MouseEvent e) {
				label_6.setForeground(display.getSystemColor(SWT.COLOR_RED));
				label_7.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
				label4.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
				label_5.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
				label_8.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
				label_1.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
			}
		});

		// 点击房间管理
		label_5.addMouseListener(new MouseAdapter() {
			
			public void mouseUp(MouseEvent e) {
				UICollection.fjgl.setVisible(true);
				UICollection.khxx.setVisible(false);
				UICollection.cxkf.setVisible(false);
			
				UICollection.yggl.setVisible(false);
				UICollection.ruzhuInfo.setVisible(false);

			}

			public void mouseDown(MouseEvent e) {
				label_5.setForeground(display.getSystemColor(SWT.COLOR_RED));
				label_7.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
				label4.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
				label_6.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
				label_8.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
				label_1.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
			}
		});

		// 点击员工管理
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				label_1.setForeground(display.getSystemColor(SWT.COLOR_RED));
				label_7.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
				label4.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
				label_5.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
				label_6.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
				label_8.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
			}

			@Override
			public void mouseUp(MouseEvent e) {
				UICollection.fjgl.setVisible(false);
				UICollection.khxx.setVisible(false);
				UICollection.cxkf.setVisible(false);		
				UICollection.yggl.setVisible(true);
				UICollection.ruzhuInfo.setVisible(false);
			}
		});

		// 实时刷新系统时间
		new Thread() {// 线程操作
			public void run() {
				while (!shell.isDisposed()) {
					try {
						// 对Label进行实时刷新，需要加上这句
						label.getDisplay().asyncExec(new Runnable() {
							@Override
							public void run() {
								// 设置时间 ，格式化输出时间
								// 获取系统当前日期
								df = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");// 设置日期格式
								date = new Date();
								String str = df.format(date);
								lblNewLabel_3.setText(str);
							}
						});
						Thread.sleep(1000);// 每隔一秒刷新一次
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
}

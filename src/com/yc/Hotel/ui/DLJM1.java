package com.yc.Hotel.ui;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import oracle.jdbc.util.Login;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * 欢迎界面（客房预定+管理登陆）
 * 
 * @author Administrator
 */
public class DLJM1 {

	protected Shell shell;
	private Display display;
	private boolean isDown = false;// 记录鼠标有没有按下去
	private int x;// 鼠标按下去时x轴坐标
	private int y;// 鼠标按下去时y轴坐标
	// private boolean maximized = false;// 记录窗口是否最大化

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DLJM1 window = new DLJM1();
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
		shell.setSize(521, 482);
		shell.setText("皇冠商务酒店");
		shell.setImage(SWTResourceManager.getImage(Reservation.class, "/images/0080.gif"));
		// 窗口居中
		shell.setLocation((display.getClientArea().width - shell.getSize().x) / 2,
				(display.getClientArea().height - shell.getSize().y) / 2);

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackgroundImage(SWTResourceManager.getImage(DLJM1.class, "/images/801.jpg"));
		composite.setBounds(0, 0, 520, 480);

		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_1.setAlignment(SWT.CENTER);
		label_1.setFont(SWTResourceManager.getFont("叶根友毛笔行书2.0版", 20, SWT.NORMAL));
		label_1.setBounds(205, 45, 122, 36);
		label_1.setText("欢迎光临");

		Button button = new Button(composite, SWT.NONE);
		button.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		button.setBounds(222, 343, 90, 45);
		button.setText("客房预订");

		Button button_1 = new Button(composite, SWT.NONE);
		button_1.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		button_1.setBounds(222, 413, 90, 45);
		button_1.setText("员工登录");

		final Label label_2 = new Label(composite, SWT.NONE);
		label_2.setImage(SWTResourceManager.getImage(DLJM1.class, "/images/btn_close_normal.png"));
		label_2.setBounds(478, 0, 39, 20);

		final Label label_4 = new Label(composite, SWT.NONE);
		label_4.setImage(SWTResourceManager.getImage(DLJM1.class, "/images/btn_mini_normal.png"));
		label_4.setBounds(448, 0, 28, 20);

		composite.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent e) {// 鼠标移动的时候
				if (isDown) {// 说明鼠标是按下去的，所以鼠标移动的时候，窗口应该跟着走
					// 获取此时的光标位置
					shell.setLocation(shell.getLocation().x + e.x - x, shell.getLocation().y + e.y - y);
				}
			}
		});

		composite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {// 按下
				isDown = true;
				x = e.x;
				y = e.y;
			}

			@Override
			public void mouseUp(MouseEvent e) {// 移开
				isDown = false;
			}
		});

		// 关闭按钮
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {// 鼠标按下
				label_2.setImage(SWTResourceManager.getImage(DLJM1.class, "/images/btn_close_down.png"));
			}

			@Override
			public void mouseUp(MouseEvent e) {// 鼠标松开
				if (MessageDialog.openConfirm(shell, "关闭提醒", "确定要离开吗？")) {
					shell.dispose();
				}
			}
		});
		label_2.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseExit(MouseEvent e) {// 鼠标移开
				label_2.setImage(SWTResourceManager.getImage(DLJM1.class, "/images/btn_close_normal.png"));
			}

			@Override
			public void mouseHover(MouseEvent e) {// 鼠标移上
				label_2.setImage(SWTResourceManager.getImage(DLJM1.class, "/images/btn_close_highlight.png"));
			}
		});

		// 最小化
		label_4.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {// 鼠标按下
				label_4.setImage(SWTResourceManager.getImage(DLJM1.class, "/images/btn_mini_down.png"));
			}

			public void mouseUp(MouseEvent e) {// 鼠标松开
				shell.setMinimized(true);
			}
		});
		label_4.addMouseTrackListener(new MouseTrackAdapter() {
			public void mouseExit(MouseEvent e) {// 鼠标移开
				label_4.setImage(SWTResourceManager.getImage(DLJM1.class, "/images/btn_mini_normal.png"));
			}

			public void mouseHover(MouseEvent e) {// 鼠标移上
				label_4.setImage(SWTResourceManager.getImage(DLJM1.class, "/images/btn_mini_highlight.png"));
			}
		});

		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Reservation reservation = new Reservation();
				shell.close();
				reservation.open();
			}
		});
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DLJM3 dljm3 = new DLJM3();
				shell.close();
				dljm3.open();
			}
		});
	}

}

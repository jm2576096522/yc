package com.yc.Hotel.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Combo;

import com.yc.Hotel.Dao.DBHelper;
import com.yc.utils.Register;
import com.yc.utils.UICollection;

/**
 * 管理员登录界面
 * @author Administrator
 *
 */
public class DLJM3 {

	protected Shell shell;
	private Text text;
	private Display display;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DLJM3 window = new DLJM3();
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
		shell.setSize(500, 450);
		shell.setText("皇冠商务酒店登录");
		shell.setImage(SWTResourceManager.getImage(Reservation.class, "/images/0080.gif"));

		// 窗口居中
		shell.setLocation((display.getClientArea().width - shell.getSize().x) / 2,
				(display.getClientArea().height - shell.getSize().y) / 2);
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBackgroundImage(SWTResourceManager.getImage(DLJM3.class, "/images/804.jpg"));
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);

		final Label label_2 = new Label(composite, SWT.NONE);
		label_2.setImage(SWTResourceManager.getImage(DLJM3.class, "/images/btn_mini_normal.png"));
		label_2.setBounds(429, 0, 28, 20);

		final Label label_3 = new Label(composite, SWT.NONE);
		label_3.setImage(SWTResourceManager.getImage(DLJM3.class, "/images/btn_close_normal.png"));
		label_3.setBounds(459, 0, 39, 20);

		Label label = new Label(composite, SWT.NONE);
		label.setLocation(98, 218);
		label.setSize(47, 20);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label.setText("账号：");

		text = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		text.setLocation(162, 263);
		text.setSize(231, 23);

		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setLocation(98, 263);
		label_1.setSize(47, 20);
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_1.setText("密码：");

		Button button = new Button(composite, SWT.BORDER);
		button.setLocation(212, 340);
		button.setSize(80, 35);
		button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		button.setText("登录");

		final Combo combo = new Combo(composite, SWT.NONE);
		combo.setBounds(162, 215, 231, 25);

		final Button btnCheckButton = new Button(composite, SWT.CHECK);
		btnCheckButton.setBounds(172, 294, 98, 17);
		btnCheckButton.setText("记住密码");

		Button btnCheckButton_1 = new Button(composite, SWT.CHECK);

		btnCheckButton_1.setBounds(286, 294, 98, 17);
		btnCheckButton_1.setText("自动登录");

		Button btnNewButton = new Button(composite, SWT.BORDER);
		btnNewButton.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		btnNewButton.setBounds(212, 392, 80, 35);
		btnNewButton.setText("退出");

		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_1.setBounds(399, 218, 108, 15);

		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		lblNewLabel_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_2.setBounds(399, 264, 108, 15);

		final Label lblNewLabel_3 = new Label(composite, SWT.NONE);
		lblNewLabel_3.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		lblNewLabel_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_3.setBounds(194, 317, 128, 17);

		// 关闭按钮
		label_3.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				label_3.setImage(SWTResourceManager.getImage(DLJM3.class, "/images/btn_close_down.png"));
			}
			public void mouseUp(MouseEvent e) {
				if (MessageDialog.openConfirm(shell, "关闭提示", "确认退出吗？")) {
					shell.dispose();
				}
			}
		});
		label_3.addMouseTrackListener(new MouseTrackAdapter() {
			public void mouseExit(MouseEvent e) {
				label_3.setImage(SWTResourceManager.getImage(DLJM3.class, "/images/btn_close_normal.png"));
			}

			public void mouseHover(MouseEvent e) {
				label_3.setImage(SWTResourceManager.getImage(DLJM3.class, "/images/btn_close_highlight.png"));
			}
		});

		// 最小化按钮
		label_2.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				label_2.setImage(SWTResourceManager.getImage(DLJM3.class, "/images/btn_mini_down.png"));
			}
			public void mouseUp(MouseEvent e) {
				shell.setMinimized(true);
			}
		});
		label_2.addMouseTrackListener(new MouseTrackAdapter() {
			public void mouseExit(MouseEvent e) {
				label_2.setImage(SWTResourceManager.getImage(DLJM3.class, "/images/btn_mini_normal.png"));
			}
			public void mouseHover(MouseEvent e) {
				label_2.setImage(SWTResourceManager.getImage(DLJM3.class, "/images/btn_mini_highlight.png"));
			}
		});

		// 项目一运行，必须先查看注册表中有没有以前记住过的用户名和密码
		final Map<String, String> map = Register.getAll();
		if (map != null && map.size() > 0) {
			Set<String> keys = map.keySet();// 用户名
			for (String key : keys) {
				// 添加到用户名下拉列表中
				combo.add(key);
			}
			combo.select(0);// 默认选择第一个
			btnCheckButton.setSelection(true);// 自动选择记住密码
			// 密码框显示第一个账号和密码
			text.setText(map.get(combo.getItem(0)));
		}

		// 当用户名输入框失去焦点时
		combo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				// 获取当前的用户名
				String uname = combo.getText().trim();
				// 查看注册表中是否存在
				if (map != null && map.size() > 0) {
					if (map.containsKey(uname)) {// 如果存在则将当前用户的密码直接显示在密码框，且必须将记住密码勾上
						text.setText(map.get(uname));
						btnCheckButton.setSelection(true);
					} else {
						text.setText("");
						btnCheckButton.setSelection(false);
					}
				} else {
					text.setText("");
					btnCheckButton.setSelection(false);
				}
			}
		});

		// 点击登录按钮
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				String uname = combo.getText().trim();
				String pwd = text.getText().trim();// trim:整理修剪
				// 查询数据库
				DBHelper db = new DBHelper();
				String sql = "select opid,opwd,ograde,oname,otel from operater where opid=? and opwd=?";
				List<Object> params = new ArrayList<Object>();
				params.add(uname);
				params.add(pwd);
				Map<String, String> map = db.find(sql, params);
				if (map != null && map.size() > 0) {
					// 说明根据用户输入的用户名和密码能够在数据库中查到对应的数据，则说明他是已经注册的用户
					// 判断是否需要记住用户名和密码
					if (btnCheckButton.getSelection()) {// 如果需要记住则写入注册表
						Map<String, String> map1 = new HashMap<String, String>();
						map1.put(uname, pwd);
						System.out.println(map);
						Register.add(map1);
					} else {// 说明不需要写入注册表
						Register.remove(uname);
					}
					// 将当前成功登录的用户存到一个共享变量中
					UICollection.currentLoginUser = map;
					/*Register re = new Register();
					String IDname = null;
					List<Map<String, String>> list1 = re.searchaname(uname);
					for (Map<String, String> map1 : list1) {
						IDname = map1.get("oname");
					}
					System.out.println(IDname);
					HotelMenu hotelMenu = new HotelMenu(IDname);*/
					HotelMenu hotelMenu = new HotelMenu();
					shell.close();
					hotelMenu.open();
				} else {
					lblNewLabel_3.setText("账号或密码错误！");
					text.setText("");
				}
			}
		});
		// 点击退出按钮
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				System.exit(0);
			}
		});
	}
}

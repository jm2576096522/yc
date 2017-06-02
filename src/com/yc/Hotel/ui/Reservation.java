package com.yc.Hotel.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.wb.swt.SWTResourceManager;

import com.yc.Hotel.Dao.ReservationInfoDao;
import com.yc.Hotel.Dao.RoomtypeDao;
import com.yc.utils.UICollection;

import oracle.sql.DATE;

import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.nebula.widgets.calendarcombo.CalendarCombo;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

/**
 * 酒店客房预订
 * 
 * @author Administrator
 *
 */
public class Reservation {

	protected Shell shell;
	private Display display;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_6;
	private Text text_8;
	private Combo combo;
	private SimpleDateFormat df;
	private Date date;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Reservation window = new Reservation();
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
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(Reservation.class, "/images/0080.gif"));
		shell.setSize(687, 527);
		shell.setText("酒店客房预订");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		// 窗口居中
		shell.setLocation((display.getClientArea().width - shell.getSize().x) / 2,
				(display.getClientArea().height - shell.getSize().y) / 2);

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackgroundImage(SWTResourceManager.getImage(Reservation.class, "/images/room.jpg"));

		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(205, 133, 63));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("宋体", 18, SWT.NORMAL));
		lblNewLabel_1.setBounds(35, 34, 172, 31);
		lblNewLabel_1.setText("预定信息填写");

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBounds(24, 74, 53, 17);
		lblNewLabel.setText("证件号：");

		text = new Text(composite, SWT.BORDER);
		text.setBounds(83, 72, 158, 23);

		final Label label_2 = new Label(composite, SWT.NONE);

		label_2.setForeground(SWTResourceManager.getColor(255, 0, 0));
		label_2.setBounds(247, 74, 163, 17);

		final Label label = new Label(composite, SWT.NONE);
		label.setText("姓   名：");
		label.setBounds(25, 115, 52, 17);

		text_1 = new Text(composite, SWT.BORDER);
		text_1.setBounds(83, 112, 158, 23);

		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setText("电话号码：");
		label_1.setBounds(10, 214, 61, 17);

		text_2 = new Text(composite, SWT.BORDER);
		text_2.setBounds(83, 211, 158, 23);

		final Label label_3 = new Label(composite, SWT.NONE);
		label_3.setForeground(SWTResourceManager.getColor(255, 0, 0));
		label_3.setBounds(247, 115, 163, 17);

		final Label label_4 = new Label(composite, SWT.NONE);
		label_4.setForeground(SWTResourceManager.getColor(255, 0, 0));
		label_4.setBounds(247, 214, 163, 17);

		Label label_6 = new Label(composite, SWT.NONE);
		label_6.setText("房间类型：");
		label_6.setBounds(416, 75, 61, 17);

		combo = new Combo(composite, SWT.NONE);

		combo.setBounds(489, 72, 158, 20);

		Label label_7 = new Label(composite, SWT.NONE);
		label_7.setText("房间号：");
		label_7.setBounds(424, 118, 53, 17);

		final Combo combo_1 = new Combo(composite, SWT.NONE);

		combo_1.setBounds(489, 115, 158, 25);

		Label label_8 = new Label(composite, SWT.NONE);
		label_8.setText("客服电话：");
		label_8.setBounds(416, 266, 61, 17);

		Label label_9 = new Label(composite, SWT.NONE);
		label_9.setText("房    价：");
		label_9.setBounds(424, 162, 48, 17);

		text_6 = new Text(composite, SWT.BORDER);
		text_6.setBounds(490, 159, 158, 23);

		Label label_10 = new Label(composite, SWT.NONE);
		label_10.setBounds(16, 165, 56, 17);
		label_10.setText("性    别：");

		Label label_11 = new Label(composite, SWT.NONE);
		label_11.setBounds(10, 267, 61, 17);
		label_11.setText("预抵时间：");

		Label label_12 = new Label(composite, SWT.NONE);
		label_12.setText("预离时间：");
		label_12.setBounds(10, 322, 61, 17);

		Label label_13 = new Label(composite, SWT.NONE);
		label_13.setText("开单时间：");
		label_13.setBounds(416, 322, 63, 17);

		Button button = new Button(composite, SWT.NONE);

		button.setBounds(356, 398, 80, 27);
		button.setText("确定");

		Button button_1 = new Button(composite, SWT.NONE);
		button_1.setBounds(247, 398, 80, 27);
		button_1.setText("退出");

		Label label_14 = new Label(composite, SWT.NONE);
		label_14.setText("邮    箱：");
		label_14.setBounds(424, 214, 53, 17);

		text_8 = new Text(composite, SWT.BORDER);
		text_8.setBounds(490, 211, 158, 23);

		final CalendarCombo calendarCombo = new CalendarCombo(composite, SWT.NONE);
		calendarCombo.setBounds(84, 267, 158, 25);

		final CalendarCombo calendarCombo_1 = new CalendarCombo(composite, SWT.NONE);
		calendarCombo_1.setBounds(84, 321, 158, 25);

		final Label label_15 = new Label(composite, SWT.NONE);
		label_15.setBounds(492, 322, 158, 17);

		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		lblNewLabel_2.setText("10086");
		lblNewLabel_2.setBounds(490, 266, 61, 17);

		final Button btnRadioButton = new Button(composite, SWT.RADIO);
		btnRadioButton.setBounds(95, 165, 38, 17);
		btnRadioButton.setText("男");

		final Button btnRadioButton_1 = new Button(composite, SWT.RADIO);
		btnRadioButton_1.setBounds(159, 165, 48, 17);
		btnRadioButton_1.setText("女");

		// 实时刷新系统时间
		new Thread() {// 线程操作
			public void run() {
				while (!shell.isDisposed()) {
					try {
						// 对Label进行实时刷新，需要加上这句
						label.getDisplay().asyncExec(new Runnable() {

							public void run() {
								// 设置时间 ，格式化输出时间
								// 获取系统当前日期
								df = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");// 设置日期格式
								date = new Date();
								String str = df.format(date);
								label_15.setText(str);
							}
						});
						Thread.sleep(1000);// 每隔一秒刷新一次
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}.start();

		// 客房预订姓名输入框
		text_1.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {// 获取焦点
				label_3.setText("");

			}

			public void focusLost(FocusEvent e) {// 失去焦点
				String txt1 = text_1.getText().trim();
				if (txt1 == null || "".equals(txt1)) {
					label_3.setText("请输入姓名");
				} else {
					label_3.setText("");
				}
			}
		});

		// 证件号获取

		text.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String txt = text.getText().trim();
				for (int i = 0; i < txt.length(); i++) {
					Pattern p = Pattern.compile("^[^0]\\d{14}(\\d{3}|\\d{2}x)?$");
					Matcher m = p.matcher(txt);
					boolean b = m.matches();
					if (txt == null || "".equals(txt)) {
						label_2.setText("请输入身份证号（18位）");
					} else {
						if (b) {
							label_2.setText("");
						} else {
							label_2.setText("身份证号输入有误");
						}
					}
				}
			}
		});

		// 电话号码获取
		text_2.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String txt2 = text_2.getText().trim();
				for (int i = 0; i < txt2.length(); i++) {
					Pattern p = Pattern.compile("^1[3-8]\\d{9}$");
					Matcher m = p.matcher(txt2);
					boolean b = m.matches();
					if (txt2 == null || "".equals(txt2)) {
						label_4.setText("请输入手机号（11位）");
					} else {
						if (b) {
							label_4.setText("");
						} else {
							label_4.setText("手机号码输入有误");
						}
					}
				}
			}
		});

		addRoomtype();

		// 显示房间价格
		combo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (combo.getText().trim().equals("1_标准单人间")) {
					int i = 120;
					String p1 = String.valueOf(i);
					text_6.setText(p1);
				}
				if (combo.getText().equals("2_标准双人间")) {
					int i = 180;
					String p1 = String.valueOf(i);
					text_6.setText(p1);
				}
				if (combo.getText().equals("3_豪华单人间")) {
					int i = 240;
					String p1 = String.valueOf(i);
					text_6.setText(p1);
				}
				if (combo.getText().equals("4_豪华双人间")) {
					int i = 320;
					String p1 = String.valueOf(i);
					text_6.setText(p1);
				}
				if (combo.getText().equals("5_商务套房")) {
					int i = 600;
					String p1 = String.valueOf(i);
					text_6.setText(p1);
				}
				if (combo.getText().equals("6_总统套房")) {
					int i = 900;
					String p1 = String.valueOf(i);
					text_6.setText(p1);
				}
			}
		});

		// 获取房间类型
		combo.addFocusListener(new FocusAdapter() {
			// 失去焦点
			public void focusLost(FocusEvent e) {
				ReservationInfoDao re = new ReservationInfoDao();
				String type = combo.getText().trim();
				type = type.substring(0, 1);
				String orderin = calendarCombo.getDateAsString();
				List<Map<String, String>> list1 = re.search(type, orderin);
				for (Map<String, String> map : list1) {
					combo_1.add(map.get("rno"));
				}
			}

			// 获取焦点
			public void focusGained(FocusEvent e) {
				combo_1.removeAll();
			}
		});

		// 获取预入住时间
		calendarCombo.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent e) {
				System.out.println(calendarCombo.getDateAsString().getClass());
				System.out.println(calendarCombo.getDateAsString());
				// System.out.println(to_date(calendarCombo.getDateAsString().trim(),'yyyy-mm-dd'));
			}
		});

		// 点击确定的时候
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ReservationInfoDao re = new ReservationInfoDao();
				String cname = text_1.getText().trim();
				String card_id = text.getText().trim();
				String tel = text_2.getText().trim();
				String typeno = combo.getText().trim();
				typeno = typeno.substring(0, typeno.indexOf("_"));
				String rno = combo_1.getText().trim();
				String price = text_6.getText().trim();
				String email = text_8.getText().trim();
				String order_in = calendarCombo.getDateAsString();
				String order_out = calendarCombo_1.getDateAsString();
				String sex = btnRadioButton.getSelection() ? "男" : "女";
				String intoTime = null;
				String outTime = null;
				/*
				 * List<Map<String, String>> list =
				 * re.rnosearch(combo_1.getText().trim()); if (list != null &&
				 * list.size() > 0) { for (Map<String, String> map : list) {
				 * intoTime = String.valueOf(map.get("order_in")); outTime =
				 * String.valueOf(map.get("order_out")); if
				 * (Integer.parseInt(intoTime.substring(8)) >
				 * Integer.parseInt(order_out.substring(8)) ||
				 * Integer.parseInt(outTime.substring(8)) <
				 * Integer.parseInt(order_out.substring(8))) { if
				 * (re.add(typeno, rno, cname, sex, tel, card_id, order_in,
				 * price, order_out, email) > 0 && re.rnostate(rno) > 0) {
				 * MessageDialog.openConfirm(shell, "确认提示", "信息完成");
				 * shell.close();
				 * 
				 * } } else { MessageDialog.openError(shell, "错误提示",
				 * "该房间这段时间有人住"); System.exit(0); } } } else {
				 * MessageDialog.openError(shell, "错误提示", "错误"); System.exit(0);
				 * }
				 */

				if (re.add(typeno, rno, cname, sex, tel, card_id, order_in, price, order_out, email) > 0) {
					MessageDialog.openConfirm(shell, "确认提示", "信息完成");
					shell.close();
				}
			}
		});

		// 退出按钮
		button_1.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});

	}

	// 显示客房类型
	public void addRoomtype() {
		RoomtypeDao roomtypeDao = new RoomtypeDao();
		List<Map<String, String>> list = roomtypeDao.find();
		if (list != null && list.size() > 0) {
			combo.add("请选择房间类型");
			for (Map<String, String> map : list) {
				combo.add(map.get("typeno") + "_" + map.get("typename"));
			}
			combo.select(0);
		}
	}
}

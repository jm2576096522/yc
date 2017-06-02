package com.yc.Hotel.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.yc.Hotel.Dao.RoomInfoDao;
import com.yc.utils.UICollection;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.nebula.widgets.calendarcombo.CalendarCombo;
import org.eclipse.wb.swt.SWTResourceManager;

/**
 * 前台对房间的操作(入住，预定，结帐，清理。。。。。。)
 * 
 * @author Administrator
 *
 */
public class FJGLsjjm {

	protected Shell shell;
	private int fh = 101;
	private int i = 2;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Label lblFangjia;
	private SimpleDateFormat df;
	private Date date;
	private String sex;
	private RoomInfoDao room = new RoomInfoDao();
	private Text text_6;
	private Text text_7;
	private Text text_11;
	private Text text_12;
	private Display display;

	public FJGLsjjm() {

	}

	public FJGLsjjm(int fh, int i) {
		this.fh = fh;
		this.i = i;

	}

	public static void main(String[] args) {
		try {
			FJGLsjjm window = new FJGLsjjm();
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
	 * 
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setText("退房");
		shell.setSize(402, 239);
		shell.setImage(SWTResourceManager.getImage(Reservation.class, "/images/0080.gif"));

		// 窗口居中
		shell.setLocation((display.getClientArea().width - shell.getSize().x) / 2,
				(display.getClientArea().height - shell.getSize().y) / 2);

		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		final Label lblNewLabel_121 = new Label(composite, SWT.NONE);
		lblNewLabel_121.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_121.setBounds(132, 10, 307, 17);
		lblNewLabel_121.setText("");

		if (i == 0) {// 入住
			final int zt = room.roomZT(fh);// 房间状态
			if (zt == 0 || zt == 2) {
				shell.setSize(561, 400);
				shell.setText("客房入住");

				Label lblNewLabel = new Label(composite, SWT.NONE);
				lblNewLabel.setBounds(10, 81, 61, 17);
				lblNewLabel.setText("姓名：");

				Label lblNewLabel_1 = new Label(composite, SWT.NONE);
				lblNewLabel_1.setBounds(10, 123, 61, 17);
				lblNewLabel_1.setText("证件号：");

				text = new Text(composite, SWT.BORDER);// 姓名

				text.setBounds(80, 78, 143, 23);

				text_1 = new Text(composite, SWT.BORDER);// 证件号
				text_1.setBounds(80, 120, 143, 23);

				final Button btnRadioButton = new Button(composite, SWT.RADIO);

				btnRadioButton.setBounds(89, 162, 49, 17);
				btnRadioButton.setText("男");

				Button btnRadioButton_1 = new Button(composite, SWT.RADIO);
				btnRadioButton_1.setBounds(144, 162, 49, 17);
				btnRadioButton_1.setText("女");

				Label lblNewLabel_2 = new Label(composite, SWT.NONE);
				lblNewLabel_2.setBounds(10, 202, 61, 17);
				lblNewLabel_2.setText("联系电话：");

				text_2 = new Text(composite, SWT.BORDER);// 电话
				text_2.setBounds(80, 199, 143, 23);

				Label lblNewLabel_3 = new Label(composite, SWT.NONE);
				lblNewLabel_3.setBounds(10, 248, 61, 17);
				lblNewLabel_3.setText("邮箱：");

				text_3 = new Text(composite, SWT.BORDER);// 邮箱
				text_3.setBounds(80, 245, 143, 23);

				Label lblNewLabel_4 = new Label(composite, SWT.NONE);
				lblNewLabel_4.setBounds(384, 81, 61, 17);
				lblNewLabel_4.setText("房价：");

				lblFangjia = new Label(composite, SWT.NONE);
				lblFangjia.setBounds(451, 81, 84, 17);

				Label lblNewLabel_5 = new Label(composite, SWT.NONE);
				lblNewLabel_5.setBounds(384, 123, 61, 17);
				lblNewLabel_5.setText("押金：");

				text_5 = new Text(composite, SWT.BORDER);// 押金
				text_5.setBounds(451, 120, 84, 23);

				Label lblNewLabel_6 = new Label(composite, SWT.NONE);
				lblNewLabel_6.setBounds(352, 205, 93, 17);
				lblNewLabel_6.setText("预计退房时间：");

				Button btnNewButton = new Button(composite, SWT.NONE);
				btnNewButton.setBounds(102, 297, 80, 40);
				btnNewButton.setText("确认入住");

				Button btnNewButton_1 = new Button(composite, SWT.NONE);

				btnNewButton_1.setBounds(359, 297, 80, 40);
				btnNewButton_1.setText("取消");

				final CalendarCombo calendarCombo = new CalendarCombo(composite, SWT.NONE);
				calendarCombo.setBounds(352, 240, 172, 25);

				final Label lblNewLabel_7 = new Label(composite, SWT.NONE);
				lblNewLabel_7.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
				lblNewLabel_7.setBounds(229, 123, 149, 17);
				lblNewLabel_7.setText("");

				final Label lblNewLabel_8 = new Label(composite, SWT.NONE);
				lblNewLabel_8.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
				lblNewLabel_8.setBounds(229, 81, 149, 17);
				lblNewLabel_8.setText("");

				final Label lblNewLabel_9 = new Label(composite, SWT.NONE);
				lblNewLabel_9.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
				lblNewLabel_9.setBounds(229, 202, 117, 17);
				lblNewLabel_9.setText("");

				Label lblNewLabel_10 = new Label(composite, SWT.NONE);
				lblNewLabel_10.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
				lblNewLabel_10.setBounds(231, 248, 115, 17);
				lblNewLabel_10.setText("");

				Label lblNewLabel_11 = new Label(composite, SWT.NONE);
				lblNewLabel_11.setBounds(350, 166, 70, 17);
				lblNewLabel_11.setText("收银员姓名：");

				final Label label = new Label(composite, SWT.NONE);
				label.setBounds(451, 166, 84, 17);
				label.setText(UICollection.currentLoginUser.get("oname"));

				Label lblNewLabel_12 = new Label(composite, SWT.NONE);
				lblNewLabel_12.setBounds(10, 162, 61, 17);
				lblNewLabel_12.setText("性别：");

				// 显示房间价格
				int lx = room.roomLX(fh);
				switch (lx) {
				case 1:
					lblFangjia.setText(120 + "");
					break;
				case 2:
					lblFangjia.setText(180 + "");
					break;
				case 3:
					lblFangjia.setText(240 + "");
					break;
				case 4:
					lblFangjia.setText(320 + "");
					break;
				case 5:
					lblFangjia.setText(600 + "");
					break;
				case 6:
					lblFangjia.setText(900 + "");
					break;
				}

				if (zt == 0) {
					// 姓名输入框
					text.addKeyListener(new KeyAdapter() {
						public void keyReleased(KeyEvent e) {
							String xm = text.getText().trim();
							if (xm == null || "".equals(xm)) {
								lblNewLabel_8.setText("请输入姓名...");
							} else {
								lblNewLabel_8.setText("");
							}
						}
					});
					// 证件号获取
					text_1.addKeyListener(new KeyAdapter() {
						public void keyReleased(KeyEvent e) {
							String txt = text_1.getText().trim();
							if (txt == null || "".equals(txt)) {
								lblNewLabel_7.setText("请输入身份证号（18位）");
							} else {
								if (txt.length() == 18 || txt.length() == 15) {
									lblNewLabel_7.setText("");
									return;
								}
								lblNewLabel_7.setText("身份证号输入有误");
								// for (int i = 0; i < txt.length(); i++) {
								// Pattern p =
								// Pattern.compile("^[^0]\\d{14}(\\d{3}|\\d{2}x)?$");
								// Matcher m = p.matcher(txt);
								// boolean b = m.matches();
								//
								// if (b) {
								// lblNewLabel_7.setText("");
								// } else {
								// lblNewLabel_7.setText("身份证号输入有误");
								// }

								// }
							}
						}
					});
					// 电话号码获取
					text_2.addKeyListener(new KeyAdapter() {
						public void keyReleased(KeyEvent e) {
							String txt2 = text_2.getText().trim();

							if (txt2 == null || "".equals(txt2)) {
								lblNewLabel_9.setText("请输入手机号（11位）");
							} else {
								for (int i = 0; i < txt2.length(); i++) {
									Pattern p = Pattern.compile("^1[3-8]\\d{9}$");
									Matcher m = p.matcher(txt2);
									boolean b = m.matches();
									if (b) {
										lblNewLabel_9.setText("");
									} else {
										lblNewLabel_9.setText("手机号码输入有误");
									}
								}
							}
						}
					});

					btnRadioButton.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							sex = "男";
						}
					});
					btnRadioButton_1.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							sex = "女";
						}
					});

				} else {// 当前房间为预定状态

					Map<String, String> map = room.resinfo(fh);
					if (map != null && map.size() > 0) {

						text.setText(String.valueOf(map.get("cname")));
						text_1.setText(String.valueOf(map.get("card_id")));
						if (String.valueOf(map.get("sex")).equals("男")) {
							btnRadioButton.setSelection(true);
						} else {
							btnRadioButton_1.setSelection(true);
						}

						text_2.setText(String.valueOf(map.get("tel")));
						text_3.setText(String.valueOf(map.get("email")));
						
						/*
						 * String dateStr =
						 * String.valueOf(map.get("order_out")); df = new
						 * SimpleDateFormat("YYYY-MM-dd");// 设置日期格式 Date
						 * date_out = null;
						 * 
						 * try { date_out = df.parse(dateStr); } catch
						 * (ParseException e1) {
						 * 
						 * e1.printStackTrace(); }
						 * calendarCombo.setData(date_out);
						 */

					}
				}

				// 点击确定的时候
				btnNewButton.addSelectionListener(new SelectionAdapter() {

					public void widgetSelected(SelectionEvent e) {
						String cname = text.getText().trim();
						String card_id = text_1.getText().trim();
						String sex = btnRadioButton.getSelection() ? "男" : "女";
						String tel = text_2.getText().trim();
						String oname = label.getText().trim();// 收银员姓名
						String cash = text_5.getText().trim();
						String email = text_3.getText().trim();

						df = new SimpleDateFormat("YYYY-MM-dd");// 设置日期格式
						date = new Date();
						String date_in = df.format(date);

						// System.out.println("calendarCombo.getDateAsString()"+calendarCombo.getDateAsString());
						String date_out = calendarCombo.getDateAsString();
						if (zt == 2) {
							String str = room.roomYDSJ(fh);
							Map<String, String> map = room.resinfo(fh);
							if (!card_id.equals(String.valueOf(map.get("card_id")))) {

								lblNewLabel_121.setText(fh + "客房已被预定，若入住请顾客于" + str + "退房或者换房");

							}

							df = new SimpleDateFormat("YYYY-MM-dd");// 设置日期格式
							Date d1 = new Date();
							Date d2 = new Date();
							try {
								d1 = df.parse(date_out);
								d2 = df.parse(str);
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
							long l = (d2.getTime() - d1.getTime());
							if (l > 0) {
								MessageDialog.openError(shell, "错误提示", "入住时间有冲突，入住失败！");
								return;
							}
						}
						// System.out.println("opid:"+UICollection.currentLoginUser.get("opid"));
						// System.out.println("room:"+room.delres(fh));
						if (room.addRZXX2(fh, cname, sex, tel, card_id, date_in, date_out, cash, email,
								UICollection.currentLoginUser.get("opid")) > 0
								&& room.addRZXX3(fh, cname, sex, tel, card_id, date_in, date_out, cash, email,
										UICollection.currentLoginUser.get("opid")) > 0) {
							if ((zt == 0 && room.addRZXX1(fh, cname, sex, tel, card_id, date_in, date_out, cash, email,
									UICollection.currentLoginUser.get("opid")) > 0)
									|| (zt == 2 && room.delres((int) fh) > 0)) {
								MessageDialog.openConfirm(shell, "确认提示", "客房入住成功！");
								shell.close();
								return;
							}
						} else {
							MessageDialog.openError(shell, "错误提示", "信息输入有误，入住失败！");
							return;
						}

					}
				});
				// 点击取消
				btnNewButton_1.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						shell.close();
						return;
					}
				});

			} else if (zt == 1) {
				MessageDialog.openError(shell, "错误提示", "该房间当前已有客户入住！");
				return;
			} else {
				MessageDialog.openError(shell, "错误提示", "该房间当前状态无法入住！");
				return;
			}

		}
		if (i == 1) {// 预定
			final int zt = room.roomZT(fh);
			if (zt == 0 || zt == 1 || zt == 2) {
				shell.setSize(561, 447);
				shell.setText("客房预定");

				Label lblNewLabel = new Label(composite, SWT.NONE);
				lblNewLabel.setBounds(10, 75, 61, 17);
				lblNewLabel.setText("姓名：");

				Label lblNewLabel_1 = new Label(composite, SWT.NONE);
				lblNewLabel_1.setBounds(10, 117, 61, 17);
				lblNewLabel_1.setText("证件号：");

				text = new Text(composite, SWT.BORDER);
				text.setBounds(80, 72, 143, 23);

				text_1 = new Text(composite, SWT.BORDER);
				text_1.setBounds(80, 114, 143, 23);

				final Button btnRadioButton = new Button(composite, SWT.RADIO);
				btnRadioButton.setBounds(64, 156, 49, 17);
				btnRadioButton.setText("男");

				Button btnRadioButton_1 = new Button(composite, SWT.RADIO);
				btnRadioButton_1.setBounds(144, 156, 49, 17);
				btnRadioButton_1.setText("女");

				Label lblNewLabel_2 = new Label(composite, SWT.NONE);
				lblNewLabel_2.setBounds(10, 196, 61, 17);
				lblNewLabel_2.setText("联系电话：");

				text_2 = new Text(composite, SWT.BORDER);
				text_2.setBounds(80, 193, 143, 23);

				Label lblNewLabel_3 = new Label(composite, SWT.NONE);
				lblNewLabel_3.setBounds(10, 242, 61, 17);
				lblNewLabel_3.setText("邮箱：");

				text_3 = new Text(composite, SWT.BORDER);
				text_3.setBounds(80, 239, 143, 23);

				Label lblNewLabel_4 = new Label(composite, SWT.NONE);
				lblNewLabel_4.setBounds(384, 75, 49, 17);
				lblNewLabel_4.setText("房价：");

				text_4 = new Text(composite, SWT.BORDER);
				text_4.setBounds(439, 72, 61, 23);

				Label lblNewLabel_5 = new Label(composite, SWT.NONE);
				lblNewLabel_5.setBounds(384, 117, 49, 17);
				lblNewLabel_5.setText("押金：");

				text_5 = new Text(composite, SWT.BORDER);
				text_5.setBounds(439, 114, 61, 23);

				Label lblNewLabel_6 = new Label(composite, SWT.NONE);
				lblNewLabel_6.setBounds(352, 199, 93, 17);
				lblNewLabel_6.setText("预计入住时间：");

				Button btnNewButton = new Button(composite, SWT.NONE);
				btnNewButton.setBounds(94, 339, 80, 40);
				btnNewButton.setText("预定");

				Button btnNewButton_1 = new Button(composite, SWT.NONE);

				btnNewButton_1.setBounds(365, 339, 80, 40);
				btnNewButton_1.setText("取消");

				final CalendarCombo calendarCombo = new CalendarCombo(composite, SWT.NONE);
				calendarCombo.setBounds(352, 222, 172, 25);

				final Label lblNewLabel_7 = new Label(composite, SWT.NONE);
				lblNewLabel_7.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
				lblNewLabel_7.setBounds(229, 75, 149, 17);
				lblNewLabel_7.setText("");

				final Label lblNewLabel_8 = new Label(composite, SWT.NONE);
				lblNewLabel_8.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
				lblNewLabel_8.setBounds(229, 117, 149, 17);
				lblNewLabel_8.setText("");

				final Label lblNewLabel_9 = new Label(composite, SWT.NONE);
				lblNewLabel_9.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
				lblNewLabel_9.setBounds(229, 196, 117, 17);
				lblNewLabel_9.setText("");

				Label lblNewLabel_10 = new Label(composite, SWT.NONE);
				lblNewLabel_10.setBounds(10, 156, 61, 17);
				lblNewLabel_10.setText("性别：");

				Label lblNewLabel_11 = new Label(composite, SWT.NONE);
				lblNewLabel_11.setBounds(350, 156, 70, 17);
				lblNewLabel_11.setText("客服电话");

				text_6 = new Text(composite, SWT.BORDER);
				text_6.setBounds(439, 153, 61, 23);
				text_6.setText("10086");

				Label lblNewLabel_13 = new Label(composite, SWT.NONE);
				lblNewLabel_13.setBounds(352, 263, 93, 17);
				lblNewLabel_13.setText("预计退房时间：");

				final CalendarCombo calendarCombo_1 = new CalendarCombo(composite, SWT.NONE);
				calendarCombo_1.setBounds(352, 287, 172, 25);
				// 单选按钮
				btnRadioButton.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						sex = "男";
					}
				});
				btnRadioButton_1.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						sex = "女";
					}
				});

				// 姓名输入框
				text.addFocusListener(new FocusAdapter() {
					public void focusGained(FocusEvent e) {// 获取焦点
						lblNewLabel_7.setText("");
					}

					public void focusLost(FocusEvent e) {// 失去焦点
						String xm = text.getText().trim();
						if (xm == null || "".equals(xm)) {
							lblNewLabel_7.setText("请输入姓名");
						} else {
							lblNewLabel_7.setText("");
						}
					}
				});

				// 证件号获取
				text_1.addKeyListener(new KeyAdapter() {
					public void keyReleased(KeyEvent e) {
						String txt = text_1.getText().trim();
						for (int i = 0; i < txt.length(); i++) {
							Pattern p = Pattern.compile("^[^0]\\d{14}(\\d{3}|\\d{2}x)?$");
							Matcher m = p.matcher(txt);
							boolean b = m.matches();
							if (txt == null || "".equals(txt)) {
								lblNewLabel_8.setText("请输入身份证号（18位）");
							} else {
								if (b) {
									lblNewLabel_8.setText("");
								} else {
									lblNewLabel_8.setText("身份证号输入有误");
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
							Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
							Matcher m = p.matcher(txt2);
							boolean b = m.matches();
							if (txt2 == null || "".equals(txt2)) {
								lblNewLabel_9.setText("请输入手机号（11位）");
							} else {
								if (b) {
									lblNewLabel_9.setText("");
								} else {
									lblNewLabel_9.setText("手机号码输入有误");
								}
							}
						}
					}
				});

				// 显示房间价格
				int lx = room.roomLX(fh);
				switch (lx) {
				case 1:
					text_4.setText(120 + "");
					break;
				case 2:
					text_4.setText(180 + "");
					break;
				case 3:
					text_4.setText(240 + "");
					break;
				case 4:
					text_4.setText(320 + "");
					break;
				case 5:
					text_4.setText(600 + "");
					break;
				case 6:
					text_4.setText(900 + "");
					break;
				}

				// 点击确定的时候
				btnNewButton.addSelectionListener(new SelectionAdapter() {

					public void widgetSelected(SelectionEvent e) {
						String cname = text.getText().trim();
						String card_id = text_1.getText().trim();
						String tel = text_2.getText().trim();

						int rno = fh;
						String cash = text_5.getText().trim();
						String email = text_3.getText().trim();
						String price = text_4.getText().trim();
						System.out.println("calendarCombo.getDateAsString()" + calendarCombo.getDateAsString());
						String date_in = calendarCombo.getDateAsString();
						String date_out = calendarCombo_1.getDateAsString();
						String sex = btnRadioButton.getSelection() ? "男" : "女";

						if (zt == 1) {
							String tfsj = room.roomTFSJ(fh);
							lblNewLabel_121.setText("当前入住客户预计退房时间：" + tfsj);
							df = new SimpleDateFormat("YYYY-MM-dd");
							Date l = null;
							try {
								l = df.parse(tfsj);
							} catch (ParseException e2) {
								e2.printStackTrace();
							}
							Date l1 = null;
							try {
								l1 = df.parse(date_in);
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
							if ((l1.getTime() - l.getTime()) > 0) {
								MessageDialog.openError(shell, "错误提示", "预定时间有冲突，预定失败！");
								return;
							}
						}
						if (zt == 2) {
							String ydrz = room.roomYDSJ(fh);
							String ydtf = room.roomYDTF(fh);
							Date d1 = null;
							try {
								d1 = df.parse(date_in);
							} catch (ParseException e2) {
								e2.printStackTrace();
							}
							Date d2 = null;
							try {
								d2 = df.parse(date_out);
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
							Date d3 = null;
							try {
								d3 = df.parse(ydrz);
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
							Date d4 = null;
							try {
								d4 = df.parse(ydtf);
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
							lblNewLabel_121.setText("当前预定预计时间：" + ydrz + "至" + ydtf);
							if ((d1.getTime() - d4.getTime()) > 0 || d2.getTime() - d3.getTime() < 0) {
							} else {
								MessageDialog.openError(shell, "错误提示", "预定时间有冲突，预定失败！");
								return;
							}
						}
						if (room.addYDXX(rno, cname, sex, tel, card_id, date_in, date_out, cash, price, email) > 0) {
							MessageDialog.openConfirm(shell, "确认提示", "预定客房成功！");
							shell.close();
							return;
						} else {
							MessageDialog.openError(shell, "错误提示", "信息输入有误，预定失败！");
							return;
						}

					}
				});
				// 点击取消
				btnNewButton_1.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						shell.close();
						return;
					}
				});

			} else {
				MessageDialog.openError(shell, "错误提示", "该房间当前状态无法预定！");
				return;
			}

		}

		if (i == 2) {// 结账
			int zt = room.roomZT(fh);// 状态：有人入住方可结账
			if (zt == 1) {
				int jz = room.roomJZ(fh);
				if (jz == 0) {
					shell.setText("结账");
					shell.setSize(402, 320);

					shell.setLayout(new FillLayout(SWT.HORIZONTAL));

					Label lblNewLabel_14 = new Label(composite, SWT.NONE);
					lblNewLabel_14.setBounds(24, 62, 61, 17);
					lblNewLabel_14.setText("入住时间：");

					Label lblNewLabel_15 = new Label(composite, SWT.NONE);
					lblNewLabel_15.setBounds(24, 104, 61, 17);
					lblNewLabel_15.setText("客房价格：");

					Label lblNewLabel_16 = new Label(composite, SWT.NONE);
					lblNewLabel_16.setBounds(232, 104, 61, 17);
					lblNewLabel_16.setText("酒水餐费：");

					Label lblNewLabel_17 = new Label(composite, SWT.NONE);
					lblNewLabel_17.setBounds(24, 186, 49, 17);
					lblNewLabel_17.setText("收银：");

					text_11 = new Text(composite, SWT.BORDER);// 收银
					text_11.setBounds(79, 183, 61, 23);

					Label lblNewLabel_18 = new Label(composite, SWT.NONE);
					lblNewLabel_18.setBounds(253, 186, 40, 17);
					lblNewLabel_18.setText("找零：");

					Button btnNewButton_2 = new Button(composite, SWT.NONE);// 结账按钮
					btnNewButton_2.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
					btnNewButton_2.setBounds(76, 230, 80, 27);
					btnNewButton_2.setText("结账");

					Button btnNewButton_3 = new Button(composite, SWT.NONE);
					btnNewButton_3.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
					btnNewButton_3.setBounds(214, 230, 80, 27);
					btnNewButton_3.setText("取消");

					Label lblNewLabel_19 = new Label(composite, SWT.NONE);
					lblNewLabel_19.setBounds(232, 62, 61, 17);
					lblNewLabel_19.setText("入住天数：");

					Label lblNewLabel_20 = new Label(composite, SWT.NONE);// 入住时间
					lblNewLabel_20.setBounds(95, 62, 104, 17);

					Label lblNewLabel_21 = new Label(composite, SWT.NONE);// 客房价格
					lblNewLabel_21.setBounds(95, 104, 61, 17);

					Label lblNewLabel_22 = new Label(composite, SWT.NONE);// 入住天数
					lblNewLabel_22.setBounds(299, 62, 61, 17);

					Label lblNewLabel_23 = new Label(composite, SWT.NONE);// 酒水餐费
					lblNewLabel_23.setBounds(299, 104, 61, 17);

					Label lblNewLabel_24 = new Label(composite, SWT.NONE);
					lblNewLabel_24.setBounds(24, 145, 61, 17);
					lblNewLabel_24.setText("押金：");

					Label lblNewLabel_25 = new Label(composite, SWT.NONE);// 押金
					lblNewLabel_25.setBounds(94, 145, 61, 17);

					Label lblNewLabel_26 = new Label(composite, SWT.NONE);
					lblNewLabel_26.setBounds(232, 145, 61, 17);
					lblNewLabel_26.setText("应付金额：");

					String date_in = room.roomRZSJ(fh);
					lblNewLabel_20.setText(date_in);

					df = new SimpleDateFormat("YYYY-MM-dd");// 设置日期格式
					date = new Date();
					DateFormat dateFormat = DateFormat.getDateInstance();
					String str = dateFormat.format(date);// 当前系统时间
					str = df.format(Calendar.getInstance().getTime());
					String day = str.substring(9);
					String day1 = date_in.substring(9);
					System.out.println(str);
					int d1 = Integer.valueOf(day).intValue();// 当前系统时间
					int d2 = Integer.valueOf(day1).intValue();// 入住时间

					int ts = d1 - d2 + 1;// 入住天数

					lblNewLabel_22.setText(ts + "");

					int yj = room.roomYJ(fh);// 押金
					lblNewLabel_25.setText(yj + "");

					int i1 = room.roomLX(fh);// 房价
					switch (i1) {
					case 1:
						i1 = 120;
						lblNewLabel_21.setText(i1 + "");
						break;// 客房价格
					case 2:
						i1 = 180;
						lblNewLabel_21.setText(i1 + "");
						break;
					case 3:
						i1 = 240;
						lblNewLabel_21.setText(i1 + "");
						break;
					case 4:
						i1 = 320;
						lblNewLabel_21.setText(i1 + "");
						break;
					case 5:
						i1 = 600;
						lblNewLabel_21.setText(i1 + "");
						break;
					case 6:
						i1 = 900;
						lblNewLabel_21.setText(i1 + "");
						break;
					}

					int jscf = 0;// 酒水餐费
					lblNewLabel_23.setText(jscf + "");

					int yfje = ts * i1 + jscf - yj;// 应付金额

					final Label lblNewLabel_27 = new Label(composite, SWT.NONE);// 应付金额
					lblNewLabel_27.setBounds(299, 145, 61, 17);
					lblNewLabel_27.setText(yfje + "");

					final Label label = new Label(composite, SWT.NONE);// 找零
					label.setBounds(296, 186, 80, 17);

					final Label label_1 = new Label(composite, SWT.NONE);// 收银提示
					label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
					label_1.setBounds(146, 186, 85, 17);

					Label label_2 = new Label(composite, SWT.NONE);
					label_2.setBounds(299, 186, 61, 17);

					text_11.addKeyListener(new KeyAdapter() {// 收银
						public void keyReleased(KeyEvent e) {
							String price = text_11.getText().trim();
							for (int i = 0; i < price.length(); i++) {
								Pattern p = Pattern.compile("^(([0-9]|([1-9][0-9]{0,9}))((\\.[0-9]{1,2})?))$");
								Matcher m = p.matcher(price);
								boolean b = m.matches();
								if (price == null || "".equals(price)) {
									label_1.setText("请输入金额");
								} else {
									if (b) {
										label_1.setText("");
										int a = Integer.valueOf(text_11.getText()).intValue();// 收银
										int a1 = Integer.valueOf(lblNewLabel_27.getText()).intValue();// 应收
										int num = a - a1;//
										if (num >= 0) {
											label.setText(String.valueOf(num));
										} else {
											label_1.setText("收银不足");
											label.setText("");
										}
									} else {
										label_1.setText("金额输入有误");
									}
								}
							}
						}
					});

					// 点击结账
					btnNewButton_2.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							if (room.jz(fh) > 0) {
								MessageDialog.openConfirm(shell, "确认提示", "结账成功！");
								shell.close();
								return;

							} else {
								MessageDialog.openError(shell, "错误提示", "结账失败！");
								System.exit(0);
							}
						}
					});

				} else {
					MessageDialog.openError(shell, "错误提示", "该客房入住客户已结账！");
					System.exit(0);

				}
			} else {
				MessageDialog.openError(shell, "错误提示", "该客房当前无人入住！");
				System.exit(0);

			}

		}
		if (i == 3) {// 退房
			final int zt = room.roomZT(fh);
			if (zt != 1) {
				MessageDialog.openError(shell, "错误提示", "该客房当前无人入住！");
				shell.close();
			}
			Button btnNewButton_4 = new Button(composite, SWT.NONE);
			btnNewButton_4.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
			btnNewButton_4.setBounds(75, 140, 80, 39);
			btnNewButton_4.setText("确认");

			Button btnNewButton_5 = new Button(composite, SWT.NONE);
			btnNewButton_5.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
			btnNewButton_5.setBounds(240, 140, 80, 39);
			btnNewButton_5.setText("取消");

			final Label lblNewLabel_28 = new Label(composite, SWT.NONE);
			lblNewLabel_28.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
			lblNewLabel_28.setBounds(153, 59, 121, 27);

			btnNewButton_5.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					shell.close();
				}
			});

			lblNewLabel_28.setText("是否确认退房？");
			btnNewButton_4.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					int jz = room.roomJZ(fh);
					if (jz == 0) {
						MessageDialog.openError(shell, "错误提示", "该客房入住客户尚未结账！");
						shell.close();
					} else {
						df = new SimpleDateFormat("YYYY-MM-dd");// 设置日期格式
						date = new Date();
						String date_in = df.format(date);
						if (room.TForderno(fh) > 0 && room.roomTF(fh, date_in) > 0 && room.roomstate(fh) > 0) {
							MessageDialog.openConfirm(shell, "确认提示", "退房成功！");
							shell.close();
							return;
						} else {
							MessageDialog.openError(shell, "错误提示", "退房失败！");
							System.exit(0);

						}
					}
				}
			});
		}
	}
}

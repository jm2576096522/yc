package com.yc.Hotel.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.wb.swt.SWTResourceManager;

import com.yc.Hotel.Dao.OperatorInfoDao;
import com.yc.utils.UICollection;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
/**
 * 添加员工信息
 * @author Administrator
 *
 */
public class AddOperaterInfo {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Display display;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AddOperaterInfo window = new AddOperaterInfo();
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
		shell.setImage(SWTResourceManager.getImage(AddOperaterInfo.class, "/images/0080.gif"));
		shell.setSize(452, 376);
		shell.setText("添加员工信息");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		// 窗口居中
		shell.setLocation((display.getClientArea().width - shell.getSize().x) / 2,
				(display.getClientArea().height - shell.getSize().y) / 2);

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);

		Label lblNewLabel_1 = new Label(composite, SWT.RIGHT);
		lblNewLabel_1.setBounds(80, 65, 61, 17);
		lblNewLabel_1.setText("姓名：");

		Label lblNewLabel_2 = new Label(composite, SWT.RIGHT);
		lblNewLabel_2.setBounds(80, 109, 61, 17);
		lblNewLabel_2.setText("密码：");

		Label lblNewLabel_3 = new Label(composite, SWT.RIGHT);
		lblNewLabel_3.setBounds(80, 152, 61, 17);
		lblNewLabel_3.setText("电话号码：");

		Label lblNewLabel_4 = new Label(composite, SWT.RIGHT);
		lblNewLabel_4.setBounds(80, 198, 61, 17);
		lblNewLabel_4.setText("员工等级：");

		text = new Text(composite, SWT.BORDER);
		text.setBounds(160, 62, 120, 23);

		text_1 = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		text_1.setBounds(160, 103, 120, 23);

		text_2 = new Text(composite, SWT.BORDER);
		text_2.setBounds(160, 146, 120, 23);

		final Combo combo = new Combo(composite, SWT.NONE);
		combo.setItems(new String[] { "收银员", "管理员" });
		combo.setBounds(160, 190, 120, 25);

		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setBounds(106, 242, 61, 27);
		btnNewButton.setText("重置");

		Button button = new Button(composite, SWT.NONE);
		button.setBounds(213, 242, 67, 27);
		button.setText("确定");

		final Label label_2 = new Label(composite, SWT.NONE);
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_2.setBounds(286, 65, 120, 17);

		final Label label_3 = new Label(composite, SWT.NONE);
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_3.setBounds(286, 109, 120, 17);

		final Label label_4 = new Label(composite, SWT.NONE);
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_4.setBounds(286, 149, 120, 17);

		// 点击重置按钮
		btnNewButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				text.setText("");
				text_1.setText("");
				text_2.setText("");
				combo.setText("");
			}
		});

		// 点击确定
		button.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				OperatorInfoDao operatorInfoDao = new OperatorInfoDao();
				String oname = text.getText().trim();
				String opwd = text_1.getText().trim();
				String otel = text_2.getText().trim();
				String ograde = combo.getText().trim().equals("收银员") ? "1" : "2";
				if (operatorInfoDao.add(opwd, ograde, oname, otel) > 0) {
					MessageDialog.openConfirm(shell, "确认提示", "信息完成");
					shell.close();				
				} else {
					MessageDialog.openError(shell, "错误提示", "错误");
					return;
				}
			}
		});

		// 电话号码获取
		text_2.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String txt2 = text_2.getText().trim();
				for (int i = 0; i < txt2.length(); i++) {
					Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
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

		// 员工姓名输入框
		text.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {// 获取焦点
				label_2.setText("");

			}

			public void focusLost(FocusEvent e) {// 失去焦点
				String txt1 = text.getText().trim();
				if (txt1 == null || "".equals(txt1)) {
					label_2.setText("请输入姓名");
				} else {
					label_2.setText("");
				}
			}
		});

		// 密码输入框
		text_1.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {// 获取焦点
				label_3.setText("");
			}

			public void focusLost(FocusEvent e) {// 失去焦点
				String txt1 = text_1.getText().trim();
				if (txt1 == null || "".equals(txt1)) {
					label_3.setText("请输入密码");
				} else {
					label_3.setText("");
				}
			}
		});
	}
}

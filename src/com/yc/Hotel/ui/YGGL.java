package com.yc.Hotel.ui;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.yc.Hotel.Dao.OperatorInfoDao;
import com.yc.utils.UICollection;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * 员工管理的操作（管理员）
 * 
 * @author Administrator
 *
 */
public class YGGL extends Composite {
	private Table table;
	private Text text;
	private Text text_1;
	private TableItem tableItem;
	private Combo combo;
	private Text text_2;
	

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public YGGL(final Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(composite, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);

		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));

		table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn.setWidth(196);
		tblclmnNewColumn.setText("员工编号");

		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_1.setWidth(223);
		tblclmnNewColumn_1.setText("姓名");

		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_2.setWidth(279);
		tblclmnNewColumn_2.setText("密码");

		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_4.setWidth(301);
		tblclmnNewColumn_4.setText("联系电话");

		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_3.setWidth(381);
		tblclmnNewColumn_3.setText("员工等级");
		TableItem tableItem = new TableItem(table, SWT.NONE);

		Composite composite_2 = new Composite(sashForm, SWT.NONE);

		Label lblNewLabel = new Label(composite_2, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		lblNewLabel.setBounds(41, 24, 52, 17);
		lblNewLabel.setText("用户名：");

		text = new Text(composite_2, SWT.BORDER | SWT.READ_ONLY);
		text.setBounds(99, 23, 125, 23);

		Label lblNewLabel_1 = new Label(composite_2, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		lblNewLabel_1.setBounds(269, 25, 73, 17);
		lblNewLabel_1.setText("用户密码：");

		text_1 = new Text(composite_2, SWT.BORDER | SWT.READ_ONLY);
		text_1.setBounds(348, 24, 112, 23);

		Label lblNewLabel_2 = new Label(composite_2, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		lblNewLabel_2.setBounds(721, 24, 61, 17);
		lblNewLabel_2.setText("用户类型：");

		Button btnNewButton = new Button(composite_2, SWT.NONE);
		btnNewButton.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		btnNewButton.setBounds(956, 18, 89, 30);
		btnNewButton.setText("修改");

		Button btnNewButton_1 = new Button(composite_2, SWT.NONE);
		btnNewButton_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		btnNewButton_1.setBounds(1076, 18, 80, 30);
		btnNewButton_1.setText("新增员工");

		combo = new Combo(composite_2, SWT.NONE);
		combo.setItems(new String[] { "收银员", "管理员" });
		combo.setBounds(799, 24, 125, 23);

		Label label = new Label(composite_2, SWT.NONE);
		label.setBounds(504, 25, 61, 17);
		label.setText("联系电话：");

		text_2 = new Text(composite_2, SWT.BORDER);
		text_2.setBounds(571, 24, 119, 23);
		
		Button btnNewButton_2 = new Button(composite_2, SWT.NONE);
		btnNewButton_2.setBounds(1190, 18, 80, 27);
		btnNewButton_2.setText("更新");
		sashForm.setWeights(new int[] { 356, 83 });
		UICollection.addOperaterInfo = new AddOperaterInfo();
		showData();
		
		table.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				changeColumnWidth();
			}
		});

		// 鼠标在表格里面时，锁定某一行，并在下方控件中输出
		table.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				TableItem[] tab = table.getSelection();
				if (tab != null && tab.length > 0) {
					combo.setText(tab[0].getText(4));
					text_1.setText(tab[0].getText(2));
					text.setText(tab[0].getText(1));
					text_2.setText(tab[0].getText(3));
				}
			}
		});
			
		// 点击新增员工信息
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {			
				UICollection.addOperaterInfo.open();
				showData();
				}
		});
		
		//点击修改
		btnNewButton.addSelectionListener(new SelectionAdapter() {			
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem tableItem = table.getSelection()[0];
					String opid = tableItem.getText(0).trim();
					String otel = text_2.getText().trim();
					String ograde = combo.getText().trim().equals("管理员") ? "2" : "1";
					if (OperatorInfoDao.update(otel, ograde,opid) > 0) {
						MessageDialog.openInformation(parent.getShell(), "成功提示", "客户信息修改成功...");					
						showData();
					} else {
						MessageDialog.openError(parent.getShell(), "失败提示", "客户信息修改失败...");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
	}
	
	// 表中显示数据
	public void showData() {
		table.removeAll();
		OperatorInfoDao operatorInfoDao = new OperatorInfoDao();
		List<Map<String, String>> list = operatorInfoDao.find();
		if (list != null && list.size() > 0) {
			TableItem ti;
			for (Map<String, String> map : list) {
				ti = new TableItem(table, SWT.NONE);
				ti.setText(new String[] { map.get("opid"), map.get("oname"), "*****", map.get("otel"),
						map.get("ograde").equals("2")?"管理员":"收银员" });
			}
		}
	}

	// 修改表格宽度
	public void changeColumnWidth() {
		TableColumn[] cols = table.getColumns();
		int width = (table.getSize().x) / (cols.length);
		for (int i = 0; i < cols.length; i++) {
			cols[i].setWidth(width);
		}
	}

	
	protected void checkSubclass() {
		
	}
}

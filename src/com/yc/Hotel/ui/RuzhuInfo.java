package com.yc.Hotel.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;

import com.yc.Hotel.Dao.RuzhuInfoDao;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
/**
 * 对入住信息的操作
 * @author Administrator
 *
 */
public class RuzhuInfo extends Composite {
	private Table table;
	private Text text;
	private Combo combo;
	private RuzhuInfoDao chack = new RuzhuInfoDao();

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public RuzhuInfo(Composite parent, int style) {
		super(parent, style);

		setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);

		Composite composite = new Composite(sashForm, SWT.NONE);

		combo = new Combo(composite, SWT.NONE);
		combo.setItems(new String[] { "全部", "房号", "顾客名" });
		combo.setBounds(181, 48, 136, 25);

		text = new Text(composite, SWT.BORDER);
		text.setBounds(389, 48, 127, 30);

		Button btnNewButton = new Button(composite, SWT.NONE);

		btnNewButton.setText("查询");
		btnNewButton.setBounds(581, 48, 114, 34);

		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));

		table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnNewColumn_5 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_5.setWidth(100);
		tblclmnNewColumn_5.setText("订单号");

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn.setText("顾客");
		tblclmnNewColumn.setWidth(220);

		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_1.setWidth(200);
		tblclmnNewColumn_1.setText("房号");

		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_2.setWidth(192);
		tblclmnNewColumn_2.setText("入住时间");

		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_3.setWidth(195);
		tblclmnNewColumn_3.setText("退房时间");

		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_4.setWidth(424);
		tblclmnNewColumn_4.setText("结账状态");
		sashForm.setWeights(new int[] {112, 313});
		// 表格自动定义列宽
		table.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				changeColumnWidth();
			}
		});
		// 查询按钮
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if ("房号".equals(combo.getText().trim())) {
					rnoIDshowdata();
				} else if ("顾客名".equals(combo.getText().trim())) {
					cnameshowdata();
				} else if ("全部".equals(combo.getText().trim())) {
					reservationshowdata();
				}
			}
		});
	}
	
	public void changeColumnWidth() {
		TableColumn[] cols = table.getColumns();
		cols[0].setWidth(100);
		int width = (table.getSize().x - 100) / (cols.length - 1);
		for (int i = 1; i < cols.length; i++) {
			cols[i].setWidth(width);
		}
	}

	// 房号查找
	public void rnoIDshowdata() {
		table.removeAll();
		String room_ID = text.getText().trim();
		String rno = null;
		String typeno = null;
		List<Map<String, String>> List = chack.roomIDfind(room_ID);

		for (Map<String, String> map : List) {

			if (List != null && List.size() > 0) {
				TableItem ti;

				ti = new TableItem(table, SWT.NONE);
				ti.setText(new String[] { map.get("orderno"), map.get("cname"), map.get("rno"),
						String.valueOf(map.get("date_in")), String.valueOf(map.get("date_out")),
						String.valueOf(map.get("bill_state")).equals("1") ? "结账" : "未结账" });
			}
		}
	}

	// 当前客户名字查找
	public void cnameshowdata() {
		table.removeAll();
		String cname = text.getText().trim();
		String cname1 = null;
		List<Map<String, String>> List = chack.cnamefind(cname);

		for (Map<String, String> map : List) {
			if (List != null && List.size() > 0) {
				TableItem ti;
				ti = new TableItem(table, SWT.NONE);
				ti.setText(new String[] { map.get("orderno"), map.get("cname"), map.get("rno"),
						String.valueOf(map.get("date_in")), String.valueOf(map.get("date_out")),
						String.valueOf(map.get("bill_state")).equals("1") ? "结账" : "未结账" });
			}
		}
	}

	// 查询全部
	public void reservationshowdata() {
		table.removeAll();
		List<Map<String, String>> List = chack.reservationfind();
		for (Map<String, String> map : List) {
			if (List != null && List.size() > 0) {
				TableItem ti;
				ti = new TableItem(table, SWT.NONE);
				ti.setText(new String[] { map.get("orderno"), map.get("cname"), map.get("rno"),
						String.valueOf(map.get("date_in")), String.valueOf(map.get("date_out")),
						String.valueOf(map.get("bill_state")).equals("1") ? "结账" : "未结账" });
			}
		}
	}
}

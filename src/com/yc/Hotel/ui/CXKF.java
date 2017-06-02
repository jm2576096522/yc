package com.yc.Hotel.ui;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.yc.Hotel.Dao.ChackInfoDao;

import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * 查询客房预定信息
 * 
 * @author Administrator
 *
 */
public class CXKF extends Composite {
	private Table table;
	private Text text;
	private ChackInfoDao chack = new ChackInfoDao();

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public CXKF(final Composite parent, int style) {
		super(parent, style);

		setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(composite, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);

		Composite composite_2 = new Composite(sashForm, SWT.NONE);

		text = new Text(composite_2, SWT.BORDER);
		text.setBounds(173, 34, 168, 23);

		Button btnNewButton = new Button(composite_2, SWT.NONE);

		btnNewButton.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		btnNewButton.setBounds(383, 31, 80, 27);
		btnNewButton.setText("查询");

		Combo combo = new Combo(composite_2, SWT.NONE);

		final Combo combo_1 = new Combo(composite_2, SWT.NONE);
		combo_1.add("查询全部");
		combo_1.add("房号");
		combo_1.add("当前客户名字");
		combo_1.setBounds(40, 34, 97, 32);

		Button button = new Button(composite_2, SWT.NONE);

		button.setBounds(487, 31, 80, 27);
		button.setText("新增预订");
		// 查询按钮
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if ("房号".equals(combo_1.getText().trim())) {
					rnoIDshowdata();

				} else if ("当前客户名字".equals(combo_1.getText().trim())) {
					cnameshowdata();
				} else if ("查询全部".equals(combo_1.getText().trim())) {
					reservationshowdata();
				}
			}

		});

		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));

		table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION | SWT.HIDE_SELECTION);

		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn.setWidth(50);
		tblclmnNewColumn.setText("房号");

		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_1.setWidth(91);
		tblclmnNewColumn_1.setText("房间类型");

		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(60);
		tableColumn.setText("楼层");

		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_3.setWidth(73);
		tblclmnNewColumn_3.setText("住宿价格");

		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_2.setWidth(102);
		tblclmnNewColumn_2.setText("入住情况");

		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_4.setWidth(116);
		tblclmnNewColumn_4.setText("预订客户");

		TableColumn tblclmnNewColumn_5 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_5.setWidth(100);
		tblclmnNewColumn_5.setText("预入住时间");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setText("预离时间");
		tableColumn_1.setWidth(100);
		sashForm.setWeights(new int[] { 193, 687 });

		table.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				changeColumnWidth();
			}
		});
		// 新增预订
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Reservation reservation = new Reservation();
				reservation.open();
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
		String roomname = null;
		List<Map<String, String>> List = chack.roomIDfind(room_ID);

		for (Map<String, String> map : List) {

			if (List != null && List.size() > 0) {

				TableItem ti;
				typeno = String.valueOf(map.get("typeno"));
				List<Map<String, String>> List1 = chack.roomname(typeno);
				for (Map<String, String> map1 : List1) {
					roomname = String.valueOf(map1.get("typename"));
				}
				System.out.println(roomname);
				ti = new TableItem(table, SWT.NONE);
				ti.setText(new String[] { String.valueOf(map.get("rno")), roomname,
						String.valueOf(map.get("rno")).substring(0, 1), String.valueOf(map.get("price")),
						String.valueOf(map.get("order_state").equals("1") ? "预订" : "入住"),
						String.valueOf(map.get("cname")), String.valueOf(map.get("order_in")),
						String.valueOf(map.get("order_out")) });
			}
		}
	}

	// 当前客户名字查找
	public void cnameshowdata() {
		table.removeAll();
		String cname = text.getText().trim();
		String cname1 = null;
		String roomname = null;
		String typeno = null;
		List<Map<String, String>> List = chack.cnamefind(cname);
		System.out.println(List);
		for (Map<String, String> map : List) {
			if (List != null && List.size() > 0) {
				TableItem ti;
				typeno = String.valueOf(map.get("typeno"));
				List<Map<String, String>> List1 = chack.roomname(typeno);
				for (Map<String, String> map1 : List1) {
					roomname = String.valueOf(map1.get("typename"));
				}
				System.out.println(roomname);
				ti = new TableItem(table, SWT.NONE);
				ti.setText(new String[] { String.valueOf(map.get("rno")), roomname,
						String.valueOf(map.get("rno")).substring(0, 1), String.valueOf(map.get("price")),
						String.valueOf(map.get("order_state").equals("1") ? "预订" : "入住"),
						String.valueOf(map.get("cname")), String.valueOf(map.get("order_in")),
						String.valueOf(map.get("order_out")) });
			}
		}
	}

	// 查询全部
	public void reservationshowdata() {
		table.removeAll();
		String reservation = text.getText().trim();
		String reservation1 = null;
		String roomname = null;
		String typeno = null;
		List<Map<String, String>> List = chack.reservationfind(reservation);
		for (Map<String, String> map : List) {
			if (List != null && List.size() > 0) {
				typeno = String.valueOf(map.get("typeno"));
				List<Map<String, String>> List1 = chack.roomname(typeno);
				for (Map<String, String> map1 : List1) {
					roomname = map1.get("typename");
				}
				System.out.println(roomname);
				TableItem ti;
				ti = new TableItem(table, SWT.NONE);
				ti.setText(new String[] { String.valueOf(map.get("rno")), roomname,
						String.valueOf(map.get("rno")).substring(0, 1), String.valueOf(map.get("price")),
						String.valueOf(map.get("order_state").equals("1") ? "预订" : "入住"),
						String.valueOf(map.get("cname")), String.valueOf(map.get("order_in")),
						String.valueOf(map.get("order_out")) });
			}
		}
	}
}

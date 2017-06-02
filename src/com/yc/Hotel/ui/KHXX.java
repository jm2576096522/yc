package com.yc.Hotel.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;

import com.yc.Hotel.Dao.ChackInfoDao;
import com.yc.Hotel.Dao.CustomerInfoDao;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * 对客户信息的操作
 * 
 * @author Administrator
 *
 */
public class KHXX extends Composite {
	private Table table;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Combo combo;
	private Button mybutton;
	private List<Button>btns=new ArrayList<Button>();
	private TableItem tableItem;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public KHXX(final Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);

		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm_1 = new SashForm(composite, SWT.NONE);
		sashForm_1.setOrientation(SWT.VERTICAL);

		Composite composite_2 = new Composite(sashForm_1, SWT.NONE);

		Label lblNewLabel = new Label(composite_2, SWT.NONE);
		lblNewLabel.setBounds(10, 30, 108, 17);
		lblNewLabel.setText("请输入顾客证件号：");

		text = new Text(composite_2, SWT.BORDER);
		text.setBounds(124, 27, 154, 23);

		Label lblNewLabel_1 = new Label(composite_2, SWT.NONE);
		lblNewLabel_1.setBounds(294, 30, 95, 17);
		lblNewLabel_1.setText("请输入顾客姓名：");

		text_1 = new Text(composite_2, SWT.BORDER);
		text_1.setBounds(395, 26, 95, 23);

		Button button = new Button(composite_2, SWT.NONE);
		button.setBounds(724, 25, 60, 27);
		button.setText("搜索");

		Label label = new Label(composite_2, SWT.NONE);
		label.setBounds(519, 30, 60, 17);
		label.setText("顾客类型：");

		combo = new Combo(composite_2, SWT.NONE);
		combo.setItems(new String[] { "全部", "1_普通顾客", "2_vip会员", "3_高级会员", "4_至尊会员" });
		combo.setBounds(585, 26, 108, 25);
		combo.select(0);

		Composite composite_3 = new Composite(sashForm_1, SWT.NONE);
		composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));

		table = new Table(composite_3, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		table.setLocation(0, 0);
		table.setSize(833, 330);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn.setWidth(55);
		tblclmnNewColumn.setText("序号");

		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(60);
		tableColumn.setText("顾客编号");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(70);
		tableColumn_1.setText("顾客姓名");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(63);
		tableColumn_2.setText("性别");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(151);
		tableColumn_3.setText("联系电话");

		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(164);
		tableColumn_4.setText("证件号");

		TableColumn tableColumn_7 = new TableColumn(table, SWT.CENTER);
		tableColumn_7.setWidth(103);
		tableColumn_7.setText("邮箱");

		TableColumn tblclmnVip = new TableColumn(table, SWT.CENTER);
		tblclmnVip.setWidth(146);
		tblclmnVip.setText("顾客类型");

		TableItem tableItem = new TableItem(table, SWT.NONE);

		Menu menu = new Menu(table);
		table.setMenu(menu);

		MenuItem mntmNewItem_2 = new MenuItem(menu, SWT.NONE);
		mntmNewItem_2.setText("删除");
		sashForm_1.setWeights(new int[] { 71, 390 });
		Composite composite_1 = new Composite(sashForm, SWT.NONE);

		Label lblNewLabel_2 = new Label(composite_1, SWT.NONE);
		lblNewLabel_2.setBounds(35, 17, 52, 17);
		lblNewLabel_2.setText("邮  箱：");

		Button btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.setBounds(517, 12, 60, 27);
		btnNewButton.setText("修改");

		Label label_2 = new Label(composite_1, SWT.NONE);
		label_2.setBounds(259, 17, 61, 17);
		label_2.setText("联系电话：");

		text_2 = new Text(composite_1, SWT.BORDER);
		text_2.setBounds(94, 14, 134, 23);

		text_3 = new Text(composite_1, SWT.BORDER);
		text_3.setBounds(331, 14, 154, 23);
		sashForm.setWeights(new int[] { 465, 51 });

		// 鼠标在表格里面时，锁定某一行，并在下方控件中输出
		table.addMouseListener(new MouseAdapter() {

			public void mouseDown(MouseEvent e) {
				TableItem[] tab = table.getSelection();
				if (tab != null && tab.length > 0) {
					combo.setText(tab[0].getText(7));
					text_2.setText(tab[0].getText(6));
					text_3.setText(tab[0].getText(4));
				}
			}
		});

		// 点击确定按钮
		btnNewButton.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				try {
					TableItem tableItem = table.getSelection()[0];
					String cid = tableItem.getText(1).trim();
					String email = text_2.getText().trim();
					String ctel = text_3.getText().trim();
					String vid = combo.getText().trim();// 商品类型1001_
					vid = vid.substring(0, vid.indexOf("_"));
					if (CustomerInfoDao.update(ctel, vid, email, cid) > 0) {
						MessageDialog.openInformation(parent.getShell(), "成功提示", "客户信息修改成功...");
						ShowAll();
						text_2.setText("");
						text_3.setText("");
						combo.setText("");
					} else {
						MessageDialog.openError(parent.getShell(), "失败提示", "客户信息修改失败...");
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

	
		// 点击查询按钮
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				String card_id = text.getText().trim();
				String cname = text_1.getText().trim();
				String ctype = combo.getText().trim();
				if (ctype.contains("_")) {
					ctype = ctype.substring(0, ctype.indexOf("_"));
				} else {
					ctype = null;
				}
				
				CustomerInfoDao customerInfoDao = new CustomerInfoDao();
				List<Map<String, String>> list = customerInfoDao.find(card_id, cname, ctype);
				
				
				table.removeAll();
				
				if(btns!=null&&btns.size()>0){
					for(Button bt:btns){
						bt.dispose();
					}
				}
				btns.clear();
				
				TableItem tableItem;
				
				TableEditor editor;//表格编辑器
				int i=1;
				for (Map<String, String> map : list) {	
					editor= new TableEditor(table);
					tableItem = new TableItem(table, SWT.NONE);
					tableItem.setText(new String[] {"",map.get("cid"), map.get("cname"), map.get("csex"),
							map.get("ctel"), map.get("card_id"), map.get("email"), map.get("vgrade")});				
				
					mybutton=new Button(table, SWT.CHECK);//创建一个多选按钮
					mybutton.setText(String.valueOf(i));//给这个多选按钮一个编号
					mybutton.setData("id", map.get("cid"));
					editor.grabHorizontal=true;
					editor.setEditor(mybutton, tableItem, 0);
					i++;
					
					mybutton.addSelectionListener(new MySelectionAdapter(mybutton));
					btns.add(mybutton);//将所有生成的多选按钮存起来
				}
			}
		});
		
		//选择删除
		mntmNewItem_2.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				TableItem[] tab=table.getSelection();
				CustomerInfoDao customerInfoDao = new CustomerInfoDao();
				if(tab==null || tab.length<=0){
					MessageDialog.openError(getShell(), "错误","此行没有数据");
				}else{
					int i=0;
					StringBuffer str=new StringBuffer();
					for(i=0;i<tab.length-1;i++){
						str.append(tab[i].getText(1)+",");
					}
					str.append(tab[i].getText(1));
					
					if(MessageDialog.openConfirm(getShell(), "警告", "你要删除这些数据吗？")){
						if(customerInfoDao.del(str.toString())>0){
							ShowAll();
						}
					}	
				}
			}
		});
		
		
		
		// 一旦表格的宽度发生改变
		table.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				ChangeColumnWidth();
			}
		});

	}
	
	class MySelectionAdapter extends SelectionAdapter{
		private Button btn;
		
		public MySelectionAdapter(Button btn) {
			this.btn=btn;
		}
		public void widgeSelection(SelectionEvent e){
			if(btn.getSelection()){
				table.select(Integer.parseInt(btn.getText().trim())+1);
			}else{
				table.deselect(Integer.parseInt(btn.getText().trim())-1);
			}
		}
	}
	

	// 显示所有顾客数据
	public void ShowAll() {
		table.removeAll();
		CustomerInfoDao customerInfoDao = new CustomerInfoDao();
		List<Map<String, String>> list = customerInfoDao.All();
		
		if(btns!=null&&btns.size()>0){
			for(Button bt:btns){
				bt.dispose();
			}
		}
		btns.clear();
		
		TableItem tableItem;
		
		TableEditor editor;//表格编辑器
		int i=1;
		for (Map<String, String> map : list) {	
			editor= new TableEditor(table);
			tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(new String[] {"",map.get("cid"), map.get("cname"), map.get("csex"),
					map.get("ctel"), map.get("card_id"), map.get("email"), map.get("vgrade")});				
		
			mybutton=new Button(table, SWT.CHECK);//创建一个多选按钮
			mybutton.setText(String.valueOf(i));//给这个多选按钮一个编号
			mybutton.setData("id", map.get("cid"));
			editor.grabHorizontal=true;
			editor.setEditor(mybutton, tableItem, 0);
			i++;
			
			mybutton.addSelectionListener(new MySelectionAdapter(mybutton));
			btns.add(mybutton);//将所有生成的多选按钮存起来
		}
		
	}

	// 修改表格的宽度
	public void ChangeColumnWidth() {
		TableColumn[] cols = table.getColumns();// 获取表格的字段数
		int width = (table.getSize().x) / (cols.length);// 用剩余的长度除以减去1的列数（字段数）
		for (int i = 0; i < cols.length; i++) {
			cols[i].setWidth(width);
		}
	}

	
	protected void checkSubclass() {
	}
}

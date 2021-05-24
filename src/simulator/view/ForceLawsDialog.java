package simulator.view;

import javax.swing.JLabel;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import org.json.JSONObject;
import simulator.control.Controller;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.FlowLayout;


public class ForceLawsDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	private int _status;
	private Controller _ctrl;	
	private List<JSONObject> _info;

	private JPanel mPanel;
	
	private JPanel buttonPanel;
	private JButton buttonCancel;
	private JButton buttonContinue;
	
	private JTable table;
	private LawsTableModel tab;
	private JScrollPane scrl;	
	private JPanel lawPanel;
	private JComboBox<String> combo;
	
	
	public ForceLawsDialog(Frame f, List<JSONObject> info, Controller ctrl)
	{
		super(f, true);
		
		setTitle("Force Laws Selection ");
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		
		_status = 0;
		_info = info;
		_ctrl = ctrl;		
		
		initGUI();
	}

	
	private void initGUI()
	{
		mPanel = new JPanel();
		mPanel.setLayout(new BoxLayout(mPanel, BoxLayout.PAGE_AXIS));
		
		setPreferredSize(new Dimension(950, 400));
		
		buttonOptions();
		
		lawPanel = new JPanel(new FlowLayout());
		
		combo = new JComboBox<String>();
		
		for (int i = 0; i < _info.size(); i++)		
		{
			combo.addItem(_info.get(i).getString("desc"));
		}
		
		combo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				tab.setInfo(_ctrl.getForceLawsInfo().get(combo.getSelectedIndex()));	//Cambia la información según lo seleccionado
			}
		});
		
		tab = new LawsTableModel(_ctrl.getForceLawsInfo().get(combo.getSelectedIndex()));
		table = new JTable(tab);
		scrl = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		lawPanel.add(combo);
		
		mPanel.add(new JLabel("Select a force law and provide values for the parameters in the Value column"
				+ "(default values are used for parameters with no value)"), null);
		mPanel.add(scrl);
		mPanel.add(lawPanel);
		mPanel.add(buttonPanel);
		add(mPanel);
	}
	
	private void buttonOptions()
	{
		buttonPanel = new JPanel(new FlowLayout());

		buttonContinue = new JButton("Continue");
		buttonCancel = new JButton("Cancel");
		
		buttonContinue.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (combo.getSelectedItem() != null)
				{
					_status = 1;
					ForceLawsDialog.this.setVisible(false);
				}
				else JOptionPane.showMessageDialog(ForceLawsDialog.this.getParent(), "The force law was not selected");
			}
		});
		
		buttonCancel.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				_status = 0;
				ForceLawsDialog.this.setVisible(false);
			}
		});
		
		buttonPanel.add(buttonCancel);
		buttonPanel.add(buttonContinue);
	}
	
	public JSONObject getForceLaw()
	{
		JSONObject o = new JSONObject();
		
		JSONObject force = _ctrl.getForceLawsInfo().get(combo.getSelectedIndex());
		
		String forceString = "{";
		String keyString;
		String valueString;
		
		for (int i = 0; i < tab.getRowCount(); i++)
		{
			keyString = (String) tab.getValueAt(i, 0);
			
			if (i != 0 && forceString != "{")
			{
				forceString += ",";
			}
			
			valueString = (String) tab.getValueAt(i, 1);
			
			if (valueString != "")
			{
				forceString += keyString + ":" + valueString;
			}
		}
		
		forceString += "}";
		
		o.put("type", force.getString("type"));
		o.put("data", new JSONObject(forceString));
		o.put("desc", force.getString("desc"));
		
		return o;
	}
	
	public int open()
	{
		pack();
		setVisible(true);
		return _status;
	}

	public String toString()
	{
		return lawPanel.toString();
	}
}

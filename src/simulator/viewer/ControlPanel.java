package simulator.viewer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.json.JSONObject;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class ControlPanel extends JPanel implements SimulatorObserver, ActionListener
{
	private static final long serialVersionUID = 1L;
	private Controller _ctrl;
	private boolean _stopped;

	private JButton buttonArchive;
	private JButton buttonForces;
	private JButton buttonPlay;
	private JButton buttonStop;
	private JButton buttonExit;

	private JSpinner steps;
	private JTextField time;

	private LawsTableModel tab;

	ControlPanel(Controller ctrl) 
	{
		_ctrl = ctrl;
		_stopped = true;		

		initGUI();
		_ctrl.addObserver(this);
	}

	private void initGUI()
	{
		this.setLayout(new BorderLayout());
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		buttonArchive = createButton(new ImageIcon("resources/icons/open.png"), "Carga el fichero seleccionado");
		p1.add(buttonArchive);

		buttonForces = createButton(new ImageIcon("resources/icons/physics.png"), "Cambia las leyes de fuerza");
		p1.add(buttonForces);

		buttonPlay = createButton(new ImageIcon("resources/icons/run.png"), "Inicia la simulación");
		p1.add(buttonPlay);

		buttonStop = createButton(new ImageIcon("resources/icons/stop.png"), "Detiene la simulación");
		p1.add(buttonStop);


		p1.add(new JLabel(" Steps: "));
		this.steps = new JSpinner();
		p1.add(steps);
		steps.setPreferredSize(new Dimension(70, 35));


		Font font1 = steps.getFont().deriveFont(Font.PLAIN, 15f);
		steps.setFont(font1);

		p1.add(new JLabel(" Delta-Time: "));

		this.time = new JTextField();
		p1.add(time);
		time.setPreferredSize(new Dimension(75, 40));

		Font font2 = time.getFont().deriveFont(Font.PLAIN, 15f);
		time.setFont(font2);

		buttonExit = createButton(new ImageIcon("resources/icons/exit.png"), "Cierra la simulación");
		p2.add(buttonExit);
		
		this.add(p1, BorderLayout.WEST);
		this.add(p2, BorderLayout.EAST);
		
	}

	private void run_sim(int n) 
	{
		if ( n > 0 && !_stopped ) 
		{
			try 
			{
				_ctrl.run(1); 
			} 
			catch (Exception e) 
			{	
				buttonArchive.setEnabled(true);
				buttonForces.setEnabled(true);
				buttonExit.setEnabled(true);
				_stopped = true;
				JOptionPane.showOptionDialog(null, "Se ha producido un error durante la ejecución", "ERROR:", JOptionPane.CLOSED_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

				return;
			}

			SwingUtilities.invokeLater( new Runnable() 
			{
				@Override
				public void run() 
				{
					run_sim(n-1);
				}
			});
		} 
		else 
		{
			buttonArchive.setEnabled(true);
			buttonForces.setEnabled(true);
			buttonExit.setEnabled(true);
			_stopped = true;
		}
	}

	public JButton createButton(Icon route, String toolTip) 
	{
		JButton button  = new JButton();
		button.setIcon(route);
		button.setToolTipText(toolTip);
		button.addActionListener(this);
		return button;
	}

	private void quit()
	{
		int n = JOptionPane.showOptionDialog(null, "Are you sure to quit?", "Quit",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (n == 0) {System.exit(0);}
	}

	private void archive()
	{
		JFileChooser fc = new JFileChooser();

		int v = fc.showOpenDialog(null);
		if (v == JFileChooser.APPROVE_OPTION)
		{
			File selectedFile = fc.getSelectedFile();
			_ctrl.reset();
			try
			{
				InputStream in = new FileInputStream(selectedFile);
				_ctrl.loadBodies(in);
			}
			catch (FileNotFoundException e)
			{
				JOptionPane.showMessageDialog(null, "Error al cargar el archivo");
			}
		}
	}

	private void forces() 
	{
		List<JSONObject> list = _ctrl.getForceLawsInfo();		
		JComboBox<String> combo = new JComboBox<String>();

		for (int i = 0; i < list.size(); i++)		
		{
			combo.addItem(list.get(i).getString("desc"));
		}

		JPanel panel = new JPanel();
		panel.setBounds(80, 20, 80, 170);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		combo.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				tab.setInfo(_ctrl.getForceLawsInfo().get(combo.getSelectedIndex()));
			}
		});

		tab = new LawsTableModel(_ctrl.getForceLawsInfo().get(combo.getSelectedIndex()));

		JTable table = new JTable(tab);

		panel.add(new JLabel("Select a force law and provide values for the parameters in the Value column "
				+ "(default values are used for parameters with no value)"), null);
		panel.add(new JScrollPane(table));
		panel.add(combo);

		int option = JOptionPane.showOptionDialog(null, panel, "Force Laws Selection", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (option == JOptionPane.CANCEL_OPTION)
		{

		}
		else if (option == JOptionPane.OK_OPTION)
		{			
			JSONObject o = new JSONObject();
			o.put("type", _ctrl.getForceLawsInfo().get(combo.getSelectedIndex()).get("type"));
			o.put("data", tab.selectedForce());
			_ctrl.setForceLaws(o);
		}
	}

	private void play()
	{
		try
		{
			_ctrl.setDeltaTime(Double.parseDouble(time.getText()));
			
			try
			{
				int s = Integer.parseInt(steps.getValue().toString());
				if (s <= 0)
				{
					throw new Exception("Por favor, introduzca un número de pasos (mayor que cero)");
				}
				buttonArchive.setEnabled(false);
				buttonExit.setEnabled(false);
				buttonForces.setEnabled(false);

				_stopped = false;
				run_sim(s);
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Por favor, introduzca un delta-time correcto", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void stop()
	{
		buttonArchive.setEnabled(true);
		buttonExit.setEnabled(true);
		buttonForces.setEnabled(true);
		_stopped = true;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource().equals(buttonArchive))
		{
			archive();
		}
		else if (e.getSource().equals(buttonForces))
		{
			forces();
		}
		else if (e.getSource().equals(buttonPlay)) 
		{
			play();
		}
		else if (e.getSource().equals(buttonStop))
		{
			stop();
		}
		else if(e.getSource().equals(buttonExit))
		{
			quit();
		}
	}

	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc) 
	{
		this.time.setText(Double.toString(dt));
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc) 
	{

		this.time.setText(Double.toString(dt));
	}

	@Override
	public void onDeltaTimeChanged(double dt) 
	{

		this.time.setText(Double.toString(dt));
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {}
	@Override
	public void onAdvance(List<Body> bodies, double time){}
	@Override
	public void onForceLawsChanged(String fLawsDesc) {}
}

package simulator.viewer;

import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
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

	ControlPanel(Controller ctrl) 
	{
		_ctrl = ctrl;
		_stopped = true;		

		initGUI();
		_ctrl.addObserver(this);
	}

	private void initGUI()
	{
		buttonArchive = createButton(new ImageIcon("resources/icons/open.png"), "Carga el fichero seleccionado");
		this.add(buttonArchive);


		buttonForces = createButton(new ImageIcon("resources/icons/physics.png"), "Cambia las leyes de fuerza");
		this.add(buttonForces);

		buttonPlay = createButton(new ImageIcon("resources/icons/run.png"), "Inicia la simulación");
		this.add(buttonPlay);

		buttonStop = createButton(new ImageIcon("resources/icons/stop.png"), "Detiene la simulación");
		this.add(buttonStop);


		this.add(new JLabel(" Steps: "));
		this.steps = new JSpinner();
		this.add(steps);
		steps.setPreferredSize(new Dimension(70, 35));


		Font font1 = steps.getFont().deriveFont(Font.PLAIN, 15f);
		steps.setFont(font1);

		this.add(new JLabel(" Delta-Time: "));

		this.time = new JTextField();
		this.add(time);
		time.setPreferredSize(new Dimension(75, 40));

		Font font2 = time.getFont().deriveFont(Font.PLAIN, 15f);
		time.setFont(font2);

		buttonExit = createButton(new ImageIcon("resources/icons/exit.png"), "Cierra la simulación");
		this.add(buttonExit);
	}

	private void run_sim(int n) 
	{
		if ( n>0 && !_stopped ) 
		{
			try 
			{
				_ctrl.run(1); 

				System.out.println("SI");
			} 
			catch (Exception e) 
			{
				buttonArchive.setEnabled(false);
				buttonForces.setEnabled(false);
				buttonPlay.setEnabled(false);
				buttonStop.setEnabled(false);
				buttonExit.setEnabled(false);
				_stopped = true;

				System.out.println(" CASI");

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

	private void forces() // ARREGLA ESTA MIERDA
	{        
		JComboBox<String> combo = new JComboBox<String>();

		List<JSONObject> list = _ctrl.getForceLawsInfo();
		String[] forceLaws = new String[list.size()];
		String[] forceLawsData = new String[list.size()];

		for (int i = 0; i < forceLaws.length; i++)
		{
			forceLaws[i] = list.get(i).getString("desc");
			forceLawsData[i] = list.get(i).getString("data");
		}

		for (int i = 0; i < forceLaws.length; i++)
		{
			combo.addItem(forceLaws[i]);
		}
	
	}

	private void play()
	{
		buttonArchive.setEnabled(false);
		buttonExit.setEnabled(false);
		buttonForces.setEnabled(false);

		_stopped = true;

		_ctrl.setDeltaTime(Double.parseDouble(time.getText()));
		int s = Integer.parseInt(steps.getValue().toString());
		run_sim(s);
	}

	private void stop()
	{
		_stopped = false;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{

		if (e.getSource() == buttonArchive)
		{
			archive();
		}
		else if (e.getSource() == buttonForces) 
		{
			forces();
		}
		else if (e.getSource() == buttonPlay) 
		{
			play();
		}
		else if (e.getSource() == buttonStop)
		{
			stop();
		}
		else if(e.getSource() == buttonExit)
		{
			quit();
		}
	}

	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc) 
	{
		// TODO Auto-generated method stub

	}
	@Override
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc) 
	{
		// TODO Auto-generated method stub

	}
	@Override
	public void onBodyAdded(List<Body> bodies, Body b) 
	{
		// TODO Auto-generated method stub

	}
	@Override
	public void onAdvance(List<Body> bodies, double time)
	{
		// TODO Auto-generated method stub

	}
	@Override
	public void onDeltaTimeChanged(double dt) 
	{
		// TODO Auto-generated method stub

	}
	@Override
	public void onForceLawsChanged(String fLawsDesc) 
	{
		// TODO Auto-generated method stub

	}
}

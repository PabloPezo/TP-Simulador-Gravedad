package simulator.viewer;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class ControlPanel extends JPanel implements SimulatorObserver 
{
	private static final long serialVersionUID = 1L;
	private Controller _ctrl;
	private boolean _stopped;
	ControlPanel(Controller ctrl) 
	{
		_ctrl = ctrl;
		_stopped = true;
		initGUI();
		_ctrl.addObserver(this);
	}
	
	private void initGUI()
	{
		JFrame mainFrame = new JFrame("PhysicsSimulator");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel(new BorderLayout(5, 5));

		JToolBar toolBar = new JToolBar();
		mainPanel.add(toolBar, BorderLayout.PAGE_START);


<<<<<<< HEAD
		toolBar.add (createButton(new ImageIcon("resources/icons/open.png"), "ARCHIVOS", "Carga el fichero seleccionado"));
		toolBar.addSeparator();
		toolBar.add (createButton(new ImageIcon("resources/icons/physics.png"), "PHYSICS", "Cambia las leyes de fuerza"));
		toolBar.addSeparator();
		toolBar.add (createButton(new ImageIcon("resources/icons/run.png"), "RUN", "Inicia la simulación"));
		toolBar.add (createButton(new ImageIcon("resources/icons/stop.png"), "STOP", "Detiene la simulación"));
=======
<<<<<<< HEAD
		mainPanel.add (createButton(new ImageIcon("resources/icons/open.png"), "ARCHIVOS"), BorderLayout.WEST);//, BorderLayout.PAGE_START);
		
		mainPanel.add (Box.createHorizontalGlue());
		
		mainPanel.add (createButton(new ImageIcon("resources/icons/physics.png"), "PHYSICS"), BorderLayout.WEST);//, BorderLayout.CENTER);
		mainPanel.add (createButton(new ImageIcon("resources/icons/run.png"), "RUN"));//, BorderLayout.WEST);//, BorderLayout.CENTER);
		mainPanel.add (createButton(new ImageIcon("resources/icons/stop.png"), "STOP"));//, BorderLayout.EAST);//, BorderLayout.CENTER);
		mainPanel.add (createButton(new ImageIcon("resources/icons/exit.png"), "EXIT"));//, BorderLayout.CENTER);
=======
		toolBar.add (createButton(new ImageIcon("resources/icons/open.png"), "ARCHIVOS"));
		
		
		JToolBar toolBar2 = new JToolBar();
		toolBar.add(toolBar2, BorderLayout.PAGE_START);
>>>>>>> c704c283461f16b4a1713b7ea0a1b72ffa855072

		toolBar.add(new JLabel(" Steps: "));
		JSpinner steps = new JSpinner();
		toolBar.add(steps);
		
		toolBar.add(new JLabel(" Delta-Time: "));
		JTextField time = new JTextField();
		toolBar.add(time);
		
		toolBar.add(new JLabel("                                                                                      "));
		
<<<<<<< HEAD
		toolBar.add(Box.createHorizontalGlue());
		toolBar.add (createButton(new ImageIcon("resources/icons/exit.png"), "EXIT", "Cierra la simulación"));
=======
		toolBar2.add (createButton(new ImageIcon("resources/icons/physics.png"), "PHYSICS"));
		toolBar2.add (createButton(new ImageIcon("resources/icons/run.png"), "RUN"));
		toolBar2.add (createButton(new ImageIcon("resources/icons/stop.png"), "STOP"));
		toolBar2.add (createButton(new ImageIcon("resources/icons/exit.png"), "EXIT"));
>>>>>>> a84ff81d0800a287c895e04822a666668763e45b
>>>>>>> c704c283461f16b4a1713b7ea0a1b72ffa855072
		
		mainFrame.add(mainPanel);
		mainFrame.setBounds(400, 300, 800, 90);
		
		
		mainFrame.setVisible(true);
		mainPanel.setVisible(true);
	}
	
	@SuppressWarnings("unused")
	private void run_sim(int n) 
	{
		if ( n>0 && !_stopped ) 
		{
			try {
				//_ctrl.run(1); // Modificar o hace run override
			} catch (Exception e) 
			{
				// TODO show the error in a dialog box
				// TODO enable all buttons
				_stopped = true;
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
			_stopped = true;
			// TODO enable all buttons
		}
	}
	
//	
//	@Override
//	public void actionPerformed(ActionEvent e)
//	{
//		if(e.getSource == toolBar.)
//	}
	
	private JButton createButton(Icon route, String caption, String toolTip) 
	{
		JButton button  = new JButton();
		button.setIcon(route);
		button.setToolTipText(toolTip);
		return button;
	}
	
	// Cositas que me pone solo
	
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
	
	public static void main(String[] args)
	{
		ControlPanel pepe = new ControlPanel(new Controller(null, null));
		pepe.initGUI();
	}
}
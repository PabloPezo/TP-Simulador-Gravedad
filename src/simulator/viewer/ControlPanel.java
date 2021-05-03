package simulator.viewer;

<<<<<<< HEAD
import java.util.List;

import javax.swing.JPanel;
=======
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
>>>>>>> be5a50f5afc0b646341e044c8883a2623f83225d
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
<<<<<<< HEAD
	
=======
		JFrame mainFrame = new JFrame("PhysicsSimulator");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel(new BorderLayout(5, 5));

		JToolBar toolBar = new JToolBar();
		mainPanel.add(toolBar, BorderLayout.PAGE_START);



		mainPanel.add (createButton(new ImageIcon("resources/icons/open.png"), "ARCHIVOS"), BorderLayout.WEST);//, BorderLayout.PAGE_START);
		
		mainPanel.add (Box.createHorizontalGlue());
		
		mainPanel.add (createButton(new ImageIcon("resources/icons/physics.png"), "PHYSICS"), BorderLayout.WEST);//, BorderLayout.CENTER);
		mainPanel.add (createButton(new ImageIcon("resources/icons/run.png"), "RUN"));//, BorderLayout.WEST);//, BorderLayout.CENTER);
		mainPanel.add (createButton(new ImageIcon("resources/icons/stop.png"), "STOP"));//, BorderLayout.EAST);//, BorderLayout.CENTER);
		mainPanel.add (createButton(new ImageIcon("resources/icons/exit.png"), "EXIT"));//, BorderLayout.CENTER);

		toolBar.add (createButton(new ImageIcon("resources/icons/open.png"), "ARCHIVOS"));
		
		
		JToolBar toolBar2 = new JToolBar();
		toolBar.add(toolBar2, BorderLayout.PAGE_START);

		
		
		toolBar2.add (createButton(new ImageIcon("resources/icons/physics.png"), "PHYSICS"));
		toolBar2.add (createButton(new ImageIcon("resources/icons/run.png"), "RUN"));
		toolBar2.add (createButton(new ImageIcon("resources/icons/stop.png"), "STOP"));
		toolBar2.add (createButton(new ImageIcon("resources/icons/exit.png"), "EXIT"));
		
		mainFrame.add(mainPanel);
		mainFrame.setBounds(400, 300, 800, 90);
		
		mainFrame.setVisible(true);
		mainPanel.setVisible(true);
>>>>>>> be5a50f5afc0b646341e044c8883a2623f83225d
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
	
<<<<<<< HEAD
=======
	private JButton createButton(Icon route, String caption) {
		JButton button  = new JButton();
		button.setIcon(route);
		//button.setAlignmentX(JButton.LEFT_ALIGNMENT);
		return button;
	}
	
>>>>>>> be5a50f5afc0b646341e044c8883a2623f83225d
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
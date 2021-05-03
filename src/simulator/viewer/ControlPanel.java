package simulator.viewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
		JPanel mainPanel = new JPanel(new BorderLayout());

		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(new BorderLayout());

		mainPanel.add (createButton(new ImageIcon("resources/icons/open.png"), "ARCHIVOS"), BorderLayout.WEST);//, BorderLayout.PAGE_START);
		mainPanel.add (createButton(new ImageIcon("resources/icons/physics.png"), "PHYSICS"));//, BorderLayout.CENTER);
		mainPanel.add (createButton(new ImageIcon("resources/icons/run.png"), "RUN"));//, BorderLayout.WEST);//, BorderLayout.CENTER);
		mainPanel.add (createButton(new ImageIcon("resources/icons/stop.png"), "STOP"));//, BorderLayout.EAST);//, BorderLayout.CENTER);
		mainPanel.add (createButton(new ImageIcon("resources/icons/exit.png"), "EXIT"), BorderLayout.EAST);//, BorderLayout.CENTER);
		
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
	
	private JButton createButton(Icon route, String caption) {
		JButton button  = new JButton();
		button.setIcon(route);
		button.setAlignmentX(JButton.LEFT_ALIGNMENT);
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
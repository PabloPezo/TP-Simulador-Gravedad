package simulator.viewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class BodiesInfo extends JPanel implements SimulatorObserver
{
	private static final long serialVersionUID = 1L;
	private List<Body> _bodies;
	private JTextArea text;
	
	public BodiesInfo(Controller _ctrl)
	{
		super();
		initGUI();
	//	_ctrl.addObserver(this);
	}
	
	private void initGUI()
	{
		JFrame mainFrame = new JFrame("PhysicsSimulator");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel(new BorderLayout());

		
		
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "Bodies", TitledBorder.LEFT, TitledBorder.TOP));
		text = new JTextArea();
		JScrollPane scroll = new JScrollPane(text);
		mainPanel.add(scroll);
		mainPanel.setVisible(true);
		mainFrame.setVisible(true);
	
	
	}
	
	private void update(List<Body> bodies)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				_bodies = bodies;
				text.setText(" ");
				
				for(int i = 0; i < bodies.size(); i++)
				{
					Body b = _bodies.get(i);
					text.append(b.getId() + "     " + b.getPosition() + "\n");
				}
			}
		});
	}

	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc) 
	{
		update(bodies);
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc) 
	{
		update(bodies);
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) 
	{
		update(bodies);
	}

	@Override
	public void onAdvance(List<Body> bodies, double time) 
	{
		update(bodies);
	}

	@Override
	public void onDeltaTimeChanged(double dt) {}

	@Override
	public void onForceLawsChanged(String fLawsDesc) {}
	
	public static void main(String[] args)
	{
		BodiesInfo pepe = new BodiesInfo(new Controller(null, null));
		pepe.initGUI();
	}

}

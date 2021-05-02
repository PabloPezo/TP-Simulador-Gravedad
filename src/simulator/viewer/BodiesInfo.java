package simulator.viewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class BodiesInfo extends JPanel implements SimulatorObserver
{
	private List<Body> bodies;
	private JTextArea text;
	
	public BodiesInfo(Controller _ctrl)
	{
		super();
		initGUI();
		_ctrl.addObserver(this);
	}
	
	private void initGUI()
	{
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), TitledBorder.LEFT, TitledBorder.TOP));
		text = new JTextArea();
		JScrollPane scroll = new JScrollPane(text);
		add(scroll);
	
	
	}
	
	private void update(List<Body> bodies)
	{
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run()
			{
				_bodies = bodies;
				text.setText(" ");
			}
		}
	}

	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onForceLawsChanged(String fLawsDesc) {
		// TODO Auto-generated method stub
		
	}
}

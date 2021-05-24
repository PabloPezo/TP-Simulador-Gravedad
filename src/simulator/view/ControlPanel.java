package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
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
	private JButton buttonArchive, buttonForces, buttonPlay;
	private JButton buttonStop, buttonExit;
	private JSpinner steps;
	private JSpinner.DefaultEditor editor;
	
	private JTextField time;
	private JFileChooser fc;
	private ForceLawsDialog fld;

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
		fc = new JFileChooser();
		fld = null;

		JToolBar toolBar = new JToolBar();
		this.add(toolBar, BorderLayout.PAGE_START);

		buttonArchive = createButton(new ImageIcon("resources/icons/open.png"), "Carga el fichero seleccionado");
		toolBar.add(buttonArchive);

		toolBar.addSeparator();

		buttonForces = createButton(new ImageIcon("resources/icons/physics.png"), "Cambia las leyes de fuerza");
		toolBar.add(buttonForces);

		toolBar.addSeparator();

		buttonPlay = createButton(new ImageIcon("resources/icons/run.png"), "Inicia la simulación");
		toolBar.add(buttonPlay);

		buttonStop = createButton(new ImageIcon("resources/icons/stop.png"), "Detiene la simulación");
		toolBar.add(buttonStop);

		toolBar.add(new JLabel(" Steps: "));
		steps = new JSpinner(new SpinnerNumberModel(10000, 1, 30000, 100));
		toolBar.add(steps);
		steps.setPreferredSize(new Dimension(70, 42));
		steps.setMaximumSize(steps.getPreferredSize());
		Font font1 = steps.getFont().deriveFont(Font.PLAIN, 15f);
		steps.setFont(font1);
		steps.setToolTipText("Configura el número de pasos");
		editor = ( JSpinner.DefaultEditor ) steps.getEditor();

		toolBar.add(new JLabel(" Delta-Time: "));
		time = new JTextField();
		time.setPreferredSize(new Dimension (70, 42));
		time.setMaximumSize(time.getPreferredSize());
		time.setToolTipText("Configura el tiempo de simulación");

		toolBar.add(time);

		Font font2 = time.getFont().deriveFont(Font.PLAIN, 15f);
		time.setFont(font2);
		toolBar.add(Box.createGlue());
		toolBar.addSeparator();
		

		buttonExit = createButton(new ImageIcon("resources/icons/exit.png"), "Cierra la simulación");
		toolBar.add(buttonExit);
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
				buttonPlay.setEnabled(true);
				buttonExit.setEnabled(true);
				editor.getTextField().setEditable( true );
				
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
			buttonPlay.setEnabled(true);
			buttonExit.setEnabled(true);
			time.setEditable(true);
			editor.getTextField().setEditable( true );
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
        if (fld == null)
        {
        	fld = new ForceLawsDialog((Frame) SwingUtilities.getWindowAncestor(this), _ctrl.getForceLawsInfo(), _ctrl);
        }
        
        int status = fld.open();
        if (status == 1)
        {
            try
            {
                JSONObject obj = fld.getForceLaw();
                
                _ctrl.setForceLaws(obj);
            }
            catch(Exception e)
            {
            	JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
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
				buttonPlay.setEnabled(false);
				buttonArchive.setEnabled(false);
				buttonExit.setEnabled(false);
				buttonForces.setEnabled(false);
				time.setEditable(false);
				editor.getTextField().setEditable( false );
				_stopped = false;
				run_sim(s);		//Comienza la simulación con el número de pasos seleccionados
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
		buttonPlay.setEnabled(true);
		buttonArchive.setEnabled(true);
		buttonExit.setEnabled(true);
		buttonForces.setEnabled(true);
		time.setEditable(true);
		editor.getTextField().setEditable( true );
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






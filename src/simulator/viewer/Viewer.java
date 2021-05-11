package simulator.viewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.TitledBorder;

import simulator.control.Controller;
import simulator.misc.Vector2D;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class Viewer extends JComponent implements SimulatorObserver 
{
	private static final long serialVersionUID = 1L;
	private int _centerX;
	private int _centerY;
	private double _scale;
	private List<Body> _bodies;
	private boolean _showHelp;
	private boolean _showVectors;
	
	Viewer(Controller ctrl) 
	{
		initGUI();
		ctrl.addObserver(this);
	}
	
	private void initGUI() 
	{
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "Viewer", TitledBorder.LEFT, TitledBorder.TOP));


		_bodies = new ArrayList<>();
		_scale = 1.0;
		_showHelp = true;
		_showVectors = true;
		addKeyListener(new KeyListener() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				switch (e.getKeyChar())
				{
				case '-':
					_scale = _scale * 1.1;
					repaint();
					break;
				case '+':
					_scale = Math.max(1000.0, _scale / 1.1);
					repaint();
					break;
				case '=':
					autoScale();
					repaint();
					break;
				case 'h':
					_showHelp = !_showHelp;
					repaint();
					break;
				case 'v':
					_showVectors = !_showVectors;
					repaint();
					break;
				default:
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) 
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent arg0) 
			{
				// TODO Auto-generated method stub

			}
		});
		addMouseListener(new MouseListener() 
		{
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				requestFocus();
			}

			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) 
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0)
			{
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D gr = (Graphics2D) g;

		gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gr.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		_centerX = getWidth() / 2;
		_centerY = getHeight() / 2;

		Font negrita = new Font("Arial", Font.BOLD, 13);
		gr.setFont(negrita);

		gr.setColor(Color.RED);
		gr.drawString("+", _centerX, _centerY);

		for(int i = 0; i < _bodies.size() && _showVectors; i ++) // He añadido el _showVectors por eficiencia algoritmica, si no funciona revisar
		{
			double posX = _bodies.get(i).getPosition().getX();
			double posY = _bodies.get(i).getPosition().getY();

			if(_showVectors)
			{
				gr.setColor(Color.BLUE);
				gr.fillOval(_centerX + (int) (posX/_scale),  _centerY - (int) (posY/_scale), 10, 10);
				gr.drawOval(_centerX + (int) (posX/_scale),  _centerY - (int) (posY/_scale), 10, 10);
				gr.setColor(Color.BLACK);
				gr.drawString(_bodies.get(i).getId(),  _centerX + (int)(posX/_scale),  _centerY - (int)(posY/_scale));
			}
		}

		if(_showHelp)
		{
			gr.setColor(Color.RED);
			gr.drawString("h_ toggle help, v: toggle vectors, +: zoom-in, -: zoom-out, =: fit", 10, 30);
			gr.drawString("Scaling ratio: " + _scale, 10, 47);
		}
	}

	private void autoScale() 
	{
		double max = 1.0;
		for (Body b : _bodies) 
		{
			Vector2D p = b.getPosition();
			max = Math.max(max, Math.abs(p.getX()));
			max = Math.max(max, Math.abs(p.getY()));
		}
		double size = Math.max(1.0, Math.min(getWidth(), getHeight()));
		_scale = max > size ? 4.0 * max / size : 1.0;
	}
	// This method draws a line from (x1,y1) to (x2,y2) with an arrow.
	// The arrow is of height h and width w.
	// The last two arguments are the colors of the arrow and the line
	@SuppressWarnings("unused")
	private void drawLineWithArrow(Graphics g, int x1, int y1, int x2, int y2, int w, int h, Color lineColor, Color arrowColor)
	{
		int dx = x2 - x1, dy = y2 - y1;
		double D = Math.sqrt(dx * dx + dy * dy);
		double xm = D - w, xn = xm, ym = h, yn = -h, x;
		double sin = dy / D, cos = dx / D;
		x = xm * cos - ym * sin + x1;
		ym = xm * sin + ym * cos + y1;
		xm = x;
		x = xn * cos - yn * sin + x1;
		yn = xn * sin + yn * cos + y1;
		xn = x;
		int[] xpoints = { x2, (int) xm, (int) xn };
		int[] ypoints = { y2, (int) ym, (int) yn };
		g.setColor(lineColor);
		g.drawLine(x1, y1, x2, y2);
		g.setColor(arrowColor);
		g.fillPolygon(xpoints, ypoints, 3);
	}

	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc) 
	{
		autoScale();
		_bodies = new ArrayList<>();
		repaint();
	}
	@Override
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc)
	{
		autoScale();
		_bodies = new ArrayList<>();
		repaint();

	}
	@Override
	public void onBodyAdded(List<Body> bodies, Body b) 
	{
		bodies.add(b);
		repaint();
	}
	@Override
	public void onAdvance(List<Body> bodies, double time) 
	{
		repaint();
	}

	@Override
	public void onDeltaTimeChanged(double dt) 
	{	}

	@Override
	public void onForceLawsChanged(String fLawsDesc) 
	{	}
}


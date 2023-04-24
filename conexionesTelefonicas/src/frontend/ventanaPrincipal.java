package frontend;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

public class ventanaPrincipal extends JFrame
{

	private JPanel contentPane;
	private JMapViewer mapa;
	private JLabel lblAutors;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					ventanaPrincipal frame = new ventanaPrincipal();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public ventanaPrincipal()
	{
		setTitle("Conexion telefonicas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 554, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		mapa = new JMapViewer();
		mapa.setBounds(69, 10, 400, 400);
		mapa.setDisplayPosition(new Coordinate(-34.521, -58.7008), 5);
		contentPane.setLayout(null);
		contentPane.add(mapa);

		setLocationRelativeTo(null);

		mapa.setZoomControlsVisible(false);

		//--- boton de salir ---
		JButton btnNewButton = new JButton("Salir");
		btnNewButton.setBounds(439, 415, 89, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseReleased(MouseEvent e){
				System.exit(0);
			}
		});

		//--- label autores ---
		lblAutors = new JLabel("Juan, Esteban, Ezequiel");
		lblAutors.setBounds(10, 419, 147, 14);
		contentPane.add(lblAutors);
	}

}
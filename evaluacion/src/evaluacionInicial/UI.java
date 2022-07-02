package evaluacionInicial;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UI {

	private JFrame frame;
	private JTextField usuario;
	private JTextField pass;
	private JLabel mensaje;
	private static DB database;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					database = new DB();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 448, 217);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(16dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(6dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(107dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(47dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(25dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(38dlu;default)"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(17dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Usuario:");
		frame.getContentPane().add(lblNewLabel, "4, 6");
		
		usuario = new JTextField();
		frame.getContentPane().add(usuario, "6, 6, default, center");
		usuario.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Constraseña:");
		lblNewLabel_1.setForeground(Color.BLACK);
		frame.getContentPane().add(lblNewLabel_1, "4, 8");
		
		pass = new JTextField();
		frame.getContentPane().add(pass, "6, 8, fill, default");
		pass.setColumns(10);
		
		
		// El label que cambiará de texto según lo que ocurra en el login o el registro
		mensaje = new JLabel();
		frame.getContentPane().add(mensaje, "6, 12, center, default");
		
		
		// Zona de Botones
		JButton acceder = new JButton("Acceder");
		frame.getContentPane().add(acceder, "8, 6");
		JButton registrar = new JButton("Registrar");
		frame.getContentPane().add(registrar, "8, 8");
		

		acceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					if (!validate()) return;

					database.openDB();
					if (database.findUser(usuario.getText(), pass.getText())) {
						mensaje.setText("Acceso correcto");
						mensaje.setForeground(Color.ORANGE);
					} else {
						mensaje.setText("Fallo en el usuario o contraseña");
						mensaje.setForeground(Color.RED);
					}
						
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		registrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					if (!validate()) return;
						
					database.openDB();
					if (database.createUser(usuario.getText(), pass.getText())) {
						mensaje.setText("Registro completado");
						mensaje.setForeground(Color.ORANGE);
					} else {
						mensaje.setText("Fallo al registrar. Usuario en uso.");
						mensaje.setForeground(Color.RED);
					}
						
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public boolean validate() {
		if (usuario.getText().isEmpty() || pass.getText().isEmpty()) {
			mensaje.setText("Ambos campos son requeridos");
			mensaje.setForeground(Color.RED);
			return false;
		} else {
			return true;
		}
	}
}

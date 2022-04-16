package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class CuentaAtras extends JFrame implements ActionListener, Runnable{
	final int TIEMPO_ESPERA = 1000;
	
	boolean bandera = false;
	
	JLabel lblMsg;
	JLabel cuentaAtras;
	JTextField txtTiempo;
	JButton btnAceptar;
	JButton btnLimpiar;
	Dimension alto;
	JPanel introducirHora;
	JPanel temporizador;
	
	int horas;
	int minutos;
	int segundos;
	
	Temporizador t = new Temporizador();
	Thread t1 = new Thread();
	
	public CuentaAtras() {
		crearVentana();
	}
	
	public void crearVentana() {
		lblMsg = new JLabel("Ingresa el tiempo siguiengo el siguiente formato: hh:mm:ss", SwingConstants.CENTER);
		txtTiempo = new JTextField();
		btnLimpiar = new JButton("Limpiar campo");
		btnAceptar = new JButton("Aceptar");
		introducirHora = new JPanel();
		temporizador = new JPanel();
		
		setLayout(new BorderLayout(5, 5));

		/**
		 * INICIO JPANEL introducirHora
		 */
		
		add(introducirHora, BorderLayout.CENTER);
		temporizador.setVisible(false);
		setResizable(false);

		lblMsg.setBounds(60, 20, 350, 20);
		introducirHora.add(lblMsg);
		
		txtTiempo.setBounds(60, 50, 350, 20);
		introducirHora.add(txtTiempo);
		
		btnLimpiar.setBounds(60, 80, 350, 50);
		introducirHora.add(btnLimpiar);
		
		btnAceptar.setBounds(60, 150, 350, 50);
		introducirHora.add(btnAceptar);
		
		add(introducirHora);
		introducirHora.setLayout(new GridLayout(4, 1));
		
		txtTiempo.setFont(new Font("Dialog", 0, 50));
		
		setTitle("Ingresa el tiempo");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btnLimpiar.addActionListener(this);
		btnAceptar.addActionListener(this);

		/**
		 * FIN JPANEL introducirHora
		 */
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String[] tiempo = new String[3];

		if(e.getSource() == btnLimpiar) {
			System.out.println("Hola mundo");
			txtTiempo.setText("");
		}

		if(e.getSource() == btnAceptar) {
			if(txtTiempo.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Campo vacío", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				tiempo = txtTiempo.getText().split(":");

				try {
					horas = Integer.parseInt(tiempo[0]);
					minutos = Integer.parseInt(tiempo[1]);
					segundos = Integer.parseInt(tiempo[2]);

				} catch(Exception e2) {
					JOptionPane.showMessageDialog(this, "Datos o formato inválidos", "Error", JOptionPane.ERROR_MESSAGE);
					txtTiempo.setText("");

				}

				if(!t.horaCorrecta()) {
					JOptionPane.showMessageDialog(this, "Hora incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
					txtTiempo.setText("");
				} else {

					t.setHoras(horas);
					t.setMinutos(minutos);
					t.setSegundos(segundos);
					
					t.comprobarHora();

					cuentaAtras = new JLabel(t.toString(), SwingConstants.CENTER);

					introducirHora.setVisible(false);
					temporizador.setVisible(true);
					this.setSize(600, 250);
					cuentaAtras.setFont(new Font("Dialog", Font.PLAIN, 150));
					this.bandera = true;

					add(temporizador, BorderLayout.CENTER);
					temporizador.add(cuentaAtras, BorderLayout.CENTER);

				}
			}
		}
	}

	@Override
	public void run() {
		while(true) {
			while(t.getHoras() == 0 && t.getMinutos() == 0 && t.getSegundos() == 0) {
				break;
			}

			while(!t.finTiempo()) {
				t.pasoTiempo();
				t.esperar(TIEMPO_ESPERA);
				t.comprobarHora();
				cuentaAtras.setText(t.toString());
			}
			break;
			
		}
		JOptionPane.showMessageDialog(this, "Fin de la cuenta atras", "Información", JOptionPane.INFORMATION_MESSAGE);
		temporizador.setVisible(false);
		crearVentana();
		
	}
}

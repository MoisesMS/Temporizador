package main;

public class Main{
	static CuentaAtras v1;
	final static int TIEMPO_ESPERAR = 1000;
	
	
	
	public static void main(String[] args) {
		v1 = new CuentaAtras();
		v1.setVisible(true);
		
		Thread t1 = new Thread(v1);

		while(true) {
			if(v1.bandera) {
				t1.start();			
			}
			System.out.println(v1.bandera);
		}
	}
}

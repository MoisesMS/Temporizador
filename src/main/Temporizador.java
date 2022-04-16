package main;

public class Temporizador {
	private int horas;
	private int minutos;
	private int segundos;
	
	public Temporizador() {
		this.horas = 0;
		this.minutos = 1;
		this.segundos = 0;
	}
	
	public Temporizador(int horas, int minutos, int segundos) {
		this.horas = horas;
		this.minutos = minutos;
		this.segundos = segundos;
	}
	
	public int getHoras() {
		return this.horas;
	}
	
	public int getMinutos() {
		return this.minutos;
	}
	
	public int getSegundos() {
		return this.segundos;
	}
	
	public void setHoras(int horas) {
		this.horas = horas;
	}
	
	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}
	
	public void setSegundos(int segundos) {
		this.segundos = segundos;
	}
	
	public void comprobarHora() {
		if(this.segundos < 0) {
			this.segundos = 59;
			this.minutos--;
		}
		
		if(this.minutos < 0) {
			this.minutos = 59;
			this.horas--;
		}
		
		if(this.horas >= 100) {
			this.horas = 99;
		}
	}
	
	public boolean horaCorrecta() {
		if(this.horas < 0 || (this.minutos < 0 || this.minutos > 59) || (this.segundos < 0 || this.segundos > 59)) {
			return false;
		}
		
		return true;
	}
	
	public void pasoTiempo() {
		this.segundos--;
	}
	
	public void esperar(int tiempo) {
		try {
			Thread.sleep(tiempo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean finTiempo() {
		if(this.horas == 0 && this.minutos == 0 && this.segundos == 0) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		String hora = "";
		String minutos = "";
		String segundos = "";
		
		String tiempo = "";
		
		if(this.horas < 10) {
			hora = "0" + this.horas;
		} else {
			hora = this.horas + "";
		}
		
		if(this.minutos < 10) {
			minutos = "0" + this.minutos;
		} else {
			minutos = this.minutos + "";
		}
		
		if(this.segundos < 10) {
			segundos = "0" + this.segundos;
		} else {
			segundos = this.segundos + "";
		}

		return hora + ":" + minutos + ":" + segundos;
	}
}

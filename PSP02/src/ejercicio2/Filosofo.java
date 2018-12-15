package ejercicio2;

import java.util.concurrent.Semaphore;

public class Filosofo extends Thread{

	private int id;
	private Semaphore[] semaforoPalillo;
	private int izq;
	private int dcho;
	private boolean hambriento = false;
	
	public Filosofo (int id, Semaphore[] semaforoPalillo) {
		
		this.id = id;
		this.semaforoPalillo = semaforoPalillo;
		this.izq = id;
		if(id == semaforoPalillo.length - 1) {//4
			this.dcho = 0;
		}else this.dcho = id+1;
		
	}
	
	public void run () {
		
		
		while(true) { //bucle infinito
			try {
				sleep((int)(Math.random())*1000);
				pensar();
				comer();
				
			}catch(InterruptedException e) {
				System.out.println(e);
			}
				
			
			
		}
		
	}
	
	public void comer() {
		while(hambriento) {	
			if(semaforoPalillo[izq].tryAcquire()) {//si devuelve true, tenemos permiso
				if(semaforoPalillo[dcho].tryAcquire()) {//si devuelve true, tenemos permiso.
					
					try {
						System.out.println("Filósofo nº "+id+" está comiendo con palillos "+izq+" y "+dcho+ ".");
						sleep((int)((Math.random())*1000));
						hambriento = false;
					}catch(InterruptedException e) {
						System.out.println(e);
					}
					System.out.println("Filósofo nº "+id+" deja palillos "+izq+" y "+dcho+ ".");
					semaforoPalillo[dcho].release();
					semaforoPalillo[izq].release();
					//si ya comió, que se ponga a pensar.
					
				}
				
			}else semaforoPalillo[izq].release();
		}
	}

	public void pensar() {
		while(!hambriento){
			System.out.println("Filósofo nº "+id+" está pensando.");
			try {
				sleep((int)((Math.random())*6000));//El filósofo piensa un tiempo aleatorio, después del cual le entrará hambre e intentará comer.
				hambriento = true;
			}catch (InterruptedException e) {
				System.out.println(e);
			}
			System.out.println("Filósofo nº "+id+" está hambriento y necesita palillos "+izq+" y "+dcho+".");
		}
	}
	
	
}

package ejercicio2;

import java.util.concurrent.Semaphore;

public class MainFilo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	    Semaphore[] palillos = new Semaphore[5];
	    Filosofo[] filosofos = new Filosofo[5];

	    for (int i = 0; i < palillos.length; i++) {
	        palillos[i] = new Semaphore(1);//Constructor con id palillo.
	    }

	    for (int i = 0; i < filosofos.length; i++) {

	        new Filosofo(i, palillos).start();
	        
	    }
		
		
		
	}

}

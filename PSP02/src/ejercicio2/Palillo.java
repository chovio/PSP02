package ejercicio2;

import java.util.concurrent.Semaphore;

public class Palillo {
		
	public Semaphore palillo = new Semaphore(1);
	public int id;

	public Palillo (int id) {
	    this.id = id;
	}

	public int getId() {
	    return id;
	}

	public boolean cogerPalillo() {
	    //Devuelve true si puede coger el palillo, y por tanto obtiene el permiso de cogerlo.
		
		return palillo.tryAcquire();
	}

	public void dejarPalillo() {
		//Deja el palillo en la mesa, y por tanto libera el permiso.
		//System.out.println("Deja el palillo "+id+".");
	    palillo.release();
	}
	

}

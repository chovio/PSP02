package ejercicio1;

import java.util.Random;

public class HiloProductor extends Thread{
	
	
private BufferCompartido buffer;
	
	public HiloProductor (String name, BufferCompartido bf) {
		
		setName(name);
		this.buffer = bf;
		
	}
	@Override
	public void run () {
		buffer.depositarCaracter(generarCaracter());
		try {
			sleep ((int)((Math.random()))*50);
		}catch (InterruptedException e) {
			System.out.println(e);
		}
				
	}
	
	public Character generarCaracter() {
		Random randomLetter = new Random();
		char c = (char)(randomLetter.nextInt(26)+'A');
		return c;
	}

}

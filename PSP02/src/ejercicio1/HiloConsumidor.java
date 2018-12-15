package ejercicio1;

public class HiloConsumidor extends Thread{
	
	private BufferCompartido buffer;
	
	public HiloConsumidor ( String name, BufferCompartido bf) {
		
		setName(name);
		this.buffer = bf;
		
	}
	@Override
	public void run () {
		buffer.recogerCaracter();
		try {
			sleep ((int)((Math.random()))*50);
			
		}catch (InterruptedException e) {
			System.out.println(e);
		}
				
	}
}

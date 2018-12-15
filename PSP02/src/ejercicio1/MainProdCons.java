package ejercicio1;

public class MainProdCons {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BufferCompartido bf = new BufferCompartido();
		for(int i = 1; i<= 15; i++) {
			
			(new HiloProductor("Hilo productor" + i, bf)).start();
			
		}
		for(int i = 1; i <= 15; i++) {
			(new HiloConsumidor("Hilo consumidor" +i, bf)).start();
			
		}
		
	}

}

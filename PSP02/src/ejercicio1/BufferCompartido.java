package ejercicio1;

import java.util.ArrayList;

public class BufferCompartido {
	
	private ArrayList<Character> buffer;
	private final int capacidad = 6;
	
	public BufferCompartido() {
		
		this.buffer = new ArrayList<Character>();
		
	}
	
	public synchronized void depositarCaracter (char c) {
		
		if(buffer.size()==capacidad) {
			while(buffer.size()==capacidad) {
				try {
					wait();
				}catch (InterruptedException e) {
					System.out.println(e);
				}
				
				
			}//Al finalizar el bucle
			System.out.println("Depositando el carácter "+c+" en el buffer.");
			buffer.add(c);
		}else if(buffer.size()<capacidad){
			System.out.println("Depositando el carácter "+c+" en el buffer.");
			buffer.add(c);
		}
		notifyAll();
		
	}
	
	public synchronized void recogerCaracter() {
		if(buffer.size()==0) {
			while(buffer.size()==0) {
				try {
					wait();
				}catch (InterruptedException e) {
					System.out.println(e);
				}
			}
			
			char c = buffer.get(buffer.size()-1);
			System.out.println("Recogiendo el carácter "+c);
			buffer.remove(buffer.size()-1);
		}else {
			char c = buffer.get(buffer.size()-1);
			System.out.println("Recogiendo el carácter "+c);
			buffer.remove(buffer.size()-1);
		}
		notifyAll();
	}
	
	
}

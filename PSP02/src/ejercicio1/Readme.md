# Ejercicio 1


De igual manera a lo visto en el tema, ahora te proponemos un ejercicio del tipo productor-consumidor que mediante un hilo productor almacene datos (15 caracteres) en un búfer compartido, de donde los debe recoger un hilo consumidor (consume 15 caracteres). La capacidad del búfer ahora es de 6 caracteres, de manera que el consumidor podrá estar cogiendo caracteres del búfer siempre que éste no esté vacío. El productor sólo podrá poner caracteres en el búfer, cuando esté vacío o haya espacio.

Clase BufferCompartido

La clase BufferCompartido contiene un ArrayList de 'Carácteres' y un limitador de la capacidad de dicho ArrayList.
La elección del ArrayList es porque lo conozco mejor que otro tipo de listas.

Tras crear un constructor que inicializa el ArrayList, creamos las clases **synchronized** que depositarán y recogerán caracteres.

```java

public synchronized void depositarCaracter (char c) {
		
	if(buffer.size()==capacidad) {//Si el buffer está lleno 
		while(buffer.size()==capacidad) {//mientras que no se vacíe
			try {
				wait();//tendremos que esperar para depositar un nuevo caracter
			}catch (InterruptedException e) {
				System.out.println(e);
			}
		}//una vez que se vacíe, ya podremos depositarlo.
		System.out.println("Depositando el carácter "+c+" en el buffer.");
		buffer.add(c);
	}else {
		System.out.println("Depositando el carácter "+c+" en el buffer.");
		buffer.add(c);
	}
	notifyAll();//Notificamos al resto de hilos.
	
}
	
public synchronized void recogerCaracter() {
	if(buffer.size()==0) {//si el buffer está a cero
		while(buffer.size()==0) {//mientras que esté a cero
			try {
				wait();//tendremos que esperar porque no podremos recoger ningún caracter.
			}catch (InterruptedException e) {
				System.out.println(e);
			}
		}
		
		char c = buffer.get(buffer.size()-1);//Cogeremos el caracter de la última posición.
		System.out.println("Recogiendo el carácter "+c);
		buffer.remove(buffer.size()-1);//Eliminamos el caracter de la lista
	}else {
		char c = buffer.get(buffer.size()-1);
		System.out.println("Recogiendo el carácter "+c);
		buffer.remove(buffer.size()-1);
	}
	notifyAll();//notificamos
}


```

Clase HiloProductor

```java

@Override
public void run () {
	buffer.depositarCaracter(generarCaracter());
	try {
		sleep ((int)((Math.random()))*50);
	}catch (InterruptedException e) {
		System.out.println(e);
	}
			
}
	
///método productor de caracteres  
public Character generarCaracter() {
	Random randomLetter = new Random();
	char c = (char)(randomLetter.nextInt(26)+'A');
	return c;
}


```
Clase HiloConsumidor

```java
@Override
public void run () {
	buffer.recogerCaracter();
	try {
		sleep ((int)((Math.random()))*50);
		
	}catch (InterruptedException e) {
		System.out.println(e);
	}
			
}


```
## Ejecución:
Pruebo su ejecución.

![Captura de pantalla](http://subirimagen.me/uploads/20181215114442.png)


## Meta

Javier Salgado Tenreiro – javisalgadotenreiro@gmail.com

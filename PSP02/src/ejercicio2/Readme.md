# Ejercicio 2

De igual manera a lo visto en el tema, ahora te proponemos que resuelvas el clásico problema denominado "La cena de los filósofos" utilizando la clase Semaphore del paquete java.util.concurrent.

El problema es el siguiente: Cinco filósofos se sientan alrededor de una mesa y pasan su vida comiendo y pensando. Cada filósofo tiene un plato de arroz chino y un palillo a la izquierda de su plato. Cuando un filósofo quiere comer arroz, cogerá los dos palillos de cada lado del plato y comerá. El problema es el siguiente: establecer un ritual (algoritmo) que permita comer a los filósofos. El algoritmo debe satisfacer la exclusión mutua (dos filósofos no pueden emplear el mismo palillo a la vez), además de evitar el interbloqueo y la inanición.

Usando el JavaDoc que hay para descargar he realizado los siguientes métodos en la clase Filósofo:


Clase Filósofo

```java
@Override
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
				semaforoPalillo[dcho].release();//El palillo pos dcho libera el permiso.
				semaforoPalillo[izq].release();//El palillo pos izq libera el permiso.
				//si ya comió, que se ponga a pensar.
					
			}else {
				semaforoPalillo[izq].release();
				}
		}
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


```

## Ejecución:
Pruebo su ejecución.

![Captura de pantalla](http://subirimagen.me/uploads/20181215114948.png)


## Meta

Javier Salgado Tenreiro – javisalgadotenreiro@gmail.com


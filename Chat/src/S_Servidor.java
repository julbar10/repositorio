import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class S_Servidor {
	
    public static void main(String[] args) throws IOException {    
			ArrayList<S_Hilo> usuarios=new ArrayList<>();
			int numusuario=0;
		try {
			ServerSocket servidor = new ServerSocket(3000);			
			do
			{
							
				System.out.println("Esperando cliente");							
				Socket socketConectado = servidor.accept();
                numusuario++;
                S_Hilo hilousuario= new S_Hilo(socketConectado,numusuario,usuarios);
                usuarios.add(hilousuario);
                Runnable nuevoSocket=hilousuario;
              
				Thread hiloSocket = new Thread(nuevoSocket);
				
				hiloSocket.start();
				
				System.out.println("Cliente recibido");								
			}while(true);
			
		}
		catch (IOException excepcion) {			
			System.out.println(excepcion);
		}

    }
}
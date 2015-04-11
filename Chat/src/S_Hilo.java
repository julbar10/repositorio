import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class S_Hilo implements Runnable {

    String recibido;
    OutputStream osalida;
	DataOutputStream dsalida;

	InputStream ientrada;
	DataInputStream dentrada;

	Socket socket;
	int nhilo;
	
	ArrayList<S_Hilo> Par;

	public void run() {	

		try{			
			osalida = socket.getOutputStream();
			dsalida = new DataOutputStream(osalida);

			ientrada = socket.getInputStream();
			dentrada = new DataInputStream(ientrada);

			dsalida.writeUTF("Llegaste al Servidor\n");

			do{
				recibido = dentrada.readUTF();	
				
				int tama=Par.size();
				for (int i = 0; i < tama; i++) {
					Par.get(i).dsalida.writeUTF("Usuario "+nhilo+": "+recibido);
					 
				}
				
			}while(!recibido.equals(""));
		}
		catch (IOException excepcion) {
			System.out.println("Se a retirado usuario "+nhilo);
		}		
		
		try{
			dsalida.close();
			dentrada.close();
			socket.close();			
		}
		catch (IOException excepcion) {
			System.out.println(excepcion);
		}			
	}
        
        
	public S_Hilo(Socket lsocket,int nhilo,ArrayList<S_Hilo> Par){
		try{
			this.nhilo=nhilo;
			this.Par=Par;
			socket = lsocket;			
		}
		catch (Exception excepcion) {
			System.out.println(excepcion);
		}		
	}

}
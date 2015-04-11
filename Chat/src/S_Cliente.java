import java.io.*;  
import java.net.*;
import javax.swing.JTextArea;

public class S_Cliente implements Runnable{
	String recibido;
    OutputStream osalida;
	DataOutputStream dsalida;
	boolean salida=true;

	InputStream ientrada;
	DataInputStream dentrada;

	Socket usuario;
	Thread hilocuadro;
	JTextArea cuadro;
        
        public void mensaje(String mmensaje){
		
		try {	
			dsalida.writeUTF(mmensaje+"\n");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
        
	public S_Cliente(JTextArea caja){
		this.cuadro=cuadro;
		hilocuadro=new Thread(this);
		hilocuadro.start();
		try {	
			usuario = new Socket("127.0.0.1", 3000);  
			osalida = usuario.getOutputStream();
			dsalida = new DataOutputStream(osalida);

			ientrada = usuario.getInputStream();
			dentrada = new DataInputStream(ientrada);
 
			recibido = dentrada.readUTF();
	        caja.setText(caja.getText()+recibido);
		}
		catch (Exception e) {
			System.err.println("Error: " + e);
		}
	}

	
		
        @Override
	public void run() {
		Thread ct= Thread.currentThread();
		if(ct==hilocuadro){
			try {
		do{	
				recibido = dentrada.readUTF();
				cuadro.setText(cuadro.getText()+recibido);
			
		}while(true);
		
       } catch (Exception e) {
				
			}
		}
	
	}
        
        
	public void cerrarsesion(){
		
		try {
			dsalida.close();
			dentrada.close();
		    usuario.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	
 
	
}
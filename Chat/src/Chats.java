import java.awt.*;  
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import javax.swing.*;

public class Chats extends JFrame  implements Runnable{
    
  public class Imagen extends javax.swing.JPanel {

    public Imagen() {
     this.setSize(300, 400); 
    }


    public void paint(Graphics grafico) {
         Dimension height = getSize();

          ImageIcon Img = new ImageIcon(getClass().getResource("/pinguino.png"));

           grafico.drawImage(Img.getImage(), 0, 0, height.width, height.height, null);

            setOpaque(false);
            super.paintComponent(grafico);
    }
}
	JTextArea mensajes=new JTextArea();
	JTextField conversacion=new JTextField(20);
	JButton enviar=new JButton();
        Font font = new Font("Kristen ITC", Font.PLAIN, 12);
        Color otverde=new Color(0, 255, 127);
        
        
	JScrollPane scroll = new JScrollPane();
	
	String recibido;
        OutputStream osalida;
	DataOutputStream dsalida;
	boolean salida=true;

	InputStream ientrada;
	DataInputStream dentrada;

	Socket cliente;
	Thread hilocaja;
	
	
	public Chats() {
		
		setSize(400,400);
		setLocation(200,200);
                setTitle("Conversacion");
		
		servidor();
		mensajes.setForeground(Color.blue);
                mensajes.setFont(font);
                mensajes.setBorder(BorderFactory.createEmptyBorder()); 
                mensajes.setBackground(new Color(0, 0, 0, 0));
		setVisible(true);	
		
		scroll.setViewportView(mensajes);
		try {	
			
			cliente = new Socket("127.0.0.1", 3000);  
			osalida = cliente.getOutputStream();
			dsalida = new DataOutputStream(osalida);

			ientrada = cliente.getInputStream();
			dentrada = new DataInputStream(ientrada);
 
			recibido = dentrada.readUTF();
	        mensajes.setText(mensajes.getText()+recibido);
		}
		catch (Exception e) {
			System.err.print(e);
		}
		
		hilocaja=new Thread(this);
		hilocaja.start();
		
	}


	public void servidor() { 
		try {
			
		} catch (Exception e) {
			
		}  
		
		this.setLayout(new GridLayout(1, 1, 1, 1));
		JPanel Pservidor=new JPanel();
		Pservidor.setLayout(new BorderLayout());
	        Imagen Imagen = new Imagen();
		JPanel Pcliente=new JPanel();
		Pcliente.setLayout(new BorderLayout());
		JPanel pcentro=new JPanel();
		pcentro.setLayout(new FlowLayout());
		this.conversacion.setText("En linea");
		this.enviar.setText("Enviar");
                enviar.setBackground(otverde); 
		pcentro.add(this.conversacion);
		pcentro.add(this.enviar);
		enviar.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent evento){
					 try {
						dsalida.writeUTF(conversacion.getText()+"\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
							
					}	
				}
			);
		
		
		
		Pcliente.add(pcentro, BorderLayout.CENTER);
		
		Pservidor.add(Imagen, BorderLayout.CENTER);
                Pservidor.repaint();
		Pservidor.add(scroll, BorderLayout.CENTER);
		Pservidor.add(Pcliente, BorderLayout.SOUTH);
		this.add(Pservidor);
		
			}
	public void cliente() {
		this.setLayout(new GridLayout(1, 1, 1, 1));
				
	}
	
public void cerrarsesion(){
		
		try {
			dsalida.close();
			dentrada.close();
		    cliente.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
@Override
public void run() {
	Thread ct= Thread.currentThread();
	if(ct==hilocaja){
		try {
			
			do{
			recibido = dentrada.readUTF();
			mensajes.setText(mensajes.getText()+recibido);
	    
	        
	}while(true);
		} catch (Exception e) {
			mensajes.setText(mensajes.getText()+"No hay conexion \n ");
		}
	
	}

}		

	public static void main(String[] args) {
            try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Chats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Chats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Chats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Chats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		Chats mensajes= new Chats();
	}
	
}

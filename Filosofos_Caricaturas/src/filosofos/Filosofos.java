
package filosofos;

import java.awt.Color;
import java.util.Random; 
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;
import java.awt.event.*;

public class Filosofos extends JFrame implements Runnable, ActionListener{
	
  ImageIcon[] IconoCarica=new ImageIcon[5];  
   JLabel[] Caricaturas=new JLabel[5];
    Thread[] Acciones=new Thread[5];
     String p="P.jpg",c="C.jpg",e="E.jpg";
      JButton boton1, reiniciar, pausar;
    
    public static void main(String[] args) {
        Filosofos inicio=new Filosofos();
    }
    
    public void run() {
     Thread hilo= Thread.currentThread();
	
	for (int i = 0; i < Acciones.length; i++) {

         while(Acciones[i]==hilo){
            Random rnd=new Random();
            int esperando = (int)(rnd.nextDouble() * 15.0)+5;
            try {
              if(!IconoCarica[i].getDescription().equalsIgnoreCase(e)){ Thread.sleep (esperando*800); }
                 int puesto1=i-1,puesto2=i+1;
                  if(i==0){
                    puesto1=4;puesto2=1;
                  }
            
                    if(i==4){puesto2=0;}
            
                      if(IconoCarica[puesto1].getDescription().equalsIgnoreCase(c)||IconoCarica[puesto2].getDescription().equalsIgnoreCase(c)){
                         IconoCarica[i]=new ImageIcon(e);
                         Caricaturas[i].setIcon(IconoCarica[i]);
                       } else {
                         IconoCarica[i]=new ImageIcon(c);Caricaturas[i].setIcon(IconoCarica[i]);
                       }
		
                    if(!IconoCarica[i].getDescription().equalsIgnoreCase(e)){ Thread.sleep (esperando*800); }
	         if(!IconoCarica[i].getDescription().equalsIgnoreCase(e)){
                     IconoCarica[i]=new ImageIcon(p);Caricaturas[i].setIcon(IconoCarica[i]);
               }
             if(!IconoCarica[i].getDescription().equalsIgnoreCase(e)){ Thread.sleep (esperando*800); }
            
            } catch (Exception e) {
                System.out.print(e);
           }       
	 }
       }
    }
	
    public Filosofos(){
    
     this.setTitle("Huevos Pensantes");
       this.setSize(1300,700);
         this.setLocationRelativeTo(null);
          this.setLayout(null);
           setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    
              Color dark=new Color(139,0,0);
               Color otverde=new Color(0, 255, 127);
                Color otred=new Color(250, 128, 114);
    
                  boton1=new JButton("Finalizar");
                   boton1.setBounds(1150,620,100,30);
                    boton1.setBackground(dark); 
                   boton1.setForeground(Color.WHITE);
                  add(boton1);
                boton1.addActionListener(this);
        
    
        
            reiniciar=new JButton("Play");
           reiniciar.setBackground(otverde); 
         reiniciar.setBounds(1150,530,100,30);
        add(reiniciar);
       reiniciar.addActionListener(this);
    
      pausar=new JButton("Pause");
     pausar.setBackground(otred);
    pausar.setBounds(1150,480,100,30);
    add(pausar);
    pausar.addActionListener(this);
		
    for (int i = 0; i < Caricaturas.length; i++){
       IconoCarica[i]=new ImageIcon("P.jpg");
	  Caricaturas[i]=new JLabel(IconoCarica[i]);
           if(i<=2){
	      Caricaturas[i].setBounds((i*320)+ 160, 30, 300,300);
             }else{
               Caricaturas[i].setBounds((i*320)-640, 350, 300,300);
           }
                        
           Acciones[i]=new Thread(this);
           Acciones[i].start();			
	}	
			
	for (int i = 0; i < Caricaturas.length; i++) add(Caricaturas[i]);
	       this.setVisible(true);
        }

        public void actionPerformed(ActionEvent e) {
           if (e.getSource()==boton1) {
               System.exit(0);
           }
        
        
        
        if (e.getSource()==pausar) {
            Acciones[0].suspend();
             Acciones[1].suspend();
              Acciones[2].suspend();
               Acciones[3].suspend();
                Acciones[4].suspend();
        }
        
           if (e.getSource()==reiniciar) {
                Acciones[0].resume();
                 Acciones[1].resume();
                  Acciones[2].resume();
                   Acciones[3].resume();
                    Acciones[4].resume();
           }
     }

}

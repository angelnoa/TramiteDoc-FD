package tramitedoc.concytec.util;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class BarradeProgreso extends JFrame{
	JProgressBar actual;
	int num=0;
	
	//llamada a constructor
	public BarradeProgreso(){
		super("Barra de Progreso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(205,68);
		setLayout(new FlowLayout());
		
		//crear la barra
		actual = new JProgressBar(0,2000);
		actual.setValue(0);
		actual.setStringPainted(true);
		add(actual);
		
	}
	
	//le indicamos como aumentar
	public void iterate(){
		while(num < 2000){
			actual.setValue(num);
			try {
				
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			num+=95;
		}
	}
}

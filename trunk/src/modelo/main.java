package modelo;



public class main  extends Thread {
 
		
	public void run() {
		
		try {
			

			ReceptorSMS receptor = new ReceptorSMS();
			EnviadorSMS enviador = EnviadorSMS.getInstance(); 
			
			
			enviador.start();
			receptor.start();
			
			//new Simulacion().start();
			
			enviador.join();
			receptor.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	 
	}
	
	public static void main(String[] args) {
		main modelo = new main();
		modelo.run();

	}

	 

	

}

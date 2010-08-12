package modelo;


import modelo.CommSMSViaArchivo;

public class ReceptorSMS extends Thread {
 
	private CommSMSViaArchivo comm;
	
	public ReceptorSMS(){
		comm = CommSMSViaArchivo.getInstance();
	}
	
	public void run() {
		SMS mensaje = null;
		ParserSMS parser = new ParserSMS();
		
		while(true){
			try {
				sleep(100);
				mensaje = comm.LeerSMS();
				if( mensaje != null ){
					SMS respuesta = parser.parsearSMS(mensaje);
					EnviadorSMS env = EnviadorSMS.getInstance();
					env.EnviarSMS(respuesta);
				}
			} catch (Exception e) {}
		}
	}
	 
}
 

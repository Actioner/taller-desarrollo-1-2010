package modelo;

import modelo.CommSMSViaArchivo;

public class EnviadorSMS extends Thread {
 
	
	private ColaSMS colaEnvio;
	private CommSMSViaArchivo comm;
	
	private static EnviadorSMS instance = null;
	
	
	public static EnviadorSMS getInstance(){
		if( instance == null )
			instance = new EnviadorSMS();
		return instance;
	}
	
	private EnviadorSMS()
	{
		colaEnvio = new ColaSMS();
		comm = CommSMSViaArchivo.getInstance();
	}
	
	public void EnviarSMS(SMS sms) {
		getColaEnvio().acolar(sms);
	}
	 
	public void run() {
		SMS mensaje = null;
		boolean tieneQueEjecutar = true;
		
		while( tieneQueEjecutar ){
			try {
				sleep(100);
				mensaje = getColaEnvio().desacolar();
				if( mensaje != null )
					this.comm.EnviarSMS(mensaje);
			} catch (InterruptedException e) {
				tieneQueEjecutar = false;
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	

	private ColaSMS getColaEnvio() {
		return colaEnvio;
	}
	 
}
 

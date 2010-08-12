package modelo;



public class SMS {
 
	private String telefono; 
	private String mensaje;
	
	public SMS( String telefono, String mensaje){
		setTelefono(telefono);
		setMensaje(mensaje);
	}
	

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	

	public String getTelefono() {
		return telefono;
	}
	

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	

	public String getMensaje() {
		return mensaje;
	}

	public void send(){
		System.out.print("---------Enviando Mensaje-----------\n" +
				"Destinatario: " + this.telefono + "\n" +
				"Mensaje: " + this.mensaje + "\n" +
				"---------Mensaje enviado-----------\n\n");
		
		CommSMSViaArchivo.getInstance().EnviarSMS(this);
	}
	
/*	public void sendCentral(){
		System.out.print("---------Enviando Mensaje-----------\n" +
				"CentralEnvio: " + this.telefono + "\n" +
				"Mensaje: " + this.mensaje + "\n" +
				"---------Mensaje enviado-----------\n\n");
		
		CommSMSViaArchivo.getInstance().EnviarACentral(this);
	}
*/	
	public String toString(){
		return "Destinatario: " + this.getTelefono() + " - Mensaje: " + this.getMensaje();
	}


		 
	 
}
 

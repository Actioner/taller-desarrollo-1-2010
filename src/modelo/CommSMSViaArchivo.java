package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

import sun.awt.Mutex;

import modelo.SMS;

public class CommSMSViaArchivo  {

	
	private static String RUTA_ENTRADA = "C:\\cygwin\\var\\spool\\sms\\incoming\\";
	private static String RUTA_SALIDA = "C:\\cygwin\\var\\spool\\sms\\outgoing\\";
	//private static String RUTA_ENTRADA = "C:\\cygwin\\var\\spol\\sms\\done\\";
	//private static String RUTA_SALIDA = "C:\\cygwin\\var\\spol\\sms\\outgoing\\";
	
		private static CommSMSViaArchivo instance;
	private static Mutex lock = new Mutex();
	
	private final Random random = new Random();
	
	public static CommSMSViaArchivo getInstance(){
		if( instance == null )
			instance = new CommSMSViaArchivo();
		return instance;		
	}
	
	private CommSMSViaArchivo(){
	
	
	}
	

	public void EnviarSMS(SMS sms) {
		String salida = "To: " + sms.getTelefono() + "\n";
		//salida += "Modem: C650\n";
		//salida += "IMSI: 722070210125302\n";
		salida += "\n" + sms.getMensaje();

		Integer prefijoRandom = new Integer(random.nextInt());
		
		String nombre_archivo = RUTA_SALIDA + "MODEM3G." + prefijoRandom;		
		
		try {
	        BufferedWriter out = new BufferedWriter(new FileWriter(nombre_archivo));
	        out.write(salida);
	        out.close();
	    } catch (IOException e) {
	    }
	    
	    System.out.println("Enviado: " + sms.toString());
	}
	
/*	public void EnviarACentral(SMS sms) {
		String salida = "TelefonoCentral:" + sms.getTelefono() + "\n";
		salida += "Mensaje:" + sms.getMensaje() +  "\n";
		
		Integer prefijoRandom = new Integer(random.nextInt());
		
		String nombre_archivo = RUTA_ENTRADA + "Central." + prefijoRandom;		
		
		try {
			CommSMSViaArchivo.lock.lock();
	        BufferedWriter out = new BufferedWriter(new FileWriter(nombre_archivo));
	        out.write(salida);
	        out.close();
	        CommSMSViaArchivo.lock.unlock();
	    } catch (IOException e) {
	    }
	    
	    System.out.println("Enviado: " + sms.toString());
	}
*/	

	public SMS LeerSMS() {
		File dir_entrada = new File(RUTA_ENTRADA);
		boolean existe = dir_entrada.exists();
		File[] archivos = dir_entrada.listFiles();
		SMS sms = null;
		
		try {
			if( archivos.length > 0 )
			{
				for( File archivoEntrada : archivos ){
					if( archivoEntrada.isDirectory() )
						continue;
					CommSMSViaArchivo.lock.lock();
					BufferedReader archivo = new BufferedReader(new FileReader(archivoEntrada.getAbsoluteFile()));;
			        String tel = archivo.readLine();
		        
			        tel = tel.split(":")[1];
			        
			        String mensaje = archivo.readLine();
			        mensaje = mensaje.split(":")[1];
			        
			        sms = new SMS(tel,mensaje);
			        
			        System.out.println("Recibido: " + sms.toString());
			        archivo.close();
			        archivoEntrada.delete();
			        CommSMSViaArchivo.lock.unlock();
			        break;
				}
			}
		} catch (IOException e) {
			//TODO corregir esto que esta horrible
	    }

		return sms;
	}
	
	

}

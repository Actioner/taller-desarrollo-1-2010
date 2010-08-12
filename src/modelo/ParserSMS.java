package modelo;

public class ParserSMS {

	public SMS parsearSMS(SMS mensaje) {
		
		if (mensaje.getMensaje().compareToIgnoreCase("TELO")==0)
			return respuestaTELO(mensaje.getTelefono());
		
		if (mensaje.getMensaje().compareToIgnoreCase("GATOS")==0)
			return respuestaGATOS(mensaje.getTelefono());
		
		if (mensaje.getMensaje().compareToIgnoreCase("RESTAURANTE")==0)
			return respuestaRESTAURANTE(mensaje.getTelefono());
		
		return respuestaERROR(mensaje);
	}

	
	public SMS respuestaTELO(String destino){
		SMS resp = new SMS(destino, "Lugares con el rubro TELO \nOsiris \nPlus \nFaraon");
		return resp;
	}
	
	public SMS respuestaGATOS(String destino){
		SMS resp = new SMS(destino, "Lugares con el rubro GATOS \nBosques de Palermo");
		return resp;
	}
	
	public SMS respuestaRESTAURANTE(String destino){
		SMS resp = new SMS(destino, "Lugares con el rubro RESTAURANTE \nMcDonalds \nEl Federal \nEl Viejo Almacen");
		return resp;
	}
	
	public SMS respuestaERROR(SMS mensaje){
		SMS resp = new SMS(mensaje.getTelefono(), "Lo siento, no se encontro nigun lugar que tenga el criterio de busqueda: " + mensaje.getMensaje());
		return resp;
	}
}


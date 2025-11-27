package espionaje.services_handlers;

public interface EnigmaService {
	
	String codificar(String mensaje);
	
	String decodificar(String mensajeCodificado, String clave);
	
}

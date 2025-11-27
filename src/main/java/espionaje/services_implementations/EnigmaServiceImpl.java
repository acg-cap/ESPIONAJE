package espionaje.services_implementations;

import org.springframework.beans.factory.annotation.Autowired;

import espionaje.services_handlers.Codec;
import espionaje.services_handlers.EnigmaService;


public class EnigmaServiceImpl implements EnigmaService {
	
	private static final String CLAVE;
	
	static {
		
		CLAVE = "ZAQ!2wsxcde3$RFV";
	}
	
	private final Codec codec;
	
	
	
	public EnigmaServiceImpl(){
		
		codec = null;
		
	}
	
	@Autowired						// SPRING 4.3 Y SUPERIORES NO LO NECESITARIAN, HACEN LA INYECCIÓN IMPLÍCITA
	public EnigmaServiceImpl(Codec codec) {
		this.codec = codec;
	}
	
	@Override
	public String codificar(String mensaje) {
		
		String mensajeCodificado = null;
				
		try {
			
			mensajeCodificado = codec.encrypt(mensaje, CLAVE);
			
		} catch (Exception e) {

			System.out.println("ERROR EN LA CODIFICACIÓN -->" + e );
			
		}
		return mensajeCodificado;
	}

	@Override
	public String decodificar(String mensajeCodificado, String claveExterna) {

		String mensajeDecodificado = null;
				
		try {
			
			mensajeDecodificado = codec.decrypt(mensajeCodificado, claveExterna);
			
		} catch (Exception e) {

			System.out.println("\nERROR EN LA DECODIFICACIÓN -->" + e);
			
		}
		return mensajeDecodificado;
	}
	
	
	@Override
	public String toString() {
		return(">>> NUEVO ENIGMA CON NÚMERO DE SERIE: " + hashCode() + "\n");
	}
	
}

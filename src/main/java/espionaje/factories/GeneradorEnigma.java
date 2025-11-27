package espionaje.factories;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import espionaje.services_handlers.Codec;
import espionaje.services_implementations.EnigmaServiceImpl;

@Configuration
public class GeneradorEnigma {

	private CodecImpl obtenerCodec() {

		return new GeneradorEnigma().new CodecImpl();
	}

	@Bean
	@Scope("singleton")
	//@Scope("prototype") 			//PARA GENERAR VARIOS ENIGMAS
	public EnigmaServiceImpl obtenerEnigmaService() {
		
		//System.err.println("\n>>> NUEVA CAJA ENIGMA SOLICITADA...\n");
		
		return new EnigmaServiceImpl(obtenerCodec());
	}

	
	
	private class CodecImpl implements Codec{
	
	    private static final String ALGORITMO = "AES";
	    private static final String ALGORITMO_MODO_RELLENO = "AES/ECB/PKCS5Padding";
	    
	   
	    public String encrypt(String texto, String clave) throws Exception {
	        
	    	SecretKeySpec secretKey = new SecretKeySpec(clave.getBytes(), ALGORITMO);
	        
	        Cipher cipher = Cipher.getInstance(ALGORITMO_MODO_RELLENO);
	        
	        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	        
	        byte[] textoCifradoBytes = cipher.doFinal(texto.getBytes());
	        
	        // Codificar a Base64 para que sea una cadena legible
	        return Base64.getEncoder().encodeToString(textoCifradoBytes);
	        
	    }
	
	    
	    
	    public String decrypt(String textoCifradoBase64, String clave) throws Exception {
	    
	    	byte[] textoCifradoBytes = Base64.getDecoder().decode(textoCifradoBase64);
	        
	    	SecretKeySpec secretKey = new SecretKeySpec(clave.getBytes(), ALGORITMO);
	        
	    	Cipher cipher = Cipher.getInstance(ALGORITMO_MODO_RELLENO);
	        
	    	cipher.init(Cipher.DECRYPT_MODE, secretKey);
	        
	    	byte[] textoDescifradoBytes = cipher.doFinal(textoCifradoBytes);
	        
	    	return new String(textoDescifradoBytes);
	    
	    }
	}

}

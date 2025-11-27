package espionaje.services_handlers;


public interface Codec {

    public String encrypt(String texto, String clave) throws Exception;

    public String decrypt(String textoCifradoBase64, String clave) throws Exception;
   
}

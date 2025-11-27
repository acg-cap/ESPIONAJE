package espionaje;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;


import espionaje.services_handlers.EnigmaService;


@ComponentScan("espionaje.factories")
public class InicioCLR implements CommandLineRunner {
	
	
	@Autowired
	private EnigmaService enigma;

	@Override
	public void run(String... args) throws Exception {
		
		Scanner scanner = new Scanner(System.in);
		
		int opcion = 0;
		
		String mensaje;
		String clave;
		

		System.out.println(enigma);
		System.err.println("\n -------------------HERRAMIENTA DE ESPIONAJE ENIGMA------------------\n");
		
		
		while(opcion != 3) {
			System.out.println("\nQUÉ DESEA HACER?\n");
			System.out.println("\t 1 - CODIFICAR MENSAJE ");
			System.out.println("\t 2 - DECODIFICAR MENSAJE ");
			System.out.println("\t 3 - SALIR DE ENIGMA");
			System.out.println("\n---------------------------");
			System.out.println("\nINTRODUZCA LA OPCIÓN:");
	
			try{
				
				opcion = Integer.parseInt(scanner.nextLine());
				
			}catch (Exception e) {

				//Delega el mensaje de error en el default del switch
			}
			//opcion = scanner.nextInt();  //ESTE MÉTODO HACE QUE SE SALTE EL SIGUIENTE nextLine()

			switch(opcion) {
			
				case 1:
				
					System.out.print("\nINTRODUCIR MENSAJE A CODIFICAR: ");
					mensaje = scanner.nextLine();
					
					String mensajeACodificar = enigma.codificar(mensaje);
					System.out.print("\nMENSAJE CODIFICADO: " + mensajeACodificar + "\n");
					
					break;
					
				case 2:
				
					System.out.print("\nINTRODUCIR MENSAJE A DECODIFICAR: ");
					mensaje = scanner.nextLine();
					
					System.out.print("\nINTRODUCIR CLAVE: ");
					clave = scanner.nextLine();
					
					String mensajeADeCodificar = enigma.decodificar(mensaje, clave);
					System.out.println("\nMENSAJE DESCODIFICADO: " + mensajeADeCodificar + "\n");
					break;
					
				case 3:
	
					System.out.println("\nGOOD BYE!");
					return;			
					
				default:
					System.err.println("OPCIÓN NO VÁLIDA");
					
			}//switch end
		}//while end

		scanner.close();
	}
}

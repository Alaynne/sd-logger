package br.ufal.ic.sd.server.java;

import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;

import br.ufal.ic.sd.server_configurater.java.*;

/**
 * 
 * Programa servidor:
 * 
 * Executa um servidor MonoThread ou MultiThread.
 *
 * @author Alaynne Moreira
 * 
 */

public class ServerProgram {

	public static void main(String[] args) throws IOException{
		
		if (args.length != 1) {
			System.err.println("Parameter size is wrong.");
			System.exit(1);
		}
		
		String configFileName = args[0];
		Properties prop = new Properties();
		FileInputStream in = new FileInputStream(configFileName);
		prop.load(in);
		in.close();
		
		Server server = null;
		ServerConfigurater serverConfig = new ServerConfigurater();
		String mode = prop.getProperty("MODE");
		
		if(mode.equals("MONO")){
			server = serverConfig.configureMonoServer(prop);
		}else if(mode.equals("MULTI")){
			server = serverConfig.configureMultiServer(prop);
		}else{
			System.err.println("Server Program: Invalid server mode.");
			System.exit(1);
		}
		
		if(server != null){
			// Inicializa o servidor.
		    server.start();
		    
		    // Enquanto o servidor estiver ouvindo ...
		    while (server.isListening() == true) {
		    	// Executa o servidor.
		    	server.run();
		    }
		}else{
			System.exit(1);
		}
	    
	}

}

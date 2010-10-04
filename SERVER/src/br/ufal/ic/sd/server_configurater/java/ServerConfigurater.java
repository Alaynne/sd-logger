package br.ufal.ic.sd.server_configurater.java;

import br.ufal.ic.sd.lsprotocol_vrs1.LogLevel.*;
import br.ufal.ic.sd.server.java.MonoThreadServer;
import br.ufal.ic.sd.server.java.MultiThreadServer;
import br.ufal.ic.sd.server.java.Server;
import br.ufal.ic.sd.service.java.*;
import java.util.Properties;

/**
 * 
 * Configurador do servidor:
 * 
 * Configura um servidor MonoThread ou um servidor MultiThread.
 * 
 * @author Alaynne Moreira
 *
 */

public class ServerConfigurater {

	// Configura um servidor MonoThread.
	public Server configureMonoServer(Properties monoProp){
		
		int portNum;
		Server server = null;
		LSP_LogLevel logLevel = LSP_LogLevel.DEBUG;
		
		portNum = Integer.parseInt(monoProp.getProperty("PORT"));
		
		if(monoProp.getProperty("SERVICE").equals("ECHO")){
			server = new MonoThreadServer(portNum, new EchoService());
		}else if (monoProp.getProperty("SERVICE").equals("SLOW")){
			server = new MonoThreadServer(portNum, new SlowService());
		}else if (monoProp.getProperty("SERVICE").equals("LOG")){
				server = new MonoThreadServer(portNum, new LogService(logLevel));
		}else{
			System.err.println("Mono Server configuration: Invalid service.");
		}
		return server;
		
	}
	
	// Configura um servidor MultiThread.
	public Server configureMultiServer(Properties multiProp){
		
		int portNum;
		String threadType = "";
		int threadPoolSize;
		Server server = null;
		LSP_LogLevel logLevel = LSP_LogLevel.DEBUG;
		
		portNum = Integer.parseInt(multiProp.getProperty("PORT"));
		
		if(multiProp.getProperty("THREAD_TYPE").equals("TRUE")){
			threadType = "THREAD";
		}else if (multiProp.getProperty("THREADPOOL_TYPE").equals("TRUE")){
			threadType = "THREADPOOL";
		}else{
			System.err.println("Multi Server configuration: Invalid thread type.");
			return null;
		}
		
		threadPoolSize = Integer.parseInt(multiProp.getProperty("THREADPOOL_SIZE"));
		
		if(multiProp.getProperty("SERVICE").equals("ECHO")){
			server = new MultiThreadServer(portNum, threadType, threadPoolSize, new EchoService());
		}else if (multiProp.getProperty("SERVICE").equals("SLOW")){
			server = new MultiThreadServer(portNum, threadType, threadPoolSize, new SlowService());
		}else if (multiProp.getProperty("SERVICE").equals("LOG")){
			server = new MultiThreadServer(portNum, threadType, threadPoolSize, new LogService(logLevel));
		}else{
			System.err.println("Multi Server Configuration: Invalid service.");
		}
		return server;
		
	}
	
}

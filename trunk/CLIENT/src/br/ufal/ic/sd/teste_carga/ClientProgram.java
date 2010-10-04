package br.ufal.ic.sd.teste_carga;

import java.io.IOException;


/**
 * 
 * Programa cliente:
 * 
 * Realiza teste de carga no servidor. 
 * 
 * @author Alaynne Moreira
 * 
 */

public class ClientProgram {

	public static void main(String[] args) throws IOException{
		
		int thAmount;
		String serverName, serverType, service;
		TesteCarga teste = new TesteCarga();
		
		if(args.length != 4){
			System.err.println("Parameter size is wrong.");
			System.exit(1);
		}
		
		thAmount = Integer.parseInt(args[0]);
		serverName = args[1];
		service = args[3];
		if((serverType = args[2]).equals("MONOTHREAD")){
			// Realiza teste de carga num servidor MonoThread.
			teste.fazerTesteCargaMonoServer(thAmount, serverName, serverType, service);
		}else if(serverType.equals("MULTITHREAD")){
			// Realiza teste de carga num servidor MultiThread.
			teste.fazerTesteCargaMultiServer(thAmount, serverName, serverType, service);
		}else{
			System.err.println("Client Program: Invalid server type.");
			System.exit(1);
		}
	}

}

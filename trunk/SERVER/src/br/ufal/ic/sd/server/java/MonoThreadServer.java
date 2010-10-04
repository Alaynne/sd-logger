package br.ufal.ic.sd.server.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;
import br.ufal.ic.sd.service.java.*;

/**
 * 
 * Servidor MonoThread.
 * 
 * @author Alaynne Moreira
 *
 */

public class MonoThreadServer implements Server{

	private String name;
	private int portNum;
	private Service service;
	private ServerSocket serverSocket = null;
	private boolean listening = false;
	
	public MonoThreadServer(int portNum, Service service){
		
		this.name = "MONOSERVER";
		this.portNum = portNum;
		this.service = service;
		
	}
	
	// Inicia o servidor.
	public void start(){
		try{
	    	// O servidor pode ouvir na porta de numero portNum.
	        serverSocket = new ServerSocket(portNum);
	        listening = true;
	        System.out.println(name + ": Started.");
	    }catch (IOException e){
	        // O servidor naum pode ouvir na porta de numero portNum.
	    	// O programa eh encerrado.
	        System.err.println(name + ": Could not listen on port " + portNum);
	        System.exit(1);
	    }
	}
	
	// Executa o servidor.
	public void run() throws IOException{
		
		Socket clientSocket = null;
		
	    try{
	        // A conexao com o cliente foi aceita.
	    	// O servidor aguarda requisicao do cliente.
	        clientSocket = serverSocket.accept();
	        System.out.println(name + ": Connection received. Waiting request.");
	    }catch (IOException e){
	        // A conexao com o cliente naum foi aceita.
	    	// O programa eh encerrado.
	        System.err.println(name + ": Accept failed.");
	        System.exit(1);
	    }
	
	    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
	    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	    String request, answer;
	        
	    // Enquanto o servidor receber requisicao do cliente ...
	    while((request = in.readLine()) != null) {
	    	if(request.equals("Close conection.")){
	    		// O cliente solicitou o encerramento da conexao.
	    		// O servidor responde com "Bye.".
	    		out.println("Bye.");
	    		System.out.println("CLIENT: Close conection.");
	    		System.out.println(name + ": Bye.");
	    		break;
	    	}else{
	    		// O servidor atende a requisicao.
	    		answer = service.doService(request, out, name);
	    		System.out.println("CLIENT: " + request);
	    		System.out.println(name + ": " + answer);
	    	}
	    }
	    out.close();
        in.close();
        clientSocket.close();
        
	}
	
	// Encerra o servidor.
	public void shutDown() throws IOException{
		
		listening = false;
		serverSocket.close();
		
	}
	
	// Verifica se o servidor estah ouvindo.
	public boolean isListening(){
		
		return listening;
		
	}
	
}

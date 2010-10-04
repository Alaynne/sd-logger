package br.ufal.ic.sd.server.java;

import java.io.IOException;
import java.net.ServerSocket;
import br.ufal.ic.sd.service.java.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;


/**
 * 
 * Servidor MultiThread:
 * 
 * Utiliza thread ou thread pool para atender as requisicoes dos clientes.
 * 
 * @author Alaynne Moreira
 *
 */

public class MultiThreadServer implements Server{
	
	private String name;
	private int portNum;
	private String threadType;
	private int threadPoolSize;
	private ExecutorService threadPool = null; 
	private Service service = null;
	private ServerSocket serverSocket = null;
	private boolean listening = false;
	
	public MultiThreadServer(int portNum, String threadType, int threadPoolSize, Service service){
		
		this.portNum = portNum;
		this.threadType = threadType;
		this.threadPoolSize = threadPoolSize;
		this.service = service;
		if(this.threadType == "THREAD"){
			this.name = "MULTISERVER (THREAD)";
		}else if(this.threadType == "THREADPOOL"){
			threadPool = Executors.newFixedThreadPool( this.threadPoolSize );
			this.name = "MULTISERVER (THREADPOOL)";
		}else{
			System.err.println("MULTITHREAD SERVER: Invalid thread type.");
			System.exit(1);
		}
		
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
		
		if(threadType == "THREAD"){
			// O servidor utiliza thread.
			try{
				// A conexao com o cliente foi aceita.
				// O servidor cria uma nova thread e aguarda requisicao do cliente.
				new ServerThread(serverSocket.accept(), service, name).start();
				System.out.println(name + ": Connection received. Waiting request.");
			}catch (IOException e){
				// A conexao com o cliente naum foi aceita.
				// O programa eh encerrado.
				System.err.println(name + ": Accept failed.");
				System.exit(1);
			}
		}else if(threadType == "THREADPOOL"){
			// O servidor utiliza thread pool.
			try{
				// A conexao com o cliente foi aceita.
				// O servidor aguarda requisicao do cliente, a qual serah executada por uma thread da thread pool.
				threadPool.execute( new ThreadPoolTask(serverSocket.accept(), service, name));
				System.out.println(name + ": Connection received. Waiting request.");
			}catch (IOException e){
				// A conexao com o cliente naum foi aceita.
				// O programa eh encerrado.
				System.err.println(name + ": Accept failed.");
				System.exit(1);
			}
		}
		
	}
	
	// Encerra o servidor.
	public void shutDown()throws IOException{
		
		listening = false;
		serverSocket.close();
		
	}
	
	// Verifica se o servidor estah ouvindo.
	public boolean isListening(){
		
		return listening;
		
	}
	
}

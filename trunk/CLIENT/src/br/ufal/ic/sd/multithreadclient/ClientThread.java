package br.ufal.ic.sd.multithreadclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import br.ufal.ic.sd.teste_carga.TesteCarga;

/**
 * 
 * Thread cliente.
 * 
 * @author Alaynne Moreira
 *
 */

public class ClientThread extends Thread {
	
	private int id;
	
	public ClientThread(int id){
	
		this.id = id;
		
	}
	
	// Executa a thread cliente.
	@Override
	public void run() {
		
		long startTime, endTime;
		
		try{
			Socket socket = null;
	        PrintWriter out = null;
	        BufferedReader in = null;

	      	try{
	        	// O cliente conecta-se ao servidor 127.0.0.1 (local host) atraves da porta 4444.
	        	socket = new Socket("127.0.0.1", 4444);
	        	out = new PrintWriter(socket.getOutputStream(), true);
	        	in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        }catch (UnknownHostException e){
	        	// O cliente naum conhece o servidor. O programa eh encerrado.
	        	System.err.println("CLIENT " + id + ": Don't know about this host (127.0.0.1)");
	        	System.exit(1);
	        }catch (IOException e){
	        	// O cliente naum consegue conectar-se ao servidor. O programa eh encerrado.
	        	System.err.println("CLIENT " + id + ": Couldn't get I/O for the connection to local host.");
	        	System.exit(1);
	        }
	        
	        startTime = System.currentTimeMillis();
	        
	        // O cliente envia uma requisicao para o servidor e recebe uma resposta.
	        out.println("CLIENT " + id + " says Hello!");
	        in.readLine();
	        
	        endTime = System.currentTimeMillis();
	        
	        // Calcula o tempo de atendimento da requisicao.
	        TesteCarga.calcTurnaroundTime(id, startTime, endTime);
	        
	        out.close();
	        in.close();
	        socket.close();
		}catch (Exception e){
			e.printStackTrace();
		}	
		
	}
	
}

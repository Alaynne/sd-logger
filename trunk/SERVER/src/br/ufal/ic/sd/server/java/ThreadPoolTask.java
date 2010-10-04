package br.ufal.ic.sd.server.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import br.ufal.ic.sd.service.java.Service;

/**
 * 
 * Tarefa executada pela thread pool.
 * 
 * @author Alaynne Moreira
 *
 */

public class ThreadPoolTask implements Runnable{

	private String serverName;
	private Service service;
	private Socket socket = null;

    public ThreadPoolTask(Socket socket, Service service, String serverName) {
        this.socket = socket;
        this.service = service;
        this.serverName = serverName;
    }

    // Executa a tarefa da thread pool.
	@Override
    public void run() {

        try{
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String request, answer;

            // Enquanto o servidor receber requisicao do cliente ...
            while ((request = in.readLine()) != null) {
            	if(request.equals("Close conection.")){
    	    		// O cliente solicitou o encerramento da conexao.
    	    		// O servidor responde com "Bye.".
    	    		out.println("Bye.");
    	    		System.out.println("CLIENT: Close conection.");
    	    		System.out.println(serverName + ": Bye.");
    	    		break;
    	    	}else{
    	    		// O servidor atende a requisicao.
    	    		answer = service.doService(request, out, serverName);
    	    		System.out.println("CLIENT: " + request);
    	    		System.out.println(serverName + ": " + answer);
    	    	}
            }
            out.close();
            in.close();
            socket.close();

        }catch (IOException e){
            e.printStackTrace();
        }
        
    }
	
}

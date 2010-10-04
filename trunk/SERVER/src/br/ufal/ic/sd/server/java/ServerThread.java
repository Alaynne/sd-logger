package br.ufal.ic.sd.server.java;

import java.net.*;
import java.io.*;
import br.ufal.ic.sd.service.java.*;

/**
 * 
 * Thread do servidor MultiThread.
 * 
 * @author Alaynne Moreira
 *
 */

public class ServerThread extends Thread{

	private String serverName;
	private Service service;
	private Socket socket = null;

    public ServerThread(Socket socket, Service service, String serverName) {
        super("ServerThread");
        this.socket = socket;
        this.service = service;
        this.serverName = serverName;
    }

    // Executa a thread do servidor.
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

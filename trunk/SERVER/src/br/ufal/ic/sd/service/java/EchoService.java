package br.ufal.ic.sd.service.java;

import java.io.PrintWriter;

/**
 * 
 * Servico de eco:
 * 
 * Gera, como resposta, o mesmo que recebeu como entrada.
 * 
 * @author Alaynne Moreira
 *
 */

public class EchoService implements Service{

	public String doService(String inputLine, PrintWriter out, String serverId){
		
		String answer;
		
		answer = inputLine;
		out.println(answer);
		return answer;
		
	}
	
}

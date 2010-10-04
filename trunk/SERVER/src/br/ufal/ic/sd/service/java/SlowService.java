package br.ufal.ic.sd.service.java;

import java.io.PrintWriter;

/**
 * 
 * Servico lento:
 * 
 * Gasta uma determinada quantidade de tempo e responde OK.
 * 
 * @author Alaynne Moreira
 *
 */

public class SlowService implements Service{

	private long SLEEP_TIME = 2000;
	
	public String doService(String request, PrintWriter out, String serverId){
		
		String answer;
		
		try {
			Thread.sleep(SLEEP_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		answer = "OK";
		out.println(answer);
		return answer;
		
	}
	
}

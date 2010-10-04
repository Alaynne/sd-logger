package br.ufal.ic.sd.service.java;

import java.io.PrintWriter;

/**
 * 
 * Servico generico do servidor.
 * 
 * @author Alaynne Moreira
 *
 */

public interface Service {

	public String doService(String request, PrintWriter out, String serverId);
	
}

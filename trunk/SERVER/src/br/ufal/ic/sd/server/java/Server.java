package br.ufal.ic.sd.server.java;

import java.io.IOException;

/**
 * 
 * Servidor generico.
 * 
 * @author Alaynne Moreira
 *
 */

public interface Server {

	// Inicia o servidor.
	public void start();
	
	// Executa o servidor.
	public void run() throws IOException;
	
	// Encerra o servidor.
	public void shutDown() throws IOException;
	
	// Verifica se o servidor estah ouvindo.
	public boolean isListening();
	
}
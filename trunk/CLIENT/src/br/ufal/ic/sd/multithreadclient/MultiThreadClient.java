package br.ufal.ic.sd.multithreadclient;

/**
 * 
 * Cliente MultiThread.
 * 
 * @author Alaynne Moreira
 *
 */

public class MultiThreadClient {
	
	private int thAmount;
	
	public MultiThreadClient(int thAmount) {
		
		this.thAmount = thAmount;
	
	}

	// Executa o cliente MultiThread.
	public void runMTClient() {
		
		for(int i = 1; i <= thAmount; i++){
			// Cria uma nova thread para o cliente e a inicializa.
			ClientThread ct = new ClientThread(i);
			ct.start();
		}
		
	}
	
}

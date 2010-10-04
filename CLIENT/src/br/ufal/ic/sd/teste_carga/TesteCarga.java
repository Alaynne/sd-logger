package br.ufal.ic.sd.teste_carga;

import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;

import br.ufal.ic.sd.multithreadclient.ClientThread;
import br.ufal.ic.sd.multithreadclient.MultiThreadClient;

/**
 * 
 * Teste de carga num servidor.
 *  
 * @author Alaynne Moreira
 * 
 */

public class TesteCarga {
	
	private static int servedReqNum = 0;
	private static double totalReqTime = 0;

	// Realiza teste de carga num servidor MultiThread, com clientNum clientes.
	public void fazerTesteCargaMultiServer(int clientNum, String serverName, String serverType, String service) throws IOException{
		
		ThreadGroup grp = Thread.currentThread().getThreadGroup();
		Thread th[] = new Thread[clientNum + 1];
		Date data = new Date();
		
		TesteCarga.report("*** Teste de carga com " + clientNum + " clientes (" + data.toLocaleString() + ") ***");
		TesteCarga.report("*** Nome do servidor testado: " + serverName + "  ***");
		TesteCarga.report("*** Tipo do servidor testado: " + serverType + "  ***");
		TesteCarga.report("*** Serviço testado: " + service + "  ***");
		
		// Cria um cliente MultiThread e o executa.
		MultiThreadClient mtClient = new MultiThreadClient(clientNum);
		mtClient.runMTClient();
		
		while(grp.enumerate(th) > 1){
			;
		}
		
		// Calcula Throughput Time.
		TesteCarga.calcThroughputTime();		
		
	}
	
	// Realiza teste de carga num servidor MonoThread, com clientNum clientes.
	public void fazerTesteCargaMonoServer(int clientNum, String serverName, String serverType, String service) throws IOException{
		
		ThreadGroup grp = Thread.currentThread().getThreadGroup();
		Thread th[] = new Thread[clientNum + 1];
		Date data = new Date();
		
		TesteCarga.report("*** Teste de carga com " + clientNum + " clientes (" + data.toLocaleString() + ") ***");
		TesteCarga.report("*** Nome do servidor testado: " + serverName + "  ***");
		TesteCarga.report("*** Tipo do servidor testado: " + serverType + "  ***");
		TesteCarga.report("*** Serviço testado: " + service + "  ***");
		
		for(int i = 1; i <= clientNum; i++){
			// Cria uma nova thread cliente e a inicializa.
			ClientThread ct = new ClientThread(i);
			ct.start();
			
			// Enquanto a thread client estiver executando ... 
			while(grp.enumerate(th) > 1){
				// Naum faz nada!
				;
			}
		}
		
		// Calcula Throughput Time.
		TesteCarga.calcThroughputTime();		
		
	}
	
	// Calcula Turnaround Time: tempo para atender uma requisicao particular.
	public static void calcTurnaroundTime(int clientId, long startTime, long endTime) throws IOException{
	
		long requestTime;
		
		requestTime = endTime - startTime;
		servedReqNum++;
        totalReqTime += (double)requestTime;
        
        TesteCarga.report("Cliente " + clientId + ": Turnaround Time = " +
				requestTime + " milisegundos.");
		
	}
	
	// Calcula Throughput Time: numero de requisicoes atendidas por segundo.
	public static void calcThroughputTime() throws IOException{
		
		double tpTime;
		
		tpTime = ((double)servedReqNum)/(totalReqTime/(double)(1000));
        TesteCarga.report("Throughput Time: " + tpTime + " requisicoes/segundo.");
        
	}
	
	// Registra uma informacao (info) no relatorio de teste de carga.
	public static void report(String info) throws IOException{
		
		FileWriter fileWriter = new FileWriter("teste_carga/relatorio.txt", true);  
        PrintWriter writer = new PrintWriter(fileWriter); 
        
        writer.println(info);
		writer.close();  
		fileWriter.close();
		
		System.out.println(info);
		
	}
	
}

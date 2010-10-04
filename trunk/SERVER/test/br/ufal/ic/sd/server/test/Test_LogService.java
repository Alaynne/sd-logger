package br.ufal.ic.sd.server.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import br.ufal.ic.sd.lsprotocol_vrs1.LogLevel.LSP_LogLevel;
import br.ufal.ic.sd.service.java.LogService;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Teste do servico de log (LogService)
 * 
 * @author Alaynne Moreira
 *
 */


public class Test_LogService {
	
	@Test
	// Testa o servico de log configurado para o nivel de log 'DEBUG'
	public void testDoService1(){
		LogService logService = new LogService(LSP_LogLevel.DEBUG);
		Socket clientSocket = new Socket();
		String requestMessage, answerMessage;
		
		try{
			requestMessage = "LSP/1.0;LOG_DEBUG;-1;MyApplication;Hello World;";
			answerMessage = "LSP/1.0;LOG_OK;MyServer;" + new Date().toString() + ";-1;;";
			assertEquals(answerMessage ,logService.doService(requestMessage, new PrintWriter(clientSocket.getOutputStream(), true), "MyServer"));
				
			requestMessage = "LSP/1.0;LOG_INFO;-1;MyApplication;Hello World;";
			answerMessage = "LSP/1.0;LOG_OK;MyServer;" + new Date().toString() + ";-1;;";
			assertEquals(answerMessage, logService.doService(requestMessage, new PrintWriter(clientSocket.getOutputStream(), true), "MyServer"));
				
			requestMessage = "LSP/1.0;LOG_WARN;-1;MyApplication;Hello World;";
			answerMessage = "LSP/1.0;LOG_OK;MyServer;" + new Date().toString() + ";-1;;";
			assertEquals(answerMessage, logService.doService(requestMessage, new PrintWriter(clientSocket.getOutputStream(), true), "MyServer"));
				
			requestMessage = "LSP/1.0;LOG_ERROR;-1;MyApplication;Hello World;";
			answerMessage = "LSP/1.0;LOG_OK;MyServer;" + new Date().toString() + ";-1;;";
			assertEquals(answerMessage, logService.doService(requestMessage, new PrintWriter(clientSocket.getOutputStream(), true), "MyServer"));
				
			requestMessage = "LSP/1.0;LOG_FATAL;-1;MyApplication;Hello World;";
			answerMessage = "LSP/1.0;LOG_OK;MyServer;" + new Date().toString() + ";-1;;";
			assertEquals(answerMessage, logService.doService(requestMessage, new PrintWriter(clientSocket.getOutputStream(), true), "MyServer"));
		
			requestMessage = "LSP/1.0;LIST_DEBUG;0;MyApplication;;";
			assertNotNull(answerMessage, logService.doService(requestMessage, new PrintWriter(clientSocket.getOutputStream(), true), "MyServer"));
			
			requestMessage = "LSP/1.0;LIST_INFO;0;MyApplication;;";
			assertNotNull(answerMessage, logService.doService(requestMessage, new PrintWriter(clientSocket.getOutputStream(), true), "MyServer"));
			
			requestMessage = "LSP/1.0;LIST_WARN;0;MyApplication;;";
			assertNotNull(answerMessage, logService.doService(requestMessage, new PrintWriter(clientSocket.getOutputStream(), true), "MyServer"));
		
			requestMessage = "LSP/1.0;LIST_ERROR;0;MyApplication;;";
			assertNotNull(answerMessage, logService.doService(requestMessage, new PrintWriter(clientSocket.getOutputStream(), true), "MyServer"));
		
			requestMessage = "LSP/1.0;LIST_FATAL;0;MyApplication;;";
			assertNotNull(answerMessage, logService.doService(requestMessage, new PrintWriter(clientSocket.getOutputStream(), true), "MyServer"));
		
			requestMessage = "LSP/1.0;LIST_ALL;0;MyApplication;;";
			assertNotNull(answerMessage, logService.doService(requestMessage, new PrintWriter(clientSocket.getOutputStream(), true), "MyServer"));
		}catch(IOException e){
			;
		}
	}
	
	@Test
	// Testa o servico de log configurado para o nivel de log 'ERROR'
	public void testDoService2() {
		LogService logService = new LogService(LSP_LogLevel.ERROR);
		Socket clientSocket = new Socket();
		String requestMessage, answerMessage;
		
		try {
			requestMessage = "LSP/1.0;LOG_DEBUG;-1;MyApplication;Hello World;";
			answerMessage = "LSP/1.0;LOG_OK;MyServer;" + new Date().toString() + ";-1;;";
			assertEquals(answerMessage ,logService.doService(requestMessage, new PrintWriter(clientSocket.getOutputStream(), true), "MyServer"));
				
			requestMessage = "LSP/1.0;LOG_INFO;-1;MyApplication;Hello World;";
			answerMessage = "LSP/1.0;LOG_OK;MyServer;" + new Date().toString() + ";-1;;";
			assertEquals(answerMessage, logService.doService(requestMessage, new PrintWriter(clientSocket.getOutputStream(), true), "MyServer"));
				
			requestMessage = "LSP/1.0;LOG_WARN;-1;MyApplication;Hello World;";
			answerMessage = "LSP/1.0;LOG_OK;MyServer;" + new Date().toString() + ";-1;;";
			assertEquals(answerMessage, logService.doService(requestMessage, new PrintWriter(clientSocket.getOutputStream(), true), "MyServer"));
				
			requestMessage = "LSP/1.0;LOG_ERROR;-1;MyApplication;Hello World;";
			answerMessage = "LSP/1.0;LOG_OK;MyServer;" + new Date().toString() + ";-1;;";
			assertEquals(answerMessage, logService.doService(requestMessage, new PrintWriter(clientSocket.getOutputStream(), true), "MyServer"));
				
			requestMessage = "LSP/1.0;LOG_FATAL;-1;MyApplication;Hello World;";
			answerMessage = "LSP/1.0;LOG_OK;MyServer;" + new Date().toString() + ";-1;;";
			assertEquals(answerMessage, logService.doService(requestMessage, new PrintWriter(clientSocket.getOutputStream(), true), "MyServer"));
		
			requestMessage = "LSP/1.0;LIST_DEBUG;0;MyApplication;;";
			assertNotNull(answerMessage, logService.doService(requestMessage, new PrintWriter(clientSocket.getOutputStream(), true), "MyServer"));
			
			requestMessage = "LSP/1.0;LIST_INFO;0;MyApplication;;";
			assertNotNull(answerMessage, logService.doService(requestMessage, new PrintWriter(clientSocket.getOutputStream(), true), "MyServer"));
			
			requestMessage = "LSP/1.0;LIST_WARN;0;MyApplication;;";
			assertNotNull(answerMessage, logService.doService(requestMessage, new PrintWriter(clientSocket.getOutputStream(), true), "MyServer"));
		
			requestMessage = "LSP/1.0;LIST_ERROR;0;MyApplication;;";
			assertNotNull(answerMessage, logService.doService(requestMessage, new PrintWriter(clientSocket.getOutputStream(), true), "MyServer"));
		
			requestMessage = "LSP/1.0;LIST_FATAL;0;MyApplication;;";
			assertNotNull(answerMessage, logService.doService(requestMessage, new PrintWriter(clientSocket.getOutputStream(), true), "MyServer"));
		
			requestMessage = "LSP/1.0;LIST_ALL;0;MyApplication;;";
			assertNotNull(answerMessage, logService.doService(requestMessage, new PrintWriter(clientSocket.getOutputStream(), true), "MyServer"));
		}catch(IOException e){
			;
		}
	}
	
}

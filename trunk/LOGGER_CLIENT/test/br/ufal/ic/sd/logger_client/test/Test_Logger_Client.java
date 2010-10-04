package br.ufal.ic.sd.logger_client.test;

import org.junit.Test;
import br.ufal.ic.sd.logger_client.LoggerClient;
import br.ufal.ic.sd.lsprotocol_vrs1.LogLevel.LSP_LogLevel;
import static org.junit.Assert.*;

/**
 * Teste de Logger_Client
 * 
 * @author Alaynne Moreira
 *
 */

public class Test_Logger_Client {

	private LoggerClient lc = new LoggerClient("MyApplication");
	
	@Test
	// Testa o nome da aplicacao para a qual logger_client trabalha
	public void testGetApplicationName(){
		
		assertEquals("MyApplication", lc.getApplicationName());
	
	}
	
	@Test
	// Testa o nivel de log default de logger_client
	public void testGetLevel(){
		
		assertEquals(LSP_LogLevel.DEBUG.toString(), lc.getLevel().toString());
	
	}
	
	@Test
	// Testa os niveis de log para os quais logger_client estah habilitado
	public void testIsLoggable(){
		
		assertEquals(true, lc.isLoggable(LSP_LogLevel.DEBUG));
		assertEquals(true, lc.isLoggable(LSP_LogLevel.INFO));
		assertEquals(true, lc.isLoggable(LSP_LogLevel.WARN));
		assertEquals(true, lc.isLoggable(LSP_LogLevel.ERROR));
		assertEquals(true, lc.isLoggable(LSP_LogLevel.FATAL));
		
		lc.setLevel(LSP_LogLevel.WARN);
		
		assertEquals(LSP_LogLevel.WARN.toString(), lc.getLevel().toString());
		
		assertEquals(false, lc.isLoggable(LSP_LogLevel.DEBUG));
		assertEquals(false, lc.isLoggable(LSP_LogLevel.INFO));
		assertEquals(true, lc.isLoggable(LSP_LogLevel.WARN));
		assertEquals(true, lc.isLoggable(LSP_LogLevel.ERROR));
		assertEquals(true, lc.isLoggable(LSP_LogLevel.FATAL));
		
		lc.setLevel(LSP_LogLevel.FATAL);
		
		assertEquals(LSP_LogLevel.FATAL.toString(), lc.getLevel().toString());
		
		assertEquals(false, lc.isLoggable(LSP_LogLevel.DEBUG));
		assertEquals(false, lc.isLoggable(LSP_LogLevel.INFO));
		assertEquals(false, lc.isLoggable(LSP_LogLevel.WARN));
		assertEquals(false, lc.isLoggable(LSP_LogLevel.ERROR));
		assertEquals(true, lc.isLoggable(LSP_LogLevel.FATAL));
		
	}
	
}

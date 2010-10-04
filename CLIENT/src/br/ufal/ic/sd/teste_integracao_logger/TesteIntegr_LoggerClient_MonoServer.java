package br.ufal.ic.sd.teste_integracao_logger;

import static org.junit.Assert.assertNotNull;
import java.io.IOException;
import java.util.Date;
import org.junit.Test;
import br.ufal.ic.sd.logger_client.LoggerClient;

/**
 * Realiza um teste de integracao entre o logger cliente e o servidor 
 * mono thread
 * 
 * @author Alaynne Moreira
 *
 */

public class TesteIntegr_LoggerClient_MonoServer {

	@Test
	public void fazerTesteIntegracao(){
		
		LoggerClient lc = new LoggerClient("AplicTest");
		String listaLogs = null;
		
		try {
			lc.debug("Hello Debug");
			lc.info("Hello Info");
			lc.warn("Hello Warn");
			lc.error("Hello Error");
			lc.fatal("Hello Fatal");
			
			listaLogs = lc.listDebug(new Date());
			assertNotNull(listaLogs);
			System.out.println(listaLogs);
			
			listaLogs = lc.listInfo(new Date());
			assertNotNull(listaLogs);
			System.out.println(listaLogs);
			
			listaLogs = lc.listWarn(new Date());
			assertNotNull(listaLogs);
			System.out.println(listaLogs);
			
			listaLogs = lc.listError(new Date());
			assertNotNull(listaLogs);
			System.out.println(listaLogs);
			
			listaLogs = lc.listFatal(new Date());
			assertNotNull(listaLogs);
			System.out.println(listaLogs);
			
			listaLogs = lc.listAll(new Date());
			assertNotNull(listaLogs);
			System.out.println(listaLogs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

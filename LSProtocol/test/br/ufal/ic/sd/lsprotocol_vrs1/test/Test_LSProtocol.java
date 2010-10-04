package br.ufal.ic.sd.lsprotocol_vrs1.test;

import java.util.Date;
import org.junit.Test;
import br.ufal.ic.sd.lsprotocol_vrs1.LSProtocol;
import br.ufal.ic.sd.lsprotocol_vrs1.AnswerMsg.LSP_AnswerMessage;
import br.ufal.ic.sd.lsprotocol_vrs1.AnswerMsg.LSP_AnswerResult;
import br.ufal.ic.sd.lsprotocol_vrs1.RequestMsg.LSP_RequestCommand;
import br.ufal.ic.sd.lsprotocol_vrs1.RequestMsg.LSP_RequestMessage;
import static org.junit.Assert.*;

/**
 * Teste de LSProtocol
 * 
 * @author Alaynne Moreira
 *
 */

public class Test_LSProtocol {

	private LSProtocol protocol = new LSProtocol();
	
	@Test
	// Testa a codificacao de uma mensagem de requisicao de log
	public void testCodeLogRequest(){
		
		// Testa a codificacao com parametros nulos
		assertEquals(null, protocol.codeLogRequest(null, null, null));
		assertEquals(null, protocol.codeLogRequest(null, "MyApplication", "Hello World"));
		assertEquals(null, protocol.codeLogRequest(LSP_RequestCommand.LOG_INFO, null, 
				"Hello World"));
		assertEquals(null, protocol.codeLogRequest(LSP_RequestCommand.LOG_WARN, "MyApplication",
				null));
		// Testa a codificacao com um comando invalido para uma requisicao de log
		assertEquals(null, protocol.codeLogRequest(LSP_RequestCommand.LIST_INFO, "MyApplication", 
				"Hello World"));
		// Testa a codificacao com parametros vazios
		assertEquals(null, protocol.codeLogRequest(LSP_RequestCommand.LOG_ERROR, "", 
				"Hello World"));
		assertEquals(null, protocol.codeLogRequest(LSP_RequestCommand.LOG_DEBUG, "MyApplication", 
				""));
		// Testa a codificacao com todos os parametros validos
		String requestMessage = "LSP/1.0;LOG_FATAL;-1;MyApplication;Hello World;";
		assertEquals(requestMessage, protocol.codeLogRequest(LSP_RequestCommand.LOG_FATAL, 
				"MyApplication", "Hello World"));
		
	}
	
	@Test
	// Testa a codificacao de uma mensagem de requisicao de listagem
	public void testCodeListRequest(){
		
		// Testa a codificacao com parametros nulos
		assertEquals(null, protocol.codeListRequest(null, null, -2));
		assertEquals(null, protocol.codeListRequest(null, "MyApplication", 0));
		assertEquals(null, protocol.codeListRequest(LSP_RequestCommand.LIST_INFO, null, 0));
		assertEquals(null, protocol.codeListRequest(LSP_RequestCommand.LIST_INFO, 
				"MyApplication", -2));
		// Testa a codificacao com um comando invalido para uma requisicao de listagem
		assertEquals(null, protocol.codeListRequest(LSP_RequestCommand.LOG_INFO, "MyApplication", 
				3));
		// Testa a codificacao com um valor invalido para o campo logged_since
		assertEquals(null, protocol.codeListRequest(LSP_RequestCommand.LIST_WARN, "MyApplication", 
				-3));
		// Testa a codificacao com parametros vazios
		assertEquals(null, protocol.codeListRequest(LSP_RequestCommand.LIST_ERROR, "", 7));
		assertEquals(null, protocol.codeListRequest(LSP_RequestCommand.LIST_DEBUG, 
				"MyApplication", -1));
		// Testa a codificacao com todos os parametros validos
		String requestMessage = "LSP/1.0;LIST_FATAL;2;MyApplication;;";
		assertEquals(requestMessage, protocol.codeListRequest(LSP_RequestCommand.LIST_FATAL, 
				"MyApplication", 2));
		
	}
	
	@Test
	// Testa a codificacao de uma mensagem de resposta para uma requisicao de log
	public void testCodeLogAnswer(){
		
		// Testa a codificacao com parametros nulos
		assertEquals(null, protocol.codeLogAnswer(null, null));
		assertEquals(null, protocol.codeLogAnswer(null, "MyServer"));
		assertEquals(null, protocol.codeLogAnswer(LSP_AnswerResult.LOG_OK, null));
		// Testa a codificacao com um resultado invalido para uma requisicao de log
		assertEquals(null, protocol.codeLogAnswer(LSP_AnswerResult.Not_Found, "MyServer"));
		// Testa a codificacao com parametro vazio
		assertEquals(null, protocol.codeLogAnswer(LSP_AnswerResult.Bad_Request, ""));
		// Testa a codificacao com todos os parametros validos
		String answerMessage = "LSP/1.0;Error_On_Server;MyServer;" + new Date().toString() + ";-1;;";
		assertEquals(answerMessage, protocol.codeLogAnswer(LSP_AnswerResult.Error_On_Server, 
				"MyServer"));
		
	}
	
	@Test
	// Testa a codificacao de uma mensagem de resposta para uma requisicao de listagem
	public void testCodeListAnswer(){
		
		// Testa a codificacao com parametros nulos
		assertEquals(null, protocol.codeListAnswer(null, null, -2, null));
		assertEquals(null, protocol.codeListAnswer(null, "MyServer", 1, "INFO - Hello World"));
		assertEquals(null, protocol.codeListAnswer(LSP_AnswerResult.LIST_OK, null, 1, "INFO - Hello World"));
		assertEquals(null, protocol.codeListAnswer(LSP_AnswerResult.LIST_OK, "MyServer", -2, "INFO - Hello World"));
		assertEquals(null, protocol.codeListAnswer(LSP_AnswerResult.LIST_OK, "MyServer", 2, null));
		// Testa a codificacao com parametros vazios
		assertEquals(null, protocol.codeListAnswer(LSP_AnswerResult.LIST_OK, "", 1, "INFO - Hello World"));
		assertEquals(null, protocol.codeListAnswer(LSP_AnswerResult.LIST_OK, "MyServer", -1, "INFO - Hello World"));
		assertEquals(null, protocol.codeListAnswer(LSP_AnswerResult.LIST_OK, "MyServer", 1, ""));
		// Testa a codificacao com um valor invalido para o campo content_length
		assertEquals(null, protocol.codeListAnswer(LSP_AnswerResult.LIST_OK, "MyServer", 0, "INFO - Hello World"));
		// Testa a codificacao com todos os parametros validos
		String answerMessage = "LSP/1.0;Bad_Request;MyServer;" + new Date().toString() + ";-1;;";
		assertEquals(answerMessage, protocol.codeListAnswer(LSP_AnswerResult.Bad_Request, "MyServer", -1, ""));	
		
	}
	
	@Test
	// Testa a decodificacao de uma mensagem de requisicao
	public void testDecodeRequestMessage(){
		
		// Testa a decodificacao com parametro nulo
		assertEquals(null, protocol.decodeRequestMessage(null));
		// Testa a decodificacao com uma mensagem de requisicao incompleta
		assertEquals(null, protocol.decodeRequestMessage("LOG_FATAL;-1;MyApplication;Hello World;"));
		assertEquals(null, protocol.decodeRequestMessage("LSP/1.0;-1;MyApplication;Hello World;"));
		assertEquals(null, protocol.decodeRequestMessage("LSP/1.0;LOG_FATAL;MyApplication;Hello World;"));
		assertEquals(null, protocol.decodeRequestMessage("LSP/1.0;LOG_FATAL;-1;Hello World;"));
		assertEquals(null, protocol.decodeRequestMessage("LSP/1.0;LOG_FATAL;-1;MyApplication;"));
		// Testa a decodificacao com uma mensagem de requisicao com campos vazios
		assertEquals(null, protocol.decodeRequestMessage(";LOG_FATAL;-1;MyApplication;Hello World;"));
		assertEquals(null, protocol.decodeRequestMessage("LSP/1.0;;-1;MyApplication;Hello World;"));
		assertEquals(null, protocol.decodeRequestMessage("LSP/1.0;LOG_FATAL;;MyApplication;Hello World;"));
		assertEquals(null, protocol.decodeRequestMessage("LSP/1.0;LOG_FATAL;-1;;Hello World;"));
		assertEquals(null, protocol.decodeRequestMessage("LSP/1.0;LOG_FATAL;-1;MyApplication;;"));
		// Testa a decodificacao com uma mensagem de requisicao invalida
		assertEquals(null, protocol.decodeRequestMessage("LSP/1.0;LOG_FATAL;1;MyApplication;Hello World;"));
		assertEquals(null, protocol.decodeRequestMessage("LSP/1.0;LIST_FATAL;2;MyApplication;Hello World;"));
		// Testa a decodificacao com uma mensagem de requisicao valida
		LSP_RequestMessage request = new LSP_RequestMessage("LSP/1.0", LSP_RequestCommand.LOG_FATAL, -1, "MyApplication", "Hello World");
		assertEquals(request.toString(), (protocol.decodeRequestMessage("LSP/1.0;LOG_FATAL;-1;MyApplication;Hello World;")).toString());
		request = new LSP_RequestMessage("LSP/1.0", LSP_RequestCommand.LIST_FATAL, 2, "MyApplication", "");
		assertEquals(request.toString(), (protocol.decodeRequestMessage("LSP/1.0;LIST_FATAL;2;MyApplication;;")).toString());
		
	}
	
	@Test
	// Testa a decodificacao de uma mensagem de resposta
	public void testDecodeAnswerMessage(){
		
		// Testa a decodificacao com parametro nulo
		assertEquals(null, protocol.decodeAnswerMessage(null));
		// Testa a decodificacao com uma mensagem de resposta incompleta
		assertEquals(null, protocol.decodeAnswerMessage("Error_On_Server;MyServer;" + new Date().toString() + ";-1;;"));
		assertEquals(null, protocol.decodeAnswerMessage("LSP/1.0;MyServer;" + new Date().toString() + ";-1;;"));
		assertEquals(null, protocol.decodeAnswerMessage("LSP/1.0;Error_On_Server;" + new Date().toString() + ";-1;;"));
		assertEquals(null, protocol.decodeAnswerMessage("LSP/1.0;Error_On_Server;MyServer;-1;;"));
		assertEquals(null, protocol.decodeAnswerMessage("LSP/1.0;Error_On_Server;MyServer;" + new Date().toString() + ";;"));
		assertEquals(null, protocol.decodeAnswerMessage("LSP/1.0;Error_On_Server;MyServer;" + new Date().toString() + ";-1;"));
		// Testa a decodificacao com uma mensagem de resposta com campos vazios
		assertEquals(null, protocol.decodeAnswerMessage(";Error_On_Server;MyServer;" + new Date().toString() + ";-1;;"));
		assertEquals(null, protocol.decodeAnswerMessage("LSP/1.0;;MyServer;" + new Date().toString() + ";-1;;"));
		assertEquals(null, protocol.decodeAnswerMessage("LSP/1.0;Error_On_Server;;" + new Date().toString() + ";-1;;"));
		assertEquals(null, protocol.decodeAnswerMessage("LSP/1.0;Error_On_Server;MyServer;;-1;;"));
		assertEquals(null, protocol.decodeAnswerMessage("LSP/1.0;Error_On_Server;MyServer;" + new Date().toString() + ";;;"));
		assertEquals(null, protocol.decodeAnswerMessage("LSP/1.0;Error_On_Server;MyServer;" + new Date().toString() + ";-1;"));
		// Testa a decodificacao com uma mensagem de resposta invalida
		assertEquals(null, protocol.decodeAnswerMessage("LSP/1.0;LIST_OK;MyServer;" + new Date().toString() + ";1;;"));
		assertEquals(null, protocol.decodeAnswerMessage("LSP/1.0;LIST_OK;MyServer;" + new Date().toString() + ";0;INFO - Hello World;"));
		assertEquals(null, protocol.decodeAnswerMessage("LSP/1.0;Error_On_Server;MyServer;" + new Date().toString() + ";1;;"));
		assertEquals(null, protocol.decodeAnswerMessage("LSP/1.0;Error_On_Server;MyServer;" + new Date().toString() + ";-1;INFO - Hello World;"));
		// Testa a decodificacao com uma mensagem de resposta valida
		LSP_AnswerMessage answer = new LSP_AnswerMessage("LSP/1.0", LSP_AnswerResult.LIST_OK,"MyServer", new Date().toString(), 1, "INFO - Hello World");
		assertEquals(answer.toString(),(protocol.decodeAnswerMessage("LSP/1.0;LIST_OK;MyServer;" + new Date().toString() + ";1;INFO - Hello World;")).toString());
		answer = new LSP_AnswerMessage("LSP/1.0", LSP_AnswerResult.LOG_OK,"MyServer", new Date().toString(), -1, "");
		assertEquals(answer.toString(),(protocol.decodeAnswerMessage("LSP/1.0;LOG_OK;MyServer;" + new Date().toString() + ";-1;;")).toString());
		
	}
	
}

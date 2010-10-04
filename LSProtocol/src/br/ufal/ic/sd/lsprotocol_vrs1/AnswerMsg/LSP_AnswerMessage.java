package br.ufal.ic.sd.lsprotocol_vrs1.AnswerMsg;

/**
 * Mensagem de resposta LSP
 * 
 * Campos da mensagem:
 * 
 * protocolVrs: versao do protocolo que estah sendo utilizada
 * result: resultado que caracteriza a mensagem de resposta
 * serverId: identificacao do servidor que enviou a mensagem de resposta
 * date: data da mensagem de resposta
 * content_length: utilizado apenas para responder a uma requisicao de listagem, para especificar
 * o total de logs listados no corpo da mensagem de resposta
 * body: corpo da mensagem (utilizado apenas para responder a uma requisicao de listagem, para
 * armazenar o conteudo listado)
 *  
 * @author Alaynne Moreira
 *
 */

public class LSP_AnswerMessage {
	
	// Resultados possiveis para uma requisicao de log
	private static final LSP_AnswerResult LOG_RESULTS[] = {LSP_AnswerResult.LOG_OK, 
		LSP_AnswerResult.Bad_Request, LSP_AnswerResult.Error_On_Server};
	// Resultados possiveis para uma requisicao de listagem
	private static final LSP_AnswerResult LIST_RESULTS[] = {LSP_AnswerResult.LIST_OK, 
		LSP_AnswerResult.Not_Found, LSP_AnswerResult.Bad_Request, 
		LSP_AnswerResult.Error_On_Server};
	
	// Delimitador dos campos da mensagem (;)
	public static final char FIELD_DELIMIT = ';';
	
	private String protocolVrs;
	private LSP_AnswerResult result;
	private String serverId;
	private String date;
	private int content_length;
	private String body;
	
	public LSP_AnswerMessage(String protocolVrs, LSP_AnswerResult result, String serverId,
			String date, int content_length, String body){
		this.protocolVrs = protocolVrs;
		this.result = result;
		this.serverId = serverId;
		this.date = date;
		this.content_length = content_length;
		this.body = body;
	}
	
	public String toString(){
		return protocolVrs + FIELD_DELIMIT + result.toString() + FIELD_DELIMIT + serverId +
		FIELD_DELIMIT +	date + FIELD_DELIMIT + content_length + FIELD_DELIMIT + body +
		FIELD_DELIMIT;
	}
	
	// Verifica se result eh um resultado valido para uma requisicao de log
	public static boolean isValidLogResult(LSP_AnswerResult result){
		for(int i = 0; i < LOG_RESULTS.length; i++){
			if(result.equals(LOG_RESULTS[i])){
				return true;
			}
		}
		return false;
	}
	
	// Verifica se result eh um resultado valido para uma requisicao de listagem
	public static boolean isValidListResult(LSP_AnswerResult result){
		for(int i = 0; i < LIST_RESULTS.length; i++){
			if(result.equals(LIST_RESULTS[i])){
				return true;
			}
		}
		return false;
	}
	
	public String getProtocolVrs(){
		return protocolVrs;
	}
	
	public LSP_AnswerResult getResult(){
		return result;
	}
	
	public String getServerId(){
		return serverId;
	}
	
	public String getDate(){
		return date;
	}
	
	public int getContent_Length(){
		return content_length;
	}
	
	public String getBody(){
		return body;
	}
	
}

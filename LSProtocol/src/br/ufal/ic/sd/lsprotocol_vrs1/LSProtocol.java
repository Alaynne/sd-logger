package br.ufal.ic.sd.lsprotocol_vrs1;

import java.util.Date;

import br.ufal.ic.sd.lsprotocol_vrs1.AnswerMsg.LSP_AnswerMessage;
import br.ufal.ic.sd.lsprotocol_vrs1.AnswerMsg.LSP_AnswerResult;
import br.ufal.ic.sd.lsprotocol_vrs1.RequestMsg.LSP_RequestCommand;
import br.ufal.ic.sd.lsprotocol_vrs1.RequestMsg.LSP_RequestMessage;

/**
 * Log Service Protocol (LSP): Protocolo de Servico de Log
 * 
 * @author Alaynne Moreira
 *
 */

public class LSProtocol {

	// Versao do protocolo (LSP/1.0)
	public static final String PROTOCOL_VRS = "LSP/1.0";
	// Numero de porta default para o protocolo
	public static final int DEFAULT_PORT = 4444;
	// Campo logged_since (da mensagem de requisicao) vazio
	private static final int EMPTY_LOGGED_AT_FIELD = -1;
	// Campo logged_since (da mensagem de requisicao) nulo
	private static final int NULL_LOGGED_AT_FIELD = -2;
	// Campo corpo (da mensagem de requisicao ou da mensagem de resposta) vazio
	private static final String EMPTY_BODY_FIELD = "";
	// Campo content_length (da mensagem de resposta) vazio
	private static final int EMPTY_CONTENT_LENGTH_FIELD = -1;
	// Campo content_length (da mensagem de resposta) nulo
	private static final int NULL_CONTENT_LENGTH_FIELD = -2;
	
	// Codifica uma mensagem de requisicao de log
	public String codeLogRequest(LSP_RequestCommand command, String applicationId, 
			String logMessage){
		String requestMessage = null;
		LSP_RequestMessage request;
		
		if((command != null) && (command.toString().startsWith("LOG_"))){
			if((applicationId != null) && (!applicationId.isEmpty())){
				if((logMessage != null) && (!logMessage.isEmpty())){
					request = new LSP_RequestMessage(PROTOCOL_VRS, command, 
							EMPTY_LOGGED_AT_FIELD, applicationId, logMessage);
					requestMessage = request.toString();
				}
			}
		}
		return requestMessage;
	}
	
	// Codifica uma mensagem de requisicao de listagem
	public String codeListRequest(LSP_RequestCommand command, String applicationId, 
			int logged_at){
		String requestMessage = null;
		LSP_RequestMessage request;
		
		if((command != null) && (command.toString().startsWith("LIST_"))){
			if((applicationId != null) && (!applicationId.isEmpty())){
				if(logged_at >= 0){
					request = new LSP_RequestMessage(PROTOCOL_VRS, command, logged_at, 
							applicationId, EMPTY_BODY_FIELD);
					requestMessage = request.toString();
				}
			}
		}
		return requestMessage;
	}
	
	// Codifica uma mensagem de resposta para uma requisicao de log
	public String codeLogAnswer(LSP_AnswerResult result, String serverId){
		String answerMessage = null;
		LSP_AnswerMessage answer;
		
		if((result != null) && (LSP_AnswerMessage.isValidLogResult(result))){
			if((serverId != null) && (!serverId.isEmpty())){
					answer = new LSP_AnswerMessage(PROTOCOL_VRS, result, serverId, 
							new Date().toString(), EMPTY_CONTENT_LENGTH_FIELD, EMPTY_BODY_FIELD);
					answerMessage = answer.toString();
			}
		}
		return answerMessage;
	}
	
	// Codifica uma mensagem de resposta para uma requisicao de listagem
	public String codeListAnswer(LSP_AnswerResult result, String serverId, int content_length, 
			String logList){
		String answerMessage = null;
		LSP_AnswerMessage answer;
		
		if((result != null) && (LSP_AnswerMessage.isValidListResult(result))){
			if((serverId != null) && (!serverId.isEmpty())){
				if(result.equals(LSP_AnswerResult.LIST_OK)){
					if(content_length > 0){
						if((logList != null) && (!logList.isEmpty())){
							answer = new LSP_AnswerMessage(PROTOCOL_VRS, result, serverId, 
							new Date().toString(), content_length, logList);
							answerMessage = answer.toString();
						}
					}
				}else{
					answer = new LSP_AnswerMessage(PROTOCOL_VRS, result, serverId, 
							new Date().toString(), EMPTY_CONTENT_LENGTH_FIELD, EMPTY_BODY_FIELD);
					answerMessage = answer.toString();
				}
			}
		}
		return answerMessage;
	}
	
	// Decodifica uma mensagem de requisicao
	public LSP_RequestMessage decodeRequestMessage(String requestMessage){
		LSP_RequestMessage request = LSProtocol.getRequestFields(requestMessage);
		
		if((request.getProtocolVrs() != null) && (request.getProtocolVrs().equals(PROTOCOL_VRS))){
			if(request.getCommand() != null){
				if((request.getApplicationId() != null) && (!request.getApplicationId().isEmpty())){
					if(request.getCommand().toString().startsWith("LOG_")){
						if(request.getLogged_At() == EMPTY_LOGGED_AT_FIELD){
							if((request.getBody() != null) && (!request.getBody().isEmpty())){
								return request;
							}
						}
					}else{
						if(request.getLogged_At() >= 0){
							if((request.getBody() != null) && (request.getBody().isEmpty())){
								return request;
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	// Separa os campos de uma mensagem de requisicao
	private static LSP_RequestMessage getRequestFields(String requestMessage){
		int fieldStart = 0, fieldEnd;
		String protocolVrs = null;
		LSP_RequestCommand command = null;
		int logged_at = NULL_LOGGED_AT_FIELD;
		String applicationId = null;
		String body = null;
		LSP_RequestMessage request;
		
		if(requestMessage != null){
			if((fieldEnd = requestMessage.indexOf(LSP_RequestMessage.FIELD_DELIMIT, fieldStart)) != -1){
				protocolVrs = requestMessage.substring(fieldStart, fieldEnd);
				fieldStart = fieldEnd + 1;
				if((fieldEnd = requestMessage.indexOf(LSP_RequestMessage.FIELD_DELIMIT, fieldStart)) != -1){
					try{
						command = LSP_RequestCommand.valueOf(requestMessage.substring(fieldStart, fieldEnd));
						fieldStart = fieldEnd + 1;
					}catch (IllegalArgumentException e){
						request = new LSP_RequestMessage(protocolVrs, command, logged_at, applicationId, body);
						return request;
					}
					if((fieldEnd = requestMessage.indexOf(LSP_RequestMessage.FIELD_DELIMIT, fieldStart)) != -1){
						try{
							logged_at = Integer.parseInt(requestMessage.substring(fieldStart, fieldEnd));
							fieldStart = fieldEnd + 1;
						}catch(NumberFormatException e){
							request = new LSP_RequestMessage(protocolVrs, command, logged_at, applicationId, body);
							return request;
						}
						if((fieldEnd = requestMessage.indexOf(LSP_RequestMessage.FIELD_DELIMIT, fieldStart)) != -1){
							applicationId = requestMessage.substring(fieldStart, fieldEnd);
							fieldStart = fieldEnd + 1;
							if((fieldEnd = requestMessage.indexOf(LSP_RequestMessage.FIELD_DELIMIT, fieldStart)) != -1){
									body = requestMessage.substring(fieldStart, fieldEnd);
							}
						}
					}
				}
			}
		}
		request = new LSP_RequestMessage(protocolVrs, command, logged_at, applicationId, body);
		return request;
	}
	
	// Decodifica uma mensagem de resposta
	public LSP_AnswerMessage decodeAnswerMessage(String answerMessage){
		LSP_AnswerMessage answer = LSProtocol.getAnswerFields(answerMessage);
		
		if((answer.getProtocolVrs() != null) && (answer.getProtocolVrs().equals(PROTOCOL_VRS))){
			if(answer.getResult() != null){
				if((answer.getServerId() != null) && (!answer.getServerId().isEmpty())){
					if((answer.getDate() != null) && (!answer.getDate().isEmpty())){
						if((answer.getResult().equals(LSP_AnswerResult.LIST_OK)) && (answer.getContent_Length() > 0)){
							if((answer.getBody() != null) && (!answer.getBody().isEmpty())){
								return answer;
							}
						}else if(answer.getContent_Length() == EMPTY_CONTENT_LENGTH_FIELD){
							if((answer.getBody() != null) && (answer.getBody().isEmpty())){
								return answer;
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	// Separa os campos de uma mensagem de resposta
	private static LSP_AnswerMessage getAnswerFields(String answerMessage){
		int fieldStart = 0, fieldEnd;
		String protocolVrs = null;
		LSP_AnswerResult result = null;
		String serverId = null;
		String date = null;
		int content_length = NULL_CONTENT_LENGTH_FIELD;
		String body = null;
		LSP_AnswerMessage answer;
		
		if(answerMessage != null){
			if((fieldEnd = answerMessage.indexOf(LSP_AnswerMessage.FIELD_DELIMIT, fieldStart)) != -1){
				protocolVrs = answerMessage.substring(fieldStart, fieldEnd);
				fieldStart = fieldEnd + 1;
				if((fieldEnd = answerMessage.indexOf(LSP_AnswerMessage.FIELD_DELIMIT, fieldStart)) != -1){
					try{
						result = LSP_AnswerResult.valueOf(answerMessage.substring(fieldStart, fieldEnd));
						fieldStart = fieldEnd + 1;
					}catch (IllegalArgumentException e){
						answer = new LSP_AnswerMessage(protocolVrs, result, serverId, date, content_length, body);
						return answer;
					}
					if((fieldEnd = answerMessage.indexOf(LSP_AnswerMessage.FIELD_DELIMIT, fieldStart)) != -1){
						serverId = answerMessage.substring(fieldStart, fieldEnd);
						fieldStart = fieldEnd + 1;
						if((fieldEnd = answerMessage.indexOf(LSP_AnswerMessage.FIELD_DELIMIT, fieldStart)) != -1){
							date = answerMessage.substring(fieldStart, fieldEnd);
							fieldStart = fieldEnd + 1;
							if((fieldEnd = answerMessage.indexOf(LSP_AnswerMessage.FIELD_DELIMIT, fieldStart)) != -1){
								try{
									content_length = Integer.parseInt(answerMessage.substring(fieldStart, fieldEnd));
									fieldStart = fieldEnd + 1;
								}catch(NumberFormatException e){
									answer = new LSP_AnswerMessage(protocolVrs, result, serverId, date, content_length, body);
									return answer;
								}
								if((fieldEnd = answerMessage.indexOf(LSP_AnswerMessage.FIELD_DELIMIT, fieldStart)) != -1){
									body = answerMessage.substring(fieldStart, fieldEnd);
								}
							}
						}
					}
				}
			}
		}
		answer = new LSP_AnswerMessage(protocolVrs, result, serverId, date, content_length, body);
		return answer;
	}
	
}

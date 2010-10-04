package br.ufal.ic.sd.lsprotocol_vrs1.AnswerMsg;

/**
 * Resultados possiveis de uma mensagem de resposta LSP:
 * 
 * LOG_OK: requisicao de log atendida com sucesso
 * LIST_OK: requisicao de listagem atendida com sucesso
 * Not_Found: o conteudo solicitado (ou seja, o conteudo a ser listado) nao foi encontrado
 * pelo servidor 
 * Bad_Request: a requisicao nao pode ser entendida pelo servidor
 * Error_On_Server: ocorreu um erro no servidor e a requisicao nao pode ser atendida
 * 
 * @author Alaynne Moreira
 *
 */

public enum LSP_AnswerResult {

	LOG_OK, LIST_OK, Not_Found, Bad_Request, Error_On_Server
	
}

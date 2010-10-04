package br.ufal.ic.sd.lsprotocol_vrs1.RequestMsg;


/**
 * Mensagem de requisicao LSP
 * 
 * Campos da mensagem:
 * 
 * protocolVrs: versao do protocolo que estah sendo utilizada
 * command: comando que caracteriza a requisicao
 * logged_at: logado_a (utilizado apenas em requisicoes de listagem, para especificar 
 * a data dos logs que devem ser considerados para listagem)
 * applicationId: identificador da aplicacao requisitante
 * body: corpo da mensagem (utilizado apenas em requisicoes de log, para especificar o conteudo
 * a ser logado)
 * 
 * @author Alaynne Moreira
 *
 */

public class LSP_RequestMessage {

	// Delimitador dos campos da mensagem (;)
	public static final char FIELD_DELIMIT = ';';
	
	private String protocolVrs;
	private LSP_RequestCommand command;
	private int logged_at;
	private String applicationId;
	private String body;
	
	public LSP_RequestMessage(String protocolVrs, LSP_RequestCommand command, int logged_at, 
			String applicationId, String body){
		this.protocolVrs = protocolVrs;
		this.command = command;
		this.logged_at = logged_at;
		this.applicationId = applicationId;
		this.body = body;
	}
	
	public String toString(){
		return protocolVrs + FIELD_DELIMIT + command.toString() + FIELD_DELIMIT + logged_at +
		FIELD_DELIMIT +	applicationId + FIELD_DELIMIT + body + FIELD_DELIMIT; 
	}
	
	public String getProtocolVrs(){
		return protocolVrs;
	}
	
	public LSP_RequestCommand getCommand(){
		return command;
	}
	
	public int getLogged_At(){
		return logged_at;
	}
	
	public String getApplicationId(){
		return applicationId;
	}
	
	public String getBody(){
		return body;
	}
	
}

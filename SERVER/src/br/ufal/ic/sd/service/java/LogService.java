package br.ufal.ic.sd.service.java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import br.ufal.ic.sd.lsprotocol_vrs1.AnswerMsg.LSP_AnswerResult;
import br.ufal.ic.sd.lsprotocol_vrs1.LogLevel.*;
import br.ufal.ic.sd.lsprotocol_vrs1.RequestMsg.*;
import br.ufal.ic.sd.lsprotocol_vrs1.LSProtocol;

/**
 * Servico de log: 
 * 
 * Loga uma mensagem ou lista (recupera) logs
 * 
 * @author Alaynne Moreira
 *
 */

public class LogService implements Service {
	
	// Nivel de log para o qual o servico de log estah habilitado
	private LSP_LogLevel level;
	
	public LogService(LSP_LogLevel level){
		this.level = level;
	}
	
	public String doService(String requestMessage, PrintWriter out, String serverId){
		
		LSProtocol protocol = new LSProtocol();
		LSP_RequestMessage request;
		String answerMessage = null, logList = null;
		
		if((request = protocol.decodeRequestMessage(requestMessage)) != null){
			if(request.getCommand().equals(LSP_RequestCommand.LOG_DEBUG)){
				if(this.log(LSP_LogLevel.DEBUG, request.getBody(), request.getApplicationId())){
					answerMessage =	protocol.codeLogAnswer(LSP_AnswerResult.LOG_OK, serverId);
				}else{
					answerMessage =	protocol.codeLogAnswer(LSP_AnswerResult.Error_On_Server, serverId);
				}
			}else if(request.getCommand().equals(LSP_RequestCommand.LOG_INFO)){
				if(this.log(LSP_LogLevel.INFO, request.getBody(), request.getApplicationId())){
					answerMessage =	protocol.codeLogAnswer(LSP_AnswerResult.LOG_OK, serverId);
				}else{
					answerMessage =	protocol.codeLogAnswer(LSP_AnswerResult.Error_On_Server, serverId);
				}
			}else if(request.getCommand().equals(LSP_RequestCommand.LOG_WARN)){
				if(this.log(LSP_LogLevel.WARN, request.getBody(), request.getApplicationId())){
					answerMessage =	protocol.codeLogAnswer(LSP_AnswerResult.LOG_OK, serverId);
				}else{
					answerMessage =	protocol.codeLogAnswer(LSP_AnswerResult.Error_On_Server, serverId);
				}
			}else if(request.getCommand().equals(LSP_RequestCommand.LOG_ERROR)){
				if(this.log(LSP_LogLevel.ERROR, request.getBody(), request.getApplicationId())){
					answerMessage =	protocol.codeLogAnswer(LSP_AnswerResult.LOG_OK, serverId);
				}else{
					answerMessage =	protocol.codeLogAnswer(LSP_AnswerResult.Error_On_Server, serverId);
				}
			}else if(request.getCommand().equals(LSP_RequestCommand.LOG_FATAL)){
				if(this.log(LSP_LogLevel.FATAL, request.getBody(), request.getApplicationId())){
					answerMessage =	protocol.codeLogAnswer(LSP_AnswerResult.LOG_OK, serverId);
				}else{
					answerMessage =	protocol.codeLogAnswer(LSP_AnswerResult.Error_On_Server, serverId);
				}
			}else if(request.getCommand().equals(LSP_RequestCommand.LIST_DEBUG)){
				if((logList = this.list(LSP_LogLevel.DEBUG, request.getLogged_At(), request.getApplicationId())) != null){
					if(!logList.isEmpty()){
						answerMessage =	protocol.codeListAnswer(LSP_AnswerResult.LIST_OK, serverId, 1, logList);
					}else{
						answerMessage =	protocol.codeListAnswer(LSP_AnswerResult.Not_Found, serverId, 0, "");
					}
				}else{
					answerMessage =	protocol.codeListAnswer(LSP_AnswerResult.Error_On_Server, serverId, 0, "");
				}
			}else if(request.getCommand().equals(LSP_RequestCommand.LIST_INFO)){
				if((logList = this.list(LSP_LogLevel.INFO, request.getLogged_At(), request.getApplicationId())) != null){
					if(!logList.isEmpty()){
						answerMessage =	protocol.codeListAnswer(LSP_AnswerResult.LIST_OK, serverId, 1, logList);
					}else{
						answerMessage =	protocol.codeListAnswer(LSP_AnswerResult.Not_Found, serverId, 0, "");
					}
				}else{
					answerMessage =	protocol.codeListAnswer(LSP_AnswerResult.Error_On_Server, serverId, 0, "");
				}
			}else if(request.getCommand().equals(LSP_RequestCommand.LIST_WARN)){
				if((logList = this.list(LSP_LogLevel.WARN, request.getLogged_At(), request.getApplicationId())) != null){
					if(!logList.isEmpty()){
						answerMessage =	protocol.codeListAnswer(LSP_AnswerResult.LIST_OK, serverId, 1, logList);
					}else{
						answerMessage =	protocol.codeListAnswer(LSP_AnswerResult.Not_Found, serverId, 0, "");
					}
				}else{
					answerMessage =	protocol.codeListAnswer(LSP_AnswerResult.Error_On_Server, serverId, 0, "");
				}
			}else if(request.getCommand().equals(LSP_RequestCommand.LIST_ERROR)){
				if((logList = this.list(LSP_LogLevel.ERROR, request.getLogged_At(), request.getApplicationId())) != null){
					if(!logList.isEmpty()){
						answerMessage =	protocol.codeListAnswer(LSP_AnswerResult.LIST_OK, serverId, 1, logList);
					}else{
						answerMessage =	protocol.codeListAnswer(LSP_AnswerResult.Not_Found, serverId, 0, "");
					}
				}else{
					answerMessage =	protocol.codeListAnswer(LSP_AnswerResult.Error_On_Server, serverId, 0, "");
				}
			}else if(request.getCommand().equals(LSP_RequestCommand.LIST_FATAL)){
				if((logList = this.list(LSP_LogLevel.FATAL, request.getLogged_At(), request.getApplicationId())) != null){
					if(!logList.isEmpty()){
						answerMessage =	protocol.codeListAnswer(LSP_AnswerResult.LIST_OK, serverId, 1, logList);
					}else{
						answerMessage =	protocol.codeListAnswer(LSP_AnswerResult.Not_Found, serverId, 0, "");
					}
				}else{
					answerMessage =	protocol.codeListAnswer(LSP_AnswerResult.Error_On_Server, serverId, 0, "");
				}
			}else if(request.getCommand().equals(LSP_RequestCommand.LIST_ALL)){
				if((logList = this.list(null, request.getLogged_At(), request.getApplicationId())) != null){
					if(!logList.isEmpty()){
						answerMessage =	protocol.codeListAnswer(LSP_AnswerResult.LIST_OK, serverId, 1, logList);
					}else{
						answerMessage =	protocol.codeListAnswer(LSP_AnswerResult.Not_Found, serverId, 0, "");
					}
				}else{
					answerMessage =	protocol.codeListAnswer(LSP_AnswerResult.Error_On_Server, serverId, 0, "");
				}
			}
		}else{
			answerMessage =	protocol.codeLogAnswer(LSP_AnswerResult.Bad_Request, serverId);
		}
		out.println(answerMessage);
		return answerMessage;
	}
	
	// Verifica se o servico de log estah habilitado para o nivel de log passado no parametro
	public boolean isLoggable(LSP_LogLevel level){
		if(level.compareTo(this.level) >= 0){
			return true;
		}
		return false;
	}
	
	// Loga uma mensagem (msg), se o servico de log estiver habilitado para o nivel de log 'level'
	private boolean log(LSP_LogLevel level, String msg, String applicationId){
		
		if(this.isLoggable(level)){
			try{
				FileWriter fileWriter = new FileWriter("LogRepository/LogFile.txt", true);  
				PrintWriter writer = new PrintWriter(fileWriter); 
	        
				msg = "<" + new Date().toString() + ">" + "<" + msg + ">";
				writer.println(msg);
	        
				writer.close();  
				fileWriter.close();
			}catch(IOException e){
				return false;
			}
		}	
		return true;
		
	}
	
	@SuppressWarnings("deprecation")
	/* Lista (recupera) logs de nivel 'level', logados 'logged_at' dias atras e referentes
	   a aplicacao 'applicationId' */
	private String list(LSP_LogLevel level, int logged_at, String applicationId){
		
		ArrayList<String> logList = new ArrayList<String>();
		try {
			BufferedReader logFile = new BufferedReader(new FileReader("LogRepository/LogFile.txt"));
			String line;
			Date date = new Date();
			
			date.setDate(date.getDate() - logged_at);
			while((line = logFile.readLine()) != null){
				if(level != null){
					if((line.contains(level.name())) && (line.contains(date.toString())) && (line.contains(applicationId))){
						logList.add(line);
					}
				}else{
					if((line.contains(date.toString())) && (line.contains(applicationId))){
						logList.add(line);
					}
				}
			}
			logFile.close();
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
		return logList.toString();
		
	}
	
}

package br.ufal.ic.sd.logger_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;
import java.net.Socket;
import java.util.Date;
import br.ufal.ic.sd.lsprotocol_vrs1.LSProtocol;
import br.ufal.ic.sd.lsprotocol_vrs1.AnswerMsg.LSP_AnswerMessage;
import br.ufal.ic.sd.lsprotocol_vrs1.AnswerMsg.LSP_AnswerResult;
import br.ufal.ic.sd.lsprotocol_vrs1.LogLevel.*;
import br.ufal.ic.sd.lsprotocol_vrs1.RequestMsg.LSP_RequestCommand;

import org.apache.log4j.*;

/**
 * Logger cliente 
 * 
 * @author Alaynne Moreira
 */

public class LoggerClient implements SD_Logger{
	
	private String applicationName;
	private LSP_LogLevel level;
	private static Logger logger;
	
	/**
	 * Constructs a new instance of LoggerClient, that will work to the
	 * given application 
	 * 
	 * @param applicationName application name
	 */
	public LoggerClient(String applicationName) {
		
		this.applicationName = applicationName;
		this.level = LSP_LogLevel.DEBUG;
		logger = Logger.getLogger("LoggerClient");
		logger.addAppender(new ConsoleAppender(new PatternLayout("%d{ABSOLUTE} %5p %c{1}:%L - %m%n")));
		try {
			logger.addAppender(new FileAppender(new PatternLayout("%d{ABSOLUTE} %5p %c{1}:%L - %m%n"), "Log/Log.txt", true));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	/**
	 * Returns application name served by LoggerClient instance
	 * 
	 * @return application name
	 */
	public String getApplicationName(){
		
		return applicationName;
		
	}
	
	/**
	 * Returns LoggerClient instance's log level
	 * 
	 * @return log level
	 */
	public LSP_LogLevel getLevel(){
		return level;
	}
	
	/**
	 * Sets LoggerClient instance's log level to a level
	 * 
	 * @param level a log level 
	 */
	public void setLevel(LSP_LogLevel level){
		
		this.level = level;
		
	}
	
	/**
	 * Verifies if LoggerClient instance is loggable to a certain log level
	 * 
	 *  @param level a log level to be verified
	 *  @return true, if LoggerClient instance is loggable to given log level,
	 *  or false, on the other hand 
	 */
	public boolean isLoggable(LSP_LogLevel level){
		
		if(level.compareTo(this.level) >= 0){
			return true;
		}
		return false;
		
	}
	 
	/**
	 * Logs a debug message
	 * 
	 *  @param msg the message to be logged
	 */
	public void debug(String msg) throws IOException{
		
		if(this.isLoggable(LSP_LogLevel.DEBUG)){
			LSProtocol protocol = new LSProtocol();
			LSP_AnswerMessage answer;
			String request, answerMsg;
			StringWriter writer = new StringWriter();
			Logger applicLogger = Logger.getLogger(applicationName);
			
			applicLogger.addAppender(new WriterAppender(new PatternLayout("%d{ABSOLUTE} %5p %c{1} - %m"), writer));
			applicLogger.debug(msg);
			
			if((request = protocol.codeLogRequest(LSP_RequestCommand.LOG_DEBUG, applicationName, writer.toString())) != null){
				if ((answerMsg = LoggerClient.sendRequest(request)) != null){
					if((answer = protocol.decodeAnswerMessage(answerMsg)) != null){
						if(!answer.getResult().equals(LSP_AnswerResult.LOG_OK)){
							// O servidor naum conseguiu logar
							logger.error("Server couldn't log");
						}
					}else{
						// O servidor naum respondeu
						logger.error("Server didn't answer");
					}
				}else{
					// A requisição de log naum pode ser enviada
		        	logger.error("Couldn't send log request");
				}
			}else{
				// A requisicao de log naum pode ser criada
	        	logger.error("Couldn't create log request");
			}
		}
		
	}
	
	/**
	 * Logs a info message
	 * 
	 *  @param msg the message to be logged
	 */
	public void info(String msg) throws IOException{
		
		if(this.isLoggable(LSP_LogLevel.INFO)){
			LSProtocol protocol = new LSProtocol();
			LSP_AnswerMessage answer;
			String request, answerMsg;
			StringWriter writer = new StringWriter();
			Logger applicLogger = Logger.getLogger(applicationName);
			
			applicLogger.addAppender(new WriterAppender(new PatternLayout("%d{ABSOLUTE} %5p %c{1} - %m"), writer));
			applicLogger.info(msg);
			
			if((request = protocol.codeLogRequest(LSP_RequestCommand.LOG_INFO, applicationName, writer.toString())) != null){
				if ((answerMsg = LoggerClient.sendRequest(request)) != null){
					if((answer = protocol.decodeAnswerMessage(answerMsg)) != null){
						if(!answer.getResult().equals(LSP_AnswerResult.LOG_OK)){
							// O servidor naum conseguiu logar
							logger.error("Server couldn't log");
						}
					}else{
						// O servidor naum respondeu
						logger.error("Server didn't answer");
					}
				}else{
					// A requisição de log naum pode ser enviada
		        	logger.error("Couldn't send log request");
				}
			}else{
				// A requisicao de log naum pode ser criada
	        	logger.error("Couldn't create log request");
			}
		}
		
	}
	
	/**
	 * Logs a warn message
	 * 
	 *  @param msg the message to be logged
	 */
	public void warn(String msg) throws IOException{
		
		if(this.isLoggable(LSP_LogLevel.WARN)){
			LSProtocol protocol = new LSProtocol();
			LSP_AnswerMessage answer;
			String request, answerMsg;
			StringWriter writer = new StringWriter();
			Logger applicLogger = Logger.getLogger(applicationName);
			
			applicLogger.addAppender(new WriterAppender(new PatternLayout("%d{ABSOLUTE} %5p %c{1} - %m"), writer));
			applicLogger.warn(msg);
			
			if((request = protocol.codeLogRequest(LSP_RequestCommand.LOG_WARN, applicationName, writer.toString())) != null){
				if ((answerMsg = LoggerClient.sendRequest(request)) != null){
					if((answer = protocol.decodeAnswerMessage(answerMsg)) != null){
						if(!answer.getResult().equals(LSP_AnswerResult.LOG_OK)){
							// O servidor naum conseguiu logar
							logger.error("Server couldn't log");
						}
					}else{
						// O servidor naum respondeu
						logger.error("Server didn't answer");
					}
				}else{
					// A requisição de log naum pode ser enviada
		        	logger.error("Couldn't send log request");
				}
			}else{
				// A requisicao de log naum pode ser criada
	        	logger.error("Couldn't create log request");
			}
		}
		
	}
	
	/**
	 * Logs an error message
	 * 
	 *  @param msg the message to be logged
	 */
	public void error(String msg) throws IOException{
		
		if(this.isLoggable(LSP_LogLevel.ERROR)){
			LSProtocol protocol = new LSProtocol();
			LSP_AnswerMessage answer;
			String request, answerMsg;
			StringWriter writer = new StringWriter();
			Logger applicLogger = Logger.getLogger(applicationName);
			
			applicLogger.addAppender(new WriterAppender(new PatternLayout("%d{ABSOLUTE} %5p %c{1} - %m"), writer));
			applicLogger.error(msg);
			
			if((request = protocol.codeLogRequest(LSP_RequestCommand.LOG_ERROR, applicationName, writer.toString())) != null){
				if ((answerMsg = LoggerClient.sendRequest(request)) != null){
					if((answer = protocol.decodeAnswerMessage(answerMsg)) != null){
						if(!answer.getResult().equals(LSP_AnswerResult.LOG_OK)){
							// O servidor naum conseguiu logar
							logger.error("Server couldn't log");
						}
					}else{
						// O servidor naum respondeu
						logger.error("Server didn't answer");
					}
				}else{
					// A requisição de log naum pode ser enviada
		        	logger.error("Couldn't send log request");
				}
			}else{
				// A requisicao de log naum pode ser criada
	        	logger.error("Couldn't create log request");
			}
		}
		
	}
	
	/**
	 * Logs a fatal message
	 * 
	 *  @param msg the message to be logged
	 */
	public void fatal(String msg) throws IOException{
		
		if(this.isLoggable(LSP_LogLevel.FATAL)){
			LSProtocol protocol = new LSProtocol();
			LSP_AnswerMessage answer;
			String request, answerMsg;
			StringWriter writer = new StringWriter();
			Logger applicLogger = Logger.getLogger(applicationName);
			
			applicLogger.addAppender(new WriterAppender(new PatternLayout("%d{ABSOLUTE} %5p %c{1} - %m"), writer));
			applicLogger.fatal(msg);
			
			if((request = protocol.codeLogRequest(LSP_RequestCommand.LOG_FATAL, applicationName, writer.toString())) != null){
				if ((answerMsg = LoggerClient.sendRequest(request)) != null){
					if((answer = protocol.decodeAnswerMessage(answerMsg)) != null){
						if(!answer.getResult().equals(LSP_AnswerResult.LOG_OK)){
							// O servidor naum conseguiu logar
							logger.error("Server couldn't log");
						}
					}else{
						// O servidor naum respondeu
						logger.error("Server didn't answer");
					}
				}else{
					// A requisição de log naum pode ser enviada
		        	logger.error("Couldn't send log request");
				}
			}else{
				// A requisicao de log naum pode ser criada
	        	logger.error("Couldn't create log request");
			}
		}
		
	}
	
	/**
	 * Lists debug messages
	 * 
	 * @param logged_at how many days before the messages are refered
	 * @return the debug message list or null, if no one message was found  
	 */
	@SuppressWarnings("deprecation")
	public String listDebug(Date logged_at) throws IOException {
		
			LSProtocol protocol = new LSProtocol();
			LSP_AnswerMessage answer;
			String request, answerMsg;
			int previousDayNum;
			Date endDate = new Date();
			String logList = null;
			
			// Calcula a quantos dias atras logged_since corresponde
			for (previousDayNum = 0; endDate.after(logged_at); previousDayNum++) {   
			    endDate.setDate(endDate.getDate() - 1);   
			}   
			
			if((request = protocol.codeListRequest(LSP_RequestCommand.LIST_DEBUG, applicationName, previousDayNum)) != null){
				if ((answerMsg = LoggerClient.sendRequest(request)) != null){
					if((answer = protocol.decodeAnswerMessage(answerMsg)) != null){
						if(answer.getResult().equals(LSP_AnswerResult.LIST_OK)){
							logList = answer.getBody();
						}else if(answer.getResult().equals(LSP_AnswerResult.Not_Found)){
							// O servidor naum conseguiu encontrar os logs solicitados
							logger.info("Logs was not found by server");
						}else{
							// O servidor naum conseguiu listar
							logger.error("Server couldn't list");
						}
					}else{
						// O servidor naum respondeu
						logger.error("Server didn't answer");
					}
				}else{
					// A requisição de listagem naum pode ser enviada
		        	logger.error("Couldn't send list request");
				}
			}else{
				// A requisicao de listagem naum pode ser criada
	        	logger.error("Couldn't create list request");
			}
			return logList;
			
	}
	
	/**
	 * Lists info messages
	 * 
	 * @param logged_at how many days before the messages are refered
	 * @return the info message list or null, if no one message was found  
	 */
	
	@SuppressWarnings("deprecation")
	public String listInfo(Date logged_at) throws IOException {
		
			LSProtocol protocol = new LSProtocol();
			LSP_AnswerMessage answer;
			String request, answerMsg;
			int previousDayNum;
			Date endDate = new Date();
			String logList = null;
			
			// Calcula a quantos dias atras logged_since corresponde
			for (previousDayNum = 0; endDate.after(logged_at); previousDayNum++) {   
			    endDate.setDate(endDate.getDate() - 1);   
			}   
			
			if((request = protocol.codeListRequest(LSP_RequestCommand.LIST_INFO, applicationName, previousDayNum)) != null){
				if ((answerMsg = LoggerClient.sendRequest(request)) != null){
					if((answer = protocol.decodeAnswerMessage(answerMsg)) != null){
						if(answer.getResult().equals(LSP_AnswerResult.LIST_OK)){
							logList = answer.getBody();
						}else if(answer.getResult().equals(LSP_AnswerResult.Not_Found)){
							// O servidor naum conseguiu encontrar os logs solicitados
							logger.info("Logs was not found by server");
						}else{
							// O servidor naum conseguiu listar
							logger.error("Server couldn't list");
						}
					}else{
						// O servidor naum respondeu
						logger.error("Server didn't answer");
					}
				}else{
					// A requisição de listagem naum pode ser enviada
		        	logger.error("Couldn't send list request");
				}
			}else{
				// A requisicao de listagem naum pode ser criada
	        	logger.error("Couldn't create list request");
			}
			return logList;
			
	}
	
	/**
	 * Lists warn messages
	 * 
	 * @param logged_at how many days before the messages are refered
	 * @return the warn message list or null, if no one message was found  
	 */
	@SuppressWarnings("deprecation")
	public String listWarn(Date logged_at) throws IOException {
		
			LSProtocol protocol = new LSProtocol();
			LSP_AnswerMessage answer;
			String request, answerMsg;
			int previousDayNum;
			Date endDate = new Date();
			String logList = null;
			
			// Calcula a quantos dias atras logged_since corresponde
			for (previousDayNum = 0; endDate.after(logged_at); previousDayNum++) {   
			    endDate.setDate(endDate.getDate() - 1);   
			}   
			
			if((request = protocol.codeListRequest(LSP_RequestCommand.LIST_WARN, applicationName, previousDayNum)) != null){
				if ((answerMsg = LoggerClient.sendRequest(request)) != null){
					if((answer = protocol.decodeAnswerMessage(answerMsg)) != null){
						if(answer.getResult().equals(LSP_AnswerResult.LIST_OK)){
							logList = answer.getBody();
						}else if(answer.getResult().equals(LSP_AnswerResult.Not_Found)){
							// O servidor naum conseguiu encontrar os logs solicitados
							logger.info("Logs was not found by server");
						}else{
							// O servidor naum conseguiu listar
							logger.error("Server couldn't list");
						}
					}else{
						// O servidor naum respondeu
						logger.error("Server didn't answer");
					}
				}else{
					// A requisição de listagem naum pode ser enviada
		        	logger.error("Couldn't send list request");
				}
			}else{
				// A requisicao de listagem naum pode ser criada
	        	logger.error("Couldn't create list request");
			}
			return logList;
			
	}
	
	/**
	 * Lists error messages
	 * 
	 * @param logged_at how many days before the messages are refered
	 * @return the error message list or null, if no one message was found  
	 */
	// Retorna a lista de logs ou null
	@SuppressWarnings("deprecation")
	public String listError(Date logged_at) throws IOException {
		
			LSProtocol protocol = new LSProtocol();
			LSP_AnswerMessage answer;
			String request, answerMsg;
			int previousDayNum;
			Date endDate = new Date();
			String logList = null;
			
			// Calcula a quantos dias atras logged_since corresponde
			for (previousDayNum = 0; endDate.after(logged_at); previousDayNum++) {   
			    endDate.setDate(endDate.getDate() - 1);   
			}   
			
			if((request = protocol.codeListRequest(LSP_RequestCommand.LIST_ERROR, applicationName, previousDayNum)) != null){
				if ((answerMsg = LoggerClient.sendRequest(request)) != null){
					if((answer = protocol.decodeAnswerMessage(answerMsg)) != null){
						if(answer.getResult().equals(LSP_AnswerResult.LIST_OK)){
							logList = answer.getBody();
						}else if(answer.getResult().equals(LSP_AnswerResult.Not_Found)){
							// O servidor naum conseguiu encontrar os logs solicitados
							logger.info("Logs was not found by server");
						}else{
							// O servidor naum conseguiu listar
							logger.error("Server couldn't list");
						}
					}else{
						// O servidor naum respondeu
						logger.error("Server didn't answer");
					}
				}else{
					// A requisição de listagem naum pode ser enviada
		        	logger.error("Couldn't send list request");
				}
			}else{
				// A requisicao de listagem naum pode ser criada
	        	logger.error("Couldn't create list request");
			}
			return logList;
			
	}
	
	/**
	 * Lists fatal messages
	 * 
	 * @param logged_at how many days before the messages are refered
	 * @return the fatal message list or null, if no one message was found  
	 */
	@SuppressWarnings("deprecation")
	public String listFatal(Date logged_at) throws IOException {
		
			LSProtocol protocol = new LSProtocol();
			LSP_AnswerMessage answer;
			String request, answerMsg;
			int previousDayNum;
			Date endDate = new Date();
			String logList = null;
			
			// Calcula a quantos dias atras logged_since corresponde
			for (previousDayNum = 0; endDate.after(logged_at); previousDayNum++) {   
			    endDate.setDate(endDate.getDate() - 1);   
			}   
			
			if((request = protocol.codeListRequest(LSP_RequestCommand.LIST_FATAL, applicationName, previousDayNum)) != null){
				if ((answerMsg = LoggerClient.sendRequest(request)) != null){
					if((answer = protocol.decodeAnswerMessage(answerMsg)) != null){
						if(answer.getResult().equals(LSP_AnswerResult.LIST_OK)){
							logList = answer.getBody();
						}else if(answer.getResult().equals(LSP_AnswerResult.Not_Found)){
							// O servidor naum conseguiu encontrar os logs solicitados
							logger.info("Logs was not found by server");
						}else{
							// O servidor naum conseguiu listar
							logger.error("Server couldn't list");
						}
					}else{
						// O servidor naum respondeu
						logger.error("Server didn't answer");
					}
				}else{
					// A requisição de listagem naum pode ser enviada
		        	logger.error("Couldn't send list request");
				}
			}else{
				// A requisicao de listagem naum pode ser criada
	        	logger.error("Couldn't create list request");
			}
			return logList;
			
	}
	
	/**
	 * Lists messages of any level
	 * 
	 * @param logged_at how many days before the messages are refered
	 * @return the message list or null, if no one message was found  
	 */
	@SuppressWarnings("deprecation")
	public String listAll(Date logged_at) throws IOException {
		
			LSProtocol protocol = new LSProtocol();
			LSP_AnswerMessage answer;
			String request, answerMsg;
			int previousDayNum;
			Date endDate = new Date();
			String logList = null;
			
			// Calcula a quantos dias atras logged_since corresponde
			for (previousDayNum = 0; endDate.after(logged_at); previousDayNum++) {   
			    endDate.setDate(endDate.getDate() - 1);   
			}   
			
			if((request = protocol.codeListRequest(LSP_RequestCommand.LIST_ALL, applicationName, previousDayNum)) != null){
				if ((answerMsg = LoggerClient.sendRequest(request)) != null){
					if((answer = protocol.decodeAnswerMessage(answerMsg)) != null){
						if(answer.getResult().equals(LSP_AnswerResult.LIST_OK)){
							logList = answer.getBody();
						}else if(answer.getResult().equals(LSP_AnswerResult.Not_Found)){
							// O servidor naum conseguiu encontrar os logs solicitados
							logger.info("Logs was not found by server");
						}else{
							// O servidor naum conseguiu listar
							logger.error("Server couldn't list");
						}
					}else{
						// O servidor naum respondeu
						logger.error("Server didn't answer");
					}
				}else{
					// A requisição de listagem naum pode ser enviada
		        	logger.error("Couldn't send list request");
				}
			}else{
				// A requisicao de listagem naum pode ser criada
	        	logger.error("Couldn't create list request");
			}
			return logList;
			
	}
	
	// Envia a requisicao para o servidor e retorna a resposta do servidor
	private static String sendRequest(String request) throws IOException{
		
			Socket socket = null;
	        PrintWriter out = null;
	        BufferedReader in = null;
	        String answer = null;

	      	try{
	        	// Conecta-se ao servidor 127.0.0.1 (local host) atraves da porta default para LSP.
	        	socket = new Socket("127.0.0.1", LSProtocol.DEFAULT_PORT);
	        	out = new PrintWriter(socket.getOutputStream(), true);
	        	in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        	// Envia a requisicao para o servidor.
		        out.println(request);
		        // Obtem a resposta do servidor 
		        answer = in.readLine();
	      	}catch (UnknownHostException e){
	        	// O servidor naum eh conhecido.
	        	logger.error("Don't know about the server");
	        }catch (IOException e){
	        	// Naum foi possivel conectar-se ao servidor.
	        	logger.error("Couldn't get I/O for the connection to server");
	        }
	              
	        out.close();
	        in.close();
	        socket.close();
	        
	        return answer;
		
	}
	
}

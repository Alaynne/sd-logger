package br.ufal.ic.sd.logger_client;

import java.io.IOException;
import java.util.Date;
import br.ufal.ic.sd.lsprotocol_vrs1.LogLevel.LSP_LogLevel;

/**
 * SD_Logger (Logger da disciplina de Sistemas Distribuidos)
 * 
 * @author Alaynne Moreira
 *
 */

public interface SD_Logger {

	public String getApplicationName();
	public LSP_LogLevel getLevel();
	public void setLevel(LSP_LogLevel level);
	public boolean isLoggable(LSP_LogLevel level);
	public void debug(String msg) throws IOException;
	public void info(String msg) throws IOException;
	public void warn(String msg) throws IOException;
	public void error(String msg) throws IOException;
	public void fatal(String msg) throws IOException;
	public String listDebug(Date logged_since) throws IOException;
	public String listInfo(Date logged_since) throws IOException;
	public String listWarn(Date logged_since) throws IOException;
	public String listError(Date logged_since) throws IOException;
	public String listFatal(Date logged_since) throws IOException;
	public String listAll(Date logged_since) throws IOException;
	
}

package br.ufal.ic.sd.lsprotocol_vrs1.RequestMsg;

/**
 * Comandos validos para uma mensagem de requisicao LSP:
 * 
 * LOG_DEBUG: logar conteudo de nivel Debug
 * LOG_INFO: logar conteudo de nivel Info
 * LOG_WARN: logar conteudo de nivel Warn
 * LOG_ERROR: logar conteudo de nivel Error
 * LOG_FATAL: logar conteudo de nivel Fatal
 * LIST_DEBUG: listar logs de nivel Debug
 * LIST_INFO: listar logs de nivel Info
 * LIST_WARN: listar logs de nivel Warn
 * LIST_ERROR: listar logs de nivel Error
 * LIST_FATAL: listar logs de nivel Fatal
 * LIST_ALL: listar logs de todos os niveis
 * 
 * @author Alaynne Moreira
 *
 */

public enum LSP_RequestCommand {
	
	LOG_DEBUG, LOG_INFO, LOG_WARN, LOG_ERROR, LOG_FATAL, LIST_DEBUG, LIST_INFO, LIST_WARN,
	LIST_ERROR, LIST_FATAL, LIST_ALL
	
}

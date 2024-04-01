package br.com.sigvet.api.application.exception;

public class CidadeNaoExistenteException extends Exception {
    
    public CidadeNaoExistenteException(String message) {
        super(message);
    }
}

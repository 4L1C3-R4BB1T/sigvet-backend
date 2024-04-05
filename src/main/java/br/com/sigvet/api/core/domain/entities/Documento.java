package br.com.sigvet.api.core.domain.entities;

import br.com.sigvet.api.core.exception.DomainInvalidException;

public class Documento {

    private String valor;

    public Documento(String valor) throws DomainInvalidException {
        setValor(valor);
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) throws DomainInvalidException {
        if (!isValidCPF(valor)) {
            throw new DomainInvalidException("O cpf não é válido");
        }
        this.valor = valor;
    }

    private static boolean isValidCPF(String cpf) {
        // cpf = cpf.replaceAll("\\D", "");

        // if (cpf.length() != 11)
        // return false;

        // if (cpf.matches("(\\d)\\1*"))
        // return false;

        // int sum = 0;
        // for (int i = 0; i < 9; i++) {
        // sum += (10 - i) * Character.getNumericValue(cpf.charAt(i));
        // }
        // int digit1 = 11 - (sum % 11);
        // if (digit1 > 9)
        // digit1 = 0;

        // sum = 0;
        // for (int i = 0; i < 10; i++) {
        // sum += (11 - i) * Character.getNumericValue(cpf.charAt(i));
        // }
        // int digit2 = 11 - (sum % 11);
        // if (digit2 > 9)
        // digit2 = 0;

        // return (Character.getNumericValue(cpf.charAt(9)) == digit1) &&
        // (Character.getNumericValue(cpf.charAt(10)) == digit2);
        return true;
    }

}

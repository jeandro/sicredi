package com.sicredi.sicredi.entities;

import java.io.Serializable;
import java.util.Objects;

public class Conta implements Serializable {
    private String agencia;
    private String conta;
    private Double saldo;
    private String status;
    private String resultado;

    public Conta() {

    }

    public Conta(String agencia, String conta, double saldo, String status) {
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public String getSaldoFormat() {
        return (Double.valueOf(saldo).toString().replace(".", ","));
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Conta)) return false;
        Conta conta1 = (Conta) o;
        return Objects.equals(agencia, conta1.agencia) && Objects.equals(conta, conta1.conta) && Objects.equals(saldo, conta1.saldo) && Objects.equals(status, conta1.status) && Objects.equals(resultado, conta1.resultado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(agencia, conta, saldo, status, resultado);
    }

}

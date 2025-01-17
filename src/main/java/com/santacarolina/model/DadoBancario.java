package com.santacarolina.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.santacarolina.dao.BancoDAO;
import com.santacarolina.dao.ContatoDAO;
import com.santacarolina.exceptions.FetchFailException;
import com.santacarolina.interfaces.Copiable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadoBancario implements Copiable<DadoBancario> {

    private long id;
    private String agencia;
    private Banco banco;
    private String numeroConta;
    private Contato contato;
    private long bancoId;
    private long contatoId;

    public long getId() { return id; }
    public String getAgencia() { return agencia; }
    public String getNumeroConta() { return numeroConta; }
    public long getBancoId() { return bancoId; }
    public long getContatoId() { return contatoId; }

    public Contato getContato() {
        try {
            if (contato == null && contatoId != 0) this.contato = new ContatoDAO().findById(contatoId).orElse(null);
            return contato;
        } catch (FetchFailException e) {
            return contato;
        }
    }

    public Banco getBanco() {
        try {
            if (banco == null && bancoId != 0) this.banco = new BancoDAO().findById(bancoId).orElse(null);
            return banco;
        } catch (FetchFailException e) {
            return banco;
        }
    }

    public void setId(long id) { this.id = id; }
    public void setAgencia(String agencia) { this.agencia = agencia; }
    public void setNumeroConta(String numeroConta) { this.numeroConta = numeroConta; }
    public void setBancoId(long bancoId) { this.bancoId = bancoId; }
    public void setContatoId(long contatoId) { this.contatoId = contatoId; }

    public void setContato(Contato contato) {
        this.contato = contato;
        this.contatoId = contato != null ? contato.getId() : 0;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
        this.bancoId = banco != null ? banco.getId() : 0;
    }

    @Override
    public DadoBancario generateCopy() {
        DadoBancario clone = new DadoBancario();
        clone.setId(id);
        clone.setContatoId(contatoId);
        clone.setAgencia(agencia);
        clone.setBancoId(bancoId);
        clone.setNumeroConta(numeroConta);
        return clone;
    }

    @Override
    public String toString() { return numeroConta; }

    public String print() {
        return "DadoBancario{id=" + id + ", agencia=" + agencia + ", banco=" + banco + ", numeroConta=" + numeroConta
                + ",  contato=" + contato + "}";
    }

}

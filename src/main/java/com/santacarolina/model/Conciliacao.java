package com.santacarolina.model;

import com.santacarolina.dao.DuplicataDAO;
import com.santacarolina.dao.ExtratoDAO;
import com.santacarolina.dto.ConciliacaoDTO;
import com.santacarolina.enums.TipoMovimento;
import com.santacarolina.exceptions.FetchFailException;
import com.santacarolina.interfaces.ToDTO;

public class Conciliacao implements ToDTO<ConciliacaoDTO> {

    private long id;
    private TipoMovimento tipoMovimento;
    private Long duplicataId;
    private long extratoId;

    public Conciliacao (ConciliacaoDTO dto) {
        this.id = dto.getId();
        this.tipoMovimento = dto.getTipoMovimento();
        this.duplicataId = dto.getDuplicataId();
        this.extratoId = dto.getExtratoId();
    }

    public Conciliacao(long extratoId, long duplicataId) {
        this.duplicataId = duplicataId;
        this.extratoId = extratoId;
        this.tipoMovimento = TipoMovimento.COMUM;
    }

    public Conciliacao(long extratoId, TipoMovimento tipoMovimento) {
        this.extratoId = extratoId;
        this.tipoMovimento = tipoMovimento;
    }

    public Conciliacao() { }

    public long getId() { return id; }
    public TipoMovimento getTipoMovimento() { return tipoMovimento; }
    public Long getDuplicataId() { return duplicataId; }
    public long getExtratoId() { return extratoId; }

    public Duplicata getDuplicata() {
        try {
            return new DuplicataDAO().findById(duplicataId).orElse(null);
        } catch (FetchFailException e) {
            return null;
        }
    }

    public Extrato getExtrato() {
        try {
            return new ExtratoDAO().findById(extratoId).orElse(null);
        } catch (FetchFailException e) {
            return null; 
        }
    }

    public void setDuplicata(Duplicata duplicata) { this.duplicataId = duplicata != null ? duplicata.getId() : null; }
    public void setExtrato(Extrato extrato) { this.extratoId = extrato != null ? extrato.getId() : 0; }

    @Override
    public ConciliacaoDTO toDTO () { return new ConciliacaoDTO(this); }

}

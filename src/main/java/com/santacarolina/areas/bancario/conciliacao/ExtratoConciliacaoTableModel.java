package com.santacarolina.areas.bancario.conciliacao;

import java.time.LocalDate;
import java.util.List;

import javax.swing.event.TableModelListener;

import com.santacarolina.dao.ExtratoDAO;
import com.santacarolina.dto.ExtratoDTO;
import com.santacarolina.exceptions.FetchFailException;
import com.santacarolina.interfaces.CustomTableModel;
import com.santacarolina.model.ContaBancaria;
import com.santacarolina.ui.CustomTableModelImpl;

public class ExtratoConciliacaoTableModel implements CustomTableModel<ExtratoDTO> {

    public static final String CONTA = "conta";

    private ExtratoDAO dao;
    private List<ExtratoDTO> extratoList;
    private CustomTableModelImpl<ExtratoDTO> model;

    public ExtratoConciliacaoTableModel(List<ExtratoDTO> extratoList) {
        this.extratoList = extratoList;
        this.model = new CustomTableModelImpl<>(this, extratoList);
        this.dao = new ExtratoDAO();
    }

    public void setContaBancaria(ContaBancaria contaBancaria) throws FetchFailException {
        extratoList = dao.findByConta(contaBancaria.getId());
        model.setList(extratoList);
    }

    @Override
    public CustomTableModelImpl<ExtratoDTO> getBaseModel() { return model; }

    public int getRowCount() { return extratoList.size(); }
    public int getColumnCount() { return 5; }
    public boolean isCellEditable(int rowIndex, int columnIndex) { return false; }
    public ExtratoDTO getObject(int rowIndex) { return extratoList.get(rowIndex); }
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) { }
    public void addTableModelListener(TableModelListener l) { model.addTableModelListener(l); }
    public void removeTableModelListener(TableModelListener l) { model.removeTableModelListener(l); }
    public void setList(List<ExtratoDTO> list) {
        extratoList = list;
        model.setList(list);
    }

    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "Data da Transação";
            case 1 -> "Conta Bancária";
            case 2 -> "Categoria Bancária";
            case 3 -> "Descrição";
            case 4 -> "Valor";
            default -> throw new IllegalStateException("Unexpected value: " + columnIndex);
        };
    }

    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> LocalDate.class;
            case 1, 2, 3 -> String.class;
            case 4 -> Double.class;
            default -> throw new IllegalStateException("Unexpected value: " + columnIndex);
        };
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        ExtratoDTO e = extratoList.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> e.getDataTransacao();
            case 1 -> e.getContaBancaria();
            case 2 -> e.getCategoriaExtrato();
            case 3 -> e.getDescricao();
            case 4 -> e.getValor();
            default -> throw new IllegalStateException("Unexpected value: " + columnIndex);
        };
    }

}
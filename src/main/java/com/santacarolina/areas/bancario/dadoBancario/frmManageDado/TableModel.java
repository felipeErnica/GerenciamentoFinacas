package com.santacarolina.areas.bancario.dadoBancario.frmManageDado;

import java.util.List;

import com.santacarolina.dao.DadoDAO;
import com.santacarolina.exceptions.FetchFailException;
import com.santacarolina.interfaces.CustomTableModel;
import com.santacarolina.interfaces.FilterModelContainer;
import com.santacarolina.model.DadoBancario;
import com.santacarolina.ui.CustomTableModelImpl;

public class TableModel implements CustomTableModel<DadoBancario>, FilterModelContainer {

    private static final DadoDAO dadoDAO = new DadoDAO();

    private CustomTableModelImpl<DadoBancario> baseModel;
    private List<DadoBancario> list;
    private FilterModel filterModel;

    private String[] columnNames = {
            "Nome do Contato",
            "Banco",
            "Agência",
            "Número da Conta"
    };

    public TableModel() throws FetchFailException {
        this.baseModel = new CustomTableModelImpl<>(this, dadoDAO.findAll() );
        list = baseModel.getList();
        filterModel = new FilterModel(this);
    }

    @Override
    public CustomTableModelImpl<DadoBancario> getBaseModel() { return baseModel; }

    @Override
    public int getRowCount() { return baseModel.getRowCount(); }

    @Override
    public int getColumnCount() { return columnNames.length; }

    @Override
    public String getColumnName(int columnIndex) { return columnNames[columnIndex]; }

    @Override
    public Class<?> getColumnClass(int columnIndex) { return String.class; }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) { return false; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DadoBancario d = getObject(rowIndex);
        return switch (columnIndex) {
            case 0 -> d.getContato().getNome();
            case 1 -> d.getBanco().getNomeBanco();
            case 2 -> d.getAgencia();
            case 3 -> d.getNumeroConta();
            default -> throw new IllegalStateException("Unexpected column value: " + columnIndex);
        };
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {}

    @Override
    public DadoBancario getObject(int rowIndex) { return baseModel.getObject(rowIndex); }

    public void addNewRow() { baseModel.addRow(new DadoBancario());}
    public void addRow(DadoBancario dto) { baseModel.addRow(dto); }
    public void removeRow(int row) { baseModel.removeRow(row); }
    public void removeRows(int[] rows) { baseModel.removeRows(rows); }
    public List<DadoBancario> getList() { return list; }

    public void requeryTable() throws FetchFailException {
        list = dadoDAO.findAll();
        filterModel.setFilters();
    }

    @Override
    public FilterModel getFilterModel() { return filterModel; }

}

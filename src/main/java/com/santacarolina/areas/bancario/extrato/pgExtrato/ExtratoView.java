package com.santacarolina.areas.bancario.extrato.pgExtrato;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.santacarolina.areas.mainFrame.common.MainPaneView;
import com.santacarolina.areas.mainFrame.common.MainPaneViewImpl;
import com.santacarolina.model.ContaBancaria;
import com.santacarolina.util.AppIcon;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class ExtratoView implements MainPaneView {

    private JButton addExtrato;
    private JButton addOFX;
    private MainPaneViewImpl mainPaneView;
    private JPanel mainPane;
    private JComboBox<ContaBancaria> contaComboBox;
    private JPanel upperPane;

    public ExtratoView() {
        mainPaneView = new MainPaneViewImpl(this);
        mainPane = mainPaneView.getPane();
        initComponents();
    }

    private void initComponents() {
        JLabel contaLabel = new JLabel("Conta Bancária:");
        contaComboBox = new JComboBox<>();
        AutoCompleteDecorator.decorate(contaComboBox);
        contaLabel.setLabelFor(contaComboBox);

        addExtrato = new JButton("Adicionar Extrato", AppIcon.paintIcon(new FlatSVGIcon("icon/extrato_icon.svg")));
        addOFX = new JButton("Adicionar OFX", AppIcon.paintIcon(new FlatSVGIcon("icon/ofx_icon.svg")));

        upperPane = new JPanel(new MigLayout("insets 15",
                "[][grow, fill][][]",
                "[fill]"));

        upperPane.add(contaLabel);
        upperPane.add(contaComboBox);
        upperPane.add(addOFX);
        upperPane.add(addExtrato);

        mainPane.add(upperPane, BorderLayout.NORTH);
        mainPane.revalidate();
    }

    public JPanel getPane() { return mainPane; }
    public JTable getTable() { return mainPaneView.getTable(); }
    public MainPaneViewImpl getBaseView() { return mainPaneView; }
    public JScrollPane getScrollPane() { return mainPaneView.getScrollPane(); }
    public JButton getAddExtrato() { return addExtrato; }
    public JButton getAddOFX() { return addOFX; }
    public JComboBox<ContaBancaria> getContaComboBox() { return contaComboBox; }

    public void formatColumns() {
        int width = getScrollPane().getWidth()/100;
        TableColumnModel model = getTable().getColumnModel();
        model.getColumn(0).setPreferredWidth(width*3);
        model.getColumn(1).setPreferredWidth(width*10);
        model.getColumn(2).setPreferredWidth(width*20);
        model.getColumn(3).setPreferredWidth(width*20);
        model.getColumn(4).setPreferredWidth(width*40);
        model.getColumn(5).setPreferredWidth(width*10);
    }

}
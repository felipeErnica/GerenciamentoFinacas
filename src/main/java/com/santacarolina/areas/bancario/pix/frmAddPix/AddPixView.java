package com.santacarolina.areas.bancario.pix.frmAddPix;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.santacarolina.areas.bancario.pix.formModel.PixFormModel;
import com.santacarolina.enums.TipoPix;
import com.santacarolina.model.Contato;
import com.santacarolina.model.DadoBancario;
import com.santacarolina.ui.AddView;
import com.santacarolina.util.AppIcon;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AddPixView implements PropertyChangeListener {

    private JButton addButton;
    private JDialog dialog;

    private JLabel contatoLabel;
    private JComboBox<Contato> contatoComboBox;
    private JCheckBox contaCheckBox;
    private JLabel contaLabel;
    private JComboBox<DadoBancario> contaComboBox;
    private JLabel bancoLabel;
    private JTextField bancoTextField;
    private JLabel tipoChaveLabel;
    private JComboBox<TipoPix> tipoPixComboBox;
    private JLabel chaveLabel;
    private JTextField chaveTextField;

    public AddPixView() {
        AddView addView = new AddView();
        dialog = addView.getDialog();
        addButton = addView.getAddButton();
        initComponents();
    }

    private void initComponents() {
        dialog.setTitle("Adicionar Chave Pix");
        dialog.setIconImage(AppIcon.paintIcon(new FlatSVGIcon("icon/pix_menu_icon.svg")).getImage());
        addButton.setText("Adicionar Chave");

        JPanel centerPanel = new JPanel();

        contatoLabel = new JLabel("Nome do Contato:");
        contatoComboBox = new JComboBox<>();
        AutoCompleteDecorator.decorate(contatoComboBox);
        contatoLabel.setLabelFor(contatoComboBox);

        contaCheckBox = new JCheckBox("Adicionar a Conta Báncaria");
        contaCheckBox.setSelected(true);

        contaLabel = new JLabel("Conta Bancária:");
        contaComboBox = new JComboBox<>();
        contaLabel.setLabelFor(contaComboBox);
        contaComboBox.setEnabled(false);
        AutoCompleteDecorator.decorate(contaComboBox);

        tipoChaveLabel = new JLabel("Tipo de Chave:");
        tipoPixComboBox = new JComboBox<>();
        tipoChaveLabel.setLabelFor(tipoPixComboBox);
        tipoPixComboBox.setEnabled(false);
        AutoCompleteDecorator.decorate(tipoPixComboBox);

        chaveLabel = new JLabel("Chave Pix:");
        chaveTextField = new JTextField();
        chaveTextField.setEnabled(false);
        chaveLabel.setLabelFor(chaveTextField);

        bancoLabel = new JLabel("Banco:");
        bancoTextField = new JTextField();
        bancoTextField.setEnabled(false);
        bancoLabel.setLabelFor(bancoTextField);

        centerPanel.setLayout(new MigLayout("insets 20",
                "[right][fill, grow][grow]",
                "[]30[][]30[][][]"));

        centerPanel.add(contatoLabel);
        centerPanel.add(contatoComboBox, "span, wrap");
        centerPanel.add(contaCheckBox, "wrap,skip");
        centerPanel.add(contaLabel);
        centerPanel.add(contaComboBox, "wrap");
        centerPanel.add(tipoChaveLabel);
        centerPanel.add(tipoPixComboBox,"wrap");
        centerPanel.add(chaveLabel);
        centerPanel.add(chaveTextField, "wrap");
        centerPanel.add(bancoLabel);
        centerPanel.add(bancoTextField);

        dialog.add(centerPanel, BorderLayout.CENTER);

    }

    public JDialog getDialog() { return dialog; }
    public JComboBox<Contato> getContatoComboBox() { return contatoComboBox; }
    public JComboBox<DadoBancario> getContaComboBox() { return contaComboBox; }
    public JTextField getBancoTextField() { return bancoTextField; }
    public JComboBox<TipoPix> getTipoPixComboBox() { return tipoPixComboBox; }
    public JTextField getChaveTextField() { return chaveTextField; }
    public JButton getAddButton() { return addButton; }
    public JCheckBox getContaCheckBox() { return contaCheckBox; }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case PixFormModel.CONTATO -> contatoComboBox.setSelectedItem(evt.getNewValue());
            case PixFormModel.CONTA_ENABLED -> contaComboBox.setEnabled((Boolean) evt.getNewValue());
            case PixFormModel.CONTA_CHECKBOX -> contaCheckBox.setSelected((Boolean) evt.getNewValue());
            case PixFormModel.CONTA -> contaComboBox.setSelectedItem(evt.getNewValue());
            case PixFormModel.PIX_ENABLED -> {
                Boolean pixEnabled = (Boolean) evt.getNewValue();
                chaveTextField.setEnabled(pixEnabled);
                tipoPixComboBox.setEnabled(pixEnabled);
            }
            case PixFormModel.TIPO_PIX -> tipoPixComboBox.setSelectedItem(evt.getNewValue());
            case PixFormModel.CHAVE_INVALID -> {
                Boolean chaveInvalid = (Boolean) evt.getNewValue();
                if (chaveInvalid) chaveTextField.putClientProperty(FlatClientProperties.OUTLINE,"error");
                else chaveTextField.putClientProperty(FlatClientProperties.OUTLINE, null);
            }
            case PixFormModel.CHAVE -> chaveTextField.setText((String) evt.getNewValue());
            case PixFormModel.BANCO -> bancoTextField.setText((String) evt.getNewValue());
        }
    }

}
package com.santacarolina.areas.pastaContabil.frmPastaContabil;

import com.santacarolina.dao.PastaDAO;
import com.santacarolina.exceptions.FetchFailException;
import com.santacarolina.interfaces.Validator;
import com.santacarolina.interfaces.ViewUpdater;
import com.santacarolina.model.PastaContabil;
import com.santacarolina.util.OptionDialog;
import com.santacarolina.util.ValidatorViolations;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.util.Optional;

public class PastaContabilValidator implements Validator {

    @Override
    public boolean validate(ViewUpdater viewUpdater) throws FetchFailException {
        PastaContabilModel model = (PastaContabilModel) viewUpdater;
        if (StringUtils.isBlank(model.getNomePasta())) {
            ValidatorViolations.violateEmptyFields("Nome da Pasta");
            return false;
        } else if (StringUtils.isBlank(model.getFolderPath())) {
            ValidatorViolations.violateEmptyFields("Caminho da Pasta");
            return false;
        } else if (model.getContaBancaria() == null) {
            ValidatorViolations.violateEmptyFields("Conta Bancária");
            return false;
        } else if (pastaExists(model)) {
            return false;
        }
        return true;
    }

    //Verifica se há uma pasta com mesmo nome, se sim, oferece opção de substituir.
    private boolean pastaExists(PastaContabilModel model) throws FetchFailException {
        Optional<PastaContabil> optionalPasta = new PastaDAO().findByNome(model.getNomePasta());
        if (optionalPasta.isPresent() && optionalPasta.get().getId() != model.getPastaContabil().getId()) {
            int result = OptionDialog.showReplaceDialog("Esta pasta já existe. Deseja substituir a existente por esta?");
            if (result == JOptionPane.YES_OPTION) {
                model.getPastaContabil().setId(optionalPasta.get().getId());
                return false;
            }
            return true;
        }
        return false;
    }

}
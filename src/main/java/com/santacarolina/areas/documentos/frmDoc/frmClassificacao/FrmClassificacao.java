package com.santacarolina.areas.documentos.frmDoc.frmClassificacao;


import com.santacarolina.exceptions.FetchFailException;
import com.santacarolina.model.Produto;
import com.santacarolina.util.CustomErrorThrower;

public class FrmClassificacao {

    public static void open(Produto produto) {
        try {
            FormView view = new FormView();
            FormModel model = new FormModel(produto);
            FormController controller = new FormController(view, model);
            controller.showView();
        } catch (FetchFailException e) {
            CustomErrorThrower.throwError(e);
        }
    }

}
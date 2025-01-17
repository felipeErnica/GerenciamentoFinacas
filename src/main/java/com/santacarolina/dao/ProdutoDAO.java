package com.santacarolina.dao;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.santacarolina.exceptions.DeleteFailException;
import com.santacarolina.exceptions.FetchFailException;
import com.santacarolina.model.DocumentoFiscal;
import com.santacarolina.model.Produto;
import com.santacarolina.util.ApiRequest;

public class ProdutoDAO {

    private final Logger logger = LogManager.getLogger();
    private final String MAPPING  = "/produtos";
    private ApiRequest<Produto> apiRequest;

    public ProdutoDAO() { this.apiRequest = new ApiRequest<>(Produto.class); }

    public List<Produto> findByDoc(DocumentoFiscal documentoFiscal) throws FetchFailException {
        String query = MAPPING  + "/documento=" + documentoFiscal.getId();
        try {
            return apiRequest.getListRequest(query);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new FetchFailException(e, logger);
        }
    }

    public List<Produto> findAll() throws FetchFailException {
        try {
            return apiRequest.getListRequest(MAPPING);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new FetchFailException(e, logger);
        }
    }

    public void deleteAll(List<Produto> list) throws DeleteFailException {
        String query = MAPPING + "/delete-batch";
        try {
            apiRequest.postListRequest(query, list);
        } catch (IOException | URISyntaxException | InterruptedException e) {
            throw new DeleteFailException(e, logger);
        }
    }

    public List<Produto> findByDocId(long id) throws FetchFailException {
        String query = MAPPING + "/documento=" + id;
        try {
            return apiRequest.getListRequest(query);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new FetchFailException(e, logger);
        }
    }

}

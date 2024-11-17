package br.com.pablopes.tcc.service;

import br.com.pablopes.tcc.model.dto.CalculateRequest;

public interface CalculateService {

    Object execute(CalculateRequest config);
}

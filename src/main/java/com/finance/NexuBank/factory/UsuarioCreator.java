package com.finance.nexubank.factory;

import com.finance.nexubank.dto.UsuarioCriacaoDTO;
import com.finance.nexubank.model.AbstractUsuario;

public interface UsuarioCreator {
    AbstractUsuario createUsuario(UsuarioCriacaoDTO dto);
}

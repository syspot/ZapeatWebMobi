package br.com.zapeat.mobile.faces;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;
import br.com.zapeat.mobile.dao.UsuarioDAO;
import br.com.zapeat.mobile.model.UsuarioModel;
import br.com.zapeat.mobile.util.Constantes;
import br.com.zapeat.mobile.util.Utilitarios;

@ManagedBean
public class TermoUsoFaces implements Serializable {
	
	public TermoUsoFaces() {
		
		String aceite = TSFacesUtil.getRequestParameter("aceito");
		
		if(!TSUtil.isEmpty(aceite)) {
			
			this.aceitarTermoUso();
			
		}
		
	}
	
	private void aceitarTermoUso() {
		
		UsuarioModel usuario = new UsuarioModel();
		
		usuario.setId(Utilitarios.getIdUsuarioTermoUso());
		
		try {
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.aceitarTermoUso(usuario);
			
			usuario =  usuarioDAO.obterById(usuario);
			
			TSFacesUtil.setManagedBeanInSession(Constantes.USUARIO_LOGADO,usuario);
			
			TSFacesUtil.getResponse().sendRedirect("menu.xhtml");
			
		} catch (TSApplicationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

}

package br.com.zapeat.mobile.faces;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import br.com.topsys.web.util.TSFacesUtil;

@ManagedBean
public class SairFaces implements Serializable {

	public SairFaces() {

		TSFacesUtil.getRequest().getSession().invalidate();

		TSFacesUtil.getRequest().getSession(true);

		try {
			TSFacesUtil.getResponse().sendRedirect("menu.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

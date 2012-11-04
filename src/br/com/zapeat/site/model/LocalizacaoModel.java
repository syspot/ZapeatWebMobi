package br.com.zapeat.site.model;

import java.io.Serializable;

import br.com.topsys.util.TSUtil;

@SuppressWarnings("serial")
public class LocalizacaoModel implements Serializable {

	private Double latitude;
	private Double longitude;
	private Integer distanciaMaxima;

	public LocalizacaoModel() {

	}

	public LocalizacaoModel(String location) {

		if (!TSUtil.isEmpty(location)) {
			location = location.replace("(", "").replace(")", "");
			String[] tokens = location.split(",");
			if (tokens.length > 1) {
				this.latitude = Double.valueOf(tokens[0].trim());
				this.longitude = Double.valueOf(tokens[1].trim());
			}
		}
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Integer getDistanciaMaxima() {
		return distanciaMaxima;
	}

	public void setDistanciaMaxima(Integer distanciaMaxima) {
		this.distanciaMaxima = distanciaMaxima;
	}

}

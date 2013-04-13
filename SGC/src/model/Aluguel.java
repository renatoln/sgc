package model;

import java.io.Serializable;


/**
 * 
 * @author magno.oliveira
 *
 */
@SuppressWarnings("serial")
public class Aluguel implements Serializable{
	
	private int idAluguel;
	private String dataAluguel;
	private String horaInicio;
	private String horaFim;
	private String status;
	private String espacoAlugavel;
	
	public int getIdAluguel() {
		return idAluguel;
	}
	
	public void setIdAluguel(int idAluguel) {
		this.idAluguel = idAluguel;
	}
	
	public String getDataAluguel() {
		return dataAluguel;
	}

	public void setDataAluguel(String dataAluguel) {
		this.dataAluguel = dataAluguel;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEspacoAlugavel() {
		return espacoAlugavel;
	}

	public void setEspacoAlugavel(String espacoAlugavel) {
		this.espacoAlugavel = espacoAlugavel;
	}
}

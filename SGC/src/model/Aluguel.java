package model;

import java.sql.Date;
import java.sql.Time;

/**
 * 
 * @author magno.oliveira
 *
 */
public class Aluguel {
	
	private int idAluguel;
	private Date dataAluguel;
	private Time horaInicio;
	private Time horaFim;
	private boolean status;
	private EspacoAlugavel espacoAlugavel;
	
	public int getIdAluguel() {
		return idAluguel;
	}
	
	public void setIdAluguel(int idAluguel) {
		this.idAluguel = idAluguel;
	}
	
	public Date getDataAluguel() {
		return dataAluguel;
	}
	
	public void setDataAluguel(Date dataAluguel) {
		this.dataAluguel = dataAluguel;
	}
	
	public Time getHoraInicio() {
		return horaInicio;
	}
	
	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}
	
	public Time getHoraFim() {
		return horaFim;
	}
	
	public void setHoraFim(Time horaFim) {
		this.horaFim = horaFim;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public EspacoAlugavel getEspacoAlugavel() {
		return espacoAlugavel;
	}
	
	public void setEspacoAlugavel(EspacoAlugavel espacoAlugavel) {
		this.espacoAlugavel = espacoAlugavel;
	}
	
	public void setEspacoAlugavel(int espacoAlugavel) {
		this.espacoAlugavel = new EspacoAlugavelDAO().getOne(espacoAlugavel);
	}
}

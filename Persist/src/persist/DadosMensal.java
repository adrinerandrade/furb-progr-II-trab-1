package persist;

import java.util.Date;
import java.util.List;

public class DadosMensal {
	
	private Date mes;
	private List<ClimaDoDia> dias;
	
	public Date getMes() {
		return mes;
	}
	
	public void setMes(Date mes) {
		this.mes = mes;
	}
	
	public List<ClimaDoDia> getDias() {
		return dias;
	}
	
	public void setDias(List<ClimaDoDia> dias) {
		this.dias = dias;
	}

}

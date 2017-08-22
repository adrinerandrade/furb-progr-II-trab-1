package persist;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DadosMensal {
	
	private Date mes;
	private List<ClimaDoDia> dias = new ArrayList<>();
	
	public Date getMes() {
		return mes;
	}
	
	public void setMes(Date mes) {
		this.mes = mes;
	}
	
	public List<ClimaDoDia> getDias() {
		return dias;
	}
	
	public void addClimaDoDia(ClimaDoDia clima) {
		this.dias.add(clima);
	}

}

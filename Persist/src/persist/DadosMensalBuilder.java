package persist;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DadosMensalBuilder {

	private List<DadosMensal> dados = new ArrayList<>();

	public void novoClimaDoDia(ClimaDoDia climaDoDia) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(climaDoDia.getData().getTime()));
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		
		Date monthYear = calendar.getTime();
		DadosMensal dadosMensal = dados.stream() //
			.filter(d -> d.getMes().equals(monthYear))
			.findFirst()
			.orElseGet(() -> {
				DadosMensal newDadosMensal = new DadosMensal();
				newDadosMensal.setMes(monthYear);
				dados.add(newDadosMensal);
				return newDadosMensal;
			});
		
		dadosMensal.addClimaDoDia(climaDoDia);
	}

	public List<DadosMensal> build() {
		return dados;
	}
	
}

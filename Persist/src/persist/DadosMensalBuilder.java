package persist;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class DadosMensalBuilder {

	private List<DadosMensal> dados = new ArrayList<>();

	public void novoClimaDoDia(ClimaDoDia climaDoDia) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(climaDoDia.getData());
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
	}
}

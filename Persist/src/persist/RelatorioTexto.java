package persist;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RelatorioTexto {
	public static void gerarRelatorio(List<ClimaDoDia> lista, String path) {
		int acumuladoChuva = 0;
		
		int totalVelocidadeVento = 0;
		int maiorVelocidadeVento = 0;
		Date dataMaiorVelocidadeVento = new Date();
		String direcaoMaiorVelocidadeVento = ""; 
		
		int menorVelocidadeVento = 0;
		Date dataMenorVelocidadeVento = new Date();
		String direcaoMenorVelocidadeVento = "";
		
		double totalTemperatura = 0;
		
		double maiorTemperatura = 0;
		Date dataMaiorTemperatura = new Date();
		
		double menorTemperatura = 0;
		Date dataMenorTemperatura = new Date();
		
		for(ClimaDoDia leitura: lista) {
			acumuladoChuva += leitura.getIndicePluviometrico();
			totalVelocidadeVento += leitura.getVelocidadeVento();
			totalTemperatura += leitura.getTemperatura();
			
			if(leitura.getVelocidadeVento() > maiorVelocidadeVento) {
				maiorVelocidadeVento = leitura.getVelocidadeVento();
				dataMaiorVelocidadeVento = leitura.getData();
				direcaoMaiorVelocidadeVento = leitura.getDirecaoVento();
			} else if (leitura.getVelocidadeVento() < menorVelocidadeVento  || menorVelocidadeVento == 0) {
				menorVelocidadeVento = leitura.getVelocidadeVento();
				dataMenorVelocidadeVento = leitura.getData();
				direcaoMenorVelocidadeVento = leitura.getDirecaoVento();
			}
			
			if(leitura.getTemperatura() > maiorTemperatura) {
				maiorTemperatura = leitura.getTemperatura();
				dataMaiorTemperatura = leitura.getData();
			} else if (leitura.getTemperatura() < menorTemperatura  || menorTemperatura == 0) {
				menorTemperatura = leitura.getTemperatura(); 
				dataMenorTemperatura = leitura.getData();
			}			
		}
		
		BufferedWriter saida;
		try {
			saida = new BufferedWriter(new FileWriter(path + "/Relatório-" + new SimpleDateFormat("MMMM-yyyy").format(lista.get(0).getData())+".txt"));
			saida.write("Mês: " + new SimpleDateFormat("MMMM/yyyy").format(lista.get(0).getData()));
			saida.newLine();
			saida.write("Quantidade de dias considerados: " + lista.size());
			saida.newLine();
			saida.write("Acumulado de Chuva: " + acumuladoChuva + " mm");
			saida.newLine();
			saida.write("Velocidade média do Vento:" + totalVelocidadeVento / lista.size() + "km/h");
			saida.newLine();
			saida.write("Maior velocidade do Vento:" + maiorVelocidadeVento + "km/h em " + new SimpleDateFormat("dd/MM/yyyy").format(dataMaiorVelocidadeVento) + " na direção: " + direcaoMaiorVelocidadeVento);
			saida.newLine();
			saida.write("Menor velocidade do Vento:" + menorVelocidadeVento + "km/h em " + new SimpleDateFormat("dd/MM/yyyy").format(dataMenorVelocidadeVento) + " na direção: " + direcaoMenorVelocidadeVento);
			saida.newLine();
			saida.write("Temperatura média: " + totalTemperatura / lista.size());
			saida.newLine();
			saida.write("Maior Temperatura: " + maiorTemperatura + "°C em " + new SimpleDateFormat("dd/MM/yyyy").format(dataMaiorTemperatura));
			saida.newLine();
			saida.write("Menor Temperatura: " + menorTemperatura + "°C em " + new SimpleDateFormat("dd/MM/yyyy").format(dataMenorTemperatura));
			saida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
       
		
}

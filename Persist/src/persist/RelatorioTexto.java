package persist;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RelatorioTexto {
	public void gerarRelatorio(List<ClimaDoDia> lista) {
		int i = 0;
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
		
		for(; i < lista.size(); i++) {
			acumuladoChuva += lista.get(i).getIndicePluviometrico();
			totalVelocidadeVento += lista.get(i).getVelocidadeVento();
			totalTemperatura += lista.get(i).getTemperatura();
			
			if(lista.get(i).getVelocidadeVento() > maiorVelocidadeVento) {
				maiorVelocidadeVento = lista.get(i).getVelocidadeVento();
				dataMaiorVelocidadeVento = lista.get(i).getData();
				direcaoMaiorVelocidadeVento = lista.get(i).getDirecaoVento();
			} else if (lista.get(i).getVelocidadeVento() < menorVelocidadeVento  || menorVelocidadeVento == 0) {
				menorVelocidadeVento = lista.get(i).getVelocidadeVento();
				dataMenorVelocidadeVento = lista.get(i).getData();
				direcaoMenorVelocidadeVento = lista.get(i).getDirecaoVento();
			}
			
			if(lista.get(i).getTemperatura() > maiorTemperatura) {
				maiorTemperatura = lista.get(i).getTemperatura();
				dataMaiorTemperatura = lista.get(i).getData();
			} else if (lista.get(i).getTemperatura() < menorTemperatura  || menorTemperatura == 0) {
				menorTemperatura = lista.get(i).getTemperatura(); 
				dataMenorTemperatura = lista.get(i).getData();
			}			
		}
		
		BufferedWriter saida;
		try {
			saida = new BufferedWriter(new FileWriter("Relatório " + new SimpleDateFormat("MMMM/YYYY").format(lista.get(0).getData())+".txt"));
			saida.write("Mês: " + new SimpleDateFormat("MMMM/YYYY").format(lista.get(0).getData()));
			saida.newLine();
			saida.write("Acumulado de Chuva: " + acumuladoChuva);
			saida.newLine();
			saida.write("Velocidade média do Vento:" + totalVelocidadeVento / lista.size());
			saida.newLine();
			saida.write("Maior velocidade do Vento:" + maiorVelocidadeVento + "km/h em " + new SimpleDateFormat("dd/MM/YYYY").format(dataMaiorVelocidadeVento) + " na direção: " + direcaoMaiorVelocidadeVento);
			saida.newLine();
			saida.write("Menor velocidade do Vento:" + menorVelocidadeVento + "km/h em " + new SimpleDateFormat("dd/MM/YYYY").format(dataMenorVelocidadeVento) + " na direção: " + direcaoMenorVelocidadeVento);
			saida.newLine();
			saida.write("Temperatura média: " + totalTemperatura / lista.size());
			saida.newLine();
			saida.write("Maior Temperatura: " + maiorTemperatura + "°C em " + new SimpleDateFormat("dd/MM/YYYY").format(dataMaiorTemperatura));
			saida.newLine();
			saida.write("Menor Temperatura: " + menorTemperatura + "°C em " + new SimpleDateFormat("dd/MM/YYYY").format(dataMenorTemperatura));
			saida.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
       
		
}

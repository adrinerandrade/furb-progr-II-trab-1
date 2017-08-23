package persist;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class RelatorioUI extends JFrame {

	private JPanel contentPane;
	JTextArea textArea;

	public RelatorioUI(List<ClimaDoDia> lista) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 537, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 11, 501, 345);
		contentPane.add(textArea);
		this.setVisible(true);
		gerarRelatorio(lista);
	}

	public void gerarRelatorio(List<ClimaDoDia> lista) {
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

		for (ClimaDoDia leitura : lista) {
			acumuladoChuva += leitura.getIndicePluviometrico();
			totalVelocidadeVento += leitura.getVelocidadeVento();
			totalTemperatura += leitura.getTemperatura();

			if (leitura.getVelocidadeVento() > maiorVelocidadeVento) {
				maiorVelocidadeVento = leitura.getVelocidadeVento();
				dataMaiorVelocidadeVento = leitura.getData();
				direcaoMaiorVelocidadeVento = leitura.getDirecaoVento();
			} else if (leitura.getVelocidadeVento() < menorVelocidadeVento || menorVelocidadeVento == 0) {
				menorVelocidadeVento = leitura.getVelocidadeVento();
				dataMenorVelocidadeVento = leitura.getData();
				direcaoMenorVelocidadeVento = leitura.getDirecaoVento();
			}

			if (leitura.getTemperatura() > maiorTemperatura) {
				maiorTemperatura = leitura.getTemperatura();
				dataMaiorTemperatura = leitura.getData();
			} else if (leitura.getTemperatura() < menorTemperatura || menorTemperatura == 0) {
				menorTemperatura = leitura.getTemperatura();
				dataMenorTemperatura = leitura.getData();
			}
		}

		textArea.append("Mês: " + new SimpleDateFormat("MMMM/yyyy").format(lista.get(0).getData()));
		textArea.append("\n");
		textArea.append("Quantidade de dias considerados: " + lista.size());
		textArea.append("\n");
		textArea.append("Acumulado de Chuva: " + acumuladoChuva + " mm");
		textArea.append("\n");
		textArea.append("Velocidade média do Vento:" + totalVelocidadeVento / lista.size() + "km/h");
		textArea.append("\n");
		textArea.append("Maior velocidade do Vento:" + maiorVelocidadeVento + "km/h em "
				+ new SimpleDateFormat("dd/MM/yyyy").format(dataMaiorVelocidadeVento) + " na direção: "
				+ direcaoMaiorVelocidadeVento);
		textArea.append("\n");
		textArea.append("Menor velocidade do Vento:" + menorVelocidadeVento + "km/h em "
				+ new SimpleDateFormat("dd/MM/yyyy").format(dataMenorVelocidadeVento) + " na direção: "
				+ direcaoMenorVelocidadeVento);
		textArea.append("\n");
		textArea.append("Temperatura média: " + String.format("%.2f", totalTemperatura / lista.size()) + "°C");
		textArea.append("\n");
		textArea.append("Maior Temperatura: " + String.format("%.2f", maiorTemperatura) + "°C em "
				+ new SimpleDateFormat("dd/MM/yyyy").format(dataMaiorTemperatura));
		textArea.append("\n");
		textArea.append("Menor Temperatura: " + String.format("%.2f", menorTemperatura) + "°C em "
				+ new SimpleDateFormat("dd/MM/yyyy").format(dataMenorTemperatura));
	}
}

package persist;

import java.awt.EventQueue;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RelatorioUI frame = new RelatorioUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RelatorioUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 537, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setEnabled(false);
		textArea.setEditable(false);
		textArea.setBounds(10, 11, 501, 345);
		contentPane.add(textArea);
	}
	
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
		
		textArea.append("Mês: " + new SimpleDateFormat("MMMM/YYYY").format(lista.get(0).getData()));
		textArea.append("/n");
		textArea.append("Acumulado de Chuva: " + acumuladoChuva);
		textArea.append("/n");
		textArea.append("Velocidade média do Vento:" + totalVelocidadeVento / lista.size());
		textArea.append("/n");
		textArea.append("Maior velocidade do Vento:" + maiorVelocidadeVento + "km/h em " + new SimpleDateFormat("dd/MM/YYYY").format(dataMaiorVelocidadeVento) + " na direção: " + direcaoMaiorVelocidadeVento);
		textArea.append("/n");
		textArea.append("Menor velocidade do Vento:" + menorVelocidadeVento + "km/h em " + new SimpleDateFormat("dd/MM/YYYY").format(dataMenorVelocidadeVento) + " na direção: " + direcaoMenorVelocidadeVento);
		textArea.append("/n");
		textArea.append("Temperatura média: " + totalTemperatura / lista.size());
		textArea.append("/n");
		textArea.append("Maior Temperatura: " + maiorTemperatura + "°C em " + new SimpleDateFormat("dd/MM/YYYY").format(dataMaiorTemperatura));
		textArea.append("/n");
		textArea.append("Menor Temperatura: " + menorTemperatura + "°C em " + new SimpleDateFormat("dd/MM/YYYY").format(dataMenorTemperatura));
	}
}

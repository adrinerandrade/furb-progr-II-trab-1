package persist;
import java.awt.EventQueue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EstacaoMetereologicaUI {

	private JFrame frame;
	private JTextField txData;
	private JTextField txVelecidadeVento;
	private JTextField txIndicePluviometrico;
	private JTextField txTemperatura;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private JComboBox cbDirecaoVento;
	private ClimaDoDia leitura;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EstacaoMetereologicaUI window = new EstacaoMetereologicaUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EstacaoMetereologicaUI() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 832, 536);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 398, 475);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setBounds(10, 11, 103, 14);
		panel.add(lblData);
		
		JLabel lblDireoDoVento = new JLabel("Dire\u00E7\u00E3o do Vento:");
		lblDireoDoVento.setBounds(10, 36, 103, 14);
		panel.add(lblDireoDoVento);
		
		JLabel lblVelocidadeDoVento = new JLabel("Velocidade do Vento:");
		lblVelocidadeDoVento.setBounds(10, 61, 103, 14);
		panel.add(lblVelocidadeDoVento);
		
		JLabel lblIndicePluviometrico = new JLabel("Indice Pluviometrico:");
		lblIndicePluviometrico.setBounds(10, 86, 103, 14);
		panel.add(lblIndicePluviometrico);
		
		JLabel lblTemperatura = new JLabel("Temperatura:");
		lblTemperatura.setBounds(10, 111, 103, 14);
		panel.add(lblTemperatura);
		
		txData = new JTextField();
		txData.setBounds(141, 8, 147, 20);
		panel.add(txData);
		txData.setColumns(10);
		
		cbDirecaoVento = new JComboBox();
		cbDirecaoVento.setBounds(141, 33, 147, 20);
		panel.add(cbDirecaoVento);
		cbDirecaoVento.addItem(DirecaoVento.E);
		cbDirecaoVento.addItem(DirecaoVento.N);
		cbDirecaoVento.addItem(DirecaoVento.NE);
		cbDirecaoVento.addItem(DirecaoVento.NW);
		cbDirecaoVento.addItem(DirecaoVento.S);
		cbDirecaoVento.addItem(DirecaoVento.SE);
		cbDirecaoVento.addItem(DirecaoVento.SW);
		cbDirecaoVento.addItem(DirecaoVento.W);
		
		txVelecidadeVento = new JTextField();
		txVelecidadeVento.setBounds(141, 58, 147, 20);
		panel.add(txVelecidadeVento);
		txVelecidadeVento.setColumns(10);
		
		txIndicePluviometrico = new JTextField();
		txIndicePluviometrico.setBounds(141, 83, 147, 20);
		panel.add(txIndicePluviometrico);
		txIndicePluviometrico.setColumns(10);
		
		txTemperatura = new JTextField();
		txTemperatura.setBounds(141, 108, 147, 20);
		panel.add(txTemperatura);
		txTemperatura.setColumns(10);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(298, 7, 89, 23);
		panel.add(btnAdicionar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(418, 11, 388, 475);
		frame.getContentPane().add(panel_1);
	}
	
	private Date getData() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		return formatter.parse(txData.getText());
	}
	
	private DirecaoVento getDirecaoVento() {
		return (DirecaoVento)cbDirecaoVento.getSelectedItem();
	}
	
	private int getVelocidadeVento() {
		return Integer.parseInt(txVelecidadeVento.getText());
	}
	
	private int getIndicePluviometrico() {
		return Integer.parseInt(txIndicePluviometrico.getText());
	}
	
	private double getTemperatura() {
		return Double.parseDouble(txTemperatura.getText());
	}
	
	public ClimaDoDia getLeitura() {
		leitura = new ClimaDoDia();
		try {
			leitura.setData(getData());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		leitura.setDirecaoVento(getDirecaoVento());
		leitura.setIndicePluviometrico(getIndicePluviometrico());
		leitura.setTemperatura(getTemperatura());
		return leitura;
	}

	
}

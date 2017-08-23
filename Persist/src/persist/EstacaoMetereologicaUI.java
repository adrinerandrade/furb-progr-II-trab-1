package persist;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class EstacaoMetereologicaUI {

	private JFrame frame;
	private JTextField txtArquivo;
	private JTextField txtArquivoMensal;
	private JRadioButton rdbtnArquivoTexto;
	private JRadioButton rdbtnEmTela;

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
		frame.setBounds(100, 100, 473, 327);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Arquivo Bin\u00E1rio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 437, 79);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		txtArquivo = new JTextField();
		txtArquivo.setBounds(10, 22, 281, 20);
		panel.add(txtArquivo);
		txtArquivo.setColumns(10);

		JButton btnSelecionarArquivo = new JButton("Selecionar");
		btnSelecionarArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jfc = new JFileChooser();
				int valor = jfc.showOpenDialog(null);
				if (valor == JFileChooser.APPROVE_OPTION) {
					txtArquivo.setText(jfc.getSelectedFile().getAbsolutePath());
				}
			}
		});
		btnSelecionarArquivo.setBounds(301, 21, 126, 23);
		panel.add(btnSelecionarArquivo);

		JButton btnNewButton = new JButton("Gerar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Path pathArq = Paths.get(txtArquivo.getText());
				List<DadosMensal> dados;
				try {
					dados = new LeitorDadosMetereologicos(txtArquivo.getText()).leia();
					PersistenciaMes.persistirMes(dados,  pathArq.toFile().getParent().toString());
				} catch (ClimaDoDiaLeituraException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		btnNewButton.setBounds(301, 45, 126, 23);
		panel.add(btnNewButton);

		JLabel lblArquivosMensair = new JLabel("Arquivos Mensais");
		lblArquivosMensair.setHorizontalAlignment(SwingConstants.RIGHT);
		lblArquivosMensair.setBounds(98, 49, 193, 14);
		panel.add(lblArquivosMensair);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Relat\u00F3rios", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 101, 437, 176);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		txtArquivoMensal = new JTextField();
		txtArquivoMensal.setBounds(10, 22, 280, 20);
		panel_1.add(txtArquivoMensal);
		txtArquivoMensal.setColumns(10);

		JButton btnSelecionarArquivoMensal = new JButton("Selecionar");
		btnSelecionarArquivoMensal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				int valor = jfc.showOpenDialog(null);
				if (valor == JFileChooser.APPROVE_OPTION) {
					txtArquivoMensal.setText(jfc.getSelectedFile().getAbsolutePath());
				}
			}
		});
		btnSelecionarArquivoMensal.setBounds(300, 21, 127, 23);
		panel_1.add(btnSelecionarArquivoMensal);

		JButton btnArquivoTexto = new JButton("Gerar");
		btnArquivoTexto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnArquivoTexto.isSelected()) {
					Path pathArq = Paths.get(txtArquivoMensal.getText());
					List<ClimaDoDia> lista = getObjetos(pathArq.toFile());
					if(lista != null && !lista.isEmpty()) {
						RelatorioTexto.gerarRelatorio(lista,  pathArq.toFile().getParent().toString());
					}
				} else if (rdbtnEmTela.isSelected()) {
					Path pathArq = Paths.get(txtArquivoMensal.getText());
					List<ClimaDoDia> lista = getObjetos(pathArq.toFile());
					if(lista != null && !lista.isEmpty()) {
						new RelatorioUI(lista);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um tipo de relatório!");
				}
			}
		});
		btnArquivoTexto.setBounds(300, 52, 127, 23);
		panel_1.add(btnArquivoTexto);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Tipo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 53, 280, 53);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		ButtonGroup bg = new ButtonGroup();
		
		rdbtnArquivoTexto = new JRadioButton("Arquivo Texto");
		rdbtnArquivoTexto.setBounds(6, 21, 138, 23);
		panel_2.add(rdbtnArquivoTexto);
		bg.add(rdbtnArquivoTexto);
		
		rdbtnEmTela = new JRadioButton("Em Tela");
		rdbtnEmTela.setBounds(157, 21, 117, 23);
		panel_2.add(rdbtnEmTela);
		bg.add(rdbtnEmTela);
	}
	
    @SuppressWarnings("unchecked")
	public List<ClimaDoDia> getObjetos(File file) {
    	ArrayList<ClimaDoDia> listaClimaDoDia = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            listaClimaDoDia = (ArrayList<ClimaDoDia>) ois.readObject();
        } catch (FileNotFoundException e) {
        	JOptionPane.showMessageDialog(null, "O arquivo " + file.getName() + " não foi encontrado!");
		} catch (StreamCorruptedException e) {
			JOptionPane.showMessageDialog(null, "O arquivo " + file.getName() + " não está no formato correto!");
		} catch (Exception e) {
			e.printStackTrace();
		}
        return listaClimaDoDia;
    }
}

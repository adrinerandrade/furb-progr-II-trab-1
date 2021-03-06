package persist;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JOptionPane;

public class PersistenciaMes {
	public static void persistirMes(List<DadosMensal> dados, String path) {
		for(DadosMensal dado: dados) {
			Path pathArq = Paths.get(path + "\\" +new SimpleDateFormat("MM-yyyy").format(dado.getMes()) + ".dat");
			ObjectOutputStream file;
			try {
				file = new ObjectOutputStream(new FileOutputStream(pathArq.toFile()));
				file.writeObject(dado.getDias());
				file.close();
				JOptionPane.showMessageDialog(null, "Os arquivos foram gerados no diretório: " + path);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}

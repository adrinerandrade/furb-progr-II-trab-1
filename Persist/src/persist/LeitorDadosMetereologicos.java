package persist;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LeitorDadosMetereologicos {

	private File file;

	public LeitorDadosMetereologicos(String path) {
		this.file = Paths.get(path).toFile();
		if (!this.file.exists()) {
			throw new IllegalArgumentException("Não existe um arquivo para o caminho informado");
		}
	}

	public List<DadosMensal> leia() {
		DadosMensalBuilder builder = new DadosMensalBuilder();
		try (DataInputStream stream = new DataInputStream(new FileInputStream(this.file))) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date lastDate = null;
			boolean eof = false;
			while (!eof) {
				try {
					ClimaDoDia climaDoDia = criarClimaDoDia(stream, formatter, lastDate);
					builder.novoClimaDoDia(climaDoDia);
				} catch (EOFException e) {
					eof = true;
				}
			}
		} catch (IOException e) {
			throw new RuntimeException("Erro ao tentar ler o arquivo.", e);
		}
		return builder.build();
	}

	private ClimaDoDia criarClimaDoDia(DataInputStream stream, SimpleDateFormat formatter, Date lastDate) throws IOException {
		Date date;
		try {
			date = formatter.parse(stream.readUTF());
		} catch (ParseException e) {
			throw new RuntimeException("Erro ao tentar ler formato da data.", e);
		}
		if (lastDate != null) {
			if (date.equals(lastDate)) {
				throw new RuntimeException(String.format("Dia %s está repetido.", date));
			}
			if (date.before(lastDate)) {
				throw new RuntimeException(String.format("Dia %s foi encontrado antes de %d.", lastDate, date));
			}
		}
		lastDate = date;

		ClimaDoDia climaDoDia = new ClimaDoDia();
		climaDoDia.setData(date);
		char pD = stream.readChar();
		char sD = stream.readChar();
		String direcao = String.valueOf(pD);
		if (sD != ' ') {
			direcao.concat(String.valueOf(sD));
		}
		climaDoDia.setDirecaoVento(direcao);
		climaDoDia.setVelocidadeVento(stream.readInt());
		climaDoDia.setIndicePluviometrico(stream.readInt());
		climaDoDia.setTemperatura(stream.readDouble());

		return climaDoDia;
	}

}

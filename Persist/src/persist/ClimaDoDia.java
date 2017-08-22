package persist;
import java.io.Serializable;
import java.util.Date;

public class ClimaDoDia implements Serializable {

	private Date data;
	private String ventoDirecao;
	private int ventoVelocidade;
	private int indicePluviometrico;
	private double temperatura;

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDirecaoVento() {
		return ventoDirecao;
	}

	public void setDirecaoVento(String direcaoVento) {
		this.ventoDirecao = direcaoVento;
	}

	public int getVelocidadeVento() {
		return ventoVelocidade;
	}

	public void setVelocidadeVento(int velocidadeVento) {
		this.ventoVelocidade = velocidadeVento;
	}

	public int getIndicePluviometrico() {
		return indicePluviometrico;
	}

	public void setIndicePluviometrico(int indicePluviometrico) {
		this.indicePluviometrico = indicePluviometrico;
	}

	public double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}

}

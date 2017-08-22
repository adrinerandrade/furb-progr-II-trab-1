package persist;
import java.io.Serializable;
import java.util.Date;

public class ClimaDoDia implements Serializable {

	private Date data;
	private DirecaoVento direcaoVento;
	private int velocidadeVento;
	private int indicePluviometrico;
	private double temperatura;

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public DirecaoVento getDirecaoVento() {
		return direcaoVento;
	}

	public void setDirecaoVento(DirecaoVento direcaoVento) {
		this.direcaoVento = direcaoVento;
	}

	public int getVelocidadeVento() {
		return velocidadeVento;
	}

	public void setVelocidadeVento(int velocidadeVento) {
		this.velocidadeVento = velocidadeVento;
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

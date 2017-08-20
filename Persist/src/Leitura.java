import java.io.Serializable;
import java.time.LocalDate;

public class Leitura implements Serializable {

	private LocalDate data;
	private DirecaoVento direcaoVento;
	private int velocidadeVento;
	private int indicePluviometrico;
	private double temperatura;

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
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

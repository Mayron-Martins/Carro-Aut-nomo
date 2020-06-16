package ambiente;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import posicao.PosicaoXY;

public class Obstaculos {
	private Caminho estrada;
	private PosicaoXY posicaoXY;
	private int quantObstaculos;
	private int maxObstaculos;
	
	public Obstaculos(Caminho estrada) {
		this.estrada = estrada;
		posicaoXY = new PosicaoXY();
		this.aleatoriedadeDosObstaculos();
		estrada.setObstaculos(this);
		this.quantObstaculos=1;
	}
	
	//Define uma posi��o aleat�ria dentro da estrada para o obst�culo
	private void aleatoriedadeDosObstaculos() {
		int inicioY = 1;
		int inicioX=0;
		int fimY = this.estrada.getTamanhoDoPercurso()-1;
		int fimX = this.estrada.getLarguraDaEstrada()-1;
		
		int posicaoY;
		int posicaoX;
		
		Random random = ThreadLocalRandom.current();
		posicaoY = random.nextInt(fimY - inicioY + 1) + inicioY;
		posicaoX = random.nextInt(fimX - inicioX + 1) + inicioX;
		
		if(this.estrada.retornarValordaPosicaodaEstrada(posicaoY, posicaoX).equals("-O-")
		|| this.estrada.retornarValordaPosicaodaEstrada(posicaoY, posicaoX).equals("*C*")) {
			aleatoriedadeDosObstaculos();
		}
		else {
			this.posicaoXY = new PosicaoXY(posicaoY, posicaoX);
		}
		
	}
	
	//Cria novos obst�culos pela estrada, necess�rio medir valor conforme tamanho da estrada
	//Muitos obst�culos para um percurso pequeno resultam em erro
	public void gerarObstaculos() {
		if(this.quantObstaculos<this.maxObstaculos) {
			Obstaculos novoObstaculo = new Obstaculos(this.estrada);
			this.quantObstaculos++;
		}
		
	}
	
	//Posicao do Obst�culo
	public PosicaoXY getPosicaoXYObstaculos () {
		return this.posicaoXY;
	}
	
	//Seta o valor m�ximo de obst�culos para a estrada
	public void setMaximoDeObstaculos(int maximo) {
		this.maxObstaculos=maximo;
	}
	
}

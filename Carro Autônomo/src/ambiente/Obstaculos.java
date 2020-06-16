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
	
	//Define uma posição aleatória dentro da estrada para o obstáculo
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
	
	//Cria novos obstáculos pela estrada, necessário medir valor conforme tamanho da estrada
	//Muitos obstáculos para um percurso pequeno resultam em erro
	public void gerarObstaculos() {
		if(this.quantObstaculos<this.maxObstaculos) {
			Obstaculos novoObstaculo = new Obstaculos(this.estrada);
			this.quantObstaculos++;
		}
		
	}
	
	//Posicao do Obstáculo
	public PosicaoXY getPosicaoXYObstaculos () {
		return this.posicaoXY;
	}
	
	//Seta o valor máximo de obstáculos para a estrada
	public void setMaximoDeObstaculos(int maximo) {
		this.maxObstaculos=maximo;
	}
	
}

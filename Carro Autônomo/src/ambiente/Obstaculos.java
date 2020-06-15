package ambiente;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import posicao.PosicaoXY;

public class Obstaculos {
	private Caminho estrada;
	private PosicaoXY posicaoXY;
	
	public Obstaculos(Caminho estrada) {
		this.estrada = estrada;
		posicaoXY = new PosicaoXY();
		this.aleatoriedadeDosObstaculos();
		//System.out.println(" "+posicaoXY.getPosicaoX()+ " "+posicaoXY.getPosicaoY());
		estrada.setObstaculos(this);
	}
	
	
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
	
	public void gerarObstaculos() {
		Obstaculos novoObstaculo = new Obstaculos(this.estrada);
	}
	
	public PosicaoXY getPosicaoXYObstaculos () {
		return this.posicaoXY;
	}
	
}

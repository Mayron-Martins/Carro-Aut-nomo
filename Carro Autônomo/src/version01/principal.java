package version01;

import agente.Carro;
import ambiente.Caminho;
import ambiente.Obstaculos;

public class principal {

	public static void main(String[] args) throws InterruptedException {
		Caminho caminho = new Caminho(5);
		caminho.exibirEstrada();
		Carro carro = new Carro(caminho);
		Obstaculos obstaculos = new Obstaculos(caminho);
		
		
		while(carro.dirigindo()&&carro.limiteDaEstrada()==false) {
			carro.zerarPilha();
			obstaculos.gerarObstaculos();
			carro.movimentar();
			caminho.exibirEstrada();
			Thread.sleep(1500);
			
		}

	}

}

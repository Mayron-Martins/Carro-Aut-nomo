package ambiente;

import java.util.ArrayList;

import agente.Carro;
import posicao.PosicaoXY;

public class Caminho {
	private int tamanhodopercurso;
	private int larguraDaEstrada = 3;
	private String percurso[][];
	private Carro carro;
	private ArrayList<Obstaculos> obstaculos = new ArrayList<Obstaculos>();
	
	//Valores
	//E - caminho Livre
	//-O- - Obstáculo
	//*C* - Carro
	
	public Caminho(int tamanhadopercurso) {
		this.tamanhodopercurso = tamanhadopercurso;
		this.construirEstrada();
	}
	
	//Construir Estrada
	private void construirEstrada() {
		this.percurso = new String[tamanhodopercurso][3];
		for(int linha=0; linha < tamanhodopercurso; linha++) {
			for(int coluna=0; coluna<larguraDaEstrada; coluna++) {
				this.percurso[linha][coluna]= ".";
			}
		}
	}
	
	//Exibir Estrada
	public void exibirEstrada() {
		exibirObstaculos();
		atualizarPosicaoAgente();
		for(int linha=0; linha<tamanhodopercurso; linha++) {
			for(int coluna=0; coluna<larguraDaEstrada; coluna++) {
				if((this.percurso[linha][coluna].equals("*C*")||this.percurso[linha][coluna].equals("-O-"))&&coluna==0)
					System.out.print("|"+this.percurso[linha][coluna]+" ");
				else {
					if((this.percurso[linha][coluna].equals("*C*")||this.percurso[linha][coluna].equals("-O-"))&&coluna==larguraDaEstrada-1)
						System.out.print(" "+this.percurso[linha][coluna]+"|");
					else {
						if(this.percurso[linha][coluna].equals("*C*")||this.percurso[linha][coluna].equals("-O-"))
							System.out.print(" "+this.percurso[linha][coluna]+" ");
						else {
							if(coluna==0)
								System.out.print("| "+this.percurso[linha][coluna]+"  ");
							else {
								if(coluna == larguraDaEstrada-1)
									System.out.print("  "+this.percurso[linha][coluna]+" |");
								else
									System.out.print("  "+this.percurso[linha][coluna]+"  ");
						}
					}
					}
				}	
			}
			System.out.println();
		}
		System.out.println();
	}
	
	//Atualizar Posição do agente
	public void atualizarPosicaoAgente() {
		if(this.carro!=null) {
			PosicaoXY posicaoDoCarro = this.carro.getPosicaoXY();
			this.percurso[posicaoDoCarro.getPosicaoY()][posicaoDoCarro.getPosicaoX()] = "*C*";
		}
	}
	
	public void exibirObstaculos() {
		for(int i=0; i<obstaculos.size(); i++) {
			if(this.obstaculos.get(i)!=null) {
				PosicaoXY posicaoDoObstaculo = this.obstaculos.get(i).getPosicaoXYObstaculos();
				this.percurso[posicaoDoObstaculo.getPosicaoY()][posicaoDoObstaculo.getPosicaoX()] = "-O-";
			}
		}
	}
	
	
	public int getTamanhoDoPercurso() {
		return this.tamanhodopercurso;
	}
	
	public int getLarguraDaEstrada() {
		return this.larguraDaEstrada;
	}
	
	
	public String retornarValordaPosicaodaEstrada(PosicaoXY posicaoXY) {
		return this.percurso[posicaoXY.getPosicaoY()][posicaoXY.getPosicaoX()];
	}

	public String retornarValordaPosicaodaEstrada(int posicaoY, int posicaoX) {
		return this.percurso[posicaoY][posicaoX];
	}
	
	public void setCarro(Carro carro) {
		this.carro = carro;
	}
	
	public void setObstaculos (Obstaculos obstaculos) {
		this.obstaculos.add(obstaculos);
	}
	
	public void limparRastro() {
		PosicaoXY posicao = this.carro.getPosicaoXY();
		this.percurso[posicao.getPosicaoY()][posicao.getPosicaoX()] = ".";
	}
	
	

}

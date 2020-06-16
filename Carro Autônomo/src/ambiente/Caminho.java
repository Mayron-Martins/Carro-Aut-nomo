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
	//. - caminho Livre
	//-O- - Obst�culo
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
				//Caso o agente ou o obst�culo estejam no canto direito
				if((this.percurso[linha][coluna].equals("*C*")||this.percurso[linha][coluna].equals("-O-"))&&coluna==0)
					System.out.print("|"+this.percurso[linha][coluna]+" ");
				else {
					//Caso o agente ou obst�culo estejam no canto esquerdo
					if((this.percurso[linha][coluna].equals("*C*")||this.percurso[linha][coluna].equals("-O-"))&&coluna==larguraDaEstrada-1)
						System.out.print(" "+this.percurso[linha][coluna]+"|");
					else {
						//Caso o agente ou o obst�culo estejam em outras posi��es
						if(this.percurso[linha][coluna].equals("*C*")||this.percurso[linha][coluna].equals("-O-"))
							System.out.print(" "+this.percurso[linha][coluna]+" ");
						else {
							//Estrada
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
	
	//Atualizar Posi��o do agente
	public void atualizarPosicaoAgente() {
		if(this.carro!=null) {
			PosicaoXY posicaoDoCarro = this.carro.getPosicaoXY();
			this.percurso[posicaoDoCarro.getPosicaoY()][posicaoDoCarro.getPosicaoX()] = "*C*";
		}
	}
	
	//Mostra todos os obst�culos conforme os constr�i
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
	
	//Retorna o valor correspondete a uma dada posi��o da pista
	public String retornarValordaPosicaodaEstrada(PosicaoXY posicaoXY) {
		return this.percurso[posicaoXY.getPosicaoY()][posicaoXY.getPosicaoX()];
	}
	
	public String retornarValordaPosicaodaEstrada(int posicaoY, int posicaoX) {
		return this.percurso[posicaoY][posicaoX];
	}
	
	//Incorpora o carro � estrada
	public void setCarro(Carro carro) {
		this.carro = carro;
	}
	
	//Incorpora obst�culo por obst�culo � estrada
	public void setObstaculos (Obstaculos obstaculos) {
		this.obstaculos.add(obstaculos);
	}
	
	//Troca o s�mbolo do carro pelo da estrada ap�s ele ter passado
	public void limparRastro() {
		PosicaoXY posicao = this.carro.getPosicaoXY();
		this.percurso[posicao.getPosicaoY()][posicao.getPosicaoX()] = ".";
	}
	
	

}

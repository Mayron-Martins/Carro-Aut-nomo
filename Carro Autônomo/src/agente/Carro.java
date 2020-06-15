package agente;

import ambiente.Caminho;
import posicao.Movimentacao;
import posicao.PosicaoXY;

public class Carro {
	private Caminho estrada;
	private Movimentacao movimentacaoDoCarro;
	private PosicaoXY posicaoXY;
	private int pilhaDeMovimentos;

	
	
	public Carro(Caminho estrada) {
		this.estrada = estrada;
		estrada.setCarro(this);
		this.posicaoXY = new PosicaoXY();
		this.movimentacaoDoCarro = Movimentacao.ATRÁS;
	}
	
	public void movimentar() {
		if(this.pilhaDeMovimentos>5) {
			return;
		}
		if(limiteDaEstrada()) {
			return;
		}
		
		PosicaoXY proximoMovimento = retornarPosicao();
		String valor = this.estrada.retornarValordaPosicaodaEstrada(proximoMovimento);
		
		
		
		if(valor.equals("-O-") || valor.equals("*C*")) {
			proximoMovimento();
			this.aumentarPilha();
			movimentar();
		}
		else {
			this.estrada.limparRastro();
			this.posicaoXY = proximoMovimento;
		}
		
	}
	
	
	public void proximoMovimento() {
		switch(this.movimentacaoDoCarro) {
		case ATRÁS:
			this.movimentacaoDoCarro = Movimentacao.FRENTE;
		break;
		
		case FRENTE:
			if(this.estrada.retornarValordaPosicaodaEstrada(getPosicaoXY().getPosicaoY()+1, getPosicaoXY().getPosicaoX()).equals("-O-")) {
				this.movimentacaoDoCarro = Movimentacao.ESQUERDA;
			}
		break;
		
		case ESQUERDA:
			if(this.estrada.retornarValordaPosicaodaEstrada(getPosicaoXY().getPosicaoY()+1, getPosicaoXY().getPosicaoX()).equals("-O-")) {
				this.movimentacaoDoCarro = Movimentacao.DIREITA;
			}
				
			else {
				this.movimentacaoDoCarro = Movimentacao.FRENTE;
			}	
		break;
		
		case DIREITA:	
			if(this.estrada.retornarValordaPosicaodaEstrada(getPosicaoXY().getPosicaoY()+1, getPosicaoXY().getPosicaoX()).equals("-O-")) {
				this.movimentacaoDoCarro = Movimentacao.ESQUERDA;
			}
					
			else {
				this.movimentacaoDoCarro = Movimentacao.FRENTE;
			}	
		}
	}
	
	
	public PosicaoXY retornarPosicao() {
		int retornoPosicaoX = this.posicaoXY.getPosicaoX();
		int retornoPosicaoY = this.posicaoXY.getPosicaoY();
		
		switch(this.movimentacaoDoCarro) {
		case ATRÁS:
			if(retornoPosicaoY>0)
				retornoPosicaoY-=1;
		break;
		
		case FRENTE:
			if(retornoPosicaoY<this.estrada.getTamanhoDoPercurso()-1)
				retornoPosicaoY+=1;
		
		break;
		
		case ESQUERDA:
			if(retornoPosicaoX<this.estrada.getLarguraDaEstrada()-1)
				retornoPosicaoX+=1;
		break;
		
		case DIREITA:
			if(retornoPosicaoX>0)
				retornoPosicaoX-=1;
			
		break;
		}
		return new PosicaoXY(retornoPosicaoY, retornoPosicaoX);
	}
	
	private void aumentarPilha() {
		this.pilhaDeMovimentos++;
	}
	
	public boolean dirigindo() {
		return this.pilhaDeMovimentos<5;
	}
	
	public void zerarPilha() {
		this.pilhaDeMovimentos = 0;
	}
	
	public void setPosicaoXY(PosicaoXY posicaoXY) {
		this.posicaoXY = posicaoXY;
	}
	
	public PosicaoXY getPosicaoXY() {
		return this.posicaoXY;
	}
	
	public boolean limiteDaEstrada() {
		return this.getPosicaoXY().getPosicaoY()==this.estrada.getTamanhoDoPercurso()-1;
	}

}

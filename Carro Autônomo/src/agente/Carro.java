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
		this.movimentacaoDoCarro = Movimentacao.ATR�S;
	}
	
	//Respons�vel pela movimenta��o do carro
	public void movimentar() {
		//Se o agente tentar se movimentar e n�o conseguir ent�o a pilha ir� crescer e resultar� em finaliza��o
		if(this.pilhaDeMovimentos>5) {
			return;
		}
		
		//Se o agente chegar ao final do percurso resulta em finaliza��o
		if(limiteDaEstrada()) {
			return;
		}
		
		//Atualiza o proximo movimento sempre para a frente caso n�o haja obst�culo � frente
		if(this.estrada.retornarValordaPosicaodaEstrada(getPosicaoXY().getPosicaoY()+1, getPosicaoXY().getPosicaoX()).equals("-O-")==false) {
			this.movimentacaoDoCarro = Movimentacao.FRENTE;
		}
		
		//Retorna a posi��o atual do agente
		PosicaoXY proximoMovimento = retornarPosicao();
		String valor = this.estrada.retornarValordaPosicaodaEstrada(retornarPosicao());
		
		
		//Se na estrada o valor correspondente � frente for o do pr�prio agente ou de um obst�culo resulta em movimenta��o
		if(valor.equals("-O-") || valor.equals("*C*")) {
			proximoMovimento();
			this.aumentarPilha();
			movimentar();
		}
		//Se n�o for preciso alterar a movimenta��o ent�o ele a mant�m, mas isso � alterado conforme regra superior
		else {
			this.estrada.limparRastro();
			this.posicaoXY = proximoMovimento;
		}
		
	}
	
	//Sequ�ncia de movimentos caso precise modificar a movimenta��o, sendo priorizada a equerda na mudan�a
	public void proximoMovimento() {
		switch(this.movimentacaoDoCarro) {
		case ATR�S:
			this.movimentacaoDoCarro = Movimentacao.FRENTE;
		break;
		
		case FRENTE:
				this.movimentacaoDoCarro = Movimentacao.ESQUERDA;
		break;
		
		case ESQUERDA:
				this.movimentacaoDoCarro = Movimentacao.DIREITA;
				
		break;
		
		case DIREITA:	
				this.movimentacaoDoCarro = Movimentacao.ESQUERDA;
		}
	}
	
	//Incremento ou decremento nos eixos conforme movimenta��o
	public PosicaoXY retornarPosicao() {
		int retornoPosicaoX = this.posicaoXY.getPosicaoX();
		int retornoPosicaoY = this.posicaoXY.getPosicaoY();
		
		switch(this.movimentacaoDoCarro) {
		case ATR�S:
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
	
	
	//Aumenta a pilha de movimentos
	private void aumentarPilha() {
		this.pilhaDeMovimentos++;
	}
	
	//Se o agente estiver ativo, se movimentando, ent�o retorna true
	public boolean dirigindo() {
		return this.pilhaDeMovimentos<5;
	}
	
	public void zerarPilha() {
		this.pilhaDeMovimentos = 0;
	}
	
	//seta uma posi��o para o carro  iniciar, o eixo x deve estar entre 0 e 2
	public void setPosicaoXY(int posicaoY, int posicaoX) {
		PosicaoXY posicaoXY = new PosicaoXY(posicaoY, posicaoX);
		this.posicaoXY = posicaoXY;
	}
	
	public PosicaoXY getPosicaoXY() {
		return this.posicaoXY;
	}
	
	//Define o limite m�ximo da estrada
	public boolean limiteDaEstrada() {
		return this.getPosicaoXY().getPosicaoY()==this.estrada.getTamanhoDoPercurso()-1;
	}

}

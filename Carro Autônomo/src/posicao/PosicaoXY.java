package posicao;

public class PosicaoXY {
	private int posicaoX;
	private int posicaoY;
	
	public PosicaoXY() {
		this.posicaoX=0;
		this.posicaoY=0;
	}

	public PosicaoXY(int posicaoY, int posicaoX) {
		super();
		this.posicaoX = posicaoX;
		this.posicaoY = posicaoY;
	}

	public int getPosicaoX() {
		return posicaoX;
	}

	public void setPosicaoX(int posicaoX) {
		this.posicaoX = posicaoX;
	}

	public int getPosicaoY() {
		return posicaoY;
	}

	public void setPosicaoY(int posicaoY) {
		this.posicaoY = posicaoY;
	}
	
	

}

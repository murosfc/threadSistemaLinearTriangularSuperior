//Grupo: Felipe Muros, Gabriel Siqueira, Rafael Panisset e Rafael Teixeira
package thread;

public class Matriz {
	private final double[][] U = { { 3, 1, 0 }, { 0, 2, -1 }, { 0, 0, 3 } };
	private final double[] b = { 4, 2, 0 };
	private final double[] x;
	private int n;
	
	public Matriz() {
		this.n = U.length;
		this.x = new double[this.n];
	}
	
	public void setXNaPosicao(int posicao, double valor){
		this.x[posicao] = valor;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public double[][] getU() {
		return U;
	}

	public double[] getB() {
		return b;
	}

	public double[] getX() {
		return x;
	}
	
}

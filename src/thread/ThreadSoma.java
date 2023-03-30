package thread;

import java.util.concurrent.Semaphore;

public class ThreadSoma extends Thread {
	private double[] soma;
	private Matriz mat;
	private Semaphore semAtual, semProximo;
	private ThreadResolveSistema calcX;

	public ThreadSoma(Matriz mat, Semaphore semAtual, Semaphore semProximo) {
		this.semAtual = semAtual;
		this.semProximo = semProximo;
		this.mat = mat;
		this.soma = new double[mat.getN()];
	}

	public double[] getSoma() {
		return soma;
	}

	public double getSomaNaPosicao(int posicao) {
		return soma[posicao];
	}

	public void setCalcX(ThreadResolveSistema calcX) {
		this.calcX = calcX;
	}

	@Override
	public void run() {
		try {
			for (int controle = 0; controle <= mat.getN(); controle++) {
				semAtual.acquire(); // adquire o semáforo atual para iniciar a execução
				int i = this.calcX.getPosicaoAtual();
				this.soma[i] = 0;
				for (int j = 0; j < mat.getN(); j++) {
					this.soma[i] += mat.getU()[i][j] * mat.getX()[j]; // preenche um vetor soma, como a soma de cada linha
				}				
				semProximo.release();
			}

		} catch (InterruptedException e) {
			System.err.println("Erro ao adquirir permissão do semáforo: " + e.getMessage());
		}
	}

}

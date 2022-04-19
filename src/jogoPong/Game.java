package jogoPong;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{ // primeiro de tudo � add o Canvas e o que nos permite rodar a janela que � o Runnable

	// primeira coisa � criar a janela do jogo
	
	public static int WIDHT = 240; // valores para criar dimens�es da tela
	public static int HEIGHT = 120;
	public static int scale = 3;
	
	public Game() { // no metodo construtor eu vou dizer as dimens�es 
		this.setPreferredSize(new Dimension(WIDHT*scale, HEIGHT*scale)); // o this me permite buscar esse m�todo por causa do extends canvas, e esse m�todo precisa de uma inst�ncia "Dimension" com parametros de altura e largura
	}
	
	public static void main(String [] agrs) {
		Game game = new Game();
		JFrame frame = new JFrame("Pong"); // JFrame � um objeto do java para criar uma nova janela 
		frame.setResizable(false); // utilizo isso pois n�o quero que o usu�rio consiga redimencionar janela
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // isso serve para quando o usu�rio fechar a janela da opera��o o java n�o continue jodando a aplica��o por tr�s, ent�o � para fechar tudo
		frame.add(game); // eu preciso adicionar um componente, e esse componente � a pr�pria classe game pois ele j� � um canvas
		frame.pack();
		frame.setLocationRelativeTo(null); // desse modo minha janela vai sempre ficar no centro da tela
		frame.setVisible(true); // para quando iniciar aparecer a janela
	}
	
	// todos os jogos utilizamos thread
	// thread � quando executa duas tarefas ao mesmo tempo sem deixar a aplica��o travar = Runnable
	@Override
	public void run() {
		
	}

}

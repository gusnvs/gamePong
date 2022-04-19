package jogoPong;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{ // primeiro de tudo é add o Canvas e o que nos permite rodar a janela que é o Runnable

	// primeira coisa é criar a janela do jogo
	
	public static int WIDHT = 240; // valores para criar dimensões da tela
	public static int HEIGHT = 120;
	public static int scale = 3;
	
	public Game() { // no metodo construtor eu vou dizer as dimensões 
		this.setPreferredSize(new Dimension(WIDHT*scale, HEIGHT*scale)); // o this me permite buscar esse método por causa do extends canvas, e esse método precisa de uma instância "Dimension" com parametros de altura e largura
	}
	
	public static void main(String [] agrs) {
		Game game = new Game();
		JFrame frame = new JFrame("Pong"); // JFrame é um objeto do java para criar uma nova janela 
		frame.setResizable(false); // utilizo isso pois não quero que o usuário consiga redimencionar janela
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // isso serve para quando o usuário fechar a janela da operação o java não continue jodando a aplicação por trás, então é para fechar tudo
		frame.add(game); // eu preciso adicionar um componente, e esse componente é a própria classe game pois ele já é um canvas
		frame.pack();
		frame.setLocationRelativeTo(null); // desse modo minha janela vai sempre ficar no centro da tela
		frame.setVisible(true); // para quando iniciar aparecer a janela
	}
	
	// todos os jogos utilizamos thread
	// thread é quando executa duas tarefas ao mesmo tempo sem deixar a aplicação travar = Runnable
	@Override
	public void run() {
		
	}

}

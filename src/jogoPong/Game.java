package jogoPong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{ // primeiro de tudo � add o Canvas e o que nos permite rodar a janela que � o Runnable

	// primeira coisa � criar a janela do jogo
	
	public static int WIDHT = 240; // valores para criar dimens�es da tela
	public static int HEIGHT = 120;
	public static int scale = 3;
	public BufferedImage layer = new BufferedImage(WIDHT, HEIGHT, BufferedImage.TYPE_INT_RGB); // essa layer ser� aonde eu irei renderizar meus gr�ficos
	public Player player;
	
	public Game() { // no metodo construtor eu vou dizer as dimens�es 
		this.setPreferredSize(new Dimension(WIDHT*scale, HEIGHT*scale)); // o this me permite buscar esse m�todo por causa do extends canvas, e esse m�todo precisa de uma inst�ncia "Dimension" com parametros de altura e largura
		this.addKeyListener(this); // quero que minha pr�pria classe adicione o KeyListener
		player = new Player(100, HEIGHT-10); // -10 pois a renderiza��o dele come�aria praticamente no final da janela, ent�o -10 para come�ar um pouco antes
	}
	
	public static void main(String [] agrs) {
		Game game = new Game();
		JFrame frame = new JFrame("Neves Pong"); // JFrame � um objeto do java para criar uma nova janela 
		frame.setResizable(false); // utilizo isso pois n�o quero que o usu�rio consiga redimencionar janela
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // isso serve para quando o usu�rio fechar a janela da opera��o o java n�o continue jodando a aplica��o por tr�s, ent�o � para fechar tudo
		frame.add(game); // eu preciso adicionar um componente, e esse componente � a pr�pria classe game pois ele j� � um canvas
		frame.pack();
		frame.setLocationRelativeTo(null); // desse modo minha janela vai sempre ficar no centro da tela
		frame.setVisible(true); // para quando iniciar aparecer a janela
		
		new Thread(game).start();
	}
	
	public void tick() {
		player.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy(); // bufferStrategy � basicamente aonde consiguimos renderizar tudo do nosso jogo
		// quando vamos executar pela primeira vez esse buffer n�o existe ainda, por isso a verifica��o, pois assim ele cria e da continuidade
		if(bs == null) { // se n�o existir ainda, ele cria
			this.createBufferStrategy(3);
			return; // return pois se foi criado, eu preciso que ele retorne e de continuidade no gameloop para na pr�xima vez ele j� existir 
		}	
		Graphics g = layer.getGraphics();
		// depois disso tenho que limpar a renderiza��o, sen�o ele fica como se tivesse sempre renderizando uma coisa nova 
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDHT, HEIGHT);
		player.render(g);
		g = bs.getDrawGraphics(); // depois que renderiza o gr�fico preciso fazer essa mudan�a
		g.drawImage(layer, 0, 0, WIDHT*scale, HEIGHT*scale, null);
		g.setFont(new Font("Arial", Font.BOLD, 16));
		g.setColor(Color.white);
		g.drawString("Neves Pong", (WIDHT*scale/2)-player.widht, (HEIGHT*scale/2)-player.height);
		bs.show(); // mostrar na tela
		
		
	}
	
	// todos os jogos utilizamos thread
	// thread � quando executa duas tarefas ao mesmo tempo sem deixar a aplica��o travar = Runnable
	@Override
	public void run() {
		while(true) {
			tick();
			render();
			try {
				Thread.sleep(1000/60); // rodar a 60FPS
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_D ) {
			//System.out.println("Bot�o direito pressionado.");
			player.right = true;
		}else if(e.getKeyCode() == KeyEvent.VK_A){
			//System.out.println("Bot�o esquerdo pressionado.");
			player.left = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_D) {
			//System.out.println("Bot�o direito pressionado.");
			player.right = false;
		}else if(e.getKeyCode() == KeyEvent.VK_A){
			//System.out.println("Bot�o esquerdo pressionado.");
			player.left = false;
		}
		
	}

}

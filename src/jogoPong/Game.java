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

public class Game extends Canvas implements Runnable, KeyListener{ // primeiro de tudo é add o Canvas e o que nos permite rodar a janela que é o Runnable

	// primeira coisa é criar a janela do jogo
	
	public static int WIDHT = 240; // valores para criar dimensões da tela
	public static int HEIGHT = 120;
	public static int scale = 3;
	public BufferedImage layer = new BufferedImage(WIDHT, HEIGHT, BufferedImage.TYPE_INT_RGB); // essa layer será aonde eu irei renderizar meus gráficos
	public Player player;
	
	public Game() { // no metodo construtor eu vou dizer as dimensões 
		this.setPreferredSize(new Dimension(WIDHT*scale, HEIGHT*scale)); // o this me permite buscar esse método por causa do extends canvas, e esse método precisa de uma instância "Dimension" com parametros de altura e largura
		this.addKeyListener(this); // quero que minha própria classe adicione o KeyListener
		player = new Player(100, HEIGHT-10); // -10 pois a renderização dele começaria praticamente no final da janela, então -10 para começar um pouco antes
	}
	
	public static void main(String [] agrs) {
		Game game = new Game();
		JFrame frame = new JFrame("Neves Pong"); // JFrame é um objeto do java para criar uma nova janela 
		frame.setResizable(false); // utilizo isso pois não quero que o usuário consiga redimencionar janela
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // isso serve para quando o usuário fechar a janela da operação o java não continue jodando a aplicação por trás, então é para fechar tudo
		frame.add(game); // eu preciso adicionar um componente, e esse componente é a própria classe game pois ele já é um canvas
		frame.pack();
		frame.setLocationRelativeTo(null); // desse modo minha janela vai sempre ficar no centro da tela
		frame.setVisible(true); // para quando iniciar aparecer a janela
		
		new Thread(game).start();
	}
	
	public void tick() {
		player.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy(); // bufferStrategy é basicamente aonde consiguimos renderizar tudo do nosso jogo
		// quando vamos executar pela primeira vez esse buffer não existe ainda, por isso a verificação, pois assim ele cria e da continuidade
		if(bs == null) { // se não existir ainda, ele cria
			this.createBufferStrategy(3);
			return; // return pois se foi criado, eu preciso que ele retorne e de continuidade no gameloop para na próxima vez ele já existir 
		}	
		Graphics g = layer.getGraphics();
		// depois disso tenho que limpar a renderização, senão ele fica como se tivesse sempre renderizando uma coisa nova 
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDHT, HEIGHT);
		player.render(g);
		g = bs.getDrawGraphics(); // depois que renderiza o gráfico preciso fazer essa mudança
		g.drawImage(layer, 0, 0, WIDHT*scale, HEIGHT*scale, null);
		g.setFont(new Font("Arial", Font.BOLD, 16));
		g.setColor(Color.white);
		g.drawString("Neves Pong", (WIDHT*scale/2)-player.widht, (HEIGHT*scale/2)-player.height);
		bs.show(); // mostrar na tela
		
		
	}
	
	// todos os jogos utilizamos thread
	// thread é quando executa duas tarefas ao mesmo tempo sem deixar a aplicação travar = Runnable
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
			//System.out.println("Botão direito pressionado.");
			player.right = true;
		}else if(e.getKeyCode() == KeyEvent.VK_A){
			//System.out.println("Botão esquerdo pressionado.");
			player.left = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_D) {
			//System.out.println("Botão direito pressionado.");
			player.right = false;
		}else if(e.getKeyCode() == KeyEvent.VK_A){
			//System.out.println("Botão esquerdo pressionado.");
			player.left = false;
		}
		
	}

}

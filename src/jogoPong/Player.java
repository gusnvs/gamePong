package jogoPong;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
	
	public boolean right, left;
	public int x,y;
	//public static int widht = 60, height = 10;
	public int widht, height;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		widht = 50;
		height = 10;

	}

	public void tick() {
		
		if(right) {
			x++;
		}else if(left) {
			x--;
		}
		
		if(x + widht > Game.WIDHT) { // colis�o
			x = Game.WIDHT - widht;
		}else if(x < 0) {
			x = 0;
		}
	}
	
	public void render(Graphics g) { // passo o objeto Graphics para ter controle e conseguir manipular os gr�ficos dentro desse m�todo
		// como n�o vou usar nenhuma sprite, posso renderizar apenas como um retangulo utilizando os pr�prios gr�ficos do jogo
		g.setColor(Color.orange);
		g.fillRect(x, y, widht, height); // x, y, altura, largura
	}
}

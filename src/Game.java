import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game implements KeyListener{
	Snake snake;
	JFrame frame;
	MyPanel panel;
	ArrayList<Integer> body;
	JLabel label;
	int v = 100;//速度
	
	private int pox = 500,poy = 200;//框架的位置
	
	public static void main(String args[]) {
		Game game = new Game();
		game.run();
	}
	
	public void run() {
		snake = new Snake(); 
		frame = new JFrame("Hungry Snake");
		panel = new MyPanel();
		frame.setSize(400, 360);
		frame.setLocation(pox, poy);
		frame.getContentPane().add(panel,BorderLayout.CENTER);
		panel.setBounds(0, 0, 300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.addKeyListener(this);
		
		Font font = new Font("serif", Font.CENTER_BASELINE,20);
		label = new JLabel();
		label.setFont(font);
		label.setOpaque(true);
		label.setBackground(Color.YELLOW);
		label.setForeground(Color.BLUE);
		frame.getContentPane().add(label, BorderLayout.SOUTH);
		
		JTextArea guide = new JTextArea(10, 8);
		guide.setEditable(false);
		guide.setLineWrap(true);
		guide.setWrapStyleWord(true);
		guide.setText("Use direction keys to turn. Press 'P' to pause. Press 'R' to restart");
		guide.setForeground(Color.blue);
		guide.setBackground(Color.yellow);
		frame.getContentPane().add(guide, BorderLayout.EAST);
		
		try {
			while(snake.move()){
				panel.repaint();
				label.setText("Your socre is:" + snake.body.size());
					Thread.sleep(v);//调节游戏速度
				}
					label.setText("Game over!Your socre is:" + snake.body.size());

			} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}
		
	public class MyPanel extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int div = 10;
		public void paintComponent(Graphics g){
			body = snake.body ;
			g.setColor(Color.white);//背景为白色
			g.fillRect(0, 0, 300, 300);
			g.setColor(Color.RED);//蛇头为红色
			g.fillOval(body.get(0)%30*div, body.get(0)/30*div, div, div);
			g.setColor(Color.GREEN);//蛇身为绿色
			for(int i = 1; i < snake.body.size(); ++i){
				g.fillOval(body.get(i)%30*div, body.get(i)/30*div, div, div);
			}
			g.setColor(Color.orange);//食物为橙色
			g.fillRect(snake.food%30*div, snake.food/30*div, div, div);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()){//对应左右上下
		case KeyEvent.VK_LEFT:snake.setDir(1);break;
		case KeyEvent.VK_RIGHT:snake.setDir(2);break;
		case KeyEvent.VK_UP:snake.setDir(3);break;
		case KeyEvent.VK_DOWN:snake.setDir(4);break;
		case KeyEvent.VK_P:snake.setDir(0);break;//暂停
		case KeyEvent.VK_EQUALS: v -= 5;break;//加速
		case KeyEvent.VK_MINUS: v += 5;break;//减速
		case KeyEvent.VK_R: snake.Init();
		default: break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {

		}

}
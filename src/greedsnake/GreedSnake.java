package greedsnake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GreedSnake extends JFrame {
	// FOOD
	private Point point = new Point();
	// snake
	private LinkedList<Point> list = new LinkedList<Point>();
	// 初始化FOOD,snake
	// 定义键盘键位
	private int key = 37;

	public GreedSnake() {
		// tittle
		this.setTitle("贪吃蛇");
		// set size
		this.setSize(500, 480);
		this.setResizable(false);
		//set close
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// visible
		this.setVisible(true);
		// center
		this.setLocationRelativeTo(null);
		this.init();
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() >= 37 && e.getKeyCode() <= 40) {
					if (Math.abs(key - e.getKeyCode()) != 2) {
						key = e.getKeyCode();

					}
				}
			}
		});
	}

	public void init() {
		point.setLocation(100, 100);
		list.add(new Point(300, 300));
		list.add(new Point(310, 300));
		list.add(new Point(320, 300));
		list.add(new Point(330, 300));
		list.add(new Point(340, 300));
		list.add(new Point(350, 300));
		list.add(new Point(360, 300));
		// 开启线程
		new Thread(new MoveThread()).start();
	}

	@Override
	public void paint(Graphics g) {
		Image img = createImage(500, 500);
		Graphics g2 = img.getGraphics();
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, 500, 500);
		g2.translate(50, 50);
		g2.setColor(Color.RED);
		g2.drawRect(0, 0, 400, 400);
		g2.setColor(Color.GRAY);
		// snake
		/*
		 * for(int i=0;i<list.size();i++) { g2.fillRect(list.get(i).x,list.get(i). y,
		 * 10,10); }两种遍历方法
		 */
		for (Point p : list) {
			g2.fillRect(p.x, p.y, 10, 10);
		}
		// food
		g2.setColor(Color.RED);
		g2.fillRect(point.x, point.y, 10, 10);
		g.drawImage(img, 0, 0, 500, 500, this);
	}

	// 内部类实现线程
	class MoveThread implements Runnable {
		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 获取第一个点
				Point p = list.getFirst().getLocation();
				switch (key) {
				case 37:
					p.x = p.x - 10;
					break;
				case 38:
					p.y = p.y - 10;
					break;
				case 39:
					p.x = p.x + 10;
					break;
				case 40:
					p.y = p.y + 10;
					break;
				}
				// GG
				if (p.x < 0 || p.x > 390 || p.y < 0 || p.y > 390 || list.contains(p)) {
					JOptionPane.showMessageDialog(null, "GG");
					break;
				}
				list.addFirst(p);
				// eat food, creat new food,longer
				if (p.equals(point)) {
					int x = (int) (Math.random() * 40) * 10;
					int y = (int) (Math.random() * 40) * 10;
					point.setLocation(x, y);
				} else {

				}
				// 删除最后一个点
				list.removeLast();
				// 画的方法
				GreedSnake.this.repaint();
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("lets's dance!!!");
		long i = 9999999999999L;
		new GreedSnake();
	}

}

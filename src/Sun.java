import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class MyJPanelSun extends JPanel {

	static int stepsNumber = 1440;
	static int leapXEarth = 0;
	static int leapYEarth = 0;
	static int leapXMoon = 0;
	static int leapYMoon = 0;
	static int xOdlegloscSZ;
	static int xOdlegloscZM;
	static int showDay = 0;
	static int years = 0;
	static int degreeOfEarth = 360;
	static double timeOneMinute = 41.667;
	static double timeMultiplier = timeOneMinute;
	static double countDay = 0;
	static JLabel days = new JLabel(showDay + " days have passed");
	static JLabel yearsJLabel = new JLabel(years + " years have passed");

	public MyJPanelSun() {
		setLayout(null);
		JLabel howManyMiliSecond = new JLabel("How many years in one minute? Enter below: ");
		howManyMiliSecond.setForeground(Color.WHITE);
		howManyMiliSecond.setOpaque(true);
		howManyMiliSecond.setBackground(Color.BLACK);
		howManyMiliSecond.setBounds(50, 925, 270, 20);
		add(howManyMiliSecond);

		JTextField timeMultiplierJTextField = new JTextField("1");
		timeMultiplierJTextField.setBounds(50, 950, 50, 20);
		timeMultiplierJTextField.setForeground(Color.WHITE);
		timeMultiplierJTextField.setBackground(Color.BLACK);
		add(timeMultiplierJTextField);

		days.setForeground(Color.WHITE);
		days.setOpaque(true);
		days.setBackground(Color.BLACK);
		days.setBounds(50, 50, 130, 20);
		add(days);
		yearsJLabel.setForeground(Color.WHITE);
		yearsJLabel.setOpaque(true);
		yearsJLabel.setBackground(Color.BLACK);
		yearsJLabel.setBounds(50, 20, 130, 20);
		add(yearsJLabel);

		timeMultiplierJTextField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timeMultiplier = timeOneMinute / Double.parseDouble(timeMultiplierJTextField.getText());
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		// Sun
		int xCenterSun = this.getWidth() / 2;
		int yCenterSun = this.getHeight() / 2;
		int widthSun = this.getWidth() / 5;
		int heightSun = this.getHeight() / 5; // we need a square
		int xStartSun = xCenterSun - widthSun / 2;
		int yStartSun = yCenterSun - heightSun / 2;
		// Earth
		int widthEarth = this.getWidth() / 8;
		int heightEarth = this.getHeight() / 8; // we need a square
		int xCenterEarth = xCenterSun + xCenterSun / 2 + widthEarth / 2;
		int xCenterEarthRunning = xCenterSun + leapXEarth; // we will need that for moon to move
		int yCenterEarthRunning = yCenterSun + leapYEarth - yCenterSun / 2;
		xOdlegloscSZ = xCenterEarth - xCenterSun;
		// Moon
		int widthMoon = this.getWidth() / 16;
		int heightMoon = this.getHeight() / 16; // we need a square
		int xCenterMoon = xCenterEarth + widthEarth;
		int xCenterMoonRunning = xCenterEarthRunning + leapXMoon; // MOON
		int yCenterMoonRunning = yCenterEarthRunning + leapYMoon;
		xOdlegloscZM = xCenterMoon - xCenterEarth;

		super.paintComponent(g);

		try {
			BufferedImage cosmos = ImageIO.read(new File("C:\\Users\\Vovul\\Desktop\\symulacja\\cosmos.jpg"));
			BufferedImage earth = ImageIO.read(new File("C:\\Users\\Vovul\\Desktop\\symulacja\\earth.png"));
			BufferedImage moon = ImageIO.read(new File("C:\\Users\\Vovul\\Desktop\\symulacja\\moon.png"));

			g.drawImage(cosmos, 0, 0, this.getWidth(), this.getHeight(), null);
			ImageIcon imgIconSun = new ImageIcon("C:\\Users\\Vovul\\Desktop\\symulacja\\sunGif.gif");
			JLabel label = new JLabel(imgIconSun);
			label.setBounds(xStartSun-45, yStartSun-45, widthSun+90, heightSun+90);
			add(label);
	
			g.drawImage(EarthRotation.rotate(earth, degreeOfEarth), xCenterEarthRunning - widthEarth / 2,
					yCenterEarthRunning - heightEarth / 2 + yCenterSun / 2, widthEarth, heightEarth, null);

			g.drawImage(moon, xCenterMoonRunning - widthMoon / 2, yCenterMoonRunning - heightMoon / 2 + yCenterSun / 2,
					widthMoon, heightMoon, null);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1000, 1000);
	}

	public void stoper() throws InterruptedException {

		while (true) {
			
			for (int i = 1; i <= MyJPanelSun.stepsNumber; i++) {
				TimeUnit.MILLISECONDS.sleep((int) (1 * timeMultiplier)/4); // we divide by 4 in order to rotate Earth with appropiate speed
				degreeOfEarth = 270;
				repaint();
				TimeUnit.MILLISECONDS.sleep((int) (1 * timeMultiplier)/4);
				degreeOfEarth = 180;
				Earth.rotation(i); // drawing Earth in distance from Sun
				repaint();
				countDay += stepsNumber / 365;
				showDay = (int) (countDay / (stepsNumber / 365 * 3.94));
				days.setText(showDay + " days have passed");
				TimeUnit.MILLISECONDS.sleep((int) (1 * timeMultiplier)/4); 
				degreeOfEarth = 90;
				repaint();
				TimeUnit.MILLISECONDS.sleep((int) (1 * timeMultiplier)/4);
				degreeOfEarth = 360;
				repaint();
			}
			years++;
			yearsJLabel.setText(years + " years have passed");
			countDay = 0;
		}
	}
}

public class Sun {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		MyJPanelSun myp = new MyJPanelSun();
		JFrame window = new JFrame();
		window.setVisible(true);
		window.setTitle("My window for simulation");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(myp);
		window.pack();
		myp.stoper();
	}
}

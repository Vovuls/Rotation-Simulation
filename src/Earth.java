
public class Earth {
	public static void rotation(int i) {
		
			// Earth
			MyJPanelSun.leapXEarth = (int) (MyJPanelSun.xOdlegloscSZ
					* Math.sin(Math.PI * 2 * i / MyJPanelSun.stepsNumber));
			MyJPanelSun.leapYEarth = (int) (MyJPanelSun.xOdlegloscSZ
					* Math.cos(Math.PI * 2 * i / MyJPanelSun.stepsNumber));
			
			// Moon
			MyJPanelSun.leapXMoon = (int) (MyJPanelSun.xOdlegloscZM
					* Math.sin(Math.PI * 2 * i * 13 / MyJPanelSun.stepsNumber));
			MyJPanelSun.leapYMoon = (int) (MyJPanelSun.xOdlegloscZM
					* Math.cos(Math.PI * 2 * i * 13 / MyJPanelSun.stepsNumber));
	}
}

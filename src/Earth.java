
public class Earth {
	public static void rotation(int i) {
		
			// Earth
			MyJPanelSun.leapXEarth = (int) (MyJPanelSun.xDistanceSunEarth
					* Math.sin(Math.PI * 2 * i / MyJPanelSun.stepsNumber));
			MyJPanelSun.leapYEarth = (int) (MyJPanelSun.xDistanceSunEarth
					* Math.cos(Math.PI * 2 * i / MyJPanelSun.stepsNumber));
			
			// Moon
			MyJPanelSun.leapXMoon = (int) (MyJPanelSun.xDistanceEarthMoon
					* Math.sin(Math.PI * 2 * i * 13 / MyJPanelSun.stepsNumber));
			MyJPanelSun.leapYMoon = (int) (MyJPanelSun.xDistanceEarthMoon
					* Math.cos(Math.PI * 2 * i * 13 / MyJPanelSun.stepsNumber));
	}
}

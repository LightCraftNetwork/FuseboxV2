package mc.lightcraft.java.common.utils;

/**
 * 
 * @author Jacob
 *
 */
public class MathUtils {
	private MathUtils() {} // Private constructor - This class is only meant to be accessed statically
	
	/**
	 * @param angle The angle you want the cosecant of, in radians
	 * @return The cosecant value of 'angle', in radians
	 */
	public static double csc (double angle) {
		double sin = Math.sin(angle);
		return 1/sin;
	}
	
	/**
	 * @param angle The angle you want the secant of, in radians
	 * @return The secant value of 'angle', in radians
	 */
	public static double sec (double angle) {
		double cos = Math.cos(angle);
		return 1/cos;
	}
	
	/**
	 * @param angle The angle you want the cotangent of, in radians
	 * @return The cotangent value of 'angle', in radians
	 */
	public static double cot (double angle) {
		double tan = Math.tan(angle);
		return 1/tan;
	}
	
	/**
	 * 
	 * @param sideA The first side length of the desired triangle
	 * @param sideB The second side length of the desired triangle
	 * @param sideC The third side length of the desired triangle
	 * @return The area of the triangle with the specified side lengths. Uses Heron's formula for calculations
	 */
	public static double areaTriangle (double sideA, double sideB, double sideC) {
		final double total = (sideA + sideB + sideC)/2;
		double ans = Math.sqrt(total * (total - sideA) * (total - sideB) * (total - sideC));
		return ans;
	}
	
	
}

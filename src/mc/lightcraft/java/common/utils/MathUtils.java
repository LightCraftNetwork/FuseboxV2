package mc.lightcraft.java.common.utils;

import java.text.DecimalFormat;

import org.bukkit.util.Vector;

/**
 * 
 * @author Jacob
 *
 */
public class MathUtils {
	private MathUtils() {} // Private constructor - This class is only meant to be accessed statically

	
	/**
	 * 
	 * @param degree
	 * @param d
	 * @return
	 */
	public static double trim(int degree, double d) {
		String format = "#.#";

		for (int i = 1; i < degree; i++) {
			format = format + "#";
		}
		DecimalFormat twoDForm = new DecimalFormat(format);
		return Double.valueOf(twoDForm.format(d)).doubleValue();
	}
	
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
	
	/**
	 * Calculate the magnitude (or length) of a three dimensional vector
	 * @param vector the vector that you wish to calculate the magnitude of
	 * @return magnitude of provided vector
	 */
	public static double vectorMagnitude(Vector vector) {
		double a = vector.getX() * vector.getX();
		double b = vector.getZ() * vector.getZ();
		// Instead of re-squaring ab and pluging it in, I'm just going to use it's preroot value of A + B and then use C for the Y value.
		// This should return the magnitude do the the commutative properties of addition
		double c = vector.getY() * vector.getY();
		double abc = Math.sqrt(a + b + c);
		return abc;
	}
	
}

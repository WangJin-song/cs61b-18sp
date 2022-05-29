public class NBody {

	public static double readRadius(String file){
		In in = new In(file);
		in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String file){
		In in = new In(file);
		int nums = in.readInt();
		double radius = in.readDouble();
		Planet[] planets = new Planet[nums];
		int i = 0;
		while (i < nums) {

			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String img = in.readString();

			planets[i++] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,img);
		}

		return planets;
	}

	private static String imageToDraw = "images/starfield.jpg";

	public static void main(String[] args){
		
		Double T = Double.parseDouble(args[0]);
		Double dt = Double.parseDouble(args[1]);

		String filename = args[2];
		Planet[] planets = readPlanets(filename);
		double radius = readRadius(filename);

		/** Sets up the universe so it goes from 
		  * -2.50e+11, -2.50e+11 up to 2.50e+11, 2.50e+11 */
		StdDraw.setScale(-radius, radius);

		/* Clears the drawing window. */
		StdDraw.clear();

		StdDraw.picture(0, 0, imageToDraw);

		for(Planet p : planets){
			p.draw();
		}

		StdDraw.enableDoubleBuffering();
		double time = 0;
		while (time < T) {
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];

			for (int i = 0; i < xForces.length; i++){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
			}
			for (int i = 0; i < yForces.length; i++){
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for (int i = 0; i < planets.length; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}

			StdDraw.picture(0, 0, imageToDraw);

			for(Planet p : planets){
				p.draw();
			}
			StdDraw.show();
			StdDraw.pause(1);
			time += dt;
		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
		                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
		}

	}
}

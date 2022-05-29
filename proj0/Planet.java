public class Planet {

	public static final double G = 6.67e-11;
	public double xxPos;	//current x position
	public double yyPos;	//current y position
	public double xxVel;	//current velocity in the x position
	public double yyVel;	//current velocity in the y position
	public double mass;		//mass
	public String imgFileName;	//the name of the file that corresponds to
								//the image that depicts the planet

	public Planet(double xP, double yP, double xV,
				  double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p){
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p){
		return Math.pow((this.xxPos - p.xxPos)*(this.xxPos - p.xxPos) + (this.yyPos - p.yyPos)*(this.yyPos - p.yyPos), 0.5);
	}

	public double calcForceExertedBy(Planet p){
		return (G * this.mass * p.mass) / (this.calcDistance(p) * this.calcDistance(p));
	}

	public double calcForceExertedByX(Planet p){
		return this.calcForceExertedBy(p) / this.calcDistance(p) * (p.xxPos - this.xxPos);
	}

	public double calcForceExertedByY(Planet p){
		return this.calcForceExertedBy(p) / this.calcDistance(p) * (p.yyPos - this.yyPos);
	}

	public double calcNetForceExertedByX(Planet[] planets){
		double forceX = 0;
		for (Planet p : planets) {
			if (!(this.equals(p))){
				forceX += this.calcForceExertedByX(p);
			}
		}
		return forceX;
	}

	public double calcNetForceExertedByY(Planet[] planets){
		double forceY = 0;
		for (Planet p : planets) {
			if (!(this.equals(p))){
				forceY += this.calcForceExertedByY(p);
			}
		}
		return forceY;
	}

	public void update(double dt, double fX, double fY){

		double netAccX = fX / this.mass;
		double netAccY = fY / this.mass;

		this.xxVel += netAccX * dt;
		this.yyVel += netAccY * dt;

		this.xxPos += this.xxVel * dt;
		this.yyPos += this.yyVel * dt;
	}

	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}
}
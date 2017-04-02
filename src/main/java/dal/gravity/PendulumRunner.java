package dal.gravity;

import java.text.NumberFormat;

/** 
 * compares the values of a simple pendulum using the harmonic motion equation
 * versus the Euler algorithm approximation
 */
public class PendulumRunner {

    public static void main (String [] args) {
    	GravityModel earthGrav = new GravityConstant(GravityModel.EARTHGRAVITY);
    	double delta = (args.length == 0) ? .1 : Double.parseDouble (args[0]);
    	double sLen = 10, pMass = 10, theta0 = Math.PI/30;
    	RegularPendulum rp = new RegularPendulum (sLen, pMass, theta0, delta,earthGrav );
    	SimplePendulum sp = new SimplePendulum (sLen, pMass, theta0, earthGrav);
    	RegularPendulum rpCoarse = 
    			new RegularPendulum (sLen, pMass, theta0, .1,earthGrav);

    	// print out difference in displacement in 1 second intervals
    	// for 20 seconds
    	printPendulumReadings(rp,sp,rpCoarse,delta,1,20);
    	
    	GravityModel jupGrav = new GravityConstant(GravityModel.JUPITERGRAVITY);
    	
    	rp.changeGravitationalConstant(jupGrav);
    	sp.changeGravitationalConstant(jupGrav);
    	rpCoarse.changeGravitationalConstant(jupGrav);
    	
    	System.out.println("\n\nSwitching to model Jupiter's Gravity\n\n");
    	
    	printPendulumReadings(rp,sp,rpCoarse,delta, 21,40);
    }
    
    //Prints all readings to make it more efficient. Takes objects, delta and start and end time.
    public static void printPendulumReadings(RegularPendulum rp, SimplePendulum sp, RegularPendulum rpCoarse, double delta, int start, int end){
    	int iterations = (int) (1/delta);
    	NumberFormat nf = NumberFormat.getInstance ();
    	nf.setMaximumFractionDigits (3);
    	System.out.println ("analytical vs. numerical displacement (fine, coarse)");
    	for (int second = start; second <= end; second++) {
    		for (int i = 0; i < iterations; i++) rp.step ();
    		for (int i = 0; i < 10; i++) rpCoarse.step (); 
	    	System.out.println ("t=" + second + "s: \t" + 
				nf.format (Math.toDegrees (sp.getTheta (second))) 
				+ "\t" +
				nf.format (Math.toDegrees (rp.getLastTheta ()))
				+ "\t" + 
				nf.format (Math.toDegrees (rpCoarse.getLastTheta ())));
    	}
    }
}




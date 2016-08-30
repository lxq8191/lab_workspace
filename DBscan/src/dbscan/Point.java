package dbscan;

public class Point {
	private double x;//经度
	private double y;//纬度
	private boolean isKey;
	private boolean isClassed;
	private int carId;//出租车编号
	private String time;//时间
	private int state;//状态
	private int rate;
	

	/**
	 * @return the carId
	 */
	public int getCarId() {
		return carId;
	}

	/**
	 * @param carId the carId to set
	 */
	public void setCarId(int carId) {
		this.carId = carId;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}

	/**
	 * @return the rate
	 */
	public int getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(int rate) {
		this.rate = rate;
	}

	public boolean isKey() {

	return isKey;

	}

	public void setKey(boolean isKey) {

	this.isKey = isKey;

	this.isClassed=true;

	}

	public boolean isClassed() {

	return isClassed;

	}

	public void setClassed(boolean isClassed) {

	this.isClassed = isClassed;

	}

	public double getX() {

	return x;

	}

	public void setX(double x) {

	this.x = x;

	}

	public double getY() {

	return y;

	}

	public void setY(double y) {

	this.y = y;

	}

	public Point(){

	x=0;

	y=0;

	}

	public Point(double x,double y){
		this.x=x;	
		this.y=y;
	}

	public Point(String str){

		String[] p = str.split(",");
		this.x = Double.parseDouble(p[3])/100000;
		this.y = Double.parseDouble(p[2])/100000;
		this.carId = Integer.parseInt(p[0]);
		this.time = p[1];
		this.rate = Integer.parseInt(p[5]);
		this.state = Integer.parseInt(p[6]);

	}

//	public String print(){
//
//	return "<"+this.x+","+this.y+">";
//
//	}



}

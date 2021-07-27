package model;
public class Request
{
	private String method;
	private String resource;
	private int error;

	public boolean isClientSideTechnologyResource;

	public String getMethod(){return this.method;}
	public String getResource(){return this.resource;}
	public int getError(){return this.error;}
	public void setMethod(String method){this.method = method;}
	public void setResource(String resource){this.resource = resource;}
	public void setError(int error){this.error = error;}

	public String toString(){return "[ method : "+method+", resource : "+resource+", error : "+error+", isClientSideTechnologyResource : "+isClientSideTechnologyResource+" ]";}
}
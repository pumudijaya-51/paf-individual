package model;
import java.io.IOException; 
import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PharmacysAPI
 */
@WebServlet("/HospitalsAPI")
public class HospitalsAPI extends HttpServlet{
private static final long serialVersionUID = 1L;
	
	Hospital HospObj = new Hospital();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HospitalsAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String output = HospObj.addHospitals(request.getParameter("hospitalName"),      
				request.getParameter("hospitalphoneNo"),     
				request.getParameter("hospitalAddress"),       
				request.getParameter("hospitalEmail")); 
	 
	 response.getWriter().write(output); 
		
	}
	protected void doPut(HttpServletRequest request, HttpServletResponse response)    
			throws ServletException, IOException 
	{  
		Map paras = getParasMap(request); 
	 
	 String output = HospObj.updateHospital(paras.get("hidHospitalIDSave").toString(),     
			 paras.get("hospitalName").toString(),     
			 paras.get("hospitalphoneNo").toString(),        
			 paras.get("hospitalAddress").toString(),        
			 paras.get("hospitalEmail").toString()); 
	 
	 response.getWriter().write(output); } 
	 
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)    
			throws ServletException, IOException 
	{  
		Map paras = getParasMap(request); 
	 
	 String output = HospObj.deleteHospital(paras.get("hospitalID").toString()); 
	 
	 response.getWriter().write(output); 
	}
	
	// Convert request parameters to a Map 
	private static Map getParasMap(HttpServletRequest request) 
	{  
		Map<String, String> map = new HashMap<String, String>();  
		try  
		{   
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");   
			String queryString = scanner.hasNext() ?          
					scanner.useDelimiter("\\A").next() : "";  
					scanner.close(); 
	 
	  String[] params = queryString.split("&");   
	  for (String param : params)   
	  { 

 
	   String[] p = param.split("=");    
	   map.put(p[0], p[1]);   
	   }  
	  }  
		catch (Exception e)  
		{  
			
		}  return map; }

	

}

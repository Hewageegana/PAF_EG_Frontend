package api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.PowerConsumption;

/**
 * Servlet implementation class PowerConsumptionAPI
 */
@WebServlet("/PowerConsumptionAPI")
public class PowerConsumptionAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	PowerConsumption powerconsObj = new PowerConsumption();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PowerConsumptionAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		PowerConsumption powerconsObj = new PowerConsumption();
		
		String output = "";
		output = powerconsObj.readPwerConsumption();
		
		response.getWriter().append(output);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String output = powerconsObj.InsertPowerConsumptionDetails(
				request.getParameter("userID"),
				request.getParameter("account_Number"),
				request.getParameter("cus_name"),
				request.getParameter("units"),
				request.getParameter("days"),
				request.getParameter("generated_date"));
				
				response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		Map paras = getParasMap(request);
		String output = "";
			if(paras.get("idcustomer") != null) {
				output = powerconsObj.updateConsumption(
						
						paras.get("userID").toString(),
						paras.get("account_Number").toString(),
						paras.get("cus_name").toString(),
						paras.get("units").toString(),
						paras.get("days").toString(),
						paras.get("generated_date").toString());
						
			}
			else {
				output = powerconsObj.updateConsumption(
						
						request.getParameter("userID"),
						request.getParameter("account_Number"),
						request.getParameter("cus_name"),
						request.getParameter("units"),
						request.getParameter("days"),
						request.getParameter("generated_date"));
			}
			response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request);
		String output = "";
		
		if (paras.get("idcustomer") != null) {
			output = powerconsObj.deleteConsume(paras.get("userID").toString());
			
		}
		else {
			output = powerconsObj.deleteConsume(request.getParameter("userID"));
		}
		System.out.println("ID: " + output);
		response.getWriter().write(output);
	}
	
	
	private static Map getParasMap(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params) {

				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
			
		}
		return map;
	}


}

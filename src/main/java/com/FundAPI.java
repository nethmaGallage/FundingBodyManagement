package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FundAPI")
public class FundAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	Fund fundObj = new Fund();
	
    public FundAPI() {
        super();
    }

 // Convert request parameters to a Map
 	private static Map getParasMap(HttpServletRequest request) {
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
     
     
     
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		// TODO Auto-generated method stub
 		response.getWriter().append("Served at: ").append(request.getContextPath());
 	}

 	
 	
 	
 	
 	
 	
 	
 	
 	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		String output = fundObj.insertFund(
 				request.getParameter("fundType"),
 				request.getParameter("funddescription"),
 				request.getParameter("fundAmount"),
 				request.getParameter("fundIssuedDate"),
 				request.getParameter("fundIssuedBy"));
 				response.getWriter().write(output);
 				
 				System.out.println(request.getParameter("fundType"));
 	}

 	
 	
 	
 	
 	
 	
 	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		Map paras = getParasMap(request);
 		String output = fundObj.updateFund(
 		paras.get("hidFundIDSave").toString(),
 		paras.get("fundType").toString(),
 		paras.get("funddescription").toString(),
 		paras.get("fundAmount").toString(),
 		paras.get("fundIssuedDate").toString(),
 		paras.get("fundIssuedBy").toString());
 		response.getWriter().write(output);
 		
 		
 	}

 	
 	
 	
 	
 	
 	
 	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		Map paras = getParasMap(request);
 		String output = fundObj.deleteFund(paras.get("fundId").toString());
 		response.getWriter().write(output);
 	}

}

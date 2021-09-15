<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="messageBean" class="de.hwg_lu.bw4s.beans.MessageBean" scope="session" />
<jsp:useBean id="loginBean" class="de.hwg_lu.bw4s.beans.LoginBean" scope="session" />

<%
String userid = request.getParameter("userid");
String password = request.getParameter("password");
String btnLogin = request.getParameter("btnLogin");
String zurRegistrierung = request.getParameter("zurRegistrierung");
if (btnLogin == null) btnLogin = "";
if (zurRegistrierung == null) zurRegistrierung = "";

if (btnLogin.equals("Anmelden")){
	loginBean.setUserid(userid);
	loginBean.setPassword(password); 
	try{
		boolean useridPasswordOk = loginBean.checkUseridPassword();
		if (useridPasswordOk){
			messageBean.setLoginSuccessful();
			loginBean.setLoggedIn(true);
			response.sendRedirect("./PortalView.jsp");
		}else{
			messageBean.setLoginFailed();
			loginBean.setLoggedIn(false);
			response.sendRedirect("./LoginView.jsp");
		}
	}catch(NoConnectionException nce){
		messageBean.setConnectionError();
		response.sendRedirect("./LoginView.jsp");
	}catch(SQLException se){
		messageBean.setSQLError();
		response.sendRedirect("./LoginView.jsp");
	}
}else if (zurRegistrierung.equals("zurRegistrierung")){
	messageBean.setRegistrationWelcome();
	response.sendRedirect("./RegView.jsp");
}else{
	messageBean.setGeneralWelcome();
	response.sendRedirect("./LoginView.jsp");
}
%>
</body>
</html>
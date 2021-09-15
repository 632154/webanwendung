<%@page import="de.hwg_lu.bw.jdbc.NoConnectionException"%>
<%@page import="java.sql.SQLException"%>
<%@page import="de.hwg_lu.bw4s.beans.AccountBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="accountBean" class="de.hwg_lu.bw4s.beans.AccountBean"
		scope="session" />
	<jsp:useBean id="messageBean" class="de.hwg_lu.bw4s.beans.MessageBean"
		scope="session" />
	<%!public String denullify(String inputString) {
		if (inputString == null)
			return "";
		else
			return inputString;
		//	return (inputString == null)?"":inputString;(selbe wie oben)
	}

	public String[] denullify(String[] inputStringArray) {
		if (inputStringArray == null)
			return new String[0];
		else
			return inputStringArray;
	}%>
	<%
	//AccountBean accountBean=(AccountBean)  session.getAttribute("accountBean");
	//if(accountBean==null){
	//	accountBean=new AccountBean();
	//	session.setAttribute("accountBean", accountBean);
	//}
	//MessageBean myMessageBean=(MessageBean) session.getAttribute("messageBean");
	//if(myMessageBean==null){
	//	myMessageBean=new MessageBean();
	//	session.setAttribute("messageBean", myMessageBean);
	//}
	//Eingabefelder
	String userid = request.getParameter("userid");
	String password = request.getParameter("password");
	String username = request.getParameter("username");
	String email = request.getParameter("email");
	//Buttons
	//denullify(verringern null) methode machen, damit braucht man eine Java declaration(<%!>)
	String register = request.getParameter("register");
	String zumlogin = request.getParameter("zumlogin");
	//if(register==null) register="";
	//if(zumlogin==null) zumlogin="";

	if (register.equals("Registrieren")) {
		System.out.println("Registrieren-Button wurde geklickt");

		accountBean.setUserid(userid);
		accountBean.setPassword(password);
		accountBean.setUsername(username);
		accountBean.setEmail(email);
		accountBean.setActive("Y");
		accountBean.setAdmin("N");

		try {
			boolean insertOk =accountBean.insertAccountifNotExists();
			if (insertOk) {//Nickname wurde geschrieben
		       messageBean.setRegistrationSuccessful(userid);
			}else{
		       messageBean.setNicknameAlreadyExists(userid);
		       response.sendRedirect("./RegView.jsp");
			}

		} catch (NoConnectionException exc) {
			exc.printStackTrace();
			messageBean. setConnectionError();
			response.sendRedirect("./RegView.jsp");
		} catch (SQLException se) {
			se.printStackTrace();
			messageBean.setSQLError();
			response.sendRedirect("./RegView.jsp");
		}
		//session.setAttribute("accountBean",myAccountBean);
		// myAccountBean.insertAccountifNotExists();

		response.sendRedirect("./RegView.jsp");
	} else if (zumlogin.equals("zum login")) {
		//.out.println("zum Login-Button wurde geklickt");
        messageBean.setGeneralWelcome();
		response.sendRedirect("./LoginView.jsp");
	} else {
		System.out.println("kein Button gedrückt");
		messageBean.setGeneralWelcome();
		response.sendRedirect("./RegView.jsp");

	}

	//out.println("userid" + userid +"<br>");
	//out.println("password" + password + "<br>");
	//out.println("username" + username + "<br>");
	//out.println("email" + email + "<br>");
	//out.println("register: " + register + "<br>");
	//System.out.println("zumlogin: " + zumlogin + "<br>");
	%>

</body>
</html>
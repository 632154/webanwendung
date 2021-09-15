<%@page import="de.hwg_lu.bw4s.beans.GUIBean"%>
<%@page import="de.hwg_lu.bw4s.beans.AccountBean"%>
<%@page import="de.hwg_lu.bw4s.beans.MessageBean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BW4S - Registrierung</title>
<script type="text/javascript" src="../js/BW4S.js"></script>
</head> 
<body onload="init()">
<jsp:useBean id="accountBean" class="de.hwg_lu.bw4s.beans.AccountBean" scope="session"/> 
<jsp:useBean id="messageBean" class="de.hwg_lu.bw4s.beans.MessageBean" scope="session"/> 
<jsp:useBean id="guiBean" class="de.hwg_lu.bw4s.beans.GUIBean" scope="session"/> 

<!--  
<table>
<tr>
  <td><h3>BW4S-Portal</h3></td>
  <td>
     <img src="../img/HWGLU_logo.png" width="256"
     alt="hier sollte das logo der HwG Lu stehen"
     title="Logo der HwG Lu"
    />
 </td>
</tr>
</table>
-->

<jsp:getProperty property="headlineHtml" name="guiBean"/>

<!-- 
<h2>Willkomen zur BW4S Registrierung</h2>
<h4>Bitte registrieren Sie sich</h4>
 -->
<h2><%// out.println(myMessageBean.getInfoMessage()); %></h2>
<h4><%// out.println(myMessageBean.getActionMessage()); %></h4>
<!--in Html geschrieben-->

<jsp:getProperty name="messageBean" property="messageHtml" /><!--  getMessageHtml()-->
<%= messageBean.getMessageHtml() %>

<form id="regForm" action "./RegAppl.jsp" methode="get" onsubmit="return inputCheck(this)">
 <table>
  <tr>
 <td>Nickname:</td>
     <td><input type="text"
                name="userid"
                value='<jsp:getProperty name="accountBean" property="userid" />'
               onchange="inputCheck2()"
               onkeyup="inputCheck2()"
               
                />
       </td>
       <td id="useridErrorMsg"></td>
    </tr>
    <tr> 
       <td>Passwort:</td>
     <td><input type="password" name="password" value=""
             onchange="inputCheck2()"
             onkeyup="inputCheck2()"
             
             />
             </td>
          <td id="passwordErrorMsg" class="errorMsg"></td>
    </tr>
    <tr>
     <td>Username:</td>
     <td><input type="text" name="username"
           value="<jsp:getProperty name="accountBean" property="username" />"
           /> </td>
                  <td id="usernameErrorMsg"></td>
    </tr>
    <tr> 
     <td>Email:</td>
     <td><input type="text" name="email"
            value='<jsp:getProperty name="accountBean" property="email" />'
            /> </td>
                   <td id="emailErrorMsg"></td>
    </tr>
    <tr>
      <td><input id="btnRegister" type="submit" name="register" value="Registrieren"/></td>
    <td><input id="btnZumLogin" type="submit" name="zumLogin" value="zum Login"/></td>
 </tr>   
</table>



</form>



</body>
</html>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
</head>
<body>
	<h:outputText value="Administrative Login"
		style="font-size: x-large; font-family: Verdana, Arial, Sans-Serif"></h:outputText>
	<p></p>
	<h:panelGroup>
	<img src="/Italian.jpg" alt="" style="height: 129px; width: 239px;" />
	<h:outputText value="Welcome to SixTwelve"
		style='font-family: "Comic Sans MS", Sans-Serif; font-size: x-large;'></h:outputText>
	</h:panelGroup>
	<br />
	<br />
	<div style="border-left-color: #525252; border-right-color: #525252; border-left-style: solid; border-right-style: solid">
		<center>
			<h:form>
				<div style="width: 275px; background-color: #E1E1E1; height: 115px">
					<h:outputText value="Login"
						style="font-size: x-large; font-weight: bold; color: #606060; font-family: Verdana, Arial, Sans-Serif"></h:outputText>
					<h:panelGrid columns="2">
						<h:outputText value="Username"
							style="font-family: Verdana, Arial, Sans-Serif"></h:outputText>
						<h:inputText></h:inputText>
	
						<h:outputText value="Password"
							style="font-family: Verdana, Arial, Sans-Serif"></h:outputText>
						<h:inputSecret></h:inputSecret>
					</h:panelGrid>
					<h:button value="Submit"
						style="font-family: Verdana, Arial, Sans-Serif; background-color: #0080FF; color: #FFFFFF"></h:button>
				</div>
			</h:form>
		</center>
	</div>	
</body>
</html>
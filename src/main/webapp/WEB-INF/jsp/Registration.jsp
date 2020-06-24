<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">

<title>Registration</title>

               <fmt:setLocale value="${sessionScope.local}" />
               <fmt:setBundle basename="localization.local" var="loc" />

              <fmt:message bundle="${loc}" key="local.registration.name" var="name" />
              <fmt:message bundle="${loc}" key="local.registration.surname" var="surname"/>
              <fmt:message bundle="${loc}" key="local.registration.phone" var="phone"/>
              <fmt:message bundle="${loc}" key="local.registration.email" var="email"/>
              <fmt:message bundle="${loc}" key="local.registration.login" var="login"/>
              <fmt:message bundle="${loc}" key="local.registration.password" var="password"/>
              <fmt:message bundle="${loc}" key="local.registration.gender" var="gender"/>
              <fmt:message bundle="${loc}" key="local.registration.gender.male" var="male"/>
              <fmt:message bundle="${loc}" key="local.registration.gender.female" var="female"/>
              <fmt:message bundle="${loc}" key="local.button.registration" var="register"/>
              <fmt:message bundle="${loc}" key="local.registration.personal" var="personal"/>


</head>
<body>


   <header>
    
       <jsp:include page="header.jsp"/>

   </header>
   
   <main>
   
       <jsp:include page="menu.jsp"/>
   
   </main>
    
    <div class="container">
    
       <fieldset>
	      <legend>${personal}</legend>
	  </fieldset>
  
 

 
  <div class="col-sm-6">  
      
     <form  class="form-horizontal" action="Controller" method="post"> 
            <input type="hidden" name="command" value="Registration" />
     
        <div class="form-group">
              <label  class="col-sm-2 control-label">${name}</label>
           <div class="col-sm-10">
             <input type="text" class="form-control" name="name" id="" placeholder="${name}">
         </div>
     </div>
     
     <div class="form-group">
              <label   class="col-sm-2 control-label">${surname}</label>
         <div class="col-sm-10">
               <input type="text" class="form-control" name="surname" id="" placeholder="${surname}">
        </div>
   </div>
   
   <div class="form-group">
          <label   class="col-sm-2 control-label">${phone}</label>
      <div class="col-sm-10">
         <input type="text" class="form-control" name="phone" id="" placeholder="${phone}">
     </div>
  </div>
  
   <div class="form-group">
          <label  class="col-sm-2 control-label">${email}</label>
      <div class="col-sm-10">
         <input type="text" class="form-control" name="email" id="" placeholder="${email}">
     </div>
  </div>
  
  <div class="form-group">
         <label class="col-sm-2 control-label">${login}</label>
     <div class="col-sm-10">
        <input type="text" class="form-control" name="login" id="" placeholder="${login}">
    </div>
 </div>
 
  <div class="form-group">
         <label  class="col-sm-2 control-label">${password}</label>
    <div class="col-sm-10">
        <input type="password" class="form-control" name="password" id="" placeholder="${password}">
    </div>
  </div>
  
  <span>${gender}</span>
    <div class="form-group">

       <label class="radio-inline">
           <input type="radio" name="gender" id="" checked="checked" value="male">${male}
      </label>

      <label class="radio-inline">
           <input type="radio" name="gender" id="" value="female">${female}
     </label>
</div>

  <div class="form-group" >
    <div class="col-sm-offset-2 col-sm-10">
      	<input type="submit" name="Registration" id="Registration" class="btn btn-primary" value="${register}">
    </div>
  </div>
</form>
 <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha384-nvAa0+6Qg9clwYCGGPpDQLVpLNn0fRaROjHqs13t4Ggj3Ez50XnGQqc/r8MhnRDZ" crossorigin="anonymous"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>
</div>
</div>
</body>
</html>
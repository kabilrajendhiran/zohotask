<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.company.auth.AuthFilter" %>
<%@ page import="com.company.audit.Audit" %>
<%@ page import="java.util.List" %>
<%@ page import="org.owasp.encoder.Encode" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<%
    AuthFilter af = new AuthFilter();
    response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
    boolean flag = af.doFilter(request);

    if(!flag)
    {
        response.sendRedirect("login.jsp");
        return;
    }
    
%>


<div class="container">
    <div class="row">
        <div class="col">
            <table class="table table-bordered">
                <thead class="table-dark">
                    <tr>
                        <th scope="col">Email Id</th>
                        <th scope="col">Browser</th>
                        <th scope="col">Device</th>
                        <th scope="col">OS</th>
                        <th scope="col">IP Address</th>
                        <th scope="col">Activity</th>
                        <th scope="col">TimeStamp</th>
                    </tr>
                </thead>

                <tbody>
            <%


                List<Audit> audits =(List<Audit>) request.getAttribute("audits");
                for (Audit audit :audits) {
                    out.write("<tr>");
                    out.write("<td>"+ Encode.forHtml(audit.getEmail()) +"</td>");
                    out.write("<td>"+ Encode.forHtml(audit.getBrowser())+"</td>");
                    out.write("<td>"+ Encode.forHtml(audit.getDevice())+"</td>");
                    out.write("<td>"+ Encode.forHtml(audit.getOs())+"</td>");
                    out.write("<td>"+ Encode.forHtml(audit.getIpAddr()) +"</td>");
                    out.write("<td>"+ Encode.forHtml(audit.getActivity()) +"</td>");
                    out.write("<td>"+ Encode.forHtml(audit.getCurrentTimestamp()) +"</td>");
                    out.write("</tr>");

                }
            %>
                </tbody>
            </table>

        </div>
    </div>



</div>




</body>
</html>
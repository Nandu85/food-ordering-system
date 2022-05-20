<%@page import="javax.print.DocFlavor.READER"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.narola.fooddelivery.utility.URLConstantOfServlet" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="MetaTag.jsp"%>
</head>
<body>
	
<form method="POST" id="paymentform" action="https://api.razorpay.com/v1/checkout/embedded">
  <input type="hidden" name="key_id" value="rzp_test_f2BZIUq23nSlLZ">
  <input type="hidden" name="amount" value="${total}.00">
  <input type="hidden" name="order_id" value="${RazorPayId}">
  <input type="hidden" name="name" value="Acme Corp">
  <input type="hidden" name="description" value="A Wild Sheep Chase">
  <input type="hidden" name="image" value="https://cdn.razorpay.com/logos/BUVwvgaqVByGp2_large.png">
  <input type="hidden" name="prefill[name]" value="${sessionScope.user.getUsername()}">
  <input type="hidden" name="prefill[contact]" value="9123456780">
  <input type="hidden" name="prefill[email]" value="gaurav.kumar@example.com">
  <input type="hidden" name="notes[shipping address]" value="L-16, The Business Centre, 61 Wellfield Road, New Delhi - 110001">
  <input type="hidden" name="callback_url" value="${returnURI}">
  <input type="hidden" name="cancel_url" value="https://example.com/payment-cancel">
</form>

	<script type="text/javascript">
		document.getElementById("paymentform").submit();
	</script>

</body>
</html>
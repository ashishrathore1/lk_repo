<html>

<head>
<title>Lendingkart</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		document.location.href = $("#appId").val()+"?role="+$("#uploadPersonId").val();
	});
</script>
</head>
<spinner name="html5spinner" ng-cloak="">
<body hidden="true">
	<h1>Loading.........</h1>
	<input id="appId" value="${appId}">
	<input id="uploadPersonId" value="${uploadPersonId}">
	<button id="submit" value="submit" onload="redirect()">Submit</button>
</body>
</html>


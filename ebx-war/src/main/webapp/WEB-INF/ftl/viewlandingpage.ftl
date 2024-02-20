<HTML lang="en" id="document:html">
<head>
<title id="document:title">Blue Exchange Cross Walk Table
Automation</title>
<link href="theme/screen.css" rel="stylesheet" type="text/css">
</head>
<body onload="isReady=true;" id="document:body" style="">
<div class="wrapper">
	<div class="hdr-container">
		<div class="hdr-logo"></div>
		<div class="hdr-right"><div>Blue Exchange Cross Walk Table
Automation</div></div>
		<div class="clear"></div>
</div>
<div class="content-container">
	<div class="view-sum-title-bar">
		<div class="headerTitle">InProgress Variables</div>
	</div>
		<div class="view-sum-content-2 pd15bottom">
		<table>
			<tr>
				<th>System</th>
				<th>Variable</th>
				<th>Description</th>
				<th>Status</th>
				<th>Action</th>
			</tr>
			
			<tr>
				<td>
					<br/>
				</td>
			</tr>
			
			<#foreach inProgressVar in inProgressVariables>
				<tr>
					<td>EWPD</td>
					<td>${inProgressVar.mapping.variable.variableId}</td>
					<td>${inProgressVar.mapping.variable.description}</td>
					<td>${inProgressVar.mapping.variableMappingStatus}</td>
					<td></td>
				</tr>
			</#foreach>
			<br/>
			
		</table>
	</div>
	<div class="view-sum-title-bar">
		<div class="headerTitle">Un mapped Variables</div>
	</div>
	<div class="view-sum-content-2 pd15bottom">
		<table>
			<tr>
				<th>System</th>
				<th>Variable</th>
				<th>Description</th>
				<th>Created On</th>
				<th>Status</th>
				<th>Action</th>
			</tr>
			
					
			<#foreach unmappedVar in unmappedVariables>
				<tr>
					<td>${unmappedVar.mapping.variable.variableSystem}</td>
					<td>${unmappedVar.mapping.variable.variableId}</td>
					<td>${unmappedVar.mapping.variable.description}</td>
					<td>${unmappedVar.mapping.variable.createdDate}</td>
					<td>Unmapped</td>
					<td></td>
				</tr>
			</#foreach>
			<br/>
			
		</table>
	</div>
</div>
<div class="form-btn-container"></div>
</div>

</body>
</html>
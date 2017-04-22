<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
    <meta name="viewport" content="width=996px">
    <meta content="text/html; charset=gb2312" http-equiv="Content-Type">
    <title>Real-time Line Chart</title>>
    <link rel="stylesheet" type="text/css" href="../css/style.css" />
    <LINK rel="stylesheet" type="text/css" href="../css/prettify.css" />
    <script type="text/javascript" src="../F_js/jquery.min.js"></script>
    <SCRIPT type="text/javascript" src="../F_js/FusionCharts.js"></SCRIPT>

	<SCRIPT type="text/javascript" src="../F_js/prettify.js"></SCRIPT>
	
	<SCRIPT type="text/javascript" src="../F_js/json2.js"></SCRIPT>
	
	<SCRIPT type="text/javascript" src="../F_js/lib.js"></SCRIPT>
	
	<SCRIPT type="text/javascript" src="../F_js/date.js"></SCRIPT>

<SCRIPT type="text/javascript">
			var isJSChartNotAvailable = false;
		</SCRIPT>

<META name="GENERATOR" content="MSHTML 9.00.8112.16561"></HEAD>
<BODY>
<H3 class="chart-title">网络监控报表</H3>
<P>&nbsp;</P>
<SCRIPT type="text/javascript" src="../F_js/RTLine.js"></SCRIPT>

<DIV id="chartdiv" align="center">Chart will load here</DIV>
<SCRIPT type="text/javascript">
			
			if ((typeof isJSChartNotAvailable=="undefined" || !isJSChartNotAvailable==true) && (GALLERY_RENDERER && GALLERY_RENDERER.search(/javascript|flash/i)==0) )  FusionCharts.setCurrentRenderer(GALLERY_RENDERER); 
			
            var chart = new FusionCharts("../FusionCharts/RealTimeLine.swf", "ChartId", "550", "390", "0", "1" );
            chart.setXMLData( dataString );
            //chart.setDataURL("Data.xml");
            chart.render("chartdiv");


			window.dataUpdateTimer = null;
			
			FusionCharts.addEventListener("Rendered", function(e,a) {
				if(e.sender.id=="tmpChartId") return;
			
				window.dataUpdateTimer = window.setInterval (function (){ 
					provideRealTimeDataThroughJSAPI(e.sender);
				},2000 );
			});			
				
			function provideRealTimeDataThroughJSAPI(sender)
			{
				var updater= sender.feedData? sender : null;
				
				var p1 = Math.round(Math.random()*100);
				var p2 = Math.round(Math.random()*100);
				var dateTimeLabel = new Date().toString("HH:mm:ss");
				if(updater) updater.feedData("&label=" + dateTimeLabel + "&value=" + p1 + "|" + p2 );
			}

			
        </SCRIPT>

<DIV class="qua-button-holder"></DIV>
<DIV class="show-code-block"></DIV>
<SCRIPT type="text/javascript">
	 	$(document).ready ( function() {
			  if(chart.options.renderer=="javascript" && isJSChartNotAvailable)
			  {
			  	$(".description-text").css( { "visibility": "hidden" } );
			  	$(".qua-button-holder").css( { "visibility": "hidden" } );
			  	$(".show-code-block").css( { "visibility": "hidden" } );				
			  	$(".chart-title").next().addClass("highlightBlock").css({ "text-align": "center" }).html("JavaScript version of Real-time Line Chart is not supported.");

			  }
			  else
			  {
			  	$(".chart-title").next().removeClass("highlightBlock").html("&nbsp;");
			  }
		});	
	 
	 </SCRIPT>
</BODY></HTML>

<%@ taglib uri="http://efsavage.com/twitter-bootstrap" prefix="bs" %>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <link href="/ext/bootstrap/css/bootstrap-responsive.css" rel="stylesheet" media="screen">

    <meta charset="utf-8">
    <title>Perfmon web console</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Jiri Kratochvil">

    <!-- Le styles -->
    <link href="/ext/bootstrap/css/bootstrap.css" rel="stylesheet">
    <style>
        body {
            padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
        }
    </style>
    <link href="/ext/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">

    <!-- Fav and touch icons -->
    <%--
            <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
            <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
            <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
            <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
            <link rel="shortcut icon" href="../assets/ico/favicon.png">
    --%>

    <script type='text/javascript' src='https://www.google.com/jsapi'></script>

    <script type='text/javascript'>
        google.load('visualization', '1', {packages: ['gauge']});
        google.setOnLoadCallback(drawChartGauge);
        function drawChartGauge() {
            var data = google.visualization.arrayToDataTable([
                ['Label', 'Value'],
                ['Time per call', 80],
                ['Call per hour', 55],
            ]);

            var options = {
                width: 400, height: 120,
                redFrom: 90, redTo: 100,
                yellowFrom: 75, yellowTo: 90,
                minorTicks: 5
            };

            var chart = new google.visualization.Gauge(document.getElementById('chart_div'));
            chart.draw(data, options);
        }
    </script>

    <script type="text/javascript">
        google.load("visualization", "1", {packages: ["corechart"]});
        google.setOnLoadCallback(drawChartLine);
        function drawChartLine() {
            var data = google.visualization.arrayToDataTable([
                ['Time', 'Calls', 'Avg. time'],
                ['12.2.2014 1:00', 50324, 15],
                ['12.2.2014 2:00', 36775, 40],
                ['12.2.2014 3:00', 66440, 12],
                ['12.2.2014 4:00', 10302, 54]
            ]);

            var options = {
                title: 'Hourly stats',
                vAxes: {0: {logScale: false},
                    1: {logScale: false, maxValue: 10}},
                series:{
                    0:{targetAxisIndex:0},
                    1:{targetAxisIndex:1}}
            };

            var chart = new google.visualization.LineChart(document.getElementById('chart_div_line'));
            chart.draw(data, options);
        }
    </script>
</head>
<body>

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="brand" href="#">Perfmon</a>

            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li class="active"><a href="/">Dashboard</a></li>
                </ul>
            </div>
            <!--/.nav-collapse -->
        </div>
    </div>
</div>

<div class="container">

    <h1>Dashboard</h1>

    <div id='chart_div'></div>

    <bs:table hover="true" bordered="true">
        <thead>
        <bs:tr>
            <th>Monitor</th>
            <th>Type</th>
            <th>Total calls (&micro;s)</th>
            <th>Total time (&micro;s)</th>
            <th>Avg time per call (&micro;s)</th>
            <th>Longest call (&micro;s)</th>
            <th>Shortest call (&micro;s)</th>
        </bs:tr>
        </thead>
        <tbody>
        <bs:tr>
            <td>row1-col1</td>
            <td>row1-col2</td>
            <td>row1-col1</td>
            <td>row1-col2</td>
            <td>row1-col1</td>
            <td>row1-col2</td>
            <td>row1-col2</td>
        </bs:tr>
        <bs:tr>
            <td>row1-col1</td>
            <td>row1-col2</td>
            <td>row1-col1</td>
            <td>row1-col2</td>
            <td>row1-col1</td>
            <td>row1-col2</td>
            <td>row1-col2</td>
        </bs:tr>
        </tbody>
    </bs:table>

    <div id='chart_div_line'></div>

</div>
<!-- /container -->

<script src="http://code.jquery.com/jquery.js"></script>
<script src="/ext/bootstrap/js/bootstrap.js"></script>
</body>
</html>

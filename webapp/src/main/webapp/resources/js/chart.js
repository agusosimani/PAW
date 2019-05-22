$(function () {

    $('#datepicker').datepicker();
    $('#datepicker2').datepicker();

    var labelsName = [];
    var labelsData = [];
    for(var i = 0; i < list.length; i++){
        labelsName[i] = list[i].type;
        labelsData[i] = (Math.round(list[i].amount * 100) / 100);
    }

    var ctxB = document.getElementById("barChart").getContext('2d');
    var myBarChart = new Chart(ctxB, {
        type: 'bar',
        data: {
            labels: labelsName,
            datasets: [{
                data: labelsData,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)'
                ],
                borderColor: [
                    'rgba(255,99,132,1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            legend: { display: false},
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });

    var donutLabels = [];
    var donutData = [];
    var randomColors = [];
    var i = 0;
    var back = ["#ff0000","blue","gray"];
    var rand = back[Math.floor(Math.random() * back.length)];
    for (var key in donutList) {
        randomColors[i] = rand;
        donutLabels[i] = key;
        donutData[i] = donutList[key];
        rand = back[Math.floor(Math.random() * back.length)];
        i++;
    }

    var ctxD = document.getElementById("doughnutChart").getContext('2d');
    var myLineChart = new Chart(ctxD, {
        type: 'doughnut',
        data: {
            labels: donutLabels,
            datasets: [{
                data: donutData,
                backgroundColor: ["#F7464A", "#46BFBD", "#FDB45C", "#949FB1", "#4D5360"],
                hoverBackgroundColor: ["#FF5A5E", "#5AD3D1", "#FFC870", "#A8B3C5", "#616774"]
            }]
        },
        options: {
            responsive: true
        }
    });
});
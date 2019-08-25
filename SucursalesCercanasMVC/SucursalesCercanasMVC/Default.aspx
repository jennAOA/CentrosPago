<%@ Page Language="C#" Inherits="SucursalesCercanasMVC.Default" %>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Campañas Sucursales</title>

        <link href="Css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="Css/sb-admin-2.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.0.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>
        <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script>
        <script type="text/javascript" src="gmaps.js"></script>
        <script src='//cdn.jsdelivr.net/gmaps4rails/2.1.2/gmaps4rails.js'> </script>
        <script src='//cdnjs.cloudflare.com/ajax/libs/underscore.js/1.8.3/underscore.js'> </script>
          <link href="map.css" rel="stylesheet" type="text/css"> 
        
        
      

  <!-- Custom styles for this template-->
   <style type="text/css">
  #map{
  display: block;
  width: 100%;
  height: 600px;
  margin: 0 auto;
  -moz-box-shadow: 0px 5px 20px #ccc;
  -webkit-box-shadow: 0px 5px 20px #ccc;
  box-shadow: 0px 5px 20px #ccc;
}
  </style>

</head>

<body id="page-top">

    <div class="pos-f-t">
        <nav class="navbar navbar-light bg-light" style="background-color:green">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggleExternalContent" aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="text-center">
                <img src="imgs/bancoazteca-01.svg " style="width:170px;height:18px;" align="middle;">
            </div>
        </nav>
    </div>
        
    <div class="container">
        <div class="row">
            <div class="col-4">
                <span class="input-group-btn">
                    
                        <br>
                        <div class="input-group">
              <input type="text" class="form-control bg-light border-0 small" placeholder="Centro de Pago" aria-label="Centro de Pago" aria-describedby="basic-addon2">
              <div class="input-group-append">
               
                  <button type="submit" class="btn btn-outline-light"><i class="fa fa-search"></i></button>
                  
              </div>
            </div>
                </span>
                    <div align="right" style="background-color:green">
                        
                            <button class="btn btn-secondary btn-lg">Filtro</button>
                    </div>
        
                <table id="content" class='table borderless'>
                <thead>
                <!--<tr>
                    <th>Sucursal</th>
                    <th>Comisiòn</th>
                    <th>Distancia</th>
                    <th>Ir a</th>
           
                </tr>-->
                </thead>
                <tbody id="tbody">
                </tbody>
                </table>
            </div>
            <div class="col-8" id="map_canvas" class= 'map'></div>
                
               <!-- <div id=""></div>-->
        </div>
    </div>
    
    
        
    <script type="text/javascript">

            
    $(document).ready(
        function() {
            alert("1");
            var data = $("#buscar").val();
            alert("2");
            $.ajax({
 
                url : 'https://jsonplaceholder.typicode.com/todos/1',
                type : 'GET',
                dataType : 'JSON',
                success : function(data) {
                    alert("3");
                    $(data).each(
                            function() {
                                $('#tbody').append(
                                        '<tr><td>' + this.userId
                                                + '</td><td>'
                                                + this.id
                                                + '</td><td>'
                                                + this.title
                                                + '</td><td>'
                                                + this.completed
                                                + '</td></tr>')
                            });
                },
                error : function(data) {
                    alert("4");
                }
 
            });
        });
            
            function getJSONMarkers() {
          const markers = [
            {
              name:  "Rixos The Palm",
              location: [25.1212, 55.1535]
            },
            {
              name: "Shangri-La Hotel",
              location: [25.2084, 55.2719]
            },
            {
              name: "Grand Hyatt",
              location: [25.2285, 55.3273]
            }
          ];
          return markers;
        }
    </script>
       
    <script type="text/javascript"
    src="http://maps.googleapis.com/maps/api/js?sensor=false"></script> 

<script type="text/javascript"> 
var siberia = new google.maps.LatLng(37.625364,-122.423905);

function initialize() {
  var myOptions = {
    zoom:19,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  };
  var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
  var infowindow = new google.maps.InfoWindow({
          map: map,
          position: siberia,
          content: 'Location found using HTML5.'
        });

  var marker =  new google.maps.Marker({
    position: siberia,
    map: map,
    title: "omt"
  });
  map.setCenter(siberia);

}
  google.maps.event.addDomListener(window, 'load', initialize);
    </script> 
        

 
</body>

</html>

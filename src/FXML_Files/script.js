

mapboxgl.accessToken = 'pk.eyJ1IjoiYWxleG1hZGVyYSIsImEiOiJja2hpN3FtMjQwMzUzMnlvN2psZHU4a3J2In0.L9w_EkpD6C6g5yW3IFjh5g';

navigator.geolocation.getCurrentPosition(successLocation, errorLocation, {enableHighAccuracy:true})

function successLocation(position){
    console.log(position)
    setupMap([-93.739136,38.758034])
}

function errorLocation(){
    setupMap([-93.736053,38.762791])
}

function setupMap(c){
    const map = new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/streets-v11',
        center: c,
        zoom:16
    })

    const nav = new mapboxgl.NavigationControl();
    map.addControl(nav);

    var directions = new MapboxDirections({
        accessToken: mapboxgl.accessToken
    });



    map.addControl(directions, 'bottom-right');
}


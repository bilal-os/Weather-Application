'use strict';

const apiKey= "511c0d53e786d6e701870951d85c605d";

export const fetchData = (URL,callback)=>{
    fetch(`${URL}&appid=${apiKey}`)
    .then(res=>res.json())
    .then(data=>callback(data))
}

export const url ={
    currentWeather(lat,lon){
        return `http://localhost:4567/currentWeather?${lat}&${lon}`
    },
    forecast(lat,lon){
        return `http://localhost:4567/forecast?${lat}&${lon}`
    },
    airPollution(lat,lon){
        return `http://localhost:4567/air?${lat}&${lon}`
    },
    reverseGeo(lat,lon){
        return `https://api.openweathermap.org/geo/1.0/reverse?${lat}&${lon}&limit=5`
    },

    addLocation(lat,lon,curr){
        return `http://localhost:4567/addlocation?${lat}&${lon}&${curr}`
    },

    storedLocations() {
        return 'http://localhost:4567/locations'
    },

    /**
     * @param {string} query search query e.g. :"london" , "New Yourk"  
     */
    geo(query){
        return `https://api.openweathermap.org/geo/1.0/direct?q=${query}&limit=5`
    }
}
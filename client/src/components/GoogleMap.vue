<template>
  <div>
      <div class="footer">
        Artists map
      </div>
    <!--<div>-->
    <!--<h2>Map</h2>-->
    <!--<button @click="addMarkers">Add Markers</button>-->
    <!--</div>-->
    <v-layout>
      <label>User:</label>
      <div class="input">
        <input type="string" class="primary" v-model="user"/>
      </div>
      <div class="slidecontainer">
        <input type="range" min="1" max="100" v-model="sliderValue" @input="changeWidth" class="slider" id="myRange">
      </div>
        <button class="btn btn-outline-primary"
                @click="addMarkers"
                target="_blank">Find
        </button>
    </v-layout>
    <br>
    <gmap-map
      :center="center"
      :zoom="1"
      style="width:100%;  height: 400px;"
    >
      <gmap-marker
        :key="key"
        v-for="(marker, key) in markers"
        :position="marker.position"
        @click="toggleInfo(marker, key)"
      >
      </gmap-marker>
      <gmap-info-window
        :options="infoOptions"
        :position="infoPosition"
        :opened="infoOpened"
        @closeclick="infoOpened=false">
        {{infoContent}}
      </gmap-info-window>
    </gmap-map>
  </div>
</template>

<script>
  export default {
    name: "GoogleMap",
    data() {
      return {
        center: {lat: 45.508, lng: -73.587},
        markers: [],
        artists: [],
        infoPosition: null,
        infoContent: null,
        infoCurrentKey: null,
        infoOpened: false,
        infoOptions: {
          pixelOffset: {
            width: 0,
            height: -35
          }
        },
        sliderValue:50,
        width: 50
      };
    },

    mounted() {
      fetch("http://localhost:8080/hometowns")
        .then(response => response.json())
        .then((data) => {
          this.artists = data;
        });
    },
    methods: {
      addMarkers() {
        for (var i = 0; i < this.artists.length; i++) {
          var artist = this.artists[i];
          if (artist === null)
            continue;
          var lat = artist.location.lat;
          var lng = artist.location.lng;
          if (lat === null && lng === null) {
            continue;
          }
          const marker = {
            position: {
              lat: lat,
              lng: lng
            },
            content: artist.bandName + ', ' + artist.hometownName
          };
          this.markers.push(marker)
          this.center = marker.position;

        }
      },
      toggleInfo(marker, key) {
        // this.center = marker.position;
        this.infoPosition = marker.position;
        this.infoContent = marker.content;
        if (this.infoCurrentKey == key) {
          this.infoOpened = !this.infoOpened;
        } else {
          this.infoOpened = true;
          this.infoCurrentKey = key;
        }
      },
      changeWidth(){
        this.width = this.sliderValue
      }
    }
  };
</script>

<style scoped>
  .input {
    margin-left: 12px;
  }
  .footer {
    width: 100%;
    height: 80px;
    background-color: #303030;
    margin: 0px;
    padding: 0px;
    color:white;
    font-family: "Courier New";
    font-size:30px;
    text-align: center;
  }

  .slider {
    -webkit-appearance: none;  /* Override default CSS styles */
    appearance: none;
    height: 10px; /* Specified height */
    background: #d3d3d3; /* Grey background */
    outline: none; /* Remove outline */
    opacity: 0.7; /* Set transparency (for mouse-over effects on hover) */
    -webkit-transition: .2s; /* 0.2 seconds transition on hover */
    transition: opacity .2s;
  }

  /* Mouse-over effects */
  .slider:hover {
    opacity: 1; /* Fully shown on mouse-over */
  }

  /* The slider handle (use -webkit- (Chrome, Opera, Safari, Edge) and -moz- (Firefox) to override default look) */
  .slider::-webkit-slider-thumb {
    -webkit-appearance: none; /* Override default look */
    appearance: none;
    width: 25px; /* Set a specific slider handle width */
    height: 10px; /* Slider handle height */
    background: #303030;
    cursor: pointer; /* Cursor on hover */
  }

  .slider::-moz-range-thumb {
    width: 25px; /* Set a specific slider handle width */
    height: 10px; /* Slider handle height */
    background: #4CAF50; /* Green background */
    cursor: pointer; /* Cursor on hover */
  }


</style>

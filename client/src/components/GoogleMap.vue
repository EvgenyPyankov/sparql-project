<template>
  <div>
    <div>
      <h2>Map</h2>
      <button @click="addMarkers">Add</button>
    </div>
    <br>
    <gmap-map
      :center="center"
      :zoom="1"
      style="width:100%;  height: 400px;"
    >
      <gmap-marker
        :key="index"
        v-for="(m, index) in markers"
        :position="m.position"
        @click="center=m.position"
      ></gmap-marker>
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
        places: [],
        currentPlace: null,
        artists: [],
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
        console.log("artists" + this.artists)
        for (var i = 0; i < this.artists.length; i++) {
          if (this.artists[i] === null)
            continue;
          var l = this.artists[i].location.lat;
          var m = this.artists[i].location.lon;
          if (l != null && m != null) {
            const marker = {
              lat: this.artists[i].location.lat,
              lng: this.artists[i].location.lon
            };
            this.markers.push({position: marker});
            this.places.push(this.currentPlace);
            this.center = marker;
            this.currentPlace = null;
          }
        }
      }
    }
  };
</script>

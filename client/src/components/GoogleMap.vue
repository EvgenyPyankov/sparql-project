<template>
  <div>
    <div>
      <h2>Map</h2>
      <button @click="addMarkers">Add Markers</button>
    </div>
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
        }
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
      }
    }
  };
</script>

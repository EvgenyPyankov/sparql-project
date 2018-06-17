<template>
  <div>
    <md-card-content>
      <div class="md-layout md-gutter">
        <div class="md-layout-item">
          <md-field :class="messageClass">
            <label>User</label>
            <md-input v-model="user" required></md-input>
            <span class="md-error">{{errorMessage}}</span>
          </md-field>
        </div>
        <div class="md-layout-item">
          <md-field>
            <label>Top Artists</label>
            <md-input v-model="topArtistsValue" type="number" min="1" max="100"></md-input>
          </md-field>
        </div>
        <div class="md-layout-item">
          <md-button class="md-raised md-primary" v-on:click="show()">Show</md-button>
        </div>
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
          <b>Artist: </b>{{infoArtistName}}
          <br>
          <b>Hometown: </b>{{infoArtistHometown}}
        </gmap-info-window>
      </gmap-map>
    </md-card-content>
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
        infoArtistName: null,
        infoArtistHometown: null,
        infoCurrentKey: null,
        infoOpened: false,
        infoOptions: {
          pixelOffset: {
            width: 0,
            height: -35
          }
        },
        sliderValue: 50,
        width: 50,
        topArtistsValue: 10,
        hasErrorMessages: false,
        errorMessage: null,
        user: null
      };
    },
    computed: {
      messageClass() {
        return {
          'md-invalid': this.hasErrorMessages
        }
      }
    },
    methods: {
      show() {
        this.markers = [];
        if (this.user === null || this.user == "") {
          this.errorMessage = "Please input user name";
          this.hasErrorMessages = true;
          return;
        }
        fetch("http://localhost:8080/hometowns?user=" + this.user + "&limit=" + this.topArtistsValue)
          .then(response => response.json())
          .then((data) => {
            this.errorMessage = null;
            this.hasErrorMessages = false;
            this.artists = data;
            this.addMarkers();
          })
          .catch(error => {
              console.log(error);
              this.errorMessage = "No such user";
              this.hasErrorMessages = true;
            }
          );
      },

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
            artistName: artist.bandName,
            artistHometown: artist.hometownName
          };
          this.markers.push(marker)
          this.center = marker.position;
        }
      },

      toggleInfo(marker, key) {
        this.infoPosition = marker.position;
        this.infoArtistName = marker.artistName;
        this.infoArtistHometown = marker.artistHometown;
        if (this.infoCurrentKey == key) {
          this.infoOpened = !this.infoOpened;
        } else {
          this.infoOpened = true;
          this.infoCurrentKey = key;
        }
      }
    }
  }
  ;
</script>

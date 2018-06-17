<template>
  <div id="app">
    <md-card>
      <md-card-header>
        <div class="md-title">Last.fm library visualization</div>
        <div class="md-subhead">Map of artists hometowns</div>
      </md-card-header>
      <google-map/>
    </md-card>
  </div>
</template>

<script>
  import GoogleMap from "./components/GoogleMap";

  export default {
    name: 'app',
    components: {
      GoogleMap
    }
  }
</script>

<style>
</style>

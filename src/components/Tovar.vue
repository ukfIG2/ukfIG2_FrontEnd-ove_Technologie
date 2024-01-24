<template>
    <div>
      <div v-for="(tovar, index) in tovars" :key="index">
        {{ tovar }}
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        tovars: [],
      };
    },
    created() {
      axios.get('http://localhost:8080/Eshop/SELECTtovarServlet')
        .then(response => {
          this.tovars = response.data;
        })
        .catch(error => {
          console.error('Error:', error);
        });
    },
  };
  </script>
<template>
    <div class="products">
      <div class="product" v-for="(tovar, index) in tovars" :key="index">
        <h2>{{ tovar.Nazov }}</h2>
        <img :src="tovar.Fotka" alt="Product image">
        <p>Brand: {{ tovar.Znacka }}</p>
        <p>Model: {{ tovar.Modelova_rada }}</p>
        <p>Processor: {{ tovar.Procesor }}</p>
        <p>Memory Size: {{ tovar.Velkost_operacnej_pamate }}</p>
        <p>Display Size: {{ tovar.Uhlopriecka_displeja }}</p>
        <p>Price: {{ tovar.Cena }}</p>
      </div>
    </div>
  </template>
  
  <script lang="ts">
  import axios, { AxiosResponse } from 'axios';
  import { Data, Tovar } from './types'; // Adjust the path as necessary
  
  export default {
    data(): Data {
      return {
        tovars: [],
      };
    },
    created() {
      axios.get('http://localhost:8080/Eshop/SELECTtovarServlet')
        .then((response: AxiosResponse<Tovar[]>) => {
          this.tovars = response.data;
        })
        .catch((error: Error) => {
          console.error('Error:', error);
        });
    },
  };
  </script>
  
  <style scoped>
  .products {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
  }
  
  .product {
    flex: 0 0 calc(33.3333% - 1em);
    margin: 0.5em;
    border: 1px solid #ccc;
    padding: 1em;
    box-sizing: border-box;
  }
  </style>
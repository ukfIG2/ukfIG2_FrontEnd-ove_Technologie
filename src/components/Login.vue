<template>
    <div class="login-container">
        <form @submit="submitForm">
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" v-model="email" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" v-model="password" required>
            </div>
            <button type="submit">Login</button>
        </form>
    </div>
</template>

<script lang="ts">
import axios, { AxiosResponse } from 'axios';
import { useUserStore } from '../stores/userStore'; // Adjust the path as necessary

export default {
  data() {
    return {
      email: '',
      password: '',
    };
  },
  methods: {
    submitForm() {
      // Create a data object with the email and password
      const data = {
        email: this.email,
        password: this.password
      };

      // Make a POST request to the servlet endpoint
      axios.get('http://localhost:8080/Eshop/loginServlet', data)
        .then((response: AxiosResponse) => {
          // If the response is successful, set the user in the store
          if (response.status === 200) {
            const userStore = useUserStore();
            userStore.setUser(response.data);
          }
        })
        .catch((error: Error) => {
          console.error('Error:', error);
        });
    }
      
    }}
</script>

<style>
.login-container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.form-group {
    margin-bottom: 1rem;
}

label {
    display: block;
}

input[type="email"],
input[type="password"] {
    width: 100%;
    padding: 0.5rem;
    border: 1px solid #ccc;
    border-radius: 4px;
}

button {
    padding: 0.5rem 1rem;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}
</style>

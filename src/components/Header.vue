<template>
    <header>
        <nav>
            <ul>
                <li>
                    <router-link to="/">Domov</router-link>
                </li>
                <li>
                    <router-link to="/products">Produkty</router-link>
                </li>
                <li>
                    <router-link to="/contact">Kontakt</router-link>
                </li>
                <li>
                    <router-link to="/Login">Login</router-link>
                </li>
            </ul>
        </nav>
    </header>
</template>

<script>
export default {
    name: 'Header',
}
</script>

<style scoped>
header {
    background-color: #f8f9fa;
    padding: 10px 0;
  }
  
  nav {
    display: flex;
    justify-content: space-around;
    align-items: center;
  }
  
  nav ul {
    display: flex;
    list-style: none;
  }
  
  nav ul li {
    margin: 0 10px;
  }
  
  nav ul li a {
    color: #333;
    text-decoration: none;
  }
  
  nav ul li a:hover {
    color: #007bff;
  }
</style>

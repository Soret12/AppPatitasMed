<template>

    <div class="screen">
      <div class="background-decor"></div>

      <div class="content">

        <!-- LOGO -->
        <img
          src="@/assets/logo.png"
          alt="PatitasMed"
          class="logo"
        />

        <!-- BIENVENIDA -->
        <h2 class="welcome">
          ¡Bienvenido, {{ usuario.nombre }}! 🐾
        </h2>

        <p class="description">
          Gestiona la salud y el bienestar de tus mascotas desde un solo lugar.
        </p>

        <!-- ESTADÍSTICAS -->
        <div class="stats">

          <div class="stat-card">
            <span>🐾</span>
            <h3>{{ totalMascotas }}</h3>
            <p>Mascotas</p>
          </div>

        </div>

        <!-- OPCIONES -->
        <div class="dashboard-grid">

          <router-link to="/perfil" class="card">
            <h3>👤 Mi Perfil</h3>
            <p>Gestiona tu información personal</p>
          </router-link>

          <router-link to="/mascotas" class="card">
            <h3>🐾 Mis Mascotas</h3>
            <p>Administra las mascotas registradas</p>
          </router-link>

          <router-link to="/citas" class="card">
            <h3>📅 Citas Médicas</h3>
            <p>Programa y revisa citas médicas veterinarias</p>
          </router-link>

        </div>

        <!-- CERRAR SESIÓN -->
        <button
          class="btn logout"
          @click="cerrarSesion"
        >
          🚪 Cerrar Sesión
        </button>

      </div>

      <div class="footer">
        Desarrollado por <strong>Yubeli Carabalí</strong>
      </div>

    </div>

</template>

<script>

import axios from "axios"

export default {

  name: "DashboardView",

  data() {

    return {

      usuario: {},
      totalMascotas: 0,
      totalCitas: 0

    }

  },

  async mounted() {

  this.usuario = JSON.parse(localStorage.getItem("usuario"))

  await this.cargarEstadisticas()

  },

  methods: {

    async cargarEstadisticas() {

      const respuestaMascotas = await axios.get(
        `http://localhost:8080/api/mascotas/usuario/${this.usuario.idUsuario}`
      )

      this.totalMascotas = respuestaMascotas.data.length

    },

    cerrarSesion() {

      localStorage.removeItem("usuario")

      this.$router.push("/")

    }

  }

}
</script>

<style scoped>

.screen {
  min-height: 100vh;
  background: linear-gradient(
    135deg,
    #fff7f0,
    #ffe0d2,
    #fffaf6
  );
  font-family: Arial, sans-serif;
  position: relative;
  overflow-x: hidden;
}

.background-decor::before,
.background-decor::after {
  content: "";
  position: absolute;
  border-radius: 50%;
  filter: blur(70px);
  opacity: 0.35;
}

.background-decor::before {
  width: 500px;
  height: 500px;
  background: #ffa07a;
  top: -150px;
  left: -150px;
}

.background-decor::after {
  width: 420px;
  height: 420px;
  background: #ffd1a1;
  bottom: -150px;
  right: -150px;
}

.content {
  position: relative;
  z-index: 2;
  max-width: 900px;
  margin: auto;
  padding: 40px 20px;
  text-align: center;
}

.logo {
  width: 280px;
  display: block;
  margin: auto;
  margin-bottom: 10px;
}

.welcome {
  color: #444;
  font-size: 30px;
  margin-bottom: 10px;
}

.description {
  color: #666;
  margin-bottom: 30px;
}

.stats{
  display:flex;
  justify-content:center;
  margin:30px 0;
}

.stat-card{
  width:160px;
  background:white;
  border-radius:20px;
  padding:20px;
  text-align:center;

  border:2px solid #ffb28f;

  box-shadow:
    0 10px 20px rgba(0,0,0,.08);
}

.stat-card span{
  font-size:35px;
}

.stat-card h3{
  margin:10px 0 5px;
  color:#ff7f50;
  font-size:30px;
}

.dashboard-grid{
  display:flex;
  flex-direction:column;
  gap:20px;
  margin-top:30px;
}

.card{
  text-decoration:none;

  background:white;

  border:2px solid #ffb28f;

  border-radius:18px;

  padding:25px;

  text-align:left;

  transition:.3s;

  box-shadow:
    0 10px 20px rgba(0,0,0,.08);
}

.card:hover{
  transform:translateY(-4px);

  border-color:#ff7f50;

  box-shadow:
    0 15px 30px rgba(0,0,0,.15);
}

.card h3{
  color:#333;
  margin-bottom:10px;
  font-size:24px;
}

.card p{
  color:#666;
  margin:0;
}

.btn{
  padding:14px 25px;
  border-radius:35px;
  cursor:pointer;
  font-weight:bold;
  border:2px solid #ff7f50;
  transition:.3s;
}

.logout{
  margin-top:30px;
  background:white;
  color:#444;
}

.logout:hover{
  background:#ff7f50;
  color:white;
}

.footer{
  text-align:center;
  padding:20px;
  color:#666;
  font-size:12px;
}

</style>
<template>
  <div class="screen">

    <div class="background-decor"></div>

    <div class="content">

      <!-- LOGO PROTAGONISTA -->
      <img src="@/assets/logo.png" alt="Logo PatitasMed" class="logo" />

      <!-- TEXTO MÁS IMPACTANTE -->

      <p class="slogan">"El bienestar de tu mascota es primero"</p>

      <p class="description">
        Plataforma inteligente para el cuidado veterinario.<br>
        Seguimiento de la salud de tu mascota en un solo lugar.
      </p>

      <!-- LOGIN -->
      <form class="login-form" @submit.prevent="iniciarSesion">

        <input
          type="text"
          placeholder="👤 Usuario"
          v-model="usuario.username"
          required
        >

        <div class="password-box">

          <input
            :type="mostrarPassword ? 'text' : 'password'"
            placeholder="🔐 Contraseña"
            v-model="usuario.password"
            required
          >

          <span
            class="eye"
            @click="mostrarPassword = !mostrarPassword"
          >
            {{ mostrarPassword ? '🙈' : '👁️' }}
          </span>

        </div>

        <button type="submit" class="btn primary">
          🔐 Iniciar sesión
        </button>

      </form>

      <p class="mensajeError" v-if="mensajeError">
        {{ mensajeError }}
      </p>

      <div class="actions">

        <p class="registro-link">
          ¿No tienes cuenta?
          <router-link to="/registro">
              Crear cuenta 🐾
          </router-link>
        </p>

      </div>

      <p class="mensaje">{{ mensaje }}</p>

    </div>

    <!-- FOOTER FUERA -->
    <div class="footer">
      Desarrollado por <strong>Yubeli Carabalí</strong>
    </div>

  </div>
</template>

<script>

import axios from 'axios'

export default {

  name: "InicioView",

  data() {

    return {

      usuario: {
        username: '',
        password: ''
      },

      mensajeError: ''

    }

  },

  methods: {

    async iniciarSesion() {

      try {

        const respuesta = await axios.post(
          'http://localhost:8080/api/usuarios/login',
          this.usuario
        )

        localStorage.setItem(
          'usuario',
          JSON.stringify(respuesta.data)
        )

        alert('Inicio de sesión exitoso')

        this.$router.push('/dashboard')

      } catch (error) {

        this.mensajeError =
          'Usuario o contraseña incorrectos'

      }

    }

  }
};
</script>

<style scoped>
.screen {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
  overflow: hidden;
  font-family: Arial, sans-serif;

  /* más suave y premium */
  background: linear-gradient(135deg, #fff7f0, #ffe0d2, #fffaf6);
}

/* fondo más dinámico */
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

/* contenido */
.content {
  text-align: center;
  z-index: 2;
  max-width: 500px;
  padding: 20px;
}

/* 🔥 LOGO MUCHO MÁS GRANDE */
.logo {
  width: 100%;
  max-width: 600px;
  height: auto;
  display: block;
  margin: 0 auto;
  animation: float 4s ease-in-out infinite;
  filter: drop-shadow(0 10px 20px rgba(0,0,0,0.15));
}

/* título */
.title {
  font-size: 34px;
  font-weight: bold;
  color: #444;
  margin-bottom: 5px;
}

/* slogan más llamativo */
.slogan {
  font-size: 16px;
  color: #ff7f50;
  font-weight: bold;
  margin-bottom: 15px;
}

/* descripción */
.description {
  font-size: 14px;
  color: #666;
  margin-bottom: 30px;
  line-height: 1.5;
}

.login-form {
  display: flex;
  flex-direction: column;
  gap: 14px;
  margin-top: 20px;

  width: 320px;
  margin-left: auto;
  margin-right: auto;
}

.login-form input {
  width: 100%;
  box-sizing: border-box;
  padding: 14px;
  border-radius: 35px;
  border: 2px solid #ffd1a1;
  background: rgba(255,255,255,0.9);
  font-size: 15px;
  outline: none;
  transition: 0.3s;
}

.login-form input:focus {
  border-color: #ff7f50;
  box-shadow: 0 0 10px rgba(255,127,80,0.25);
}

.password-box {
  position: relative;
}

.password-box input {
  padding-right: 45px;
}

.eye {
  position: absolute;
  right: 15px;
  top: 50%;
  transform: translateY(-50%);
  cursor: pointer;
}

.registro-link {
  margin-top: 18px;
  color: #666;
  font-size: 14px;
}

.registro-link a {
  color: #ff7f50;
  font-weight: bold;
  text-decoration: none;
}

.registro-link a:hover {
  text-decoration: underline;
}

.mensaje {
  margin-top: 10px;
  font-size: 14px;
  color: #d9534f;
}

/* botones más “app real” */
.actions {
  display: flex;
  flex-direction: column;
  gap: 14px;
}

.btn {
  padding: 14px;
  border-radius: 35px;
  font-weight: bold;
  cursor: pointer;
  transition: 0.3s ease;
  font-size: 15px;
  min-width: 240px;

  border: 2px solid #000;
}

/* crear cuenta */
.primary {
  background: linear-gradient(135deg, #ffa07a, #ff7f50);
  color: white;
}

/* login */
.secondary {
  background: linear-gradient(135deg, #ffe0b2, #ffd1a1);
  color: #333;
}

/* hover más pro */
.btn:hover {
  transform: translateY(-4px);
  box-shadow: 0 15px 30px rgba(0,0,0,0.18);
}

/* footer */
.footer {
  position: absolute;
  bottom: 15px;
  font-size: 12px;
  color: #666;
}

/* animación */
@keyframes float {
  0% { transform: translateY(0); }
  50% { transform: translateY(-12px); }
  100% { transform: translateY(0); }
}

</style>
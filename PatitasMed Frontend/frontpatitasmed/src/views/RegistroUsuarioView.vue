<template>
  <div class="screen">

    <div class="background-decor"></div>

    <div class="content">

      <!-- LOGO -->
      <img src="@/assets/logo.png" class="logo" alt="PatitasMed" />

      <h2 class="title">Registro de Usuario</h2>
      <p class="subtitle">Crea tu cuenta para cuidar a tu mascota 🐾</p>

      <form class="form" @submit.prevent="registrarUsuario">

        <input v-model="usuario.nombre" placeholder="Nombre" required />

        <!-- SELECT TIPO DOCUMENTO -->
        <select v-model="usuario.tipoDocumento" required>
          <option disabled value="">Tipo de Documento</option>
          <option value="CC">CC - Cédula de Ciudadanía</option>
          <option value="TI">TI - Tarjeta de Identidad</option>
          <option value="PAS">PAS - Pasaporte</option>
          <option value="CE">CE - Cédula de Extranjería</option>
          <option value="PEP">PEP - Permiso Especial de Permanencia</option>
          <option value="OTR">OTR - Otro</option>
        </select>

        <input v-model="usuario.numeroDocumento" placeholder="Número Documento" required />
        <input v-model="usuario.telefono" placeholder="Teléfono" required />
        <input v-model="usuario.email" placeholder="Email" required />
        <input v-model="usuario.username" placeholder="Usuario" required />

        <!-- PASSWORD -->
        <div class="password-box">
          <input
            :type="mostrarPassword ? 'text' : 'password'"
            v-model="usuario.password"
            placeholder="Contraseña"
            required
          />
          <span class="eye" @click="mostrarPassword = !mostrarPassword">
            {{ mostrarPassword ? '🙈' : '👁️' }}
          </span>
        </div>

        <!-- CONFIRM PASSWORD -->
        <div class="password-box">
          <input
            :type="mostrarConfirm ? 'text' : 'password'"
            v-model="confirmPassword"
            placeholder="Confirmar contraseña"
            required
          />
          <span class="eye" @click="mostrarConfirm = !mostrarConfirm">
            {{ mostrarConfirm ? '🙈' : '👁️' }}
          </span>
        </div>

        <button type="submit">🐾 Registrarse</button>

      </form>

      <p class="mensaje">{{ mensaje }}</p>

    </div>

  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "RegistroView",

  data() {
    return {
      usuario: {
        nombre: "",
        tipoDocumento: "",
        numeroDocumento: "",
        telefono: "",
        email: "",
        username: "",
        password: ""
      },

      confirmPassword: "",
      mostrarPassword: false,
      mostrarConfirm: false,
      mensaje: ""
    };
  },

  methods: {
    async registrarUsuario() {

      if (this.usuario.password !== this.confirmPassword) {
        this.mensaje = "Las contraseñas no coinciden ❌";
        return;
      }

      try {
        await axios.post(
          "http://localhost:8080/api/usuarios",
          this.usuario
        );

        this.mensaje = "Usuario registrado correctamente 🐾";

        this.usuario = {
          nombre: "",
          tipoDocumento: "",
          numeroDocumento: "",
          telefono: "",
          email: "",
          username: "",
          password: ""
        };

        this.confirmPassword = "";

        setTimeout(() => {
          this.$router.push("/");
        }, 1500);

      } catch (error) {
         this.mensaje = error.response?.data || "Error al registrar usuario ❌";
      }
    }
  }
};
</script>

<style scoped>
.screen {
  min-height: 100vh; /* 🔥 cambio importante */
  display: flex;
  justify-content: center;
  align-items: flex-start; /* 🔥 evita que se suba o corte */
  position: relative;
  overflow-y: auto;
  padding: 40px 0; /* espacio arriba y abajo */
  background: linear-gradient(135deg, #fff7f0, #ffe0d2, #fffaf6);
  font-family: Arial, sans-serif;
}

/* fondo decorativo */
.background-decor::before,
.background-decor::after {
  content: "";
  position: absolute;
  border-radius: 50%;
  filter: blur(70px);
  opacity: 0.35;
}

.background-decor::before {
  width: 450px;
  height: 450px;
  background: #ffa07a;
  top: -120px;
  left: -120px;
}

.background-decor::after {
  width: 420px;
  height: 420px;
  background: #ffd1a1;
  bottom: -120px;
  right: -120px;
}

/* contenido */
.content {
  text-align: center;
  z-index: 2;
  width: 100%;
  max-width: 420px;
  padding: 20px;
}

/* logo */
.logo {
  width: 220px;
  margin-bottom: 10px;
  animation: float 4s ease-in-out infinite;
  filter: drop-shadow(0 10px 20px rgba(0,0,0,0.15));
  display: block;
  margin: 0 auto;
}

/* títulos */
.title {
  font-size: 24px;
  font-weight: bold;
  color: #444;
}

.subtitle {
  font-size: 14px;
  color: #ff7f50;
  margin-bottom: 20px;
}

/* form */
.form {
  display: flex;
  flex-direction: column;
  gap: 12px;

  width: 300px;
  margin: 0 auto;
}

/* inputs + select */
input, select {
  padding: 12px;
  border-radius: 12px;
  border: 1px solid #ddd;
  outline: none;
  transition: 0.3s;
  background: white;
}

input:focus, select:focus {
  border-color: #ff7f50;
  box-shadow: 0 0 8px rgba(255,127,80,0.3);
}

/* password */
.password-box {
  position: relative;
  display: flex;
  align-items: center;
}

.password-box input {
  width: 100%;
  padding-right: 40px;
}

.eye {
  position: absolute;
  right: 10px;
  cursor: pointer;
  font-size: 18px;
}

/* botón */
button {
  padding: 12px;
  border-radius: 30px;
  border: 2px solid #000;
  background: linear-gradient(135deg, #ffa07a, #ff7f50);
  color: white;
  font-weight: bold;
  cursor: pointer;
  transition: 0.3s;
}

button:hover {
  transform: translateY(-3px);
  box-shadow: 0 10px 20px rgba(0,0,0,0.15);
}

/* mensaje */
.mensaje {
  margin-top: 10px;
  font-size: 13px;
  color: #555;
}

/* animación */
@keyframes float {
  0% { transform: translateY(0); }
  50% { transform: translateY(-10px); }
  100% { transform: translateY(0); }
}
</style>
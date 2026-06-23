<template>

  <div class="screen">

    <div class="background-decor"></div>

    <div class="content">

      <!-- CABECERA -->
      <div class="header">

        <img
          src="@/assets/logo.png"
          alt="PatitasMed"
          class="logo"
        >

        <h1>Mis Mascotas 🐾</h1>

      </div>

      <!-- BOTON AGREGAR -->
      <div class="top-actions">

        <button
          class="btn agregar"
          @click="abrirModalAgregar"
        >
          ➕ Agregar Mascota
        </button>

      </div>

      <!-- TARJETAS -->
      <div
        class="mascota-card"
        v-for="mascota in mascotas"
        :key="mascota.idMascota"
      >

        <div class="foto">

          <img
            src="https://placehold.co/180x180"
            alt="Mascota"
          >

        </div>

        <div class="info">

          <p><strong>Nombre:</strong> {{ mascota.nombre }}</p>
          <p><strong>Especie:</strong> {{ mascota.especie }}</p>
          <p><strong>Raza:</strong> {{ mascota.raza }}</p>
          <p><strong>Sexo:</strong> {{ mascota.sexo }}</p>
          <p><strong>Nacimiento:</strong> {{ mascota.fechaNacimiento }}</p>

        </div>

        <div class="acciones">

          <button
            class="btn editar"
            @click="abrirModal(mascota)"
          >
            ✏️ Modificar
          </button>

          <button
            class="btn historia"
          >
            📋 Historia Clínica
          </button>

          <button
            class="btn citas"
            @click="verCitasMascota(mascota)"
          >
            📅 Ver Citas
          </button>

          <button
            class="btn eliminar"
            @click="eliminarMascota(
              mascota.idMascota,
              mascota.nombre
            )"
          >
            🗑️ Eliminar
          </button>

        </div>

      </div>

      <div
        v-if="mostrarModalAgregar"
        class="modal-overlay"
      >

        <div class="modal">

          <h2>➕ Agregar Mascota</h2>

          <div class="campo">
            <label>Nombre</label>
            <input v-model="nuevaMascota.nombre">
          </div>

          <div class="campo">
            <label>Especie</label>
            <select v-model="nuevaMascota.especie">
              <option value="Gato">Gato</option>
              <option value="Hembra">Perro</option>
            </select>
          </div>

          <div class="campo">
            <label>Raza</label>
            <input v-model="nuevaMascota.raza">
          </div>

          <div class="campo">
            <label>Sexo</label>
            <select v-model="nuevaMascota.sexo">
              <option value="Macho">Macho</option>
              <option value="Hembra">Hembra</option>
            </select>
          </div>

          <div class="campo">
            <label>Fecha de Nacimiento</label>
            <input
              type="date"
              v-model="nuevaMascota.fechaNacimiento"
              :max="fechaMaxima"
            >
          </div>

          <div class="modal-buttons">

            <button
              class="btn editar"
              @click="guardarNuevaMascota"
            >
              💾 Guardar
            </button>

            <button
              class="btn eliminar"
              @click="cancelarAgregarMascota"
            >
              ❌ Cancelar
            </button>

          </div>

        </div>

      </div>

      <div
        v-if="mostrarModal"
        class="modal-overlay"
      >

        <div class="modal">

          <h2>✏️ Modificar Mascota</h2>

          <div class="campo">
            <label>Nombre</label>
            <input
              v-model="mascotaEditar.nombre"
            >
          </div>

          <div class="campo">
            <label>Especie</label>
            <select v-model="mascotaEditar.especie">
              <option value="Gato">Gato</option>
              <option value="Hembra">Perro</option>
            </select>
          </div>

          <div class="campo">
            <label>Raza</label>
            <input
              v-model="mascotaEditar.raza"
            >
          </div>

          <div class="campo">
            <label>Sexo</label>
            <select v-model="mascotaEditar.sexo">
              <option value="Macho">Macho</option>
              <option value="Hembra">Hembra</option>
            </select>
          </div>

          <div class="campo">
            <label>Fecha de Nacimiento</label>
            <input
              type="date"
              v-model="mascotaEditar.fechaNacimiento"
              :max="fechaMaxima"
            >
          </div>

          <div class="modal-buttons">

            <button
              type="button"
              class="btn editar"
              @click="guardarCambios"
            >
              💾 Guardar
            </button>

            <button
              type="button"
              class="btn eliminar"
              @click="cancelarModificacion"
            >
              ❌ Cancelar
            </button>

          </div>

        </div>

      </div>

      <div
        v-if="mostrarModalCitas"
        class="modal-overlay"
      >

        <div class="modal">

          <h2>
            📅 Citas de {{ mascotaSeleccionada.nombre }}
          </h2>

          <div
            v-for="cita in citasMascota"
            :key="cita.idCita"
            class="cita-item"
          >

            <p>
              <strong>Fecha:</strong>
              {{ cita.fechaCita }}
            </p>

            <p>
              <strong>Clínica:</strong>
              {{ cita.nombreClinica }}
            </p>

            <p>
              <strong>Veterinario:</strong>
              {{ cita.nombreVeterinario }}
            </p>

            <p>
              <strong>Estado:</strong>
              {{ cita.estado }}
            </p>

            <hr>

          </div>

          <div class="modal-buttons">

            <button
              class="btn editar"
              @click="mostrarModalCitas = false"
            >
              OK
            </button>

          </div>

        </div>

      </div>

    </div>

  </div>

</template>

<script>

import axios from 'axios'

export default {

  name: 'MascotasView',

  data() {

    return {

      mascotas: [],
      usuario: {},

      mostrarModal: false,

      mostrarModalAgregar: false,

      nuevaMascota: {
        nombre: '',
        especie: '',
        raza: '',
        sexo: '',
        fechaNacimiento: '',
        
        usuario: {
          idUsuario: null
        }

      },

      mascotaEditar: {
        idMascota: null,
        nombre: '',
        especie: '',
        raza: '',
        sexo: '',
        fechaNacimiento: ''
      },

      mostrarModalCitas: false,

      citasMascota: [],

      mascotaSeleccionada: null,

      fechaMaxima: new Date()
      .toISOString()
      .split("T")[0]
      
    }

  },

  mounted() {

    this.usuario =
        JSON.parse(localStorage.getItem("usuario"))

    this.cargarMascotas()

  },

  methods: {

  async cargarMascotas() {

  const respuesta =
    await axios.get(
      `http://localhost:8080/api/mascotas/usuario/${this.usuario.idUsuario}`
    )

  this.mascotas = respuesta.data

  },

  abrirModalAgregar() {

  this.nuevaMascota = {

    nombre: '',
    especie: '',
    raza: '',
    sexo: '',
    fechaNacimiento: '',

    usuario: {
      idUsuario: this.usuario.idUsuario
    }

  }

  this.mostrarModalAgregar = true

 },

  async guardarNuevaMascota() {

  if (this.nuevaMascota.fechaNacimiento > this.fechaMaxima) {

      alert("La fecha de nacimiento no puede ser futura.")

      return

  }

  const confirmar = confirm(
    `¿Deseas registrar a ${this.nuevaMascota.nombre}?`
  )

  if (!confirmar) {
    return
  }

  await axios.post(
    'http://localhost:8080/api/mascotas',
    this.nuevaMascota
  )

  alert(
    `${this.nuevaMascota.nombre} fue registrada correctamente 🐾`
  )

  this.mostrarModalAgregar = false

  this.cargarMascotas()

 },

 cancelarAgregarMascota() {

  const confirmar = confirm('¿Deseas cancelar el registro de la mascota?')

  if (confirmar) {
    this.mostrarModalAgregar = false
  }

 },

  abrirModal(mascota) {

    this.mascotaEditar = {...mascota}

    this.mostrarModal = true

  },

  async guardarCambios() {

  if (this.nuevaMascota.fechaNacimiento > this.fechaMaxima) {

      alert("La fecha de nacimiento no puede ser futura.")

      return
      
  }

    const confirmar = confirm(
      `¿Deseas guardar los cambios de ${this.mascotaEditar.nombre}?`
    )

    if (!confirmar) {
      return
    }

    await axios.put(
      `http://localhost:8080/api/mascotas/actualizar/${this.mascotaEditar.idMascota}`,
      this.mascotaEditar
    )

    alert("Mascota actualizada correctamente 🐾")

    this.mostrarModal = false

    this.cargarMascotas()

  },

  cancelarModificacion() {

    const confirmar = confirm(
      "¿Deseas cancelar la modificación de la mascota? Los cambios no guardados se perderán."
    )

    if (confirmar) {
      this.mostrarModal = false
    }

  },

  async verCitasMascota(mascota) {

    const respuesta = await axios.get(
      `http://localhost:8080/api/citas/mascota/${mascota.idMascota}`
    )

    if (respuesta.data.length === 0) {

      alert(
        `${mascota.nombre} no tiene citas agendadas 📅`
      )

      return
    }

    this.citasMascota = respuesta.data

    this.mascotaSeleccionada = mascota

    this.mostrarModalCitas = true

  },

  async eliminarMascota(id, nombre) {

      const confirmar = confirm(
        `¿Deseas eliminar a ${nombre}?`
      )

      if (!confirmar) {
        return
      }

      await axios.delete(
        `http://localhost:8080/api/mascotas/${id}`
      )

      alert(`${nombre} fue eliminada correctamente 🐾`)

      this.cargarMascotas()

   }

  }

}
</script>


<style scoped>

.screen {
  min-height: 100vh;
  position: relative;
  overflow-y: auto;
  padding: 30px;
  font-family: Arial, sans-serif;
  background: linear-gradient(
    135deg,
    #fff7f0,
    #ffe0d2,
    #fffaf6
  );
}

/* Fondo decorativo igual al resto */
.background-decor::before,
.background-decor::after {
  content: "";
  position: fixed;
  border-radius: 50%;
  filter: blur(70px);
  opacity: 0.35;
  z-index: 0;
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

/* CONTENIDO */
.content {
  position: relative;
  z-index: 2;
  max-width: 1200px;
  margin: auto;
}

/* CABECERA */
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 35px;
}

.logo {
  width: 260px;
  animation: float 4s ease-in-out infinite;
  filter: drop-shadow(
    0 10px 20px rgba(0,0,0,0.15)
  );
}

.header h1 {
  color: #444;
  font-size: 38px;
}

/* BOTON SUPERIOR */
.top-actions {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 25px;
}

/* TARJETA */
.mascota-card {
  display: flex;
  align-items: center;
  justify-content: space-between;

  background: rgba(255,255,255,0.95);

  border: 2px solid #12b7d6;
  border-radius: 18px;

  padding: 20px;

  margin-bottom: 25px;

  box-shadow:
    0 10px 25px rgba(0,0,0,0.08);

  transition: 0.3s;
}

.mascota-card:hover {
  transform: translateY(-3px);
}

/* FOTO */
.foto img {
  width: 200px;
  height: 200px;
  object-fit: cover;
  border-radius: 16px;
}

/* INFO */
.info {
  flex: 1;
  padding: 0 35px;
}

.info p {
  margin: 10px 0;
  font-size: 20px;
  color: #15428b;
}

.info strong {
  color: #0e3978;
}

/* BOTONES DERECHA */
.acciones {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

/* BOTONES GENERALES */
.btn {
  min-width: 210px;
  padding: 14px;

  border-radius: 14px;
  border: 2px solid #000;

  font-size: 14px;
  font-weight: bold;

  cursor: pointer;
  transition: 0.3s;
}

.btn:hover {
  transform: translateY(-3px);
  box-shadow:
    0 10px 20px rgba(0,0,0,0.15);
}

/* COLORES */
.agregar {
  background: linear-gradient(
    135deg,
    #ffa07a,
    #ff7f50
  );
  color: white;
}

.editar {
  background: linear-gradient(
    135deg,
    #11b4cc,
    #0da0b5
  );
  color: white;
}

.historia {
  background: linear-gradient(
    135deg,
    #8fd0bf,
    #7dc5b1
  );
  color: #222;
}

.eliminar {
  background: linear-gradient(
    135deg,
    #f2d0a3,
    #e8c08b
  );
  color: #222;
}

/* RESPONSIVE */
@media (max-width: 900px) {

  .mascota-card {
    flex-direction: column;
    text-align: center;
  }

  .info {
    padding: 20px 0;
  }

  .acciones {
    width: 100%;
  }

  .btn {
    width: 100%;
  }

}

/* Animación logo */
@keyframes float {

  0% {
    transform: translateY(0);
  }

  50% {
    transform: translateY(-12px);
  }

  100% {
    transform: translateY(0);
  }

}

.modal-overlay {

  position: fixed;
  inset: 0;

  background: rgba(0,0,0,.5);

  display: flex;
  justify-content: center;
  align-items: center;

  z-index: 999;
}

.modal {

  width: 450px;

  background: white;

  border-radius: 20px;

  padding: 25px;

  box-shadow:
    0 15px 30px rgba(0,0,0,.2);

  display: flex;
  flex-direction: column;
  gap: 12px;
}

.modal h2 {

  text-align: center;
  color: #444;
}

.modal input,
.modal select {

  padding: 12px;

  border: 2px solid #ffd1a1;

  border-radius: 10px;
}

.modal-buttons {

  display: flex;
  gap: 10px;
  justify-content: center;

  margin-top: 10px;
}

.campo{
  display:flex;
  flex-direction:column;
  text-align:left;
}

.campo label{
  margin-bottom:6px;
  font-weight:bold;
  color:#444;
  font-size:14px;
}

.modal input,
.modal select{
  width:100%;
  box-sizing:border-box;
  padding:12px;
  border:2px solid #ffd1a1;
  border-radius:10px;
  outline:none;
}

.modal input:focus,
.modal select:focus{
  border-color:#ff7f50;
  box-shadow:0 0 8px rgba(255,127,80,.3);
}

.cita-item{
  padding:10px 0;
}

.cita-item p{
  margin:5px 0;
}

.cita-item strong{
  color:#ff7f50;
}

</style>
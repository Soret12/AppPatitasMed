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

        <h1>Citas Médicas 📅</h1>

      </div>

      <!-- BOTÓN AGREGAR -->
      <div class="top-actions">

        <button
          class="btn agregar"
          @click="mostrarModalCita = true"
        >
          ➕ Agendar Cita
        </button>

      </div>

      <!-- TARJETAS -->
      <div
        v-for="cita in CitasMedicas"
        :key="cita.idCita"
        class="cita-card"
        :class="{
          programada: cita.estado === 'Programada',
          cancelada: cita.estado === 'Cancelada',
          completada: cita.estado === 'Completada',
          noasistio: cita.estado === 'No Asistió'
        }"
      >

        <div class="info">

          <p>
            <strong>Clínica:</strong>
            {{ cita.nombreClinica }}
          </p>

          <p>
            <strong>Veterinario:</strong>
            {{ cita.nombreVeterinario }}
          </p>

          <p>
            <strong>Fecha:</strong>
            {{ cita.fechaCita }}
          </p>

          <p>
            <strong>Estado:</strong>
            {{ cita.estado }}
          </p>

          <p>
            <strong>Mascota:</strong>
            {{ cita.mascota.nombre }}
          </p>

        </div>

        <div class="acciones">

          <button
            class="btn editar"
            @click="abrirModalEditar(cita)"
            :disabled="cita.estado !== 'Programada'"
          >
            ✏️ Modificar
          </button>

          <button
            class="btn eliminar"
            @click="cancelarCitaMedica(cita)"
            :disabled="cita.estado !== 'Programada'"
          >
            ❌ Cancelar
          </button>

        </div>


      </div>

      <div
        v-if="mostrarModalCita"
        class="modal-overlay"
      >

        <div class="modal">

          <h2>📅 Agendar Cita</h2>

          <div class="campo">
            <label>Clínica Veterinaria</label>

            <select v-model="nuevaCita.nombreClinica"  @change="nuevaCita.nombreVeterinario = ''">

              <option value="">
                Seleccione una clínica
              </option>

              <option
                v-for="clinica in clinicas"
                :key="clinica"
                :value="clinica"
              >
                {{ clinica }}
              </option>

            </select>

          </div>

          <div class="campo">

              <label>Veterinario</label>

              <select
                v-model="nuevaCita.nombreVeterinario"
              >

                <option value="">
                  Seleccione un veterinario
                </option>

                <option
                  v-for="vet in veterinariosPorClinica[nuevaCita.nombreClinica]"
                  :key="vet"
                  :value="vet"
                >
                  {{ vet }}
                </option>

              </select>

          </div>

          <div class="campo">

            <label>Fecha de la Cita</label>

            <input
              type="date"
              v-model="nuevaCita.fechaCita"
              :min="fechaMinima"
            >

          </div>

          <div class="campo">

            <label>Mascota</label>

            <select
              v-model="nuevaCita.mascota.idMascota"
            >

              <option value="">
                Seleccione una mascota
              </option>

              <option
                v-for="mascota in mascotas"
                :key="mascota.idMascota"
                :value="mascota.idMascota"
              >
                {{ mascota.nombre }}
              </option>

            </select>

            <div class="campo">

                <label>Observaciones</label>

                <textarea
                  v-model="nuevaCita.observaciones"
                ></textarea>

           </div>

          </div>

          <div class="modal-buttons">

            <button
              class="btn agregar"
              @click="guardarCita"
            >
              💾 Guardar
            </button>

            <button
              class="btn eliminar"
              @click="cancelarCita"
            >
              ❌ Cancelar
            </button>

          </div>

        </div>

      </div>
      <div
        v-if="mostrarModalEditar"
        class="modal-overlay"
      >

        <div class="modal">

          <h2>✏️ Modificar Cita</h2>

          <div class="campo">

            <label>Clínica</label>

            <select
              v-model="citaEditar.nombreClinica"
              @change="citaEditar.nombreVeterinario=''"
            >

              <option
                v-for="clinica in clinicas"
                :key="clinica"
                :value="clinica"
              >
                {{ clinica }}
              </option>

            </select>

          </div>

          <div class="campo">

            <label>Veterinario</label>

            <select
              v-model="citaEditar.nombreVeterinario"
            >

              <option
                v-for="vet in veterinariosPorClinica[citaEditar.nombreClinica]"
                :key="vet"
                :value="vet"
              >
                {{ vet }}
              </option>

            </select>

          </div>

          <div class="campo">

            <label>Fecha</label>

            <input
              type="date"
              v-model="citaEditar.fechaCita"
              :min="fechaMinima"
            >

          </div>

          <div class="campo">

            <label>Mascota</label>

            <select
              v-model="citaEditar.mascota.idMascota"
            >

              <option
                v-for="mascota in mascotas"
                :key="mascota.idMascota"
                :value="mascota.idMascota"
              >
                {{ mascota.nombre }}
              </option>

            </select>

          </div>

          <div class="campo">

            <label>Observaciones</label>

            <textarea
              v-model="citaEditar.observaciones"
            ></textarea>

          </div>

          <div class="modal-buttons">

            <button
              class="btn editar"
              @click="guardarCambiosCita"
            >
              💾 Guardar
            </button>

            <button
              class="btn eliminar"
              @click="cancelarModificacion"
            >
              ❌ Cancelar
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

  name: 'CitasMedicasView',

  data() {

    return {

      CitasMedicas: [],
      usuario: {},

      mostrarModalCita: false,

      mascotas: [],

      nuevaCita: {
        nombreClinica: "",
        nombreVeterinario: "",
        fechaCita: "",
        estado: "Programada",
        mascota: {
          idMascota: null
        }
      },

      clinicas: [

        "Patitas Felices",
        "Animal Care",
        "VetSalud",
        "Mundo Animal",
        "Veterinaria Central",
        "Pet Life",
        "Amigos Peludos",
        "Huellitas Vet",
        "Dog & Cat Center",
        "Veterinaria San Francisco"

      ],

      veterinariosPorClinica: {

        "Patitas Felices": [
          "Dra. Laura Gómez",
          "Dr. Carlos Rojas"
        ],

        "Animal Care": [
          "Dra. Natalia Pérez",
          "Dr. Andrés Díaz"
        ],

        "VetSalud": [
          "Dra. Juliana Torres",
          "Dr. Felipe Castro"
        ],

        "Mundo Animal": [
          "Dra. Mariana Ruiz",
          "Dr. Sebastián López"
        ],

        "Veterinaria Central": [
          "Dra. Valentina Herrera",
          "Dr. Juan Camilo Vega"
        ],

        "Pet Life": [
          "Dra. Paula Martínez",
          "Dr. Ricardo Muñoz"
        ],

        "Amigos Peludos": [
          "Dra. Daniela Silva",
          "Dr. Camilo Sánchez"
        ],

        "Huellitas Vet": [
          "Dra. Karen Moreno",
          "Dr. David Giraldo"
        ],

        "Dog & Cat Center": [
          "Dra. Sofía Ramírez",
          "Dr. Esteban Torres"
        ],

        "Veterinaria San Francisco": [
          "Dra. Lina Rodríguez",
          "Dr. Miguel Vargas"
        ]

      },

      mostrarModalEditar: false,

      citaEditar: {
          idCita: null,
          nombreClinica: "",
          nombreVeterinario: "",
          fechaCita: "",
          observaciones: "",
          estado: "",
          mascota: {
            idMascota: null
          }
      },

      fechaMinima: new Date()
      .toISOString()
      .split("T")[0]

    
    }

  },

  mounted() {

      this.usuario = JSON.parse(localStorage.getItem("usuario"))

      this.cargarMascotas()
      
      this.cargarCitasMedicas()

  },

  methods: {

    async cargarMascotas() {

      const respuesta = await axios.get(
        `http://localhost:8080/api/mascotas/usuario/${this.usuario.idUsuario}`
      )

      this.mascotas = respuesta.data

    },

    async cargarCitasMedicas() {

    const respuesta = await axios.get(
      `http://localhost:8080/api/citas/usuario/${this.usuario.idUsuario}`)

    this.CitasMedicas = respuesta.data

    },

    async guardarCita() {

      if (this.nuevaCita.fechaCita < this.fechaMinima) {

          alert("No puedes agendar citas en fechas pasadas.")

          return

      }

      const confirmar = confirm(
        "¿Deseas registrar esta cita médica?"
      )

      if (!confirmar) {
        return
      }

      await axios.post("http://localhost:8080/api/citas",this.nuevaCita)

      alert("Cita registrada correctamente 📅")

      this.mostrarModalCita = false

      this.nuevaCita = {
        nombreClinica: "",
        nombreVeterinario: "",
        fechaCita: "",
        estado: "Programada",
        mascota: {
          idMascota: null
        }
      }

      this.cargarCitasMedicas()

    },

    abrirModalEditar(cita) {

        if (cita.estado !== "Programada") {
          alert("Esta cita ya no puede modificarse.")
          return
        }

      this.citaEditar = JSON.parse(JSON.stringify(cita))

      this.mostrarModalEditar = true

    },

    async guardarCambiosCita() {

      if (this.nuevaCita.fechaCita < this.fechaMinima) {

          alert("No puedes agendar citas en fechas pasadas.")

          return

      }

      const confirmar = confirm(
        "¿Deseas guardar los cambios?"
      )

      if (!confirmar) {
        return
      }

      await axios.put(`http://localhost:8080/api/citas/actualizar/${this.citaEditar.idCita}`,this.citaEditar
      )

      alert(
        "Cita actualizada correctamente 📅"
      )

      this.mostrarModalEditar = false

      this.cargarCitasMedicas()

    },

    cancelarModificacion() {

      const confirmar = confirm(
        "¿Deseas cerrar sin guardar?"
      )

      if(confirmar){
        this.mostrarModalEditar = false
      }

    },

    cancelarCita() {

      const confirmar = confirm(
        "¿Deseas cancelar el registro de la cita?"
      )

      if (confirmar) {
        this.mostrarModalCita = false
      }

    },

    async cancelarCitaMedica(cita) {

        if (cita.estado !== "Programada") {

          alert("Esta cita ya no puede cancelarse.")

          return
        }

        const confirmar = confirm(
          `¿Deseas cancelar la cita de ${cita.mascota.nombre}?`
        )

        if (!confirmar) {
          return
        }

        cita.estado = "Cancelada"

        await axios.put(`http://localhost:8080/api/citas/actualizar/${cita.idCita}`,cita)

        alert("Cita cancelada correctamente 📅")

        this.cargarCitasMedicas()

      }

  }

}

</script>

<style>

.screen {
  min-height: 100vh;
  padding: 30px;
  font-family: Arial, sans-serif;
  background: linear-gradient(
    135deg,
    #fff7f0,
    #ffe0d2,
    #fffaf6
  );
}

.content{
  max-width:1200px;
  margin:auto;
  position:relative;
  z-index:2;
}

.header{
  display:flex;
  justify-content:space-between;
  align-items:center;
  margin-bottom:35px;
}

.logo{
  width:260px;
}

.header h1{
  color:#444;
  font-size:38px;
}

.top-actions{
  display:flex;
  justify-content:flex-end;
  margin-bottom:25px;
}

.cita-card{

  display:flex;
  justify-content:space-between;
  align-items:center;

  background:rgba(255,255,255,.95);

  border:2px solid #ffb28f;

  border-radius:18px;

  padding:25px;

  margin-bottom:20px;

  box-shadow:
    0 10px 25px rgba(0,0,0,.08);
}

.info{
  flex:1;
}

.info p{
  margin:10px 0;
  font-size:18px;
  color:#444;
}

.info strong{
  color:#ff7f50;
}

.acciones{
  display:flex;
  flex-direction:column;
  gap:12px;
}

.btn{
  min-width:200px;
  padding:14px;

  border-radius:14px;
  border:2px solid #000;

  font-weight:bold;
  cursor:pointer;

  transition:.3s;
}

.btn:hover{
  transform:translateY(-3px);
}

.agregar{
  background:linear-gradient(
    135deg,
    #ffa07a,
    #ff7f50
  );
  color:white;
}

.editar{
  background:linear-gradient(
    135deg,
    #11b4cc,
    #0da0b5
  );
  color:white;
}

.eliminar{
  background:linear-gradient(
    135deg,
    #f2d0a3,
    #e8c08b
  );
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
  box-shadow: 0 15px 30px rgba(0,0,0,.2);
}

.campo{
  display:flex;
  flex-direction:column;
  text-align:left;
  margin-bottom:12px;
}

.campo label{
  font-weight:bold;
  margin-bottom:6px;
}

.modal input,
.modal select{
  width:100%;
  padding:12px;
  border:2px solid #ffd1a1;
  border-radius:10px;
  box-sizing:border-box;
}

.modal textarea{
  width:100%;
  min-height:90px;
  padding:12px;
  border:2px solid #ffd1a1;
  border-radius:10px;
  resize:none;
  box-sizing:border-box;
}

.modal-buttons{
  display:flex;
  justify-content:center;
  gap:10px;
  margin-top:15px;
}

.programada{
  background:#fff3cd;
  border-color:#ffcc80;
}

.cancelada{
  background:#fde2e2;
  border-color:#f5a5a5;
}

.completada{
  background:#e3f9e5;
  border-color:#8fd19e;
}

.noasistio{
  background:#e9ecef;
  border-color:#adb5bd;
}

.btn:disabled{
  opacity: 0.5;
  cursor: not-allowed;
  transform: none;
}

</style>
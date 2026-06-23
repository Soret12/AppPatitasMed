<template>

    <div class="usuarios">

        <div class="components">
            <FormularioUsuarios ref="formularioUsuarios" @actualizar-tabla="actualizarTabla"/>
            <TablaUsuarios ref="tablaUsuarios" @seleccionar-usuario="seleccionarUsuario"/>
        </div>

    </div>

</template>

<script>

import FormularioUsuarios
from '@/components/FormularioUsuarios.vue';

import TablaUsuarios
from '@/components/TablaUsuarios.vue';

export default{

name: "UsuariosView",

    components:{

    FormularioUsuarios,
    TablaUsuarios

    },
    methods:{

        actualizarTabla(){

            this.$refs.tablaUsuarios.obtenerUsuarios();

        },

        seleccionarUsuario(usuario){

            this.$refs.formularioUsuarios.idUsuario = usuario.idUsuario;
            this.$refs.formularioUsuarios.tipoDocumento = usuario.tipoDocumento;
            this.$refs.formularioUsuarios.numeroDocumento =usuario.numeroDocumento;
            this.$refs.formularioUsuarios.nombre = usuario.nombre;
            this.$refs.formularioUsuarios.telefono = usuario.telefono;
            this.$refs.formularioUsuarios.email =usuario.email;
        }
    }

}

</script>

<style>

body { 
    font-family: Arial, sans-serif;
    background-color: #fff8f0;
    margin: 0;
    padding: 0;
    color: #000;
}

.components{

    display: grid;
    grid-template-columns: 1fr 2fr;
    grid-auto-rows: 1fr;
    gap: 15px;
    padding: 20px;

}

.container {

    margin: 1rem;
    padding: 1.5rem;
    background-color: #fffdf9;
    box-shadow: 0 0 10px rgba(210, 140, 70, 0.15);
    border-radius: 12px;
    border: 2px solid #f7d2a5;
}

h1 {

    text-align: center;
    margin-bottom: 20px;
    color: #e28b2d;
}

.filaSeleccionada{

    background-color: #ffd8a8;
    cursor: pointer;

}

tr{

    cursor: pointer;

}

.form-group {

    margin-bottom: 15px;
}

form{

    padding: 2%;
    
}

label {

    display: block;
    margin-bottom: 5px;
    font-weight: bold;
    color: #000;
}

input {

    width: 95%;
    padding: 8px;
    border: 1px solid #edc89a;
    border-radius: 5px;
    background-color: #fffaf5;
}

input:focus {

    outline: none;
    border-color: #e28b2d;
    box-shadow: 0 0 4px rgba(226, 139, 45, 0.3);
}

button {

    display: block;
    width: 70%;
    padding: 10px;
    background-color: #f7b267;
    color: black;
    border: none;
    border-radius: 6px;
    cursor: pointer;
    font-weight: bold;
    transition: 0.3s;
    margin: 10px auto;
}

button:hover {

    background-color: #ee9b4d;
}

table {

    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
    background-color: white;
}

th, td {

    padding: 10px;
    text-align: left;
    border-style: solid;
    border-width: 1px;
    border-color: #f7d2a5;
}

tr{

    border-style: solid;
    border-color: #f7d2a5;
}

th {

    background-color: #f7b267;
    color: black;
    border-style: solid;
}

tr:hover {

    background-color: #fff1df;
}

.filaSeleccionada:hover{

    background-color: #ffd8a8;

}

</style>
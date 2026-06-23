<template>

    <div class="container">

        <h1>Formulario Usuarios</h1>

        <form action="" id="usuario-form" @submit.prevent="guardar">

            <div class="form-group">
                <label for="idUsuario">ID usuario:</label>
                <input type="text" id="idUsuario" name="idUsuario" v-model="idUsuario">
            </div>

            <div class="form-group">
                <label for="tipoDocumento">Tipo Documento:</label>
                <input type="text" id="tipoDocumento" name="tipoDocumento" required v-model="tipoDocumento">
            </div>

            <div class="form-group">
                <label for="numeroDocumento">Número Documento:</label>
                <input type="text" id="numeroDocumento" name="numeroDocumento" required v-model="numeroDocumento">
            </div>

            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" required v-model="nombre">
            </div>

            <div class="form-group">
                <label for="telefono">Teléfono:</label>
                <input type="text" id="telefono" name="telefono" required v-model="telefono">
            </div>

            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required v-model="email">
            </div><br>

            <button type="submit">Guardar</button><br>
            <button type="button" @click="eliminarUsuario">Eliminar</button><br>
            <button type="button" @click="actualizarUsuario">Actualizar</button><br>
            <button type="button" @click="consultarPorId">Consultar</button>

        </form>
    
    </div>

</template>

<script>

import axios from 'axios'

export default {

    data(){

        return{

            idUsuario:"",
            tipoDocumento:"",
            numeroDocumento:"",
            nombre:"",
            telefono:"",
            email:""

        };


    },
    methods:{

        guardar(){

            axios.post("http://localhost:8080/api/usuarios",
            {   
                tipoDocumento:this.tipoDocumento,
                numeroDocumento:this.numeroDocumento,
                nombre:this.nombre,
                telefono:this.telefono,
                email:this.email

            })
            .then((response)=>{
                console.log("Usuario regisrtrado con éxito",response);
                alert("Usuario registrado");
                this.idUsuario=""
                this.tipoDocumento=""
                this.numeroDocumento=""
                this.nombre=""
                this.telefono=""
                this.email=""
                this.$emit("actualizar-tabla")

            })
            .catch((error)=>{
                console.error ("Error al registar usuario",error)
                alert("Registro de usuario fallido");
            });

        },
        consultarPorId(){
            axios.get("http://localhost:8080/api/usuarios/"+this.idUsuario)
            
            .then((response)=>{

                this.idUsuario= response.data.idUsuario;
                this.tipoDocumento= response.data.tipoDocumento;
                this.numeroDocumento=response.data.numeroDocumento;
                this.nombre= response.data.nombre;
                this.telefono = response.data.telefono;
                this.email = response.data.email;


            })
            .catch((error)=>{
                console.error ("Error al consular usuario",error)
                alert("Consulta de usuario fallida");
            });


        },
        actualizarUsuario(){

            axios.put("http://localhost:8080/api/usuarios/actualizar/"+this.idUsuario,{

                idUsuario:this.idUsuario,
                tipoDocumento:this.tipoDocumento,
                numeroDocumento:this.numeroDocumento,
                nombre:this.nombre,
                telefono:this.telefono,
                email:this.email

            })
            .then((response)=>{

                alert("Datos del usuario actualizados exitosamente",response.data);
                this.$emit("actualizar-tabla")


            })
            .catch((error)=>{

                alert("Actualización de datos del usuario fallida",error);
            });
        },
        eliminarUsuario(){

            axios.delete("http://localhost:8080/api/usuarios/"+this.idUsuario,{ 
            })
            .then((response)=>{

                alert("Usuario eliminado",response);
                this.idUsuario=""
                this.tipoDocumento=""
                this.numeroDocumento=""
                this.nombre=""
                this.telefono=""
                this.email=""
                this.$emit("actualizar-tabla")
            })
            .catch((error)=>{

                alert("Falló la eliminación del usuario",error);
            });
        }        
    },

};

</script>
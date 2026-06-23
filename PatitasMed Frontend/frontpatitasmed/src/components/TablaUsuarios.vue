<template>

<div class="container">

    <h1>Tabla Usuarios</h1>

    <table>
        <thead>
            <tr>
                <th>ID Usuario (Auto)</th>
                <th>Tipo Documento</th>
                <th>Número Documento</th>
                <th>Nombre</th>
                <th>Teléfono</th>
                <th>Email</th>
            </tr>
       </thead>

        <tbody>

            <tr v-for="usuario in usuarios" :key="usuario.idUsuario" @click="seleccionarUsuario(usuario)"
                :class="{
                    filaSeleccionada:
                    usuario.idUsuario === usuarioSeleccionado
                }">

                <td>{{usuario.idUsuario }}</td>
                <td>{{ usuario.tipoDocumento }}</td>
                <td>{{ usuario.numeroDocumento }}</td>
                <td>{{ usuario.nombre }}</td>
                <td>{{ usuario.telefono }}</td>
                <td>{{ usuario.email }}</td>
            </tr>

        </tbody>

    </table>

</div>

</template>
<script>

import axios from "axios";

export default {

data(){


    return{
    usuarios:[],
    usuarioSeleccionado: null    
        };
    },
    methods:{
        
        obtenerUsuarios(){

            axios.get("http://localhost:8080/api/usuarios/listar")

            .then((response)=>{

                this.usuarios = response.data;

            })
            .catch((error)=>{

                alert("No se puede mostrar la lista de usuarios",error)
            })
        },

        seleccionarUsuario(usuario){

            this.usuarioSeleccionado =
                    usuario.idUsuario;

            this.$emit(
                "seleccionar-usuario",
                usuario
            );

        }

    },
     mounted(){

        this.obtenerUsuarios();

    }

}

</script>
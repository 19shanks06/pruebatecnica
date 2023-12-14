import { Component, OnInit } from '@angular/core';
import { Usuario } from '../../core/models/Usuario';
import swal from 'sweetalert2';
import { AuthService } from '../../core/services/auth.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {

  titulo:String = "Por favor Sing Inicia Sesion"
  usuario:Usuario;
  constructor(private authService : AuthService,private router : Router) {
    this.usuario=new Usuario(); }

  ngOnInit(): void {

    if(this.authService.isAuthenticated()){
    swal("Login","Hola "+this.authService.usuario.username+" ya estas autenticado","info");
    this.router.navigate(["/clientes"]);

  }

  }

  login ():void{
  console.log(this.usuario);
   if(this.usuario.username == null || this.usuario.password==null){
  swal("Error Login","Usuario o contraseña vacias","error");
  return;
}

this.authService.login(this.usuario).subscribe(response=>{

  this.authService.guardarUsuario(response.access_token);
  this.authService.guardarToken(response.access_token);
  let usuario = this.authService.usuario;
  this.router.navigate(["entidades"]);
  swal("Login","Hola "+usuario.username+" iniciaste sesion con exito!",'success')
},err=>{
  if(err.status==400){
      swal("Error Login","Usuario o Contraseña incorrecta!","error");
  }
}


);
}

}

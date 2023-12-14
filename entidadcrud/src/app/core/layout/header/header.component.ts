import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth.service';
import swal from "sweetalert2";
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
 titulo:string = "Entidad CRUD"
  constructor(public authService : AuthService,private router: Router) { }

  ngOnInit(): void {
  }

  logout():void{
   this.authService.logout();
    swal("Logout","Sesion cerrada con exito","success");
    this.router.navigate(["/login"]);
}

}

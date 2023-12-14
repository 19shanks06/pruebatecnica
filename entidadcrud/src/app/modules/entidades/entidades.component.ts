import { Component, OnInit } from '@angular/core';
import { Entidad } from '../../core/models/entidad';

import { EntidadesService } from '../../core/services/entidades.service';
import swal from 'sweetalert2';
import { AuthService } from '../../core/services/auth.service';
@Component({
  selector: 'app-entidades',
  templateUrl: './entidades.component.html',
  styleUrls: ['./entidades.component.css']
})
export class EntidadesComponent implements OnInit {
  entidades : Entidad[];

  //inyectamos el SERVICIO ENTIDAD
  constructor(private entidadesService : EntidadesService,public authService : AuthService ) { }

  ngOnInit(): void {
   this.entidadesService.getEntidades().subscribe(

  entidades => this.entidades = entidades


   );
  }

  delete(entidad : Entidad):void{
    swal({
    title: 'Estas seguro de eliminar?',
    text: `Se procedera a eliminar a la entidad ${entidad.razon_social}`,
    type: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Si,eliminar',
    cancelButtonText:' No,cancelar',
    confirmButtonClass:'btn btn-success',
    cancelButtonClass:'btn btn-danger',
    buttonsStyling:false,
    reverseButtons: true
  }).then((result) => {
    if (result.value) {

      this.entidadesService.delete(entidad.id_entidad).subscribe(
          response =>{
        this.entidades = this.entidades.filter(ent => ent !== entidad)
            swal(
              'Eliminado con exito!',
              'La entidad fue eliminada',
              'success'
            )

          })


    }
  })
  }

}

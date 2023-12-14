import { Component, OnInit } from '@angular/core';

import { EntidadesService } from '../../../core/services/entidades.service';
import{Router,ActivatedRoute} from '@angular/router';
import swal from 'sweetalert2';
import { Entidad } from '../../../core/models/entidad';
import { TipoDocumento } from '../../../core/models/tipo_documento';
import { TipoContribuyente } from '../../../core/models/tipo_contribuyente';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

public entidad: Entidad = new Entidad()
documentos:TipoDocumento[];
contribuyentes:TipoContribuyente[];

public errores: string[];
public titulo:string = "Registrar Entidad"

  constructor(private entidadesService : EntidadesService,private router : Router
    ,private activateRoute:ActivatedRoute) { }

  ngOnInit() {
    this.cargarEntidad()


 this.entidadesService.getTipoDocumentos().subscribe(
   documentos=>this.documentos = documentos
 );

 this.entidadesService.getTipoContribuyentes().subscribe(
   contribuyentes=>this.contribuyentes = contribuyentes
 );
  }

cargarEntidad():void{
this.activateRoute.params.subscribe(params=>{
  let id_entidad = params ['id_entidad']
  if(id_entidad){
    this.entidadesService.getEntidad(id_entidad).subscribe(
      (entidad)=>this.entidad = entidad
    )
  }
}




)
}


  create(): void{
   this.entidadesService.create(this.entidad)
   .subscribe(entidad =>{

   this.router.navigate(['/entidades'])

     swal('Entidad Registrada',`La entidad ${entidad.razon_social} fue registrada con exito`,'success')
   },err=>{
     this.errores = err.error.errors as string[];
   }
    );
  }


  update():void{
    this.entidadesService.update(this.entidad).subscribe(
      entidad=>{
         this.router.navigate(['/entidades'])
         swal('Entidad Actualizada',`La entidad ${entidad.razon_social} fue actualizada con exito!`,'success')
      },
      err=>{
        this.errores = err.error.errors as string[];
      }
    )
  }


compararDocumento(o1:TipoDocumento , o2:TipoDocumento):boolean{

if(o1 === undefined && o2 === undefined){
  return true;
}

  return o1===null || o2 === null || o1===undefined || o2 === undefined? false: o1.id_tipo_documento === o2.id_tipo_documento;
}


compararContribuyente(o1:TipoContribuyente , o2:TipoContribuyente):boolean{

if(o1 === undefined && o2 === undefined){
  return true;
}

  return o1===null || o2 === null || o1===undefined || o2 === undefined? false: o1.id_tipo_contribuyente === o2.id_tipo_contribuyente;
}

}

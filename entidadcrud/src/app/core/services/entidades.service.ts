import { Injectable } from '@angular/core';
import { Observable, throwError} from 'rxjs';
import { Entidad } from '../models/entidad';
import{HttpClient, HttpHeaders} from '@angular/common/http';
import{map,catchError} from 'rxjs/operators';
import swal from 'sweetalert2';
import{Router} from '@angular/router';
import { TipoDocumento } from '../models/tipo_documento';
import { TipoContribuyente } from '../models/tipo_contribuyente';
import { AuthService } from './auth.service';



@Injectable({
  providedIn: 'root'
})
export class EntidadesService {

private apiURL:string = "http://localhost:8080/api/entidades";
private httpHeaders = new HttpHeaders({'Content-Type':'application/json'});

  constructor(private http : HttpClient,private router :Router, private authService: AuthService) { }

  private agregarAuthorizationHeader(){
    let token = this.authService.token;
    if(token != null){
      return this.httpHeaders.append("Authorization","Bearer " + token);
    }
    return this.httpHeaders;
  }

  private isNoAutorizado(e):boolean{
    if(e.status==401){
      this.router.navigate(['/login']);
      return true;
    }
    if(e.status==403){
          swal("Acceso denegado","Hola "+this.authService.usuario.username+" No tienes acceso a este recurso","warning")
      this.router.navigate(['/entidades']);
      return true;
    }
    return false;
  }

  getTipoContribuyentes():Observable<TipoContribuyente[]>{
  return this. http.get<TipoContribuyente[]>(this.apiURL+'/tipo_contribuyentes',{headers:this.agregarAuthorizationHeader()}).pipe(
    catchError(e=>{
      this.isNoAutorizado(e);
      return throwError(e);
    })
  );
  }

  getTipoDocumentos():Observable<TipoDocumento[]>{
  return this. http.get<TipoDocumento[]>(this.apiURL+'/tipo_documentos',{headers:this.agregarAuthorizationHeader()}).pipe(
    catchError(e=>{
      this.isNoAutorizado(e);
      return throwError(e);
    })
  );
  }

  getEntidades():Observable<Entidad[]>{
  return this. http.get<Entidad[]>(this.apiURL);
  }


  create(entidad:Entidad):Observable<Entidad>{
    return this.http.post(this.apiURL,entidad,{headers:this.agregarAuthorizationHeader()}).pipe(
      map((response:any)=>response.entidad as Entidad),
      catchError(e=>{

        if(this.isNoAutorizado(e)){
            return throwError(e);
        }

         if(e.status==400){
            return throwError(e);
         }

        swal(e.error.mensaje,e.error.error,"error");
        return throwError(e);
      })
    );
  }

  getEntidad(id_entidad):Observable<Entidad>{
    return this.http.get<Entidad>(`${this.apiURL}/${id_entidad}`,{headers:this.agregarAuthorizationHeader()}).pipe(
      catchError (e=> {
        if(this.isNoAutorizado(e)){
            return throwError(e);
        }


        this.router.navigate(['/entidades']);
        swal("Error al buscar la entidad",e.error.mensaje,"error");
        return throwError(e);
      })
    );

  }


  update(entidad:Entidad): Observable<any>{
    return this.http.put<any>(`${this.apiURL}/${entidad.id_entidad}`,entidad,{headers:this.agregarAuthorizationHeader()}).pipe(
        map((response:any)=>response.entidad as Entidad),
      catchError(e=>{

        if(this.isNoAutorizado(e)){
            return throwError(e);
        }


        if(e.status==400){
          return throwError(e);
        }


        swal("Error al editar la entidad",e.error.mensaje,"error");
        return throwError(e);
      })
    );
  }

  delete(id_entidad: number ):Observable<Entidad>{
     return this.http.delete<Entidad>(`${this.apiURL}/${id_entidad}`,{headers:this.agregarAuthorizationHeader()}).pipe(
       catchError(e=>{

         if(this.isNoAutorizado(e)){
             return throwError(e);
         }

         swal("Error al borrar la entidad",e.error.mensaje,"error");
         return throwError(e);
       })
     );
  }

}

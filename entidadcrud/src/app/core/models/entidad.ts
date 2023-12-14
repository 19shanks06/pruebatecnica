import { TipoContribuyente } from "./tipo_contribuyente";
import { TipoDocumento } from "./tipo_documento";


export class Entidad{
  id_entidad: number;
id_tipo_documento:TipoDocumento;
nro_documento:string;
razon_social:string ;
nombre_comercial:string;
id_tipo_contribuyente:TipoContribuyente;
direccion:string;
telefono:string;
estado:boolean;

}

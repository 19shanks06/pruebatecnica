import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EntidadesRoutingModule } from './entidades-routing.module';
import { EntidadesComponent } from './entidades.component';
import { FormComponent } from './components/form.component';
import {FormsModule} from "@angular/forms";



@NgModule({
  declarations: [
    EntidadesComponent,
    FormComponent
  ],
  imports: [
    CommonModule,
    EntidadesRoutingModule,
    FormsModule,

  ]
})
export class EntidadesModule { }

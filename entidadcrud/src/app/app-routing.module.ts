import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ReglasComponent } from './core/layout/reglas/reglas.component';
import { UserSessionGuard } from './core/guards/user-session.guard';

const routes: Routes = [
    {path:'',redirectTo: "/entidades",pathMatch:"full"},
    {path:'reglas',component:ReglasComponent},

   { path: 'entidades', loadChildren: () => import('./modules/entidades/entidades.module').then(m => m.EntidadesModule) },
    { path: 'login', loadChildren: () => import('./modules/auth/auth.module').then(m => m.AuthModule), canActivate:[UserSessionGuard] },

 ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

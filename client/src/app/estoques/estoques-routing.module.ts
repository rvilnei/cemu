import { AuthGuard } from './../core/auth/auth.guard';
import { EstoquesComponent } from './estoques.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const estoquesRoutes: Routes = [
  { path:'estoques', component: EstoquesComponent, canActivate: [AuthGuard]  }
];

@NgModule({
  imports: [RouterModule.forChild(estoquesRoutes)],
  exports: [RouterModule]
})
export class EstoquesRoutingModule { }

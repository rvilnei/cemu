import { PendenciaNewComponent } from './pendencia-new/pendencia-new.component';
import { PendenciasComponent } from './pendencias.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
{ path:'pendencias', component: PendenciasComponent },
{ path: 'pendenciaNew', component: PendenciaNewComponent  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PendenciasRoutingModule { }

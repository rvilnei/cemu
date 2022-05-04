import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RelatoriosComponent } from 'src/app/relatorios/relatorios/relatorios.component';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from 'src/app/core/auth/auth.guard';

const relatoriosRoutes: Routes = [
  { path:'relatorioGuia/:id', component: RelatoriosComponent, canActivate: [AuthGuard]  }
];

@NgModule({
  imports: [ RouterModule.forChild(relatoriosRoutes)],
  exports: [ RouterModule ]
})
export class RelatoriosRoutingModule { }

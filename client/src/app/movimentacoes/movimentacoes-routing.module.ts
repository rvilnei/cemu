import { SaidaNewComponent } from './saida/saida-new/saida-new.component';
import { MovimentacoesComponent } from './movimentacoes.component';
import { AuthGuard } from './../core/auth/auth.guard';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MovimentacaoDatailComponent } from 'src/app/movimentacoes/movimentacao-datail/movimentacao-datail.component';

const movimentacoesRoutes: Routes = [
  { path:'movimentacoes', component: MovimentacoesComponent, canActivate: [AuthGuard]  },
  { path:'movimentacoes/:unidadeId/:tipo', component: MovimentacoesComponent, canActivate: [AuthGuard]  },
  // { path:'movimentacoes/:unidadeId/:tipo', component: MovimentacoesComponent, canActivate: [AuthGuard]  },
  { path: 'movimentacao-new', component: SaidaNewComponent,  canActivate: [AuthGuard] },
  { path: 'movimentacaoDetail/:id', component: MovimentacaoDatailComponent,  canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(movimentacoesRoutes)],
  exports: [RouterModule]
})
export class MovimentacoesRoutingModule { }

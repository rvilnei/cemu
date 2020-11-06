import { NomeUnidadePipe } from './../shared/pipes/nome-unidade.pipe';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AppBootstrapModule } from './../app-bootstrap/app-bootstrap.module';
import { MovimentacoesRoutingModule } from './movimentacoes-routing.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MovimentacoesComponent } from './movimentacoes.component';
import { SaidaNewComponent } from './saida/saida-new/saida-new.component';
import { SaidaEstoqueComponent } from './saida/saida-estoque/saida-estoque.component';
import { NgScrollbarModule } from 'ngx-scrollbar';
import { EntradaNewComponent } from './entrada/entrada-new/entrada-new.component';
import { GuiasModule } from 'src/app/guias/guias.module';
import { MovimentacaoDatailComponent } from './movimentacao-datail/movimentacao-datail.component';

@NgModule({
  declarations: [MovimentacoesComponent, SaidaNewComponent, SaidaEstoqueComponent, NomeUnidadePipe, EntradaNewComponent, MovimentacaoDatailComponent],
  imports: [
    CommonModule,
    MovimentacoesRoutingModule,
    AppBootstrapModule,
    ReactiveFormsModule,
    NgScrollbarModule,
    FormsModule,
    GuiasModule
  ]
})
export class MovimentacoesModule { }

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RelatoriosComponent } from './relatorios/relatorios.component';
import { RelatoriosRoutingModule } from 'src/app/relatorios/relatorios-routing.module';



@NgModule({
  declarations: [RelatoriosComponent],
  imports: [
    RelatoriosRoutingModule,
    CommonModule
  ]
})
export class RelatoriosModule { }

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PendenciasRoutingModule } from './pendencias-routing.module';
import { PendenciaNewComponent } from './pendencia-new/pendencia-new.component';
import { PendenciasComponent } from './pendencias.component';


@NgModule({
  declarations: [PendenciaNewComponent, PendenciasComponent],
  imports: [
    CommonModule,
    PendenciasRoutingModule
  ]
})
export class PendenciasModule { }

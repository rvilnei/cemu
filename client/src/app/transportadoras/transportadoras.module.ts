import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TransportadorasComponent } from './transportadoras.component';
import { TransportadorasRoutingModule } from 'src/app/transportadoras/transportadoras-routing.module';
import { AppBootstrapModule } from 'src/app/app-bootstrap/app-bootstrap.module';
import { TransportadoraNewComponent } from './transportadora-new/transportadora-new.component';
import { ReactiveFormsModule } from '@angular/forms';
import { TransportadoraEditComponent } from './transportadora-edit/transportadora-edit.component';
import { TransportadoraDeleteComponent } from './transportadora-delete/transportadora-delete.component';



@NgModule({
  declarations: [
    TransportadorasComponent, 
    TransportadoraNewComponent, TransportadoraEditComponent, TransportadoraDeleteComponent
  ],
  imports: [
    CommonModule,
    TransportadorasRoutingModule,
    AppBootstrapModule,
    ReactiveFormsModule
  ]
})
export class TransportasdorasModule { }

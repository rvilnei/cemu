import { RouterModule } from '@angular/router';
import { VmessageModule } from './../shared/componentes/vmessage/vmessage.module';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: 
  [ HomeComponent,
    LoginComponent
  ],
  imports: [
    ReactiveFormsModule,
    CommonModule,
    VmessageModule,
    RouterModule
  ]
})
export class HomeModule { }

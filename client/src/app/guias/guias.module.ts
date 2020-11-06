import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { GuiasComponent } from './guias.component';
import { GuiasRoutingModule } from 'src/app/guias/guias-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppBootstrapModule } from 'src/app/app-bootstrap/app-bootstrap.module';
import { NgScrollbarModule } from 'ngx-scrollbar';
import { GuiaNewComponent } from './guia-new/guia-new.component';
import { GuiaDetailComponent } from 'src/app/guias/guia-detail/guia-detail.component';
import { GuiaEditComponent } from './guia-edit/guia-edit.component';
import { EditModalComponent } from './guia-edit/edit-modal/edit-modal.component';
import { NewModalComponent } from './guia-new/new-modal/new-modal.component';
import { GuiaDeleteComponent } from './guia-delete/guia-delete.component';



@NgModule({
  declarations: [
    GuiasComponent,
    GuiaNewComponent,
    GuiaDetailComponent,
    GuiaEditComponent,
    EditModalComponent,
    NewModalComponent,
    GuiaDeleteComponent
  ],
  imports: [
    ReactiveFormsModule,
    CommonModule, 
    GuiasRoutingModule, 
    FormsModule,
    AppBootstrapModule
  ],
  providers: [
    DatePipe
  ],
  exports: [NewModalComponent]
})
export class GuiasModule { }

import { MateriaisRoutingModule } from './materiais-routing.module';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { TypeaheadModule } from 'ngx-bootstrap/typeahead';
import { AppBootstrapModule } from './../app-bootstrap/app-bootstrap.module';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MaterialNewComponent } from './material-new/material-new.component';
import { MaterialEditComponent } from './material-edit/material-edit.component';
import { MaterialDetailComponent } from './material-detail/material-detail.component';
import { MateriaisComponent } from './materiais.component';
import { TipoMaterialPipe } from '../shared/pipes/tipo-material.pipe';
import { LancamentosComponent } from './material-detail/lancamentos/lancamentos.component';
import { MaterialNovoComponent } from './material-novo/material-novo.component';

@NgModule({
    declarations: [ 
        MateriaisComponent,
        MaterialDetailComponent,
        MaterialEditComponent,
        MaterialNewComponent,
        TipoMaterialPipe,
        LancamentosComponent,
        MaterialNovoComponent
     ],
     imports: [
        CommonModule,
        MateriaisRoutingModule,
        FormsModule,
        RouterModule,
        AppBootstrapModule,
        TypeaheadModule.forRoot()
      ]
})
export class MateriaisModule{}
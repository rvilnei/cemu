import { AuthGuard } from './../core/auth/auth.guard';
import { MateriaisComponent } from './materiais.component';
import { MaterialNewComponent } from './material-new/material-new.component';
import { MaterialEditComponent } from './material-edit/material-edit.component';
import { MaterialDetailComponent } from './material-detail/material-detail.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MaterialNovoComponent } from 'src/app/materiais/material-novo/material-novo.component';

const materiaisRoutes: Routes = [
    { path: 'materialDetail/:id', component: MaterialDetailComponent, canActivate: [AuthGuard] },
    { path: 'materialEdit/:id', component: MaterialEditComponent, canActivate: [AuthGuard] },
    { path: 'materialNew', component: MaterialNewComponent, canActivate: [AuthGuard] },
    { path:'materialNovo', component: MaterialNovoComponent, canActivate: [AuthGuard]  },
    { path:'materiais', component: MateriaisComponent, canActivate: [AuthGuard]  },
    
];

@NgModule({
    imports: [ RouterModule.forChild( materiaisRoutes ) ],
    exports: [RouterModule]
})
export class MateriaisRoutingModule{}
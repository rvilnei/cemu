import { Routes, RouterModule } from "@angular/router";
import { TransportadorasComponent } from "src/app/transportadoras/transportadoras.component";
import { AuthGuard } from "src/app/core/auth/auth.guard";
import { NgModule } from "@angular/core";
import { TransportadoraNewComponent } from "src/app/transportadoras/transportadora-new/transportadora-new.component";
import { TransportadoraEditComponent } from "src/app/transportadoras/transportadora-edit/transportadora-edit.component";
import { TransportadoraDeleteComponent } from "src/app/transportadoras/transportadora-delete/transportadora-delete.component";


const transportadorasRoutes: Routes = [
    { path:'transportadoras', component: TransportadorasComponent, canActivate: [AuthGuard]  },
    { path:'transportadoraNew', component: TransportadoraNewComponent, canActivate: [AuthGuard]  },
    { path: 'transportadoraEdit/:id', component: TransportadoraEditComponent, canActivate: [AuthGuard] },
    { path: 'transportadoraDelete/:id', component: TransportadoraDeleteComponent, canActivate: [AuthGuard] }
    // { path: 'materialDetail/:id', component: MaterialDetailComponent, canActivate: [AuthGuard] },
    // { path: 'materialEdit/:id', component: MaterialEditComponent, canActivate: [AuthGuard] },
    // { path: 'materialNew', component: MaterialNewComponent, canActivate: [AuthGuard] }, 
];


@NgModule({
    imports: [ RouterModule.forChild( transportadorasRoutes ) ],
    exports: [RouterModule]
})
export class TransportadorasRoutingModule{}


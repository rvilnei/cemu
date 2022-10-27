import { Routes, RouterModule } from "@angular/router";
import { GuiasComponent } from "src/app/guias/guias.component";
import { NgModule } from "@angular/core";
import { GuiaNewComponent } from "src/app/guias/guia-new/guia-new.component";
import { AuthGuard } from "src/app/core/auth/auth.guard";
import { GuiaDetailComponent } from "src/app/guias/guia-detail/guia-detail.component";
import { GuiaEditComponent } from "src/app/guias/guia-edit/guia-edit.component";
import { GuiaDeleteComponent } from "src/app/guias/guia-delete/guia-delete.component";

const guiasRoutes: Routes = [
    { path: 'guias', component: GuiasComponent  ,canActivate: [AuthGuard] },
    { path: 'guiaNew', component: GuiaNewComponent ,canActivate: [AuthGuard] },
    { path: 'guiaDetail/:id/:autenticar', component: GuiaDetailComponent, canActivate: [AuthGuard]  },
    { path: 'guiaEdit/:id', component: GuiaEditComponent, canActivate: [AuthGuard] },
    { path: 'GuiaDelete/:id', component: GuiaDeleteComponent, canActivate: [AuthGuard] }
];

@NgModule( {
    imports: [ RouterModule.forChild( guiasRoutes ) ],
    exports: [RouterModule]
} )
export class GuiasRoutingModule{}

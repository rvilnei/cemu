import { AuthGuard } from './core/auth/auth.guard';

import { LoginComponent } from './home/login/login.component';
import { HomeComponent } from './home/home.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule, ActivatedRoute } from '@angular/router';

const routes: Routes = [
  { path: '',
    component: LoginComponent
  },
  {  path: 'login',
     component: LoginComponent
  },
  {  path:'home',
     component: HomeComponent,
     canActivate: [AuthGuard]
  } //,
 // { path: '', redirectTo: '/home', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes  ,{ enableTracing: true } // <-- debugging purposes only
           )],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { RequestInterceptor } from './auth/request.interceptor';
import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header/header.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { MenuModule } from 'src/app/shared/componentes/menu/menu.module';
import { ShowIfLoggedModule } from 'src/app/shared/directives/show-if-logged/show-if-logged.module';

@NgModule({
  declarations: [HeaderComponent],
  exports: [ HeaderComponent ],
  imports: [
    CommonModule,
    RouterModule,
    BsDropdownModule,
    MenuModule,
    ShowIfLoggedModule
  ],
  providers: [
    {
        provide: HTTP_INTERCEPTORS,
        useClass: RequestInterceptor,
        multi: true
    }
  ]
})
export class CoreModule { }

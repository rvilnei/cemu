
  import { UserService } from './../user/user.service';
  import { Injectable, ErrorHandler, Injector } from '@angular/core';
  import { Router } from '@angular/router';
  import { HttpErrorResponse } from '@angular/common/http';
  @Injectable()
  export class AppGlobalErrorhandler implements ErrorHandler {
  
      constructor(
                  private injector: Injector,
                  private userService: UserService
                  ) {
      }
   
      handleError(error) {
          let router = this.injector.get(Router);
         // console.log('URL: ' + router.url);
         // console.error('An error occurred:', error.message);
          if ( error.status === 0 || error.status === 401 || error.status === 403) {
             this.userService.logout;
             router.navigate(['/login']);
          }
         // throw new Error("Metodo nao implementado.");
     }
  
    
  }
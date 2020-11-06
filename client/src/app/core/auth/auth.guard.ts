import { Observable } from 'rxjs';
import { UserService } from './../user/user.service';
import { Injectable } from '@angular/core';

import { ActivatedRouteSnapshot, RouterStateSnapshot, CanActivate, Router } from '@angular/router';


@Injectable({ providedIn: 'root' })    
export class AuthGuard implements CanActivate {

    constructor( private userService: UserService, 
                 private router: Router   ){}

    canActivate(  router: ActivatedRouteSnapshot,
                  state: RouterStateSnapshot ): boolean | Observable<boolean> | Promise<boolean>
                {

        if( !this.userService.isLogged() ){
          this.router.navigate( ['/login'], {
              queryParams: {
                  fromUrl: state.url // state.url
              }
          } );
          return false;
        }
        return true;
    };

}
import { User } from './user';
import { TokenService } from './../token/token.service';
import { Injectable } from '@angular/core';
import { Subject, BehaviorSubject } from 'rxjs';
import * as jtw_decode from 'jwt-decode';

@Injectable({
    providedIn: 'root'
})
export class UserService{
    private userSubject = new BehaviorSubject<User>(null);
    private userName: string;
    private roles: Array<String>;
    constructor( private tokenService: TokenService ){
            this.tokenService.hasToken() && 
            this.decodeAnNotify();
    }

    setToken(token: string){
        this.tokenService.setToken(token);
        this.tokenService.hasToken() &&
             this.decodeAnNotify();
    }

    getUser(){
        return this.userSubject.asObservable();
    }

    decodeAnNotify(){
        const token = this.tokenService.getToken();
        const user = jtw_decode(token) as User ;
        this.userName = user.name;
        this.roles = user.roles;
        this.userSubject.next(user);
    }

    logout(){
        this.tokenService.removeToken();
        this.userSubject.next(null);
    }

    isLogged(){
        return this.tokenService.hasToken();
    }

    getUserName(){
        return this.userName;
    }

    getUserRles(){
        return this.roles;
    }

}
import { UserService } from './../user/user.service';

import { tap } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { environment } from 'src/environments/environment';

//const API_URL = 'http://localhost:8080/';
//const API_URL =  'api';
const API_URL = environment.Apiurl;
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(  private http: HttpClient,
                private userService: UserService
            ) { }

  autenticate(email: string, senha: string){
     return this.http.post(API_URL+'user/login',
                          { email, senha },
                          { observe: 'response'} // abre o cabecalho para consultar o token
                        )
                        .pipe( tap( (res: HttpResponse<any>) => {
                           // const authToken = res.headers.get('x-access-token');
                            const authToken = res.body.tipo+' '+res.body.token ;
                            this.userService.setToken(authToken);
                          } ) );
   //return new Observable();
  }
}

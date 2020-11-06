import { TokenService } from './../token/token.service';
import { Observable } from 'rxjs/internal/Observable';
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpSentEvent, HttpProgressEvent, HttpResponse, HttpUserEvent, HttpHeaderResponse, HttpHeaders } from '@angular/common/http';

@Injectable()
export class RequestInterceptor implements HttpInterceptor {

    constructor( private tokenService: TokenService ){};

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable < HttpSentEvent 
            | HttpHeaderResponse | HttpProgressEvent | HttpResponse<any> | HttpUserEvent<any> >  {
                      
       if ( this.tokenService.hasToken() ){
           const token = this.tokenService.getToken();
           req = req.clone({
               setHeaders: {
                    //'x-access-token': token
                    Authorization: token
               }
           });
       }   
        return next.handle(req);
    }
}
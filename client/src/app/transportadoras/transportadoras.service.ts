import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { delay } from 'rxjs/operators';
import { tap } from 'rxjs/internal/operators/tap';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';


const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Methods': 'GET, POST, OPTIONS, PUT, PATCH, DELETE',
    'Access-Control-Allow-Headers':
      'Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers'
  })
};

@Injectable({
  providedIn: 'root'
})
export class TransportadorasService {

  private readonly API = environment.Apiurl;
  
  constructor(
     private http: HttpClient
  ) { }

  getTransportadoras(): Observable<any[]> {
    return this.http.get<any[]>(this.API+'transportadoras', httpOptions )
    .pipe( delay(200) ,
    tap(transportadoras => this.log('fetched transportadoras'))//,
    //catchError(this.handleError('getMateriais', []))
  );
  }

  salvar(transportadoraForm) : Observable<any> {
    console.log( "service transportadoraForm : ", transportadoraForm );
   return this.http.post<any>( this.API+"transportadoras", transportadoraForm, httpOptions  );
  };

  atualizar(transportadoraForm) : Observable<any> {
    console.log( "service atualizar transportadoraForm : ", transportadoraForm );
   return this.http.put<any>( this.API+"transportadoras", transportadoraForm, httpOptions  );
  };

  /** PUT: update the material on the server */
  updateTransportadora(transportadora): Observable<any> {
    const url = `${this.API}transportadoras/${transportadora.id}`;
    return this.http.put<any>( url, transportadora  );
  }

  getTransportadora(id: number): Observable<any> {
    const url = `${this.API}transportadoras/${id}`;
    return this.http.get<any>( url );
  }

  delete(transportadora){
    const url = `${this.API}transportadoras/${transportadora.id}`;
    return this.http.delete<any>( url);
  }

  /** Log a MaterialService message with the MessageService */
  private log(message: string) {
    //  this.messageService.add(`MaterialService: ${message}`);
  }


}

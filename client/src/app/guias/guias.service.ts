import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { delay } from 'rxjs/operators';
import { tap } from 'rxjs/internal/operators/tap';
import { Guia } from 'src/app/guias/guia';
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
export class GuiasService {
  private readonly API = environment.Apiurl;

  constructor( private http: HttpClient ){ }

    getGuias(){
      return this.http.get<Guia[]>( this.API+'guias' )
              .pipe(
                delay(100),
                tap(guias => this.log( 'fetched guias' ) )
              );
    }

    salva(guia): Observable<Guia> {
      return this.http.post<Guia>(  this.API+'guias', guia, httpOptions );
    }

    /** PUT: update the material on the server */
    updateGuia(guia: Guia): Observable<Guia> {
      const url = `${this.API}guias/${guia.id}`;
      return this.http.put<Guia>( url, guia, httpOptions  );
    }

    getGuia(id: number) {
      const url = `${this.API}guias/${id}`;
      return this.http.get<Guia>( url );
    }

    getNumeroNovaGuia(){
      const url = `${this.API}guias/numeroNovaGuia`;
      return this.http.get<Guia>( url );
    }

    delete(id: number){
      const url = `${this.API}guias/${id}`;
      console.log( "****** URL *******" );
      console.log( url );
      return this.http.delete<any>( url, httpOptions);
    }
 
    /** Log a MaterialService message with the MessageService */
    private log(message: string) {
      //  this.messageService.add(`MaterialService: ${message}`);
    }

}

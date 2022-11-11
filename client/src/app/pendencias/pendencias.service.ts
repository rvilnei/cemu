import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import { delay, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PendenciasService {
  private readonly API = environment.Apiurl;

  constructor( private http: HttpClient ) { }

  getTiposPendencias(): Observable<any[]>{
    return this.http.get<any[]>( this.API+'pendencias/tipos' )
                  .pipe(
                   // delay(100)
                  )
  }

getTipos(): Observable<any[]> {
  return this.http.get<any[]>( this.API+'pendencias/tipos' )
  .pipe(
    tap( tp => console.log(tp) ),
    delay(100)
  )
}

}

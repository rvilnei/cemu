import { HttpClient } from '@angular/common/http';
import { delay, tap, catchError, filter } from 'rxjs/operators';

import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EstoquesService {
  // private readonly API = `http://localhost:3000/`;
 // private readonly API =`http://localhost:8080/`;
  private readonly API = environment.Apiurl;

  constructor(
    private http: HttpClient 
  ) { }

  getEstoques(): Observable<any[]> {
    return this.http.get<any[]>(this.API+'estoques')
    .pipe( 
      delay(200) ,
      tap(estoques => this.log('fetched estoques')),
    //  catchError(this.handleError('getEstoque()', []))
    );
  }

  getEstoqueUnidade( unidadeId ): Observable<any[]> {
    return this.http.get<any[]>(this.API+'estoques/'+unidadeId)
    .pipe( 
      delay(200) ,
      tap(estoques => this.log('fetched estoques')),
    //  catchError(this.handleError('getEstoque()', []))
    );
  }

  getUnidades(): Observable<any[]> {
    return this.http.get<any[]>(this.API+'estoques/unidades')
      .pipe( delay(200) ,
        tap(unidades => this.log('fetched movimentacoes'))
      );
  }

   /**
    * Handle Http operation that failed.
    * Let the app continue.
    * @param operation - name of the operation that failed
    * @param result - optional value to return as the observable result
    */
    // private handleError<T> (operation = 'operation', result?: T) {
    //   return (error: any): Observable<T> => {
    //     // TODO: send the error to remote logging infrastructure
    //     console.error(error); // log to console instead
    //     // TODO: better job of transforming error for user consumption
    //     this.log(`${operation} failed: ${error.message}`);
    //     // Let the app keep running by returning an empty result.
    //     return of(result as T);
    //   };
    // }

    /** Log a MaterialService message with the MessageService */
    private log(message: string) {
      //  this.messageService.add(`MaterialService: ${message}`);
    }



}

import { MATERIAIS } from './../shared/materiais';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { delay, map, catchError, tap } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';

// const httpOptions = {
//   headers: new HttpHeaders({ 'Content-Type': 'application/json' })
// };

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
export class MateriaisService {
    materiais: any[] =  MATERIAIS;
    material: any = {};
  //  private readonly API =`http://localhost:8080/`;
    private readonly API = environment.Apiurl;
    processHTTPMsgService: any;

    constructor( private http: HttpClient ) { }

    /** GET materiais from the server */
    getMateriais (): Observable<Material[]> {
        return this.http.get<any[]>(this.API+'materiais')
          .pipe( delay(200) ,
            tap(materiais => this.log('fetched materiais'))//,
            //catchError(this.handleError('getMateriais', []))
          );
      }

    /** GET material by id. Will 404 if id not found */
    getMaterial(id: number): Observable<Material> {
        const url = `${this.API}materiais/${id}`;
        return this.http.get<any>(url).pipe(
            tap(_ => this.log(`fetched material id=${id}`)),
           // catchError(this.handleError<any>(`getMaterial id=${id}`))
          );
    }

   /** DELETE: delete the material from the server */
   deleteMaterial (material: any | number): Observable<any> {
      const id = typeof material === 'number' ? material : material.id;
      const url = `${this.API}materiais/${id}`;
      return this.http.delete<any>(url, httpOptions).pipe(
          tap(_ => this.log(`deleted material id=${id}`)),
         // catchError(this.handleError<any>('deleteMaterial'))
        );
    }
 
    getMateriaisIds() {
      return this.getMateriais().pipe( map( materiais => materiais.map( material => material.id ) ) );
    }

    /** POST: add a new material to the server */
    addMaterial (material: any): Observable<any> {
      return this.http.post<any>(this.API+'materiais', material,  httpOptions).pipe(
        tap((material: any) => this.log(`added material w/ id=${material.id}`)),
       // catchError(this.handleError<any>('addMaterial'))
      );
    }
 
    /** PUT: update the material on the server */
    updateMaterial( material: any): Observable<any>{
      return this.http.put<any>( this.API+'materiais/'+material.id, material, httpOptions)
        .pipe( tap(_ => this.log(`updated material id=${material.id}`)),
     //   catchError(this.handleError<any>('updatedMaterial')
        // .pipe(catchError(this.processHTTPMsgService.handleError));
    //  )
    );
    }

    /* GET material onde o nome contaém a busca term */
    searchMateriais(term: string): Observable<string[]> {
      if (!term.trim()) {
        // if not search term, return empty material array.
        return of([]);
      }
      return this.http.get<any[]>(`${this.API}materiais/?nome=${term}`).pipe(
        tap(_ => this.log(`found materiais matching "${term}"`)),
       // catchError(this.handleError<any[]>('searcMateriais', []))
      );
    }

    /** POST: add a new itemLancamentoMaterial to the server */
    addLancamentoItemMaterial (material: any, itemLancado: any): Observable<any> {
      let id = material.id;
      console.log('****** addLancamentoItemMaterial *****  '+this.API+`materiais/${id}/lancamentos`);
      return this.http.post<any>(this.API+`materiais/${id}/lancamentos` , itemLancado , httpOptions).pipe(  
        tap((material: any) => this.log(`added material w/ id=`)),
       // catchError(this.handleError<any>('addMaterial'))
      );
    }

    getTipos(): Observable<any[]> {
      return this.http.get<any[]>(this.API+'materiais/tipos');
    }
    
    getTipo( id: number): Observable<Tipo> {
      return this.http.get<Tipo>(this.API+`materiais/${id}/tipo`);
    }

    getTipoPorNome( nome: string): Observable<Tipo> {
      return this.http.get<Tipo>(this.API+`materiais/${nome}/nomeTipo`);
    }

    getStatus(): Observable<Status[]> {
      return this.http.get<Status[]>( this.API+'materiais/status' );
    }

    getUnidades(): Observable<any[]> {
      return this.http.get<any[]>(this.API+'estoques/unidades')
        .pipe( delay(200) ,
          tap(unidades => this.log('fetched unidade'))
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

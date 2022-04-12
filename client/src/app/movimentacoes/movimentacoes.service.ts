import { EstoquesService } from './../estoques/estoques.service';
import { MateriaisService } from './../materiais/materiais.service';
import { FormGroup } from '@angular/forms';
import { delay, tap, map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { filter } from 'rxjs/internal/operators/filter';
import { environment } from 'src/environments/environment';

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
export class MovimentacoesService {
     // private readonly API =`http://localhost:8080/`;
      private readonly API = environment.Apiurl;
      movimentacao: any = {};
      listaMaterial = [] ;
      listaMaterialEstoque = [] ;

      constructor( private http: HttpClient,
                  private materiaisService: MateriaisService,
                  private estoquesService: EstoquesService  ) { }
      
        /** GET movimentacoes from the server */
        getMovimentacoes (): Observable<any[]> {
          return this.http.get<any[]>(this.API+'movimentacoes')
            .pipe( delay(200) ,
              tap(movimentacoes => this.log('fetched movimentacoes'))
            );
        }
  
        getMovimentacao( id: number ){
            return this.http.get<any>( this.API+'movimentacoes/'+id );
        }

        getItensMovimentacoes(): Observable<any[]> {
          return this.http.get<any[]>(this.API+'itens')
            .pipe( delay(200) ,
              tap(movimentacoes => this.log('fetched movimentacoes'))
            );
        }

        /** GET movimentacoes by id da unidade e tipo ( entrada, saida de materiais ). Will 404 if id not found */
        getTipoMovimentacoes(unidadeId: number, tipoMovimentacao: string): Observable<any> {
            let movimentacao = tipoMovimentacao;
            const url = `${this.API}movimentacoes/${unidadeId}/`+tipoMovimentacao;
            if (movimentacao == 'entrada'){
              console.log('**entrada : '+movimentacao);
              return this.http.get<any>(url, httpOptions ).pipe(
                tap(_ => this.log(`fetched movimentacoes unidadeId=${unidadeId}`)),
              // catchError(this.handleError<any>(`getMaterial id=${id}`))
              );
            }
            if (movimentacao == 'saida'){
                return this.http.get<any>(url, httpOptions ).pipe(
                  tap(_ => this.log(`fetched movimentacoes unidadeId=${unidadeId}`)),
                // catchError(this.handleError<any>(`getMaterial id=${id}`))
                );
            }
        }

        /** POST: add a new material to the server */
        addMovimentacao(movimentacaoForm: FormGroup, itens: any[]):  Observable<any> {
            this.movimentacao = {
              unidadeorigemId: movimentacaoForm.value.unidadeorigemId ,
              unidadedestinoId: movimentacaoForm.value.unidadedestinoId ,
              datacriacao: movimentacaoForm.value.datacriacao ,
              dataarecebimento: movimentacaoForm.value.datarecebimento ,
              observacao: movimentacaoForm.value.observacao,
              itens: movimentacaoForm.value.itens,
              material: 1
            };
              console.log( this.movimentacao.unidadeorigemId +" ****addMovimentacao "+this.movimentacao.unidadedestinoId );
            return this.http.post<any>(this.API+'movimentacoes', this.movimentacao , httpOptions).pipe(
              //  tap((movimentacao: any) => this.log(`added material w/ id=${movimentacaoForm.id}`)),
              // catchError(this.handleError<any>('addMaterial'))
             );
        }  
          
        receberItens(movimentacao: any):  Observable<any> {
          return this.http.put<any>(this.API+'movimentacoes/updateItem', movimentacao , httpOptions).pipe(
          //  tap((movimentacao: any) => this.log(`added material w/ id=${movimentacaoForm.id}`)),
          // catchError(this.handleError<any>('addMaterial'))
          );
        }

        itemNaoRecebido(movimentacao: any, item: any ):  Observable<any> {
            return this.http.put<any>(this.API+'movimentacoes/'+movimentacao.id+'/updateItem/', item , httpOptions).pipe(
            //  tap((movimentacao: any) => this.log(`added material w/ id=${movimentacaoForm.id}`)),
            // catchError(this.handleError<any>('addMaterial'))
            );
        }

        /** Log a MovimentacoesService message with the MessageService */
        private log(message: string) {
          //  this.messageService.add(`MaterialService: ${message}`);
        }

        getListaMateriaisEstoque(): Observable<any[]> { 
          //  this.materiaisService.getMateriais().subscribe( m =>  this.listaMaterial = m ) ;
          return this.estoquesService.getEstoques() ;
        }
    
        getListaEstoqueUnidade( unidadeId ): Observable<any[]> { 
          return this.estoquesService.getEstoques() ;
        }

        getUnidades(): Observable<any[]> {
          return this.http.get<any[]>(this.API+'movimentacoes/unidades')
            .pipe( delay(200) ,
              tap(unidades => this.log('fetched movimentacoes'))
            );
        }
      
}

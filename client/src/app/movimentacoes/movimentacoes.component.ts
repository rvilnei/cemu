import { switchMap } from 'rxjs/operators';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { MovimentacoesService } from './movimentacoes.service';
import { Observable, Subscription } from 'rxjs';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-movimentacoes',
  templateUrl: './movimentacoes.component.html',
  styleUrls: ['./movimentacoes.component.css']
})
export class MovimentacoesComponent implements OnInit {
  movimentacoes$: Observable<any[]>; // para usar | async
  currentPage:number = 1;
  numPages:number = 0;
  itemsPerPage = 8;
  inscricao: Subscription
  tipMovimentacao: string;
  unidadeId: number;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private movimentacoesService: MovimentacoesService
  )  { }

  ngOnInit() {
    this.inscricao = this.route.params.subscribe(
      ( params: any ) => {
        // let unidadeId: number = params['unidadeId'];
        // let tipo = params['tipo'];

          this.tipMovimentacao =  params['tipo'];
          this.unidadeId = params['unidadeId']; 

          console.log(this.tipMovimentacao );
          console.log(this.unidadeId);
          this.movimentacoes$ = this.movimentacoesService.getTipoMovimentacoes(  this.unidadeId , this.tipMovimentacao  )  ;

          this.movimentacoesService.getItensMovimentacoes().subscribe(item => {
            console.log("item movimentacao: ");
            console.log(item);
          });
      }
    );

    // this.route.params
    //     .pipe(switchMap((params: Params) => this.service.getMaterial(params['id'])))
    //     .subscribe(material => { this.material = material; });
        
        /** substitui para usar | async n view **/
      //  this.movimentacoes$ = this.movimentacoesService.getMovimentacoes();
  }



  ngOnDestroy(){
    this.inscricao.unsubscribe();
  }


}

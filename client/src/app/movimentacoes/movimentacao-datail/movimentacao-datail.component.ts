import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { GuiasService } from 'src/app/guias/guias.service';
import { Subscription } from 'rxjs';
import { MovimentacoesService } from 'src/app/movimentacoes/movimentacoes.service';
import { switchMap } from 'rxjs/operators';
import { map } from 'rxjs/internal/operators/map';
import { Location } from '@angular/common';

@Component({
  selector: 'app-movimentacao-datail',
  templateUrl: './movimentacao-datail.component.html',
  styleUrls: ['./movimentacao-datail.component.css']
})
export class MovimentacaoDatailComponent implements OnInit {
  inscricao: Subscription;
  movimentacao: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private movimentacoesService: MovimentacoesService,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.inscricao = this.route.params
    .pipe(
          switchMap(
              (params: Params) => 
              this.movimentacoesService.getMovimentacao(params['id'])
          ),
          map( resp => { this.movimentacao = resp } ) 
        )
    .subscribe();
  }

  voltar(): void{
    this.location.back();
  }

  ngOnDestroy(){
    this.inscricao.unsubscribe();
  }


}

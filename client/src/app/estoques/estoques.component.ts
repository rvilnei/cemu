import { EstoquesService } from './estoques.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';

@Component({
  selector: 'app-estoques',
  templateUrl: './estoques.component.html',
  styleUrls: ['./estoques.component.css']
})
export class EstoquesComponent implements OnInit {
  estoques$: Observable<any[]>; // para usar | async
  unidades$: Observable<any[]>;
  
  constructor(
    private estoquesService: EstoquesService,
  ) { }

  ngOnInit() {
    this.estoques$ = this.estoquesService.getEstoques();
    this.unidades$ = this.estoquesService.getUnidades();
  }
  unidadeId;
  onChangeUnidade(){
    this.estoques$ = this.estoquesService.getEstoqueUnidade( this.unidadeId );
  }

}

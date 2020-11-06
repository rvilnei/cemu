import { Component, OnInit, Input } from '@angular/core';
import { Observable } from 'rxjs';
import { MateriaisService } from 'src/app/materiais/materiais.service';

@Component({
  selector: 'app-lancamentos',
  templateUrl: './lancamentos.component.html',
  styleUrls: ['./lancamentos.component.css']
})
export class LancamentosComponent implements OnInit {
  @Input() material;
  @Input() materialAtual;
  lancamentos=[];
  unidades$: Observable<any[]>;
  unidadeId;

  constructor(
    private materiaisService: MateriaisService
  ) { }

  onChangeUnidade(){ 
    this.material = Object.assign( {} , this.materialAtual )
    console.log( "unidade select ****    "+this.unidadeId  );
    if (this.unidadeId != 0 ){  
      this.material.lancamentos = this.material.lancamentos.filter( lancamento => { return lancamento.unidadedestinoId === this.unidadeId || lancamento.unidadeorigemId === this.unidadeId  }  );  
    } else {
      this.material = Object.assign( {} , this.materialAtual )
    } 
   //this.material.lancamentos = this.material.lancamentos.filter( lancamento => { return lancamento.unidadedestinoId === this.unidadeId  }  )  ;
  }

  ngOnInit() {
    this.unidades$ = this.materiaisService.getUnidades();
  }
  ngAfterViewInit() {
    this.lancamentos = this.material.lancamentos;
  }
}

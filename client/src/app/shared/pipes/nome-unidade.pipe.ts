import { MovimentacoesService } from './../../movimentacoes/movimentacoes.service';
import { MateriaisService } from './../../materiais/materiais.service';
import { Pipe, PipeTransform } from '@angular/core';
import { pipe } from 'rxjs';
import { filter, map } from 'rxjs/operators';

@Pipe({
  name: 'nomeUnidade'
})
export class NomeUnidadePipe implements PipeTransform {
  unidades: any;
  unidade: any;

    constructor(
        private movimentacoesService: MovimentacoesService
    ){
        this.movimentacoesService.getUnidades()
        .subscribe( unidades => {
          this.unidades = unidades;
        }
    );
  }

  transform(value: any, args?: any): any {
    let filter = args;
    this.unidade = this.unidades.filter( u => u.id == value ) ;
    return this.unidade[0].sigla;
  }

}

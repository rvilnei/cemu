import { MateriaisService } from './../../materiais/materiais.service';
import { Pipe, PipeTransform } from '@angular/core';
import { pipe } from 'rxjs';
import { filter, map } from 'rxjs/operators';

@Pipe({
  name: 'nomeTipoMaterial'
})
export class TipoMaterialPipe implements PipeTransform {
  tipos: any;
  tp: any;

    constructor(
      private materiaisService: MateriaisService
    ){
        this.materiaisService.getTipos()
        .subscribe( tipos => {
          this.tipos = tipos;
        }
    );
  }

  transform(value: any, args?: any): any {
    let filter = args;
    this.tp = this.tipos.filter( t => t.id == value ) ;
    return this.tp[0].nome;
  }

}

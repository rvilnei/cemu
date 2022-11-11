import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { PendenciasService } from '../pendencias.service';

@Component({
  selector: 'app-pendencia-new',
  templateUrl: './pendencia-new.component.html',
  styleUrls: ['./pendencia-new.component.css']
})
export class PendenciaNewComponent implements OnInit {
  pendencia:any = {};

  tiposPendencias$: Observable<any[]>;

  constructor( private pendenciasService: PendenciasService  ) { }

  ngOnInit(): void {
    this.tiposPendencias$ = this.pendenciasService.getTiposPendencias()
        .pipe(
        //  tap(tipo => console.log(tipo) )
        )
  }

  save(  pendenciaForm){
    console.log("Salvar pendencias*******");
    console.log(pendenciaForm.control );
    // console.log("Salvar pendencias");
   // console.log(pendenciaForm.get("descricao") ) ;
  }

}

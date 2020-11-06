

import { Component, OnInit, Input } from '@angular/core';
import {Guia} from '../guia';
import { GuiasService } from 'src/app/guias/guias.service';
import { Router } from '@angular/router';
import { BsModalRef } from 'ngx-bootstrap';
import { Observable } from 'rxjs';
import { TransportadorasService } from 'src/app/transportadoras/transportadoras.service';
import { Location, DatePipe } from '@angular/common';

@Component({
  selector: 'app-guia-new',
  templateUrl: './guia-new.component.html',
  styleUrls: ['./guia-new.component.css']
})
export class GuiaNewComponent implements OnInit {

  @Input() modal: BsModalRef;
  @Input() movimentacao: any;
  guia: any = { transportadora: { id: null },
                movimentacao: { } }  ; 
  transportadoras$: Observable<any> = new Observable();
  numeroGuia;

  constructor(
    private guiasService: GuiasService,
    private router: Router,
    private transportadorasService: TransportadorasService,
    private location: Location,
    private datePipe: DatePipe
  ) { }

  onSubmit( guiaForm ){
    // let dt: Date = new Date(data);
   // this.guia.movimentacao = dt;
    this.guia.dataCriacao = new Date();
    this.guia.movimentacao = this.movimentacao;

    this.guiasService.salva( this.guia )
        .subscribe( (guia) => {
          this.guia = guia;
          this.router.navigate( ['/guias'] );
        }
   )
  }

  voltar(): void{
    console.log( "tem q voltar" );
    this.location.back();
  }

  ngOnInit(): void {
    this.guiasService.getNumeroNovaGuia()
    .subscribe( numero =>{
      this.guia.numeroGuia = numero;
    });
   this.transportadoras$ = this.transportadorasService.getTransportadoras();
   }

}
